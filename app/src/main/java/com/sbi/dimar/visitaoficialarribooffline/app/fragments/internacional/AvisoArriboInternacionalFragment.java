package com.sbi.dimar.visitaoficialarribooffline.app.fragments.internacional;

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
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboInternacionalActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;

/**
 * Created by Jenny Galindo
 * <p>
 * The class AvisoArriboInternacionalFragment represents the information of the arrival notice, this information is shown as an informative only.
 * <p>
 * A simple {@link Fragment} subclass.
 * Use the {@link AvisoArriboInternacionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvisoArriboInternacionalFragment extends Fragment {
    private static final String TAG = AvisoArriboInternacionalFragment.class.getName();
    private static final String ARG_PARAM_INT = "ARG_PARAM_INT";
    private EditText txtNumAviso, txtOmiMatricula, txtEta, txtFecha, txtNitAgencia, txtNombreNave, txtNombrePuertoDestino, txtPuertoProcedencia;
    private InformationTO informationTO;

    public AvisoArriboInternacionalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AvisoArriboInternacionalFragment.
     */
    public static AvisoArriboInternacionalFragment newInstance(InformationTO informationTO) {
        AvisoArriboInternacionalFragment fragment = new AvisoArriboInternacionalFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_INT, informationTO);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            informationTO = (InformationTO) getArguments().getSerializable(ARG_PARAM_INT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_aviso_internacional, container, false);
        initLayout(inflate);
        initCore();
        return inflate;
    }

    private void initLayout(View inflate) {
        txtNumAviso = inflate.findViewById(R.id.txtNumAviso);
        txtOmiMatricula = inflate.findViewById(R.id.txtOmiMatricula);
        txtNombreNave = inflate.findViewById(R.id.txtNombreNave);
        txtNitAgencia = inflate.findViewById(R.id.txtNitAgencia);
        txtPuertoProcedencia = inflate.findViewById(R.id.txtPuertoProcedencia);
        txtNombrePuertoDestino = inflate.findViewById(R.id.txtNombrePuertoDestino);
        txtFecha = inflate.findViewById(R.id.txtFecha);
        txtEta = inflate.findViewById(R.id.txtEta);
    }

    private void initCore() {
        AvisoArriboInternacionalTO avisoArriboInternacionalTO = informationTO.getAvisoArriboInternacionalTO();
        txtNumAviso.setText(String.valueOf(avisoArriboInternacionalTO.getNumeroAvisoArribo()));
        txtOmiMatricula.setText(avisoArriboInternacionalTO.getOmiMatricula());
        txtNombreNave.setText(avisoArriboInternacionalTO.getNombreNave());
        txtNitAgencia.setText(avisoArriboInternacionalTO.getRazonSocial());
        txtPuertoProcedencia.setText(avisoArriboInternacionalTO.getPais().concat("-").concat(avisoArriboInternacionalTO.getPuertoProcedencia()));
        txtNombrePuertoDestino.setText(avisoArriboInternacionalTO.getNombrePuertoDestino());
        if (!TextUtils.isEmpty(avisoArriboInternacionalTO.getFecha()) && !avisoArriboInternacionalTO.getFecha().equals("null")) {
            try {
                txtFecha.setText(ArriboInternacionalActivity.getDateFormatter().format(ArriboInternacionalActivity.getDateFormatter().parse(avisoArriboInternacionalTO.getFecha())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }
        if (!TextUtils.isEmpty(avisoArriboInternacionalTO.getEta()) && !avisoArriboInternacionalTO.getEta().equals("null")) {
            try {
                txtEta.setText(ArriboInternacionalActivity.getDateFormatter().format(ArriboInternacionalActivity.getDateFormatter().parse(avisoArriboInternacionalTO.getEta())));
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
