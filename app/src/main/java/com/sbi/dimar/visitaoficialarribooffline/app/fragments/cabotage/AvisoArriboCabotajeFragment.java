package com.sbi.dimar.visitaoficialarribooffline.app.fragments.cabotage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboCabotajeActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;

/**
 * Created by Jenny Galindo
 * <p>
 * The class NoticeArriboCabotajeFragment represents the information of the arrival notice, this information is shown as an informative only.
 * <p>
 * A simple {@link Fragment} subclass.
 * Use the {@link AvisoArriboCabotajeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvisoArriboCabotajeFragment extends Fragment {
    private static final String TAG = AvisoArriboCabotajeFragment.class.getSimpleName();
    private static final String ARG_PARAM_NAL = "ARG_PARAM_NAL";
    private InformationTO informationTO;
    private EditText txtNumAviso, txtOmiMatricula, txtEta,
            txtFecha, txtRazonSocial, txtNombreNave,
            txtNombrePuertoDestino,
            txtPuertoProcedencia;


    public AvisoArriboCabotajeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AvisoArriboCabotajeFragment.
     */
    public static AvisoArriboCabotajeFragment newInstance(InformationTO informationTO) {
        AvisoArriboCabotajeFragment fragment = new AvisoArriboCabotajeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_NAL, informationTO);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            informationTO = (InformationTO) getArguments().getSerializable(ARG_PARAM_NAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_aviso_cabotaje, container, false);
        initLayout(inflate);
        initCore();
        return inflate;
    }

    private void initLayout(View inflate) {
        txtNumAviso = inflate.findViewById(R.id.txtNumAviso);
        txtOmiMatricula = inflate.findViewById(R.id.txtOmiMatricula);
        txtNombreNave = inflate.findViewById(R.id.txtNombreNave);
        txtRazonSocial = inflate.findViewById(R.id.txtRazonSocial);
        txtPuertoProcedencia = inflate.findViewById(R.id.txtPuertoProcedencia);
        txtNombrePuertoDestino = inflate.findViewById(R.id.txtNombrePuertoDestino);
        txtFecha = inflate.findViewById(R.id.txtFecha);
        txtEta = inflate.findViewById(R.id.txtEta);
    }

    private void initCore() {
        AvisoArriboCabotageTO avisoArriboCabotageTO = informationTO.getAvisoArriboCabotageTO();
        txtNumAviso.setText(String.valueOf(avisoArriboCabotageTO.getNumeroAvisoArribo()));
        txtOmiMatricula.setText(avisoArriboCabotageTO.getOmiMatricula());
        txtNombreNave.setText(avisoArriboCabotageTO.getNombreNave());
        txtRazonSocial.setText(avisoArriboCabotageTO.getResponsable());
        txtPuertoProcedencia.setText(avisoArriboCabotageTO.getMuelleOrigen());
        txtNombrePuertoDestino.setText(avisoArriboCabotageTO.getNombrePuertoDestino());
        if (!TextUtils.isEmpty(avisoArriboCabotageTO.getFecha())
                && !avisoArriboCabotageTO.getFecha().equals("null")) {
            try {
                txtFecha.setText(ArriboCabotajeActivity.getDateFormatter().format(ArriboCabotajeActivity.getDateFormatter().parse(avisoArriboCabotageTO.getFecha())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }
        if (!TextUtils.isEmpty(avisoArriboCabotageTO.getEta())
                && !avisoArriboCabotageTO.getEta().equals("null")) {
            try {
                txtEta.setText(ArriboCabotajeActivity.getDateFormatter().format(ArriboCabotajeActivity.getDateFormatter().parse(avisoArriboCabotageTO.getEta())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
