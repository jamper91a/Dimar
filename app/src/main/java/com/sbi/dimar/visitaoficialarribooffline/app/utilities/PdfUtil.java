package com.sbi.dimar.visitaoficialarribooffline.app.utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;
import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboCabotajeActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboInternacionalActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PDFTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfConstants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_AB;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_AGENCIA_MARITIMA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_AN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_BANDERA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_CALADO_MAXIMO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_CALADO_POPA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_CALADO_PROA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_CAPITANIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_CLASE_CARGA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_DESECHOS_NO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_DESECHOS_SI;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_FECHA_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_FECHA_VISITA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_INSTALACION_PORTUARIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_NOMBRE_CAPITAN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_N_AVISO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_N_PASAJEROS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_N_TRIPULANTES;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_OBSERVACIONES;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_OMI;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_PAIS_PROCEDENCIA_DESTINO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_PUERTO_PROCEDENCIA_DESTINO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_SIG_AGENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_SIG_AUTORIDAD_MIGRATORIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_SIG_AUTORIDAD_SANITARIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_SIG_CAPITANIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_SIG_CAPITAN_BUQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_SIG_ICA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.PdfEnum.CMP_TN;

/**
 * Created by Jenny Galindo on 16/5/2018
 * <p>
 * The PdfUtil class contains the methods that allow the generation of the PDF with the information stored for each arrival notice
 */
public class PdfUtil implements Serializable {
    private static final String TAG = PdfUtil.class.getSimpleName();
    private static final String PDF_TEMPLATE = "pdf/template.pdf";
    private static final String DIR_NAME_VOA = "VOA";
    private static final String DIR_VOA = DIR_NAME_VOA + "/";
    private static final String PDF_EXTENSION = ".pdf";
    private static SessionManager sessionManager;
    private static final String imageSerializable = "iVBORw0KGgoAAAANSUhEUgAAA2AAAALDCAYAAABzbWNQAAAABHNCSVQICAgIfAhkiAAAIABJREFU\n" +
            "eJzs3Xl4VPWh//HPzCQhgQQmIBDZEsQWREgibgihKIssKm6sFhXq3oq2VkVrFeHaKq5VqNWWFqwL\n" +
            "S9zREC3KkkQE4UcYVFxYEiTKPkPYQkKS3x9eua3VnJOQ8505M+/X8/S5z9N+Ej7tNSSffM/iqa2t\n" +
            "rRUAAAAAwHHecBcAAAAAgFjBAAMAAAAAQxhgAAAAAGAIAwwAAAAADGGAAQAAAIAhDDAAAAAAMIQB\n" +
            "BgAAAACGMMAAAAAAwBAGGAAAAAAYwgADAAAAAEMYYAAAAABgCAMMAAAAAAxhgAEAAACAIQwwAAAA\n" +
            "ADCEAQYAAAAAhjDAAAAAAMAQBhgAAAAAGMIAAwAAAABDGGAAAAAAYAgDDAAAAAAMYYABAAAAgCEM\n" +
            "MAAAAAAwhAEGAAAAAIYwwAAAAADAEAYYAAAAABjCAAMAAAAAQxhgAAAAAGAIAwwAAAAADGGAAQAA\n" +
            "AIAhDDAAAAAAMIQBBgAAAACGMMAAAAAAwBAGGAAAAAAYwgADAAAAAEMYYAAAAABgCAMMAAAAAAxh\n" +
            "gAEAAACAIQwwAAAAADCEAQYAAAAAhjDAAAAAAMAQBhgAAAAAGMIAAwAAAABDGGAAAAAAYAgDDAAA\n" +
            "AAAMYYABAAAAgCEMMAAAAAAwhAEGAAAAAIYwwAAAAADAEAYYAAAAABjCAAMAAAAAQxhgAAAAAGAI\n" +
            "AwwAAAAADGGAAQAAAIAhDDAAAAAAMIQBBgAAAACGMMAAAAAAwBAGGAAAAAAYwgADAAAAAEMYYAAA\n" +
            "AABgCAMMAAAAAAxhgAEAAACAIQwwAAAAADCEAQYAAAAAhjDAAAAAAMAQBhgAAAAAGMIAAwAAAABD\n" +
            "GGAAAAAAYAgDDAAAAAAMYYABAAAAgCEMMAAAAAAwhAEGAAAAAIYwwAAAAADAEAYYAAAAABjCAAMA\n" +
            "AAAAQxhgAAAAAGAIAwwAAAAADGGAAQAAAIAhDDAAAAAAMIQBBgAAAACGMMAAAAAAwBAGGAAAAAAY\n" +
            "wgADAAAAAEMYYAAAAABgCAMMAAAAAAyJC3cBAIhVBQUF2rhxo3bt2qUvv/xShw8f1uHDh+Xz+ZSU\n" +
            "lKTOnTvrhBNO0Pjx4+X3+8NdFwAANAJPbW1tbbhLAEA0ycvL0+bNm7V69Wp9/vnn2rFjh/bu3auD\n" +
            "Bw+qurpaDf1r1+v1qkWLFrrgggt0xx13KDMzs5GbAwAApzHAAOA45OXl6YknntCaNWu0b98+1dTU\n" +
            "GPuzfT6fTjvtNOXm5iojI8PYnwsAABqOAQYA9VRYWKiJEydq48aN4a5yTEpKip588klNnDgx3FUA\n" +
            "AEAdGGAAYFNRUZGGDh2qAwcOhLvKj0pKStKf//xnhhgAABGKAQYANmRlZSkQCIS7hm3NmjXTjBkz\n" +
            "GGIAAEQYBhgA1GH9+vXq1auXjh49Gu4qDdKpUyctX75c6enp4a4CAADEe8AA4Ec999xzyszMdO34\n" +
            "kqStW7cqIyNDl112WbirAAAAMcAA4AdNnTpVEyZMCHeNRvPaa6+padOmysvLC3cVAABiGpcgAsD3\n" +
            "jB49Wrm5ucb+PI/HI4/H8x//Xm1tbYPfF2blzDPP1KpVqxz53AAAoG4MMAD4NwMGDNCSJUuO+/N4\n" +
            "vV4lJSWpSZMmSktLU9u2bdWtWzf16tVLycnJGjp0qPx+v63PVVpaqscee0wvv/yytm/f3ijDzOfz\n" +
            "6cUXX9SYMWOO+3MBAAD7GGAA8L/69OmjFStWNOhjU1JSdPHFF+vGG29U3759G7nZfwoEArrsssu0\n" +
            "adOm4/5cp59+ulavXt0IrQAAgB0MMACQ1LlzZ5WUlNT74/r06aOioqLGL2TT+PHjNXfuXNXU1DT4\n" +
            "cyQkJOijjz5SZmZmIzYDAAA/hIdwAIhpoVBIycnJ9R5fiYmJCgQCYR1fkvTCCy+ourpat99+u7ze\n" +
            "hv2VXllZqaysLD399NON3A4AAHwfAwxAzFq0aJFat26tgwcP1uvjsrKydPjwYfXs2dOhZvX3yCOP\n" +
            "qLq6WuPGjWvw5/jVr36lK6+8shFbAQCA72OAAYhJ9913n4YPH17vd3xdddVVKi4udqjV8XvppZcU\n" +
            "DAZ19tlnN+jjX3jhBQ0ePLiRWwEAgO9wDxiAmHPmmWc26METf/7zn/XLX/7SgUbOKCws1ODBg1VR\n" +
            "UVHvj83JyVFBQYEDrQAAiG2cgAGIGaWlpUpOTm7Q+MrLy3PV+JK+HVGHDx9u0GWFhYWFuvTSSx1o\n" +
            "BQBAbGOAAYgJubm5ysjIqPf9XnFxcSosLNSwYcMcaua8f/7znyooKJDP56vXx73++uuaOXOmQ60A\n" +
            "AIhNXIIIIOpNmjSpQUOiefPmKi0ttf3CZDfo0qWLNm/eXK+PKSkpUXp6ukONAACILZyAAYhqvXv3\n" +
            "btD46tatm/bt2xdV40uSNm3apPHjx9frY7Kzsx1qAwBA7GGAAYhaHTp00MqVK+v9cTfccIM2bNjg\n" +
            "QKPI8Pzzz+vPf/6z7XwoFNJdd93lYCMAAGIHlyACiEp+v1/79u2r18d4vV4tXLhQw4cPd6hVZHnu\n" +
            "uec0YcIE2/lgMBh1J4IAAJjGCRiAqNOqVat6j68mTZpo7dq1MTO+JOnqq6/W5MmTbecHDBjgYBsA\n" +
            "AGIDJ2AAokp6erq2bt1ar4/p2LFjvT8mmnTv3t32JZecggEAcHw4AQMQNS6++OJ6D6mLLroopseX\n" +
            "JH366ae2H1F/9tlnO9wGAIDoxgADEBVmzpypN998s14fc//999f7Y6LVsmXLbOW++OILhUIhh9sA\n" +
            "ABC9uAQRgOsFAgFlZWXV62MWLFigUaNGOdTInTp06KCysjLL3AUXXKC33nrLQCMAAKIPAwyA68XH\n" +
            "x+vo0aO283l5eRo2bJiDjdwpFAopNTXVVpZvHQAANAyXIAJwtYyMjHqNr8LCQsbXj/D7/erataut\n" +
            "7KRJkxxuAwBAdOIEDIBr/f73v9cf/vAH2/lFixZp6NChDjZyP7unYAkJCTpy5IiBRgAARBdOwAC4\n" +
            "UiAQqNf4evjhhxlfNvj9fqWnp1vmKisrNXv2bAONAACILpyAAXClJk2aqLKy0lZ22LBhysvLc7hR\n" +
            "9CgtLVVGRoZlzu/3KxgMOl8IAIAowgkYANfJycmxPb7atWvH+Kqn9PR0tWzZ0jIXCoUUCAQMNAIA\n" +
            "IHowwAC4yqxZs1RUVGQrGx8fb+ux6vhvzz//vK3cyJEjHW4CAEB04RJEAK4RCASUnZ1t+xHo69at\n" +
            "U2ZmpsOtolfz5s21f/9+yxz/OwMAYB8nYABc45xzzrE9vv74xz8yCo6T3Yec3HjjjQ43AQAgenAC\n" +
            "BsAVsrOztW7dOlvZ888/X++8847DjWJDQkKCqqqq6sx4PB7t3btXfr/fUCsAANyLEzAAEe++++6z\n" +
            "Pb5SU1MZX41o6tSplpna2lqNHz/eQBsAANyPEzAAES0QCCgrK8t2PhgMchLTyDgFAwCg8XACBiCi\n" +
            "nXnmmbazc+fOZQA44IorrrDM1NbWavjw4QbaAADgbpyAAYhYXbp00ebNm21lhwwZovz8fIcbxS6f\n" +
            "z6eampo6M5yCAQBgjRMwABFp4sSJtsdXamoq48thEyZMsMzU1tbq0ksvdb4MAAAuxgkYgIiTm5ur\n" +
            "0aNH28p6vV6tXbuWR84bEB8fr6NHj1rmuA8PAIAfxwkYgIgSCoU0btw42/mXXnqJ8WXI5Zdfbis3\n" +
            "YsQIh5sAAOBenIABiChpaWnasWOHrewFF1ygt956y+FG+Hd2T8FKSkqUnp5uoBEAAO7CCRiAiJGT\n" +
            "k2N7fKWmpjK+wmDixIm2ctwLBgDAD+MEDEBEmD59uu666y5bWZ62F15er1d2vnVs2bJFGRkZzhcC\n" +
            "AMBFOAEDEHalpaW2x5ckzZ8/n/EVRtdff72t3JVXXulwEwAA3IcTMABhFQqF1Lp1a1v3FUnfPg59\n" +
            "9uzZDrdCXUKhkFq1amX5XjCJJyICAPB9nIABCKtTTjnF9vhq27Yt4ysC+P1+jRo1ylZ2+PDhDrcB\n" +
            "AMBdOAEDEDajRo3Syy+/bCvr9Xq1Z88eTlMiRCgUUsuWLW3dC8YpGAAA/4cTMABhMWfOHNvjS5KW\n" +
            "Ll3KD/ERxO/3235Z9pgxYxxuAwCAe3ACBsC40tLSej0d75577tEDDzzgXCE0SCgUUmpqqq0s32oA\n" +
            "APgWJ2AAjPvpT39qO3vqqacyviKU3+/XeeedZys7ZMgQh9sAAOAOnIABMKpDhw4qKyuzlW3SpIkq\n" +
            "KiocboTjwSkYAAD1wwkYAGNGjx5te3xJ0ueff+5gGzQGv9+vbt262crefvvtDrcBACDycQIGwIjc\n" +
            "3FzbD22QpAULFth+1DnCy+4pmNfrVXV1tYFGAABELk7AADhu/fr19RpfI0aMYHy5iN/vV4cOHSxz\n" +
            "NTU1eu655ww0AgAgcnECBsBxPp9PNTU1trJpaWn65ptvHG6ExhYIBJSVlWWZS0xM1OHDhw00AgAg\n" +
            "MnECBsBRrVu3tj2+fD6fNmzY4HAjOCEzM1PNmze3zFVUVKioqMhAIwAAIhMDDIBjhg8frt27d9vO\n" +
            "r127lpctu9isWbNs5S699FKHmwAAELkYYAAc8eijj2rRokW28w8//LB69uzpYCM4bdSoUYqPj7fM\n" +
            "7dq1S6FQyEAjAAAiDwMMQKNbv3697rjjDtv5QYMG1SuPyHXjjTfayo0fP97hJgAARCYewgGg0cXF\n" +
            "xdl+3HjLli21Z88ehxvBJI/HYytj995AAACiCSdgABrViSeeaHt8eb1ebdq0yeFGMK1Hjx6Wmdra\n" +
            "WuXn5xtoAwBAZGGAAWg0V199tbZv3247v3z5ch66EYVefPFFW7krr7zS4SYAAEQeLkEE0Cjy8/M1\n" +
            "bNgw2/nJkyfroYcecrARwikxMVFHjhyxzPEtCAAQazgBA3DcQqFQvcZXr169GF9R7oEHHrCVu/XW\n" +
            "Wx1uAgBAZOEEDMBxS0lJ0YEDB2xlk5KSdOjQIYcbIRLYeRhHQkKCrZMyAACiBSdgAI7LeeedZ3t8\n" +
            "eTweff311w43QqTo2LGjZaayspJ3ggEAYgoDDECDvfLKK1q6dKntfEFBAQ/diCFvvfWWrdwNN9zg\n" +
            "cBMAACIHlyACaDA7l5h9Z8KECZo9e7aDbRCJvF6v5YM24uPjVVlZaagRAADhxQkYgAZp3ry57Wyb\n" +
            "Nm0YXzGqd+/elpmqqiouQwQAxAwGGIB6GzNmjPbv328r6/V6tWPHDocbIVLl5eXZyl1//fUONwEA\n" +
            "IDJwCSKAelm/fr0yMzNt5wOBgHr27OlgI0S6Jk2aWF5imJiYqMOHDxtqBABA+HACBqBeTjvtNNvZ\n" +
            "+++/n/EFDR8+3DJTUVHBZYgAgJjACRgA23r27KmPP/7YVrZjx47aunWrw43gBqFQSKmpqZa5iRMn\n" +
            "6h//+IeBRgAAhA8DDIAtr7zyikaOHGkr6/V6VV1d7XAjuImdyxCTk5Nt31sIAIBbcQkiAFtGjx5t\n" +
            "O1tQUOBgE7jRiBEjLDN2X+gNAICbMcAAWMrKylJNTY2t7GWXXaY+ffo43Ahu8+ijj9rKTZkyxeEm\n" +
            "AACEF5cgAqhTfn6+hg0bZivbvHlz7du3z+FGcCs7lyG2bdtW27dvN9QIAADzOAEDUKcLL7zQdjYQ\n" +
            "CDjYBG537rnnWmZ4ZxwAINoxwAD8qLPPPtv2wzSmTp2q9PR0hxvBzZ599llbuXnz5jncBACA8OES\n" +
            "RAA/qKioSDk5ObayrVu31s6dOx1uhGgQFxdnOeozMzO1bt06Q40AADCLAQbgBzVt2lSHDx+2leWv\n" +
            "EdjVp08frVixos4MrzEAAEQzLkEE8F9uueUW2+Nr6tSpDrdBNHnmmWcsMzU1NSouLjbQBgAA8zgB\n" +
            "A/AfSktLlZGRYSvLE+vQED6fz/K1BiNGjNAbb7xhqBEAAOYwwAD8h44dO2rbtm22ssFgUH6/3+FG\n" +
            "iDbZ2dmW93g1adJEFRUVhhoBAGAOlyACOCY/P9/2+Jo0aRLjCw3y4IMPWmaOHDmiUChkoA0AAGZx\n" +
            "AgbgmISEBFVVVVnmUlJSVF5ebqARopXX67V8eMu4ceP00ksvGWoEAIAZnIABkCRdc801tsaX9O0j\n" +
            "6oHj0a1bN8tMfn6+gSYAAJjFAAOgUCikf/zjH7ay5513nnr27OlwI0S72267zTITDAa5DBEAEHW4\n" +
            "BBGAevbsqY8//tgy5/P5dPToUQONEAvsXIb4xBNP6Ne//rWhRgAAOI8TMCDGFRQU2BpfkjRr1iyH\n" +
            "2yCWdOjQwTLz1FNPGWgCAIA5nIABMS4lJUUHDhywzLVp00Y7duww0Aix4sknn7Q83fJ4PJbvDAMA\n" +
            "wE04AQNi2PTp022NL0latWqVw20Qa2699VbLTG1trR544AEDbQAAMIMTMCCGxcfH27qna+TIkcrN\n" +
            "zTXQCLHGzou/u3btqs8++8xQIwAAnMUJGBCjLr30UlvjKy4ujvEFxwwbNswy88UXXxhoAgCAGQww\n" +
            "IAaFQiG9/vrrtrKPP/64w20Qy/76179aZmpra/klAAAganAJIhCDunfvrg0bNljm2rZtq+3btxto\n" +
            "hFiWmppq+b6v7OxsrV271lAjAACcwwkYEGMCgYCt8SVJ7777rsNtAOnCCy+0zHzyyScGmgAA4DxO\n" +
            "wIAY4/f7tW/fPsvc2WefrQ8//NBAI8S6kpISde7c2TK3fPly9evXz0AjAACcwwkYEEPmz59va3x5\n" +
            "PB7GF4zJyMhQSkqKZW7KlCkG2gAA4CwGGBBDJkyYYCt3/fXXO1sE+J7u3btbZgoLCw00AQDAWQww\n" +
            "IEb87ne/U0VFhWUuMTFRzzzzjIFGwP959NFHLTNVVVUqKSlxvgwAAA5igAExIBQK6ZFHHrGVffrp\n" +
            "px1uA/y3nJwceTwey9xDDz1koA0AAM7hIRxADLjyyiv1wgsvWObat2+vbdu2GWgE/Dc7r0do1qyZ\n" +
            "Dhw4YKgRAACNjxMwIMqFQiFb40uS8vLyHG4D/LibbrrJMnPw4EHLd4YBABDJGGBAlBs2bJit3Bln\n" +
            "nKHMzEyH2wA/btKkSbZyTzzxhMNNAABwDpcgAlEsEAgoOztbdr7M161bxwBD2LVu3Vq7d++uM3PC\n" +
            "CSdo165dhhoBANC4OAEDotjYsWNtja9LLrmE8YWIMGLECMuM1UADACCScQIGRKmCggL97Gc/s8zF\n" +
            "xcVp165d8vv9BloBdQuFQkpNTbXMvf322xo+fLiBRgAANC5OwIAodfnll9vK3X333YwvRAy/36/4\n" +
            "+HjL3G9+8xsDbQAAaHwMMCAKPfHEE7bukWnatKmmTZtmoBFgX58+fSwzGzduNNAEAIDGxwADotA9\n" +
            "99xjK/fkk0863ASovzvvvNMyU1NTo5KSEufLAADQyLgHDIgyt99+ux577DHLnN/vVzAYNNAIqD+v\n" +
            "12v5AJkRI0bojTfeMNQIAIDGwQADokgoFFLr1q119OhRy+zy5cvVr18/A62A+mvVqpX27t1bZyYx\n" +
            "MVGHDx821AgAgMbBJYhAFBk/fryt8dWhQwfGFyLauHHjLDMVFRUGmgAA0Lg4AQOiRGlpqTIyMmxl\n" +
            "eeky3MDj8VhmFixYoFGjRhloAwBA42CAAVEiOztb69ats8z16dNHRUVFBhoBxycuLk7V1dV1Zk49\n" +
            "9VR9/PHHhhoBAHD8GGBAFLD70mVJCgaDvPcLrtCjRw998skndWa8Xq/lSAMAIJJwDxgQBUaOHGkr\n" +
            "N3r0aMYXXOP222+3zNTU1BhoAgBA42GAAS43Y8YM7dy50zIXFxenZ5991kAjoHFMmDDBVu6uu+5y\n" +
            "tggAAI2ISxABl0tISFBVVZVl7t5779W0adMMNAIaj51/vk888UR9/fXXhhoBAHB8OAEDXOyXv/yl\n" +
            "rfGVmJjI+IIrnXHGGZaZHTt2GGgCAEDjYIABLhUKhfSXv/zFVnbGjBkOtwGccf3111tmuA8MAOAm\n" +
            "XIIIuFTv3r21cuVKy1zr1q1t3SMGRCo77wO75ZZb9OSTTxpoAwDA8eEEDHCh0tJSW+NLkl599VWH\n" +
            "2wDOSkhIsMy89tprBpoAAHD8GGCAC/Xp08dW7vTTT1dOTo7DbQBnnXLKKZaZsrIyA00AADh+DDDA\n" +
            "ZWbPnm37iW+LFy92uA3gvN/85jeWGe4DAwC4BfeAAS7TpEkTVVZWWuYuueQSLstC1LBzH9js2bNt\n" +
            "vzsMAIBw4QQMcJEbb7zR1vjy+XyML0SVuLg4y8zMmTMNNAEA4PgwwAAXefbZZ23l7rnnHoebAGa1\n" +
            "bdvWMvPpp58aaAIAwPFhgAEuYffBG8nJyZo6darDbQCzfvGLX1hmKioqDDQBAOD4cA8Y4ALr169X\n" +
            "Zmamrezy5cvVr18/hxsB5tm5DywYDMrv9xtoAwBAw3ACBrjAgAEDbOU6derE+EJMe+ihh8JdAQCA\n" +
            "OjHAgAg3c+ZM7d6921Z2+fLlDrcBwqd58+aWmblz5xpoAgBAwzHAgAh322232coNHDhQ6enpDrcB\n" +
            "wufMM8+0zNh9Rx4AAOHCAAMi2DXXXKOqqirLnNfr5aXLiHo33HCDZebo0aMGmgAA0HAMMCBChUIh\n" +
            "zZ4921b297//vcNtgPAbNWqUrVxhYaHDTQAAaDiegghEqFNOOUWfffaZZS4pKUmHDh0y0AgIv7i4\n" +
            "OFVXV9eZGT9+vJ5//nlDjQAAqB9OwIAIVFRUZGt8SdKrr77qcBsgcpx44omWmYKCAgNNAABoGAYY\n" +
            "EIGGDBliK9exY0cNHTrU4TZA5Lj44ostM2VlZQaaAADQMAwwIMJMmTJFBw8etJXlN/2INXfccYdl\n" +
            "hgdxAAAiGfeAARHG6/XKzpfl+eefr3feecdAIyCyeDwey8y8efM0ZswYA20AAKgfTsCACDJs2DBb\n" +
            "48vr9TK+ELOSkpIsMy+88IKBJgAA1B8DDIgQoVBI+fn5trI8dh6xrEuXLpaZNWvWGGgCAED9McCA\n" +
            "CHHaaafZyiUmJmrq1KkOtwEi1xVXXGGZ2bVrl4EmAADUH/eAAREgLy9PF1xwga3sokWLePIhYp6d\n" +
            "+8D49gYAiEQMMCACxMfH23pyW7t27XjENiDJ5/OppqamzszcuXM1duxYQ40AALCHSxCBMLvjjjts\n" +
            "Pzb7gw8+cLgN4A5t2rSxzMyfP99AEwAA6ocBBoTZo48+ais3cuRIpaenO9wGcIf+/ftbZj788EMD\n" +
            "TQAAqB8GGBBGgwYNspXzeDzKzc11uA3gHldffbVlZufOnQaaAABQPwwwIExKS0v13nvv2cpOmTLF\n" +
            "4TaAuwwbNswyU1NTo9LSUgNtAACwjwEGhElWVpatXGJiIgMM+AGJiYmWmddee81AEyB2lZSUqLi4\n" +
            "WFOnTlVOTo5OOOEExcfHy+PxKDk5WZdeeqlKSkrCXROIKDwFEQiD/Px8W7/Bl3jsPPBjTjvtNBUX\n" +
            "F9eZycnJUUFBgaFGQOQKhULaunWrNmzYoEOHDqm0tFQVFRUKBoPasWOHdu/erb1796qqqkqSdOjQ\n" +
            "IVVUVKiyslI1NTWqrq4+9q/6/ujYokULzZkzR5dccokT/9UA12GAAWGQkJBw7JtcXTp27KitW7ca\n" +
            "aAS4z+9//3v94Q9/qDPTvHlz7du3z1AjoPGVlJRo5cqVKi0tVVlZmb744gvt3LlTO3fu1JEjR3Tw\n" +
            "4EEdPXpU1dXVqqmpidj33/n9fm3ZskV+vz/cVYCwY4ABhk2dOlX333+/rWwwGOSbFfAjSkpK1Llz\n" +
            "Z8sc3+YQCUpKShQKhbRmzRrt379fX375pbZs2aKysjLt3r1bhw8f1oEDB1RbW2v71SRuM2XKFNvf\n" +
            "/4BoFhfuAkCsmTZtmq3cwIEDGV9AHTIyMuT1ei1fyJyXl6fhw4cbaoVYEAqFlJ+fr4MHD2rr1q1a\n" +
            "s2aN9uzZo2AwqP379ysUCkmSjhw5EtGnUqZxLxjwLQYYYNCYMWMsf1iUvn3s/OLFiw00AtytZcuW\n" +
            "2r17d52ZxYsXM8BgKT8/X8uWLdP27du1evVq7dmzRwcPHlRFRcWxEyk7f3/jx303TIFYxwADDFqw\n" +
            "YIGt3G9/+1uHmwDR4dxzz9XLL79cZyY/P1+PP/64oUaINEVFRXr++ee1bds2FRcXa//+/Tp06FCD\n" +
            "HiaB45OdnR3uCkBE4B4wwJDu3btrw4YNlrm4uDhbD+gAIL311lu66KKL6swkJSXp0KFDhhrBlFAo\n" +
            "pPXr12vFihUqKirSZ599ph07dujgwYOMqwjUokULFRcXKyMjI9xVgLDjBAwwoLS01Nb4kqSXXnrJ\n" +
            "4TZA9LjwwgstM4cPHzbQBI2toKBAX3zxhVasWKGPP/5Yu3fvVllZmY4ePRq1D6mIZn/6058YX8D/\n" +
            "4gQMMKBDhw4qKyuzzKWkpKi8vNxAIyB6NGnSRJWVlXVm/va3v+naa6811AhWCgoKtHHjRn3zzTda\n" +
            "vHixgsGgtm7dqoMHD6qyspLTKxeIj4+X1+s99n+rqqqOvTPs3///17dvX82cOZPLD4F/wwADHBYI\n" +
            "BJSVlWUrW1JSovT0dIcbAdElOztb69atqzMzcOBAHmxjSGFhoT7//HMVFBToq6++0jfffKOysjJV\n" +
            "VFSoqqqKcRVGXq9XkuTz+RQXF6fExEQlJCSoWbNmatGihdq0aaOTTz5ZktS1a1e1adNGzZo1U48e\n" +
            "Pep9elVcXMzoAn4ElyACDhswYICtXNu2bRlfQANcdtlllgPM6j+HPfPmzdPu3bu1ZMkSlZaWKhQK\n" +
            "6euvv1ZVVRWXBTrA5/NJ+va0KSEhQcnJyWrSpImSk5N1wgknqH379kpLS1PLli3VuXNndevWTRkZ\n" +
            "GRHxChPGF/DjOAEDHFRUVKScnBxbWV66DDRMYWGh+vXrV2fG4/HwCPE6lJSUaPHixSouLtbnn3+u\n" +
            "TZs2qby8XOXl5aqpqVF1dXW4K7qWx+M5duKUmpqqhIQEnXDCCercubOaNm2q0047TcnJyeratas6\n" +
            "duzIfVJADGCAAQ5KTk7WwYMHLXPZ2dlau3atgUZAdPJ6vZaXtm3ZsiUmf7hdtGiRVq5cqbVr12rz\n" +
            "5s3avn279u/fr6qqKkZpA3m9XsXFxSk+Pl7t27dXUlKSunXrpg4dOqj1vgh7AAAgAElEQVR79+5q\n" +
            "06aNcnJy+KUagB/EJYiAQ4qKimyNL0lasmSJw22A6Ob3+xUMBuvMPP7443rqqacMNXJeaWmp5s+f\n" +
            "r82bNysQCGjr1q0KBoM6cuTIfz0IAfbEx8crOTlZSUlJ6tSpk04++WR17txZvXv35mXeABoNAwxw\n" +
            "yPnnn28r179/f35LChynrKwsLV26tM7Mu+++a6ZMIwgEAnrxxRe1ceNGFRcXKxgMav/+/bzfqgE8\n" +
            "Hs+xYdWiRQv17NlTGRkZGjJkCKMKQFgwwAAHBAIB2y9+tfqhEYC1n//855ZfS1999ZWZMhYCgYD+\n" +
            "/ve/67PPPtP69eu1f/9+HTp0SLW1tYyrevB6vfJ6vWrevLn8fr86deqkU089Vd26ddP48eP5xRaA\n" +
            "iMU9YIADUlJSdODAActc//79GWBAIwiFQkpNTbXMOf0tLxAI6JVXXtEnn3yiVatWaf/+/SovL2dc\n" +
            "1VNcXJwSEhLUsmVLnXjiiWrfvr369++vtLQ0DR06lHEFwNUYYEAjq897v/jyAxpPfHy85aPQj/eF\n" +
            "zLm5uVq9erVWrlypzz77TOXl5cfuuYI9Pp9PTZo0UZs2bdS+fXt16tRJgwYNUps2bXThhReGux4A\n" +
            "OI5LEIFGNnDgQFu5/v37O9wEiC2tW7fWN998U2fm5ZdfrnOA5ebmas2aNVq2bJm++uor7dmzR0eO\n" +
            "HOGXJTb5fD7Fx8erVatWSk9P18knn6x+/fopOTmZkysA+F+cgAGNyO5lUBKnX0BjGzt2rObPn19n\n" +
            "JjU1VXfddZfef/99bdmyRWVlZTp8+DAnWDbExcXJ5/MpLS1Nbdu2Vbt27XTBBRcoOTlZY8eODXc9\n" +
            "AHANBhjQiLp3764NGzZY5rj3C2h88+fPZwg0kNfrVWJiopo3b642bdro9NNPV9euXdWnTx/Ll1wD\n" +
            "AOqHAQY0Io/HYyvHlx3QcHl5eQoEAlq0aJG2b9+ubdu26ciRI6qurg53tYjk8/mOjat27dqpd+/e\n" +
            "6tixo2644QYuCQSAMOAeMKCRXHHFFbZydh/QAcSq0tJSffjhh1q+fLk++ugjbd26Vfv27eNerO/x\n" +
            "eDzyeDxKTExUamqqOnbsqJycHJ1yyim67LLLGFcAEKE4AQMaid3Tr2AwyA9GiHnz5s3T/v379f77\n" +
            "72vTpk36/PPPVVFRocrKynBXixg+n0/NmjVTixYt1KtXL/3kJz/Reeedx8uDAcDlOAEDGsGMGTNs\n" +
            "5Tp06MD4QkwIhUJ655139MUXX2jFihX6+uuvtWnTJlVUVFg+Kj4W+Hw+NW3aVKmpqTr99NOVlZWl\n" +
            "Sy+9VJmZmeGuBgBwGCdgQCNISkpSRUWFZW7dunX8gIWoUVpaqsWLF2vJkiXauHGjSkpKFAqFdOTI\n" +
            "kXBXCyuv16ukpCQ1b95cvXr1UpcuXXTNNdfwtQ8AkMQJGHDcAoGArfHVtGlTfgCD64RCIT377LNa\n" +
            "v369Vq9erV27dqm8vDxmT7E8Ho/i4+OVmpqqtLQ0nXXWWerSpYsmT54c7moAAJdggAHH6ZJLLrGV\n" +
            "mzNnjrNFgOOQn5+vpUuX6s0339SuXbsUDAZj8qmCXq/32H1X7du3V//+/dW/f3/uuwIANBouQQSO\n" +
            "k52Hb3i93pj8YRaRJxAI6Omnn1ZeXp527doVs08WjIuL03nnnadzzjlHvXv31rBhw8JdCQAQIzgB\n" +
            "A47DLbfcYit3zTXXONwE+G+TJ0/WwoULtW3bNh04cCAmhpbP55PH47G8RLK2tlbvvvuuoVYAAPwf\n" +
            "TsCA45CQkKCqqirLHF9mcNL69ev197//Xbm5udq5c2dU35/l9XqVkJCgli1bqk2bNho4cKC6d++u\n" +
            "AQMGKCMjQ9K3962lpqZafi6+LgEA4cAJGNBAgUDA1vjq0qWLgTaIFfn5+Zo7d67+9a9/aceOHaqp\n" +
            "qQl3pUaXkJBw7B6sTp066bzzztOAAQNsP8TG7qseZs+erYkTJx5PVQAA6o0TMKCBunfvrg0bNljm\n" +
            "SkpKlJ6ebqARok1ubq5eeOEFrVixQnv37o2a+wg9Ho8SExPVsWNHtW/fXmeffba6dOmikSNHNtp7\n" +
            "8pKTk3Xw4ME6M+eff77eeeedRvnzAACwiwEGNJCdh28kJCTE/DuRYC0UCun555/X22+/rZUrV6q8\n" +
            "vNz1J1s+n0+JiYk65ZRT1KVLF5199tnq2rWrsacJ9u3bVx988EGdmZYtW2rPnj1G+gAA8B0uQQQa\n" +
            "4OGHH7aVu/766x1uArcJBAJ64YUXtHjxYm3atEn79+937b1I371wuEOHDjrppJN0/vnn1+tSQScN\n" +
            "GjTIcoCFQiFDbQAA+D+cgAENkJqaauuHN768Ytu8efP05ptv6pNPPtGXX36pw4cPh7tSg8TFxal1\n" +
            "69Zq166dLr/8cvXr1085OTnhrmXJzik1X6MAANM4AQPqKRQK2RpfnTp1MtAGkaCgoED/+te/tHr1\n" +
            "ahUXF2vnzp2uvF8rLi5OrVq10uDBg9WjRw9Nnjw53JWOi8fjsRxYDz/8sO68805DjQAAYIAB9Xbb\n" +
            "bbfZyr3wwgsON4FpoVBIr7zyil5++WV9/vnn+uabb1RRURHuWvXm9XrVokUL9e/fXwMHDtTNN98c\n" +
            "7kqOSExMtDx1nDdvHgMMAGAUlyAC9dSsWTMdOnSozozP54vqdzHFgj/96U9aunSpPv30U3399deW\n" +
            "T9SLRN89bbBt27YaPXq0pk+fHu5KRvXq1Utr166tM2NnpAEA0Jg4AQPqobS01HJ8SdLgwYMNtEFj\n" +
            "yM3N1RtvvKGPPvpIpaWlqqysdOV9QR6PR61bt1ZWVpbGjRvH+60kjR071nKA8ZRSAIBpnIAB9TBi\n" +
            "xAgtXLjQMhcMBhvtfUZoHC+//LKWLVumt99+W998842OHDniyqElfft6gxNOOEEjR47UsGHDNHTo\n" +
            "0HBXilg8iAMAEGkYYEA9JCQkqKqqqs5MUlKSrVMyOKOoqEi5ubl66623VFZWpsrKSte+U8vr9Sol\n" +
            "JUUnnXSSRo0apSuuuIKXeteTnQF2//33a8qUKQbaAADAAANsC4VCSk1Ntcz9/Oc/5wEcBpSWlurB\n" +
            "Bx/U+++/r+3bt+vAgQOuPslo0qSJ2rdvr7POOkvnn38+lxA2ksTERMvLDE899VR9/PHHhhoBAGId\n" +
            "AwywacCAAVqyZIllji+pxvfoo48qNzdXX3zxhfbt2+fq/419Pp+Sk5PVrVs3nX/++Ro5cmREvLg4\n" +
            "Wtl5EEezZs104MABQ40AALGOh3AANhUUFFhmWrRoYaBJ9Fq/fr3+/ve/a8GCBdq9e7fl5Z6RzOPx\n" +
            "KCUlRZ07d1b37t01YsQIjR07Nty1Yk6/fv0sB5gbn3AJAHAvTsAAG0pKStS5c2fL3D333KMHHnjA\n" +
            "QCP3mz17tt58802tWLFCu3btcu19WtK3l7mlpKQoMzNT1113nU455RROtSIID+IAAEQSBhhgw9Ch\n" +
            "Q/XOO+9Y5vhy+mEzZszQ4sWLVVRUpGAw6Nqx5fV61bRpU2VnZ6tv37467bTTNGbMmHDXggU7A2zG\n" +
            "jBlR+0JqAEBkYYABNti5kb9ly5bas2ePoUaRa/r06SooKNAHH3ygffv2uXZsJSQkqG3bturZs6cu\n" +
            "vfRSXXvtteGuhAay8/X7s5/9TMuWLTPUCAAQy7gHDLBQUlJi62WtsfgD+qxZs7RgwQIVFxdrz549\n" +
            "rhxbPp9PzZs310knnaTLL79cN910E+9wizLt2rXTli1b6sysXr3aUBsAQKxjgAEWHnnkEVu5u+++\n" +
            "2+Em4TVv3jzl5uZq7dq1+uqrr3T06NFwV6q3Jk2aKC0tTVdddZX69OnDC4xjxEUXXaSnnnqqzszh\n" +
            "w4cNtQEAxDouQQQstGjRQuXl5XVmUlNTtXfvXkONnDd//nx98MEHKigo0KeffmrrBDCSeL1epaWl\n" +
            "6ayzztKIESN4p1aMKy0tVUZGhmWOb4cAABM4AQPqEAqFLMeXJI0cOdJAG2cUFBTo9ddf14oVK/TJ\n" +
            "J5/Y+u8bKb571Pupp56qQYMGadq0aeGuhAiUnp5uK5efn8+pKADAcQwwoA5z5syxlXv44YedLdJI\n" +
            "CgsLVVBQoEWLFmn16tWqqKhwxW/9PR6P4uPjlZaWposvvljXXXedevbsGe5acBGfz6fq6uo6M3/9\n" +
            "618ZYAAAx3EJIlCHtm3baufOnXVmUlJSIvLUqLS0VAsXLtSCBQu0bt26iOz4Q7xer0444QRlZWXp\n" +
            "uuuu06hRo8JdCVGgdevW2r17d50Zv9+vYDBoqBEAIFZxAgbUwWp8SdKQIUMMNLH24IMP6r333tOa\n" +
            "NWtUXl4e8U8k9Hg8SkhIUFpamkaNGqWbb77Z9qViQH0NHjxYc+fOrTNz4MABQ20AALGMEzDgR+Tl\n" +
            "5emCCy6wzAWDQeOPLS8qKtJf/vIXvf/++9q5c6flpVXh5vF45Pf7dfLJJ2vo0KHcqwXj8vPzNWzY\n" +
            "MMsc3xIBAE5jgAE/ok+fPlqxYkWdmYSEBMefEBgKhfTQQw/p1Vdf1ZYtWyL+8e9er1ctW7bUWWed\n" +
            "pdNPP52xhYjh8XgsM+H4hQoAILZwCSLwI+y8mPWcc85p9D83NzdXf/vb37Rq1SqVl5dH9G/k4+Pj\n" +
            "1bZtW2VmZuqqq67SmDFjwl0J+FEej8fy62nKlCl68sknDTUCAMQiBhjwA0pKSlRVVWWZe+CBB477\n" +
            "z5o2bZrmz5+vL774IqJPtxISEo6NrUsuuUTXXnttuCsB9dK0aVMdPHiwzsw777xjqA0AIFYxwIAf\n" +
            "cO+991pmPB6PcnJy6v25J0+erOeee067du2K2AdlfPdwjLPOOktDhgxhbCEq9OrVSwUFBXVmtm3b\n" +
            "ZqgNACBWMcCAH5CXl2eZ6dSpk2UmFApp8uTJys3NVSgUisjLCX0+n5o3b64BAwZo5MiRGjp0KPfA\n" +
            "ICqde+65lgPM6oQMAIDjxUM4gB9g52b9p556SpMmTfqPf6+0tFQPPvig5s+fr3379kXk4GrWrJnO\n" +
            "OOMMDRkyRDfddBNjCzHFztd2JH7dAgCiBwMM+J558+Zp3Lhxlrna2loFAgHde++9WrFihXbv3h1x\n" +
            "P7glJCQoPT1do0eP1rXXXquMjIxwVwLCys4AmzFjhm6++WYDbQAAsYhLEIHveeKJJywzHo9HXq83\n" +
            "ogaX1+tVamqq+vfvr2uuuUbDhw8PdyUg4jRp0sTy1RErV65kgAEAHMMJGPA98fHxEf00wu/Ex8fr\n" +
            "Jz/5iS6//HLddtttXEoI2NCxY0fLB22kpaXpm2++MdQIABBrvOEuAESSmTNnRuz4atGihYYNG6a3\n" +
            "335btbW1qqys1CeffKJp06YxvgCbrrjiCsvMrl27DDQBAMQqTsAQ02bNmqV58+Zp9erV2rdvX7jr\n" +
            "HOPxeHTiiSfqwgsv1PTp0xlYQCNZv369MjMzLXN8awQAOIUBhpgRCoX03HPPaeHChfroo49UXl4e\n" +
            "7krHxMXF6eSTT9aECRM0efLkcNcBopqdB3EEg0F+8QEAcAQP4UBUCgQCWrRokZYtW6bPPvtMZWVl\n" +
            "qqysDHetYxISEpSVlaVp06Zp6NCh4a4DxBSv12v5EvTHH39c06ZNM9QIABBLOAGD6z344INasmSJ\n" +
            "Nm/erG3btlk+4SwcmjZtqr59+2rKlCnq27dvuOsAMe3EE0/U9u3b68xkZ2dr7dq1hhoBAGIJJ2Bw\n" +
            "jfz8fM2fP1/vvvuudu3apaqqqnBX+kEej0cpKSkaMmSIHnnkEaWnp4e7EoB/c+aZZ2rhwoV1ZjZv\n" +
            "3myoDQAg1jDAEJGKioo0bdo0rVq1Svv27YvoG+K9Xq/atm2ryy67TA888AD3jQAR7rLLLrMcYAcO\n" +
            "HDDUBgAQa7gEERFhzpw5+uMf/6gtW7ZE7GPgvxMXF6eOHTtq+PDhDC7Apew8iINvjwAAJ3AChrDI\n" +
            "zc3Vww8/rOLi4ogfXN/xer2qrq4Odw0AjcDj8VgOrDlz5mjChAlmCgEAYgYvYka9TJkyRYMHD1b3\n" +
            "7t3VtWtXnXPOORo7dqzy8/Pr/LhAIKDLL79cKSkp8ng8Gj16tFavXu2a8SVJP/nJT8JdAUAjSUlJ\n" +
            "scy8++67BpoAAGINJ2Co0+23366//e1vdb4z68MPP9T8+fMlfXtK1LFjR915553auHGjXnnlFW3b\n" +
            "ts3ykc/hEhcXp9atW2vnzp2Wp1vjx4831AqA0zp16qSPP/64zsyqVasMtQEAxBLuAcN/KSgo0OjR\n" +
            "oy0f0+xGiYmJ6tatm4YMGaIbb7xRGRkZKikpUefOnS0/lhezAtHjvvvu0//8z//UmYmPj4+o9wcC\n" +
            "AKIDlyDimBdffFEtWrTQz372s6gaX61atdINN9ygLVu26PDhw1q7dq0eeughZWRkSJKeeeYZy8/R\n" +
            "okULxhcQRa655hrLTKS+6gIA4G5cggiFQiENHTpUK1euDHeVRuHxeNSlSxdde+21mjx5smV+wYIF\n" +
            "lpkzzjijMaoBiBDp6em2HsQRCoX45QsAoFFxAhbjiouL1aFDB9ePr/j4eOXk5Gj58uWqqanRl19+\n" +
            "aWt8SVJJSYll5rrrrjvOhgAiTZMmTSwzf/nLXww0AQDEEgZYDAuFQrr44ot18ODBcFdpEL/fr9tv\n" +
            "v121tbWqrKxUQUGB+vXrV6/PUVBQYOtdP2PGjGloTQAR6qc//all5o033jDQBAAQSxhgMez+++/X\n" +
            "1q1bw13Dtri4OJ155pkqLCxUbW2tgsGgHnnkkeP6nHZ+u52amnpcfwaAyDR48GDLzIYNGww0AQDE\n" +
            "EgZYjHr99df15JNPhrtGnbxer9LT0/XUU0+ptrZWVVVVWrVqlfr27dtof4bV+8skaejQoY325wGI\n" +
            "HJdccollxq1XCAAAIhcP4YhRS5cuDXeF/+LxeNSmTRvdcMMNmjp1qpE/MxgMWmbuvPNOA00AmJaT\n" +
            "k2OZsXo/IAAA9cUJWIyKhAHm9XrVtm1bXXfddQoGg6qpqdH27duNja/vXh5dF4/Ho+zsbANtAIRD\n" +
            "QkKCZWbGjBkGmgAAYgUDLEatW7fO+J/p8XjUrl07/epXv1JJSYmqq6u1fft2/fWvfw3LY56ffvpp\n" +
            "y0yHDh0MNAEQLp06dbLMvPPOOwaaAABiBZcgwnHt2rXT/PnzbV3uY5KdEcr9X0B0O/PMM7Vx48Y6\n" +
            "M1b/OQAA9cEJGBzVq1cvlZWVRdz4CoVC2rdvn2Xud7/7nYE2AMJlxIgRlpmvvvrKQBMAQKxggMUo\n" +
            "n8/n+J/Rv39/rVmzxvE/pyFmzpxpmUlISFBGRobzZQCEzdixYy0zhw4dMtAEABArGGAxyul7m8aM\n" +
            "GRMRD/r4Ma+99pplpmvXrgaaAAi3uDjrq/Hz8vIMNAEAxAIGWIxy8t6mf/7zn5o3b55jn78xfPzx\n" +
            "x5aZgQMHGmgCINzs/ELKzjsDAQCwgwEWo+xcdlNfHTp0UG1tra688spG/9yNqaSkRJWVlZa5KVOm\n" +
            "GGgDINzsnHbb+aUNAAB2MMBi1LnnnquLL764UT5XUlKS1q1b55ob1R9//HHLTFJSUlgejQ/AvJEj\n" +
            "R1pmIvV+VgCA+3hqa2trw10C4REKhZSenq7y8vIGfXzr1q01Z84cDR8+vJGbOSs9PV1bt26tM9O7\n" +
            "d2+tWLHCUCMA4RQKhZSammqZ49slAKAxcAIWw/x+v5YtW6YWLVrU6+MGDhyoYDConTt3um58SdK2\n" +
            "bdssM2PGjDHQBEAk8Pv9th7EEQgEDLQBAEQ7BliMy87OVklJia3LEXv37q1gMKjFixe79vK84uJi\n" +
            "1dTUWOZ+/etfG2gDIFK0bNnSMrNkyRIDTQAA0Y5LEHHM0qVLj/2ruLhY0rcD7dxzzz32L7cbNWqU\n" +
            "Xn755TozKSkpDb4sE4A75eTkqKioqM5M3759VVhYaKgRACBaWV9zgZgRLSOrLu+9955lpl+/fgaa\n" +
            "AIgko0aNshxgGzduNNQGABDNuAQRMSUYDFpmrrrqKgNNAESS8847zzKze/duA00AANGOSxARM956\n" +
            "6y1ddNFFljm+JIDY5PF4LDP8/QAAOF6cgCFmPPjgg5aZNm3aGGgCIBI1a9bMMjNr1iwDTQAA0YwB\n" +
            "hpixfv16y0yfPn0MNAEQiTIyMiwzCxcudL4IACCqMcAQE0KhkPbv32+Z++1vf2ugDYBIlJmZaZn5\n" +
            "f//v/xloAgCIZgwwxASrR89/Jycnx+EmACLVgAEDLDM7d+400AQAEM14CAdiwhlnnKE1a9bUmenU\n" +
            "qZNKS0sNNQIQiXgQBwDAaZyAISasW7fOMjNw4EADTQBEMq/X+ttiIBAw0AQAEK0YYIh6oVBIR48e\n" +
            "tcxNmTLFQBsAkSwpKckyY/eSZgAAfggDDFFv6tSplpm4uDilp6cbaAMgkqWlpVlm3nzzTQNNAADR\n" +
            "igGGqGfnhyU7j58GEP2GDh1qmdmyZYuBJgCAaMUAQ9Sz82CNMWPGGGgCINJ17drVMnPgwAEDTQAA\n" +
            "0YqnICKqlZaW2jrdCgaD8vv9zhcCEPF4EiIAwEmcgCGq3X333ZYZn8/H+AJQL7yyAgDQUAwwRLUl\n" +
            "S5ZYZjIzMw00AeAWPp/PMvPYY48ZaAIAiEYMMES1HTt2WGYmTpxooAkAt2jevLllpqioyEATAEA0\n" +
            "YoAhahUUFNi6T+PKK6800AaAW9h5JQWXIAIAGooBhqj16KOPWmaaNGnC/V8A/sOgQYMsM8Fg0EAT\n" +
            "AEA0YoAhai1btswy06NHDwNNALiJnVPxmpoaA00AANGIAYaotW/fPsvMr371KwNNALgJD+YBADiJ\n" +
            "AYaolJeXZyvHAzgA/BA77wLLz8830AQAEG0YYIhKDz/8sGUmMTHRQBMAbmTnUfRz58410AQAEG0Y\n" +
            "YIhKa9asscycfvrpBpoAcKOUlBTLjJ37TAEA+D4GGKLSgQMHLDOTJk0y0ASAG/Xp08cys3PnTgNN\n" +
            "AADRhgGGqDNv3jxbuTFjxjjcBIBb9evXzzJTUVFhoAkAINp4au28qRZwkf79+2v58uV1ZlJSUlRe\n" +
            "Xm6oEQA3svMgDr6FAgDqixMwRJ1Vq1ZZZs455xwDTQBEO56ECACoLwYYoo6dy4JuvfVWA00AuFlc\n" +
            "XJxl5r333jPQBAAQTRhgiCrTp0+3lRs+fLjDTQC4XVpammXm1VdfNdAEABBNGGCIKi+99JJlxs7j\n" +
            "pQHAzpMQy8rKDDQBAEQTBhiiyoYNGywz3P8FwI6JEydaZo4cOWKgCQAgmvAUREQVO08tKygoUE5O\n" +
            "joE2ANzOzt8pwWBQfr/fQBsAQDTgBAxRw+79X4wvAHbZeRDH888/b6AJACBaMMAQNezc/5WcnGyg\n" +
            "CYBo0bZtW8vMnDlznC8CAIgaDDBEDTv3f9m5qR4AvtOvXz/LzKZNmww0AQBECwYYokZVVZVl5t57\n" +
            "7zXQBEC0uPLKKy0z5eXlBpoAAKIFD+FAVJgxY4ZuueUWyxz/uAOoLx7EAQBoTJyAISrMmjXLMtO8\n" +
            "eXMDTQBEm8TERMvMo48+aqAJACAaMMAQFT777DPLTO/evQ00ARBtMjMzLTNLly51vggAICowwBAV\n" +
            "KisrLTO33nqrgSYAos24ceMsM4FAwEATAEA04B4wuN78+fM1duxYyxz/qANoiJKSEnXu3Nkyx98x\n" +
            "AAA7OAGD6z322GOWmaSkJANNAESjjIwM+Xw+y1xeXp6BNgAAt2OAwfXWrVtnmbFzDwcA/Jj27dtb\n" +
            "Zl555RUDTQAAbscAg+vZuf/rt7/9rYEmAKLVoEGDLDOLFy820AQA4HYMMLhabm6urdyoUaMcbgIg\n" +
            "ml1++eWWmbKyMgNNAABux0M44Go5OTkqKiqqMxMfH2/rlAwA6mLnhczr1q3jkmcAQJ04AYOr2bn/\n" +
            "q0ePHgaaAIh2LVq0sMy8+eabBpoAANyMAQZXO3DggGVm4sSJBpoAiHZnnHGGZYYHcQAArDDA4FqF\n" +
            "hYW2cpMmTXK4CYBY0Lt3b8vMp59+aqAJAMDNuAcMrjVixAgtXLiwzozX61V1dbWhRgCiWSgUUmpq\n" +
            "qmWOb6sAgLpwAgbXWrZsmWUmLS3NQBMAscDv9ysuLs4yN2vWLANtAABuxQCDa+3fv98yc8UVVxho\n" +
            "AiBWtG3b1jLz0ksvGWgCAHArBhhcKRQK2brM5+abbzbQBkCssPNOweLiYgNNAABuxQCDKz3++OOW\n" +
            "GY/Ho/T0dANtAMSKq6++2jITDAYNNAEAuBUP4YArdejQQWVlZXVmkpOTbV2mCAD1YeeFzHl5eRo2\n" +
            "bJiBNgAAt+EEDK60fft2y0yfPn0MNAEQa5o3b26ZeeaZZww0AQC4EQMMrmTn0fK/+c1vDDQBEGu6\n" +
            "d+9umfnggw8MNAEAuBEDDK6Tm5trKzd06FCHmwCIRddcc41lZs+ePQaaAADciHvA4Dr9+/fX8uXL\n" +
            "68zExcWpqqrKUCMAscbOfWAlJSU8CAgA8F84AYPr2HnE80knnWSgCYBYFR8fb5mZMWOGgSYAALdh\n" +
            "gMF17DzZcMiQIQaaAIhV7dq1s8zMmzfPQBMAgNswwOA6dq6anTZtmoEmAGKVnRcy23laKwAg9jDA\n" +
            "4Cr33XefrZzf73e4CYBYds8991hm7DytFQAQexhgcJW5c+daZpo1a2agCYBYZveXPHZ/aQQAiB0M\n" +
            "MLjKV199ZZk5/fTTDTQBEOtSUlIsM//4xz8MNAEAuAkDDK5y5MgRy4ydezMA4Hidc845lhnuAwMA\n" +
            "fB/vAYNrlJaWKiMjwzLHP9IATCgsLFS/fv0sc/ydBAD4d5yAwTUef/xxy4ydl6MCQGPIycmxlZs+\n" +
            "fbrDTQAAbsIJGFyjQ4cOKisrqzPTokULhUIhQ40AxLrExETLS6M7duyorVu3GmoEAIh0nIDBNXbs\n" +
            "2GGZ+elPf2qgCQB8Kzs72zJj9YsjAEBsYYDBNY4ePWqZuemmmww0AYBv3XDDDZaZmpoaA00AAG7B\n" +
            "JYhwDTv3d/GPMwDT7Pzd9NBDD2ny5MkG2gAAIh0nYHAFO79l5gEcAMIhLi7OMvPUU08ZaAIAcAMG\n" +
            "GFxh2bJllpmmTZsaaAIA/+nUU0+1zPA+MADAdxhgcIWSkhLLTCzuCHcAABl+SURBVGZmpvNFAOB7\n" +
            "fv3rX1tmuA8MAPAdBhhcweoxz5I0ePBgA00A4D9NmDDBVu6uu+5ytggAwBV4CAdcgQdwAIhkCQkJ\n" +
            "qqqqqjPTunVr7dy501AjAECk4gQMEW/mzJnhrgAAderRo4dlZvfu3QaaAAAiHQMMEW/x4sWWGTtP\n" +
            "IQMAp9x9992WmdraWoVCIQNtAACRjAGGiFdUVGSZadOmjYEmAPDDRo0aZSvHfWAAAAYYIt7evXst\n" +
            "M4MGDTLQBAB+XGJiomVm7ty5BpoAACIZAwwRz87jm8eNG2egCQD8uF69ellmysvLDTQBAEQynoKI\n" +
            "iMcTEAG4QSAQUFZWlmVu3bp1vLcQAGIYJ2CIaNOnTw93BQCwxe6o4j4wAIhtDDBENDtPQLRz3wUA\n" +
            "mNCqVSvLzJIlSww0AQBEKgYYItratWstM2lpaQaaAIC1K664wjJTUVFhoAkAIFIxwBDRgsGgZeZn\n" +
            "P/uZgSYAYG3atGm2clxeDQCxi4dwIKLZeQBHYWGh+vbta6ANAFjz+XyWT29NT09XSUmJmUIAgIjC\n" +
            "AENE4wmIANwmOztb69atqzPj9XpVXV1tqBEAIJJwCSIiVn5+frgrAEC93XPPPZaZmpoahUIhA20A\n" +
            "AJGGAYaItWjRIsuMz+cz0AQA7Bs1apSt3KRJkxxuAgCIRFyCiIjVpUsXbd68uc5Mq1attHv3bkON\n" +
            "AMCe1NRUyxOu1NRU7d2711AjAECk4AQMEcvOsOrRo4eBJgBQPxdeeKFlxs5TXgEA0YcBhoi1f/9+\n" +
            "y0xmZqaBJgBQPzNmzLCVy8vLc7gJACDScAkiIpadJyCWlJQoPT3dQBsAqJ/4+HgdPXq0zkzfvn1V\n" +
            "WFhoqBEAIBIwwBCxeAQ9ADfLyspSIBCoM5OYmKjDhw8bagQAiARcgoiIxCPoAbjdrbfeapmpqKjg\n" +
            "cfQAEGMYYIhIr776qmWGR9ADiGS/+MUvbOXmzJnjbBEAQERhgCEiWT1+Xv+/vXuNseos1AD8zYWR\n" +
            "wkyZwRYqDZkBiY2VDpD0H4MYbBrbaIRWitdQmwZbbAOkMbQ1KWpQQtIGWq2iRi61URM0MTEaG02d\n" +
            "CCimxlLakmotnenN2haZwjDcZmafHyc98XgOsxbDWt9ae+/n+SnvfHl/bOm8fHuvHUKYPHlyhCYA\n" +
            "4/fud787MfPtb387QhMAysIAo5SefvrpxMy0adMiNAEYv2uvvTYxk+YfnACoHQYYpZTmMxFLliyJ\n" +
            "0ARg/DZt2pSYGRkZSXxYBwC1wwCjlM6cOZOYueaaayI0ARi/zs7OMGHChMTcAw88EKENAGXgMfSU\n" +
            "kkfQA7Wiu7s78W3VHR0d4V//+lekRgAUyQ0YAOToy1/+cmLm6NGjEZoAUAYGGKWzb9++xEyaGzKA\n" +
            "MlixYkWq3E9+8pOcmwBQBgYYpfOLX/wiMdPY6KULVI80T2391re+FaEJAEXzWyyl89RTTyVmfAcY\n" +
            "UE1uuOGGxMyf/vSnCE0AKJoBRuk899xziZmOjo4ITQCycfvttydmhoeHQ39/f4Q2ABTJAKN00jwJ\n" +
            "7Morr4zQBCAb3d3doampKTH34IMPRmgDQJEMMEpnaGgoMdPd3R2hCUB2FixYkJj56U9/GqEJAEUy\n" +
            "wCidkZGRxMynP/3pCE0AsnPXXXclZl599dUITQAoki9ipnR8CTNQiwYGBlJ9fvWXv/xluP766yM0\n" +
            "AqAIbsAAIIL29vYwc+bMxNy2bdsitAGgKAYYpTIwMFB0BYDcLFmyJDHz29/+NkITAIpigFEqP/zh\n" +
            "DxMzvoQZqFa33nprYubkyZP+MQqghvlNllJ55plnEjMTJ06M0AQgez09PakeR3///fdHaANAEQww\n" +
            "SuXQoUOJmdbW1ghNAPLR1dWVmNm+fXv+RQAohAFGqfT19SVmpk2bln8RgJzce++9iZnXX389QhMA\n" +
            "imCAUSppvoR59uzZEZoA5OOGG25IzFQqlbBnz54IbQCIzQCjVAYHBxMz73//+yM0AchHe3t7aGlp\n" +
            "Scz5HBhAbTLAKJXh4eHEzMc+9rEITQDy09PTk5jxOHqA2mSAUSqjo6OJmYULF0ZoApCf2267LTEz\n" +
            "NDTkcfQANcgAA4DIli9fnir3ne98J+cmAMTWUKlUKkWXgHc0NDQkZrxkgVowderUcPTo0TEznZ2d\n" +
            "qZ4OC0D1cANGafT39ydm0gw0gGqwdOnSxMxLL70UoQkAMRlglMbjjz+emGlqaorQBCB/GzZsSMxU\n" +
            "KpVw8ODBCG0AiMUAozQOHTqUmJk0aVKEJgD56+zsDI2Nyf8Z/uIXvxihDQCxGGCUxsmTJxMzzc3N\n" +
            "EZoAxHH55ZcnZp544okITQCIxQCjNPbv35+YmT59eoQmAHGkud06ffp0hCYAxGKAURpvvPFGYmbO\n" +
            "nDkRmgDEsX79+lS5NJ8XA6A6GGCUxvHjxxMzV1xxRYQmAPG0tLQkZrZt2xahCQAxGGCUxtmzZxMz\n" +
            "U6dOjdAEIJ4FCxYkZt58880ITQCIwQCjNM6cOZOYWbRoUYQmAPHcf//9iRmPoweoHQ2VSqVSdAkI\n" +
            "IYTGxsaQ9HL0cgVqUZovmb/mmmvCb37zmwhtAMiTAUZppPkFxMsVqEWXXHJJOHLkyJiZ5ubmVG/V\n" +
            "BqDcvAURAAp24403JmaGh4fDwMBAhDYA5MkAo2qkuSEDqEabN29OlbvvvvtybgJA3rwFkVLo6+sL\n" +
            "s2bNGjPT1NQUhoeHIzUCiKu5uTmMjIyMmWltbU31lR0AlJcbMEph//79iZnm5uYITQCKMX/+/MTM\n" +
            "4OBghCYA5MkAoxReeumlxMzEiRMjNAEoxtatW1Pldu/enXMTAPJkgFEKL7/8cmKmo6MjQhOAYvT0\n" +
            "9KT6rOv69esjtAEgLwYYpfDXv/41MTN58uQITQCKc9lllyVm+vr68i8CQG4MMEohzYfKL7300ghN\n" +
            "AIqzbt26xEylUgn79u2L0AaAPBhglMLrr7+emJk7d26EJgDF+dKXvpQqd9ttt+XcBIC8GGCUwrFj\n" +
            "xxIzM2bMiNAEoFhtbW2JmUOHDkVoAkAeDDBK4dSpU4kZb0EE6sHq1asTM6Ojoz4LBlClfBEzpdDS\n" +
            "0hLOnj07ZmbPnj2hp6cnUiOA4qR5GuLVV18dnnjiiQhtAMiSAUYpNDc3h5GRkTEzXqpAvWhtbQ0n\n" +
            "TpwYM9Pc3Jz4D1cAlI+3IFIKo6OjRVcAKI1bbrklMTM8PBz6+/sjtAEgS27AKIU0b7fxUgXqSZq/\n" +
            "F6+99trw2GOPRWgDQFYMMEoh6ReNhoYGt2RAXWlrawuDg4NjZrwNEaD6eAsiVaGx0UsVqC9f+MIX\n" +
            "EjPehghQfdyAUbiDBw+GefPmjZl517velepR9QC1JM3bEBcvXhx6e3vzLwNAJlwrULg0Xyja3Nwc\n" +
            "oQlAuUyfPj0x84c//CFCEwCyYoBRuKTPOIQQwpQpUyI0ASiXNWvWJGbOnj0bDh48GKENAFkwwCjc\n" +
            "c889l5hpbW2N0ASgXO65555UuTvuuCPnJgBkxQCjcC+88EJipq2tLUITgPLp7OxMzPzxj3+M0ASA\n" +
            "LBhgFO6tt95KzKT5BQSgFt11112JmeHh4bBnz54IbQC4UAYYhTt69GhiZtasWRGaAJTPnXfemeqr\n" +
            "ONK+XRGAYhlgFC7N4+UnTZoUoQlAOV111VWJmf3790doAsCFMsAo3LFjxxIzXV1d+RcBKKlvfvOb\n" +
            "iZmRkZHwq1/9KkIbAC6EAUbhhoaGEjPTpk2L0ASgnBYtWpTqbYhf+cpX8i8DwAUxwCjcyMhIYmbu\n" +
            "3LkRmgCUV09PT2Lmz3/+c4QmAFwIA4zCDQ8PJ2a8BRGod2nehlipVML27dsjtAFgvBoqlUql6BLU\n" +
            "t6ampjA6OjpmxssUIISLLroo8cFFCxYsCH/5y18iNQLgfLkBo3BJ4wuA//bxj388MfPkk09GaALA\n" +
            "eBlglF6aD54D1IPNmzenym3cuDHnJgCMl7cgUriGhoYx/3zChAnhzJkzkdoAlFtHR0cYGBgYMzN7\n" +
            "9uzwwgsvRGoEwPlwtUChDhw4kJhpamqK0ASgOtx8882JmcOHDyeONACKYYBRepMmTSq6AkBpbNmy\n" +
            "JVXuq1/9as5NABgPA4xCpfnOmokTJ0ZoAlA93vve9yZmPI4eoJwMMAo1ODiYmGlra4vQBKB63HHH\n" +
            "HYmZY8eOpXqbNwBxGWAUqr+/PzFz8cUXR2gCUD3Wrl2b+ACjEEJYt25dhDYAnA8DjEKleUpXa2tr\n" +
            "hCYA1WXOnDmJmT179kRoAsD5MMAo1PHjxxMznZ2dEZoAVJevfe1riZmRkZGwY8eOCG0ASMsAo1Bp\n" +
            "HpM8c+bMCE0AqssnP/nJVF/T4WmIAOVigFGokydPJmauvPLKCE0Aqs/cuXMTM/39/b4TDKBEDDAK\n" +
            "dezYsaIrAFStRx55JFVu48aNOTcBIK2GSqVSKboE9au9vT28/fbbY2aeeuqp0N3dHakRQHWZMGFC\n" +
            "GB4eTsycOXMmUiMAxuIGjEKdOnUqMeMx9ADntnTp0sTM2bNnw8GDByO0ASCJGzAKleZfbr1EAc5t\n" +
            "YGAgdHR0JObmzp0bnn766QiNABiLAUahGhsbEweWlyjA2KZMmZLqM7X+PgUonrcgUmqNjV6iAEnW\n" +
            "rl2bKrdhw4acmwCQxA0YhWpoaBjzz5uamhLfoghA8t+nIYTQ0tISTp8+HaENAOfieoFSa2lpKboC\n" +
            "QFWYM2dOYubMmTMexgFQMAOMwvT19SVmvAURIJ2f/exnqXIf/ehHc24CwFj8dkth0nxgfPLkyRGa\n" +
            "AFS/7u7u0NzcnJh7+eWXI7QB4FwMMApz6NChxIy3IAKkt3r16lS5lStX5twEgHMxwCjM0NBQYmb6\n" +
            "9OkRmgDUhgcffDBV7tFHH825CQDnYoBRmMOHDydmJk6cGKEJQO3o6upKzIyOjoZdu3blXwaA/8MA\n" +
            "ozBpPgM2derUCE0Aakdvb2+q3O23355vEQD+XwYYhfnb3/6WmLnssssiNAGoHZ2dneGiiy5KzJ08\n" +
            "edIj6QEKYIBRmDSPoTfAAM7fww8/nCr3iU98IucmAPwnA4zCvPrqq4mZt956K0ITgNry+c9/PjQ0\n" +
            "NCTmnn/++QhtAPh3BhiFGRwcTMw8+eSTEZoA1J6lS5emyi1fvjznJgD8u4ZKpVIpugT1Z+/evWHR\n" +
            "okWJufe85z3htddei9AIoPakuQVrbGwMIyMjEdoAEIIbMAry61//OlXuyJEjOTcBqF2zZs1KzIyO\n" +
            "joYdO3ZEaANACG7AKMhVV10VnnnmmVRZL1GA8enr60s1wi6++OLw9ttvR2gEgBswCvHiiy+mzvb3\n" +
            "9+fYBKB2dXV1hdbW1sTcsWPHPJIeIBIDjEKcOnUqdfYHP/hBjk0Aatv27dtT5VasWJFzEwBC8BZE\n" +
            "CpLmg+HvmD17dnjhhRdybANQ2yZMmBCGh4cTc34lAMifGzCiGxgYOK/8yy+/nFMTgPqwZs2aVLll\n" +
            "y5bl3AQAN2BEt3v37nDTTTed1894mQJcmMbGxsS/S5uamlLdlAEwfm7AiO7pp58+75/ZuXNn9kUA\n" +
            "6sjVV1+dmBkZGUn9mTEAxscNGNF97nOfC48++uh5/czll18eXnnllZwaAdS+gYGB0NHRkZi79NJL\n" +
            "wxtvvBGhEUB9cgNGdIcPHz7vn3n11VdzaAJQP9rb28P73ve+xNybb74Z+vr68i8EUKcMMKIbGhoa\n" +
            "18/t27cv4yYA9SXt13p85jOfybkJQP3yFkSi6+7uHtfnwGbMmOEmDOACtbW1hcHBwTEzjY2NYWRk\n" +
            "JFIjgPriBozoxvsf9ddeey3jJgD15957703MjI6OhrvvvjtCG4D64waM6D74wQ+GPXv2jOtnP/Wp\n" +
            "T4Uf/ehHGTcCqC9pvph50qRJ4cSJE5EaAdQPN2BEl+YpXOfy4x//OMMmAPXpxhtvTMwMDQ2F3t7e\n" +
            "/MsA1BkDjOg6Ozsv6Od9OBzgwmzbti1Vbs2aNTk3Aag/3oJIdPv27Qs9PT0XdIaXLcCFmT9/fnjq\n" +
            "qacSc0ePHg3t7e0RGgHUBzdgRLdw4cILPqO7uzuDJgD165FHHkmVW7FiRc5NAOqLGzAK0dDQcMFn\n" +
            "9PX1XfDbGQHq2SWXXBKOHDkyZqahoSGMjo5GagRQ+9yAUYimpqYLPuOKK67IoAlA/Vq7dm1iplKp\n" +
            "hJtuuilCG4D64AaMQsycOTO88sorF3zOPffcE77xjW9k0AigPjU2NiZ+rtYtGEB23IBRiFtvvTWT\n" +
            "czZt2pTJOQD1asGCBYmZSqUSVq1aFaENQO1zA0ZhsvgcWAghtLW1hWPHjmVyFkC9GRgYSPX9jG7B\n" +
            "ALLhBozCNDc3Z3LO8ePHw9e//vVMzgKoN+3t7akeM1+pVMJ9990XoRFAbXMDRmFWrlyZ+jHIaXgp\n" +
            "A4zP3r17w6JFixJzLS0t4fTp0xEaAdQuA4zCpH3bS1rTp08Pr7/+embnAdSTSZMmhZMnTybmvv/9\n" +
            "72f2OV6AeuQtiBSmvb09TJ48ObPz/vnPf4bHHnsss/MA6smuXbtS5Xbu3JlvEYAaZ4BRqB07dmR6\n" +
            "3nXXXZfpeQD1Yvny5aGtrS0xNzg4GKENQO0ywCjU8uXLM/lS5ndUKpXw4Q9/OLPzAOrJ3r17EzNp\n" +
            "HtgBwLkZYBQu6y9Sfvzxx0N/f3+mZwLUg+7u7jB79uwxM0uXLo3UBqA2eQgHpdDY2JjpUwxbW1vD\n" +
            "8ePHMzsPoF709fWFD3zgA2FoaOj//Nm8efPCgQMHCmgFUDvcgFEKGzZsyPS8wcHBsHv37kzPBKgH\n" +
            "XV1d4dlnnw2LFy/+X//7ypUrQ29vbzGlAGqIGzBKo6mpKYyOjmZ2XmNjYxgZGcnsPIB6dODAgTB/\n" +
            "/vyiawDUDDdglMb27dszPW90dDSsWbMm0zMB6o3xBZAtN2CUysSJE8Pp06czO6+hoSHTWzUAALgQ\n" +
            "bsAolSeeeCLT8yqVSli2bFmmZwIAwHi5AaN0ZsyYEf7xj39kdp5bMAAAysINGKVz6NChTM+rVCrh\n" +
            "s5/9bKZnAgDAeLgBo5R6enrCvn37MjvPLRgAAGXgBoxS2rt3b6bnVSqVcMstt2R6JgAAnC8DjNK6\n" +
            "8847Mz1v165dmZ4HAADnywCjtB566KHQ1NSU2Xmjo6Nh06ZNmZ0HAADnywCj1B566KFMz9u4cWOm\n" +
            "5wEAwPnwEA5Kb8KECWF4eDiz837/+9+HRYsWZXYeAACk5QaM0vve976X6XnXXXddpucBAEBabsCo\n" +
            "Ci0tLeHs2bOZndfc3Bx+97vfhZ6enszOBACAJG7AqAoPPPBApucNDw+HRYsWhe3bt2d6LgAAjMUN\n" +
            "GFWjqakply9T/u53vxtWrVqV+bkAAPCf3IBRNdatW5fLuatXrw59fX25nA0AAP/ODRhVY2BgIHR0\n" +
            "dORy9pw5c8Lzzz+fy9kAAPAON2BUjfb29rBw4cJczv773/8etm7dmsvZAADwDjdgVJW+vr4wa9as\n" +
            "XM6eP39+ePLJJ3M5GwAAQjDAqEJ5PYwjhBD83wEAgDx5CyJVZ8aMGbmd3dvbm9vZAABggFF1li1b\n" +
            "ltvZBw4cyO1sAAAwwKg6H/nIR4quAAAA42KAUXWuv/760NDQkMvZ8+fPz+VcAAAIwQCjSq1atSqX\n" +
            "cz/0oQ/lci4AAITgKYhUsdbW1nDixInMzps3b57PgAEAkCs3YFStZ599NtPzbr755kzPAwCA/2SA\n" +
            "UbU6OzvD1q1bMzlr8eLFYe3atZmcBQAA5+ItiFS9zZs3h7vvvnvcPz9lypRw4MCB0NXVlV0pAAD4\n" +
            "f7gBo+qtX78+PPzww+N6MuKUKVPCz3/+c+MLAIAo3IBRM/r6+sKSJUvCiy++mCq/ePHisHPnTuML\n" +
            "AIBo3IBRM7q6usLhw4fDli1bwrx5886ZmzdvXtiyZUvo7e01vgAAiMoNGDWtt7f3fx4tP3/+fN/z\n" +
            "BQBAoQwwAACASLwFEQAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAA\n" +
            "IBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBID\n" +
            "DAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAA\n" +
            "IBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBID\n" +
            "DAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAA\n" +
            "IBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBID\n" +
            "DAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAA\n" +
            "IBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBID\n" +
            "DAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAA\n" +
            "IBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBID\n" +
            "DAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAAIBIDDAAA\n" +
            "IBIDDAAAIBIDDAAAIJL/AuJvavDm4zkdAAAAAElFTkSuQmCC\n";

    public static PDFTO generatePDF(String TAG, Object data, Context context) {
        sessionManager = SessionManager.getInstance(context);
        if (TAG.equals(ArriboCabotajeActivity.TAG)) {
            AvisoArriboCabotageTO data1 = (AvisoArriboCabotageTO) data;
            return generatePdfCabotaje(context, data1);
        } else {
            if (TAG.equals(ArriboInternacionalActivity.TAG)) {
                AvisoArriboInternacionalTO data1 = (AvisoArriboInternacionalTO) data;
                return generatePdfInternacional(context, data1);
            }
        }
        return null;
    }

    private static PDFTO generatePdfCabotaje(Context context, AvisoArriboCabotageTO avisoArriboCabotageTO) {
        PdfConstants pdfConstants = new PdfConstants();
        pdfConstants.setCMP_CAPITANIA(sessionManager.getUser().getIdLogin());
        pdfConstants.setCMP_N_AVISO(String.valueOf(avisoArriboCabotageTO.getNumeroAvisoArribo()));
        pdfConstants.setCMP_FECHA_ARRIBO(avisoArriboCabotageTO.getArriboAdminCabotageTO().getFechaCreacion());
        pdfConstants.setCMP_OMI(avisoArriboCabotageTO.getOmiMatricula());
        pdfConstants.setCMP_NOMBRE_NAVE(avisoArriboCabotageTO.getNombreNave());
        pdfConstants.setCMP_NOMBRE_CAPITAN(avisoArriboCabotageTO.getResponsable());
        pdfConstants.setCMP_BANDERA(avisoArriboCabotageTO.getPais());
        pdfConstants.setCMP_AB(String.valueOf(avisoArriboCabotageTO.getTrb()));
        pdfConstants.setCMP_AN(String.valueOf(avisoArriboCabotageTO.getTrn()));
        pdfConstants.setCMP_AGENCIA_MARITIMA(avisoArriboCabotageTO.getArriboOperCabotageTO().getRazonArribo());
        pdfConstants.setCMP_NOMBRE_CAPITAN(avisoArriboCabotageTO.getResponsable());
        pdfConstants.setCMP_CALADO_MAXIMO(String.valueOf(avisoArriboCabotageTO.getArriboOperCabotageTO().getCalado()));
        pdfConstants.setCMP_PAIS_PROCEDENCIA_DESTINO(avisoArriboCabotageTO.getPais());
        pdfConstants.setCMP_PUERTO_PROCEDENCIA_DESTINO(avisoArriboCabotageTO.getNombrePuertoDestino());
        pdfConstants.setCMP_INSTALACION_PORTUARIA(avisoArriboCabotageTO.getMuelleOrigen());
        pdfConstants.setCMP_N_PASAJEROS(String.valueOf(avisoArriboCabotageTO.getCantidadPasajeros()));
        pdfConstants.setCMP_CLASE_CARGA(avisoArriboCabotageTO.getTipoCarga());
        pdfConstants.setCMP_TN(String.valueOf(avisoArriboCabotageTO.getCantidadCarga()));

        pdfConstants.setCMP_OBSERVACIONES(avisoArriboCabotageTO.getArriboAdminCabotageTO().getObservaciones());//Movimiento admin
        for (int i = 0; i < avisoArriboCabotageTO.getSignatureTOS().size(); i++) {
            avisoArriboCabotageTO.getSignatureTOS().get(i).getRepresentantTO().getId();
            switch (avisoArriboCabotageTO.getSignatureTOS().get(i).getRepresentantTO().getId()) {

                case "1":
                    pdfConstants.setCMP_SIG_AGENCIA(avisoArriboCabotageTO.getSignatureTOS().get(i).getFirmaFuncionario());
                    break;
                case "2":
                    pdfConstants.setCMP_SIG_CAPITANIA(avisoArriboCabotageTO.getSignatureTOS().get(i).getFirmaFuncionario());
                    break;
                case "3":
                    pdfConstants.setCMP_SIG_AUTORIDAD_SANITARIA(avisoArriboCabotageTO.getSignatureTOS().get(i).getFirmaFuncionario());
                    break;
                case "4":
                    //DAS
                    break;
                case "5":
                    pdfConstants.setCMP_SIG_ICA(avisoArriboCabotageTO.getSignatureTOS().get(i).getFirmaFuncionario());
                    break;
               /*
                pdfConstants.setCMP_SIG_CAPITAN_BUQUE();
                pdfConstants.setCMP_SIG_AUTORIDAD_MIGRATORIA();*/
            }
        }
        return generatePDF(context, pdfConstants);
    }


    private static PDFTO generatePdfInternacional(Context context, AvisoArriboInternacionalTO avisoArriboInternacionalTO) {
        PdfConstants pdfConstants = new PdfConstants();
        pdfConstants.setCMP_CAPITANIA(sessionManager.getUser().getIdLogin());
        pdfConstants.setCMP_N_AVISO(String.valueOf(avisoArriboInternacionalTO.getNumeroAvisoArribo()));
        pdfConstants.setCMP_FECHA_ARRIBO(avisoArriboInternacionalTO.getFecha());
        pdfConstants.setCMP_FECHA_VISITA(avisoArriboInternacionalTO.getArriboAdminInternacionalTO().getFechaHoraVisita());
        pdfConstants.setCMP_OMI(avisoArriboInternacionalTO.getOmiMatricula());
        pdfConstants.setCMP_NOMBRE_NAVE(avisoArriboInternacionalTO.getNombreNave());
        pdfConstants.setCMP_BANDERA(avisoArriboInternacionalTO.getPaisBandera());
        pdfConstants.setCMP_AB(String.valueOf(avisoArriboInternacionalTO.getTrb()));
        pdfConstants.setCMP_AN(String.valueOf(avisoArriboInternacionalTO.getTrn()));
        pdfConstants.setCMP_AGENCIA_MARITIMA(avisoArriboInternacionalTO.getNitAgencia());
        pdfConstants.setCMP_NOMBRE_CAPITAN(avisoArriboInternacionalTO.getNombreCapitanNave());
        pdfConstants.setCMP_CALADO_POPA(String.valueOf(avisoArriboInternacionalTO.getArriboOperInternacionalTO().getCaladoPopa()));
        pdfConstants.setCMP_CALADO_PROA(String.valueOf(avisoArriboInternacionalTO.getArriboOperInternacionalTO().getCaladoProa()));
        pdfConstants.setCMP_PAIS_PROCEDENCIA_DESTINO(avisoArriboInternacionalTO.getPais());
        pdfConstants.setCMP_PUERTO_PROCEDENCIA_DESTINO(avisoArriboInternacionalTO.getNombrePuertoDestino());
        pdfConstants.setCMP_INSTALACION_PORTUARIA(avisoArriboInternacionalTO.getArriboOperInternacionalTO().getIdInstalacionportuaria());
        pdfConstants.setCMP_N_TRIPULANTES(String.valueOf(avisoArriboInternacionalTO.getTripulacionArribo()));
        pdfConstants.setCMP_N_PASAJEROS(String.valueOf(avisoArriboInternacionalTO.getCantidadPasajeros()));
        pdfConstants.setCMP_CLASE_CARGA(avisoArriboInternacionalTO.getTipoCarga());
        pdfConstants.setCMP_TN(String.valueOf(avisoArriboInternacionalTO.getCantidadCarga()));
        pdfConstants.setCMP_DESECHOS(String.valueOf(avisoArriboInternacionalTO.isDesechosPuerto()));
        pdfConstants.setCMP_OBSERVACIONES(avisoArriboInternacionalTO.getArriboAdminInternacionalTO().getObservaciones());//Movimiento admin

        for (int i = 0; i < avisoArriboInternacionalTO.getSignatureTOS().size(); i++) {
            avisoArriboInternacionalTO.getSignatureTOS().get(i).getRepresentantTO().getId();
            switch (avisoArriboInternacionalTO.getSignatureTOS().get(i).getRepresentantTO().getId()) {

                case "1":
                    pdfConstants.setCMP_SIG_AGENCIA(avisoArriboInternacionalTO.getSignatureTOS().get(i).getFirmaFuncionario());
                    break;
                case "2":
                    pdfConstants.setCMP_SIG_CAPITANIA(avisoArriboInternacionalTO.getSignatureTOS().get(i).getFirmaFuncionario());
                    break;
                case "3":
                    pdfConstants.setCMP_SIG_AUTORIDAD_SANITARIA(avisoArriboInternacionalTO.getSignatureTOS().get(i).getFirmaFuncionario());
                    break;
                case "4":
                    //DAS
                    break;
                case "5":
                    pdfConstants.setCMP_SIG_ICA(avisoArriboInternacionalTO.getSignatureTOS().get(i).getFirmaFuncionario());
                    break;
               /*
                pdfConstants.setCMP_SIG_CAPITAN_BUQUE();
                pdfConstants.setCMP_SIG_AUTORIDAD_MIGRATORIA();*/
            }
        }

        return generatePDF(context, pdfConstants);

    }

    private static PDFTO generatePDF(Context context, PdfConstants pdfConstants) {
        PDFTO pdfto = null;
        try {
            String pathDownloadPDF = generatePath(pdfConstants.getCMP_N_AVISO() != null ? pdfConstants.getCMP_N_AVISO() : "");
            InputStream inputStream = readTemplate(context);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            PdfReader reader = new PdfReader(inputStream);
            PdfStamper stamp = new PdfStamper(reader, baos);
            //READ FIELS
            AcroFields acroFields = stamp.getAcroFields();
            if (acroFields.getFields() != null) {
                if (pdfConstants.getCMP_CAPITANIA() != null) {
                    acroFields.setField(CMP_CAPITANIA.name(), pdfConstants.getCMP_CAPITANIA());
                }
                if (pdfConstants.getCMP_N_AVISO() != null) {
                    acroFields.setField(CMP_N_AVISO.name(), pdfConstants.getCMP_N_AVISO());
                }
                if (pdfConstants.getCMP_FECHA_ARRIBO() != null) {
                    acroFields.setField(CMP_FECHA_ARRIBO.name(), pdfConstants.getCMP_FECHA_ARRIBO());
                }
                if (pdfConstants.getCMP_FECHA_VISITA() != null) {
                    acroFields.setField(CMP_FECHA_VISITA.name(), pdfConstants.getCMP_FECHA_VISITA());
                }
                if (pdfConstants.getCMP_OMI() != null) {
                    acroFields.setField(CMP_OMI.name(), pdfConstants.getCMP_OMI());
                }
                if (pdfConstants.getCMP_NOMBRE_NAVE() != null) {
                    acroFields.setField(CMP_NOMBRE_NAVE.name(), pdfConstants.getCMP_NOMBRE_NAVE());
                }
                if (pdfConstants.getCMP_BANDERA() != null) {
                    acroFields.setField(CMP_BANDERA.name(), pdfConstants.getCMP_BANDERA());
                }
                if (pdfConstants.getCMP_AB() != null) {
                    acroFields.setField(CMP_AB.name(), pdfConstants.getCMP_AB());
                }
                if (pdfConstants.getCMP_AN() != null) {
                    acroFields.setField(CMP_AN.name(), pdfConstants.getCMP_AN());
                }
                if (pdfConstants.getCMP_AGENCIA_MARITIMA() != null) {
                    acroFields.setField(CMP_AGENCIA_MARITIMA.name(), pdfConstants.getCMP_AGENCIA_MARITIMA());
                }
                if (pdfConstants.getCMP_NOMBRE_CAPITAN() != null) {
                    acroFields.setField(CMP_NOMBRE_CAPITAN.name(), pdfConstants.getCMP_NOMBRE_CAPITAN());
                }
                if (pdfConstants.getCMP_CALADO_POPA() != null) {
                    acroFields.setField(CMP_CALADO_POPA.name(), pdfConstants.getCMP_CALADO_POPA());
                }
                if (pdfConstants.getCMP_CALADO_PROA() != null) {
                    acroFields.setField(CMP_CALADO_PROA.name(), pdfConstants.getCMP_CALADO_PROA());
                }
                if (pdfConstants.getCMP_CALADO_MAXIMO() != null) {
                    acroFields.setField(CMP_CALADO_MAXIMO.name(), pdfConstants.getCMP_CALADO_MAXIMO());
                }
                if (pdfConstants.getCMP_PAIS_PROCEDENCIA_DESTINO() != null) {
                    acroFields.setField(CMP_PAIS_PROCEDENCIA_DESTINO.name(), pdfConstants.getCMP_PAIS_PROCEDENCIA_DESTINO());
                }
                if (pdfConstants.getCMP_PUERTO_PROCEDENCIA_DESTINO() != null) {
                    acroFields.setField(CMP_PUERTO_PROCEDENCIA_DESTINO.name(), pdfConstants.getCMP_PUERTO_PROCEDENCIA_DESTINO());
                }
                if (pdfConstants.getCMP_INSTALACION_PORTUARIA() != null) {
                    acroFields.setField(CMP_INSTALACION_PORTUARIA.name(), pdfConstants.getCMP_INSTALACION_PORTUARIA());
                }
                if (pdfConstants.getCMP_N_TRIPULANTES() != null) {
                    acroFields.setField(CMP_N_TRIPULANTES.name(), pdfConstants.getCMP_N_TRIPULANTES());
                }
                if (pdfConstants.getCMP_N_PASAJEROS() != null) {
                    acroFields.setField(CMP_N_PASAJEROS.name(), pdfConstants.getCMP_N_PASAJEROS());
                }
                if (pdfConstants.getCMP_CLASE_CARGA() != null) {
                    acroFields.setField(CMP_CLASE_CARGA.name(), pdfConstants.getCMP_CLASE_CARGA());
                }
                if (pdfConstants.getCMP_TN() != null) {
                    acroFields.setField(CMP_TN.name(), pdfConstants.getCMP_TN());
                }

                if (pdfConstants.getCMP_DESECHOS() != null && Boolean.getBoolean(pdfConstants.getCMP_DESECHOS())) {
                    acroFields.setField(CMP_DESECHOS_SI.name(), "X");
                } else {
                    acroFields.setField(CMP_DESECHOS_NO.name(), "X");

                }
                if (pdfConstants.getCMP_OBSERVACIONES() != null) {
                    acroFields.setField(CMP_OBSERVACIONES.name(), pdfConstants.getCMP_OBSERVACIONES());
                }

                //READ FIELS IMAGE
                if (pdfConstants.getCMP_SIG_CAPITANIA() != null) {
                    acroFields.replacePushbuttonField(CMP_SIG_CAPITANIA.name(), generateImgField(acroFields, CMP_SIG_CAPITANIA.name(), pdfConstants.getCMP_SIG_CAPITANIA()));
                }
                if (pdfConstants.getCMP_SIG_CAPITAN_BUQUE() != null) {
                    acroFields.replacePushbuttonField(CMP_SIG_CAPITAN_BUQUE.name(), generateImgField(acroFields, CMP_SIG_CAPITAN_BUQUE.name(), pdfConstants.getCMP_SIG_CAPITAN_BUQUE()));
                }
                if (pdfConstants.getCMP_SIG_AUTORIDAD_MIGRATORIA() != null) {
                    acroFields.replacePushbuttonField(CMP_SIG_AUTORIDAD_MIGRATORIA.name(), generateImgField(acroFields, CMP_SIG_AUTORIDAD_MIGRATORIA.name(), pdfConstants.getCMP_SIG_AUTORIDAD_MIGRATORIA()));
                }
                if (pdfConstants.getCMP_SIG_AUTORIDAD_SANITARIA() != null) {
                    acroFields.replacePushbuttonField(CMP_SIG_AUTORIDAD_SANITARIA.name(), generateImgField(acroFields, CMP_SIG_AUTORIDAD_SANITARIA.name(), pdfConstants.getCMP_SIG_AUTORIDAD_SANITARIA()));
                }
                if (pdfConstants.getCMP_SIG_ICA() != null) {
                    acroFields.replacePushbuttonField(CMP_SIG_ICA.name(), generateImgField(acroFields, CMP_SIG_ICA.name(), pdfConstants.getCMP_SIG_ICA()));
                }
                if (pdfConstants.getCMP_SIG_AGENCIA() != null) {
                    acroFields.replacePushbuttonField(CMP_SIG_AGENCIA.name(), generateImgField(acroFields, CMP_SIG_AGENCIA.name(), pdfConstants.getCMP_SIG_AGENCIA()));
                }
                stamp.setFormFlattening(true);
                stamp.close();
                inputStream.close();
                reader.close();

                File externalStoragePublicDirectory = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), DIR_NAME_VOA);

                FileOutputStream fileOutputStream = new FileOutputStream(externalStoragePublicDirectory + "/" + pathDownloadPDF);
                fileOutputStream.write(baos.toByteArray());
                fileOutputStream.close();
                fileOutputStream.flush();

                String archivoSerializado = context.getString(R.string.not_found_voa_pdf, pdfConstants.getCMP_N_AVISO());
                //archivoSerializado=BitmapSerializableUtil.byteToString(baos.toByteArray());
                archivoSerializado = externalStoragePublicDirectory + "/" + pathDownloadPDF;
                pdfto = generateTO(pathDownloadPDF, archivoSerializado, pdfConstants.getCMP_N_AVISO());

            }
        } catch (IOException e) {
            e.printStackTrace();
            pdfto = null;
            Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error en generatePDF");
        } catch (Exception e) {
            pdfto = null;
            e.printStackTrace();
            Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error en generatePDF");
        }
        return pdfto;
    }

    private static PdfFormField generateImgField(AcroFields acroFields, String fieldName, String fieldContent) {
        try {
            PushbuttonField pushbuttonField = acroFields.getNewPushbuttonFromField(fieldName);
            pushbuttonField.setLayout(PushbuttonField.LAYOUT_ICON_ONLY);
            pushbuttonField.setProportionalIcon(true);
            byte[] bytes = BitmapSerializableUtil.stringToByte(fieldContent == null ? imageSerializable : fieldContent);
            pushbuttonField.setImage(Image.getInstance(bytes));
            return pushbuttonField.getField();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error en generateImgField");
            return null;
        }
    }

    private static String generatePath(String aviso) {
        File externalStoragePublicDirectory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), DIR_NAME_VOA);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        return DIR_NAME_VOA + new Date().getTime() + aviso + PDF_EXTENSION;
    }

    private static InputStream readTemplate(Context context) {
        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        try {
            in = assetManager.open(PDF_TEMPLATE);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error en readTemplate");
        }
        return in;
    }

    public static void getPDF(Context context, PDFTO pdfto) {
        InputStream inputStream = null;
        String archivoSerializado = context.getString(R.string.not_found_voa_pdf, String.valueOf(pdfto.getNumeroAvisoArribo()));
        try {
            inputStream = new FileInputStream(pdfto.getArchivo());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfReader reader = new PdfReader(inputStream);
            PdfStamper stamp = new PdfStamper(reader, baos);
            stamp.setFormFlattening(true);
            stamp.close();
            inputStream.close();
            reader.close();

            archivoSerializado = BitmapSerializableUtil.byteToString(baos.toByteArray());
            if (!TextUtils.isEmpty(archivoSerializado)) {
                pdfto.setArchivo(archivoSerializado);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error en getPDF");
        }
        pdfto.setArchivo(archivoSerializado);
    }

    private static PDFTO generateTO(String nombre, String archivo, String numeroAviso) {
        PDFTO pdfto = new PDFTO();
        pdfto.setIdPDF(Long.parseLong(numeroAviso));
        pdfto.setNombreArchivo(nombre);
        pdfto.setArchivo(archivo);
        pdfto.setNumeroAvisoArribo(Long.parseLong(numeroAviso));
        return pdfto;
    }

}
