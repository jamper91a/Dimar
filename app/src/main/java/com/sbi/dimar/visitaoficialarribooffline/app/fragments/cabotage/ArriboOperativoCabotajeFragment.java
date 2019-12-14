package com.sbi.dimar.visitaoficialarribooffline.app.fragments.cabotage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.satsuware.usefulviews.LabelledSpinner;
import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboCabotajeActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.adapter.ValorDominioAdapter;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PortInstallationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.ReasonArrivalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.AvisoFragmentInteractionListener;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.DecimalDigitsInputFilter;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.MaskWatcher;

import java.util.Date;

/**
 * Created by Jenny Galindo
 * <p>
 * The class ArriboOperativoCabotajeFragment represents the information of the operational movement,
 * this information may come pre-filled or not and may be modified by the user
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link ArriboOperativoCabotajeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArriboOperativoCabotajeFragment extends Fragment {
    public static final String TAG = ArriboOperativoCabotajeFragment.class.getName();
    private static final String ARG_PARAM_CAB = "ARG_PARAM_CAB";
    private static AvisoFragmentInteractionListener mListener;
    private AvisoArriboCabotageTO.ArriboOperativoCabotageTO arriboOperativoCabotageTO;
    private LabelledSpinner spnRazonArribo, spnInstalacionPortuaria;
    private EditText txtFechaHoraAreaControl, txtFechaHoraAtraque, txtCaladoMaximo, txtObservaciones;
    private SlideDateTimePicker buildSlideDateTimePicker;
    private InformationTO informationTO;

    public ArriboOperativoCabotajeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArriboOperativoCabotajeFragment.
     */
    public static ArriboOperativoCabotajeFragment newInstance(InformationTO informationTO) {
        ArriboOperativoCabotajeFragment fragment = new ArriboOperativoCabotajeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CAB, informationTO);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            informationTO = (InformationTO) getArguments().getSerializable(ARG_PARAM_CAB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_arribo_operativo_cabotaje, container, false);
        initLayout(inflate);
        initCore();
        updateDataAvtivity();
        return inflate;
    }

    private void initLayout(View inflate) {
        txtFechaHoraAreaControl = inflate.findViewById(R.id.txtFechaHoraAreaControl);
        txtFechaHoraAtraque = inflate.findViewById(R.id.txtFechaHoraAtraque);
        txtCaladoMaximo = inflate.findViewById(R.id.txtCaladoMaximo);
//        txtCaladoMaximo.addTextChangedListener(new MaskWatcher("###.##"));
        txtCaladoMaximo.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,2)});
        txtObservaciones = inflate.findViewById(R.id.txtObservaciones);
        spnRazonArribo = inflate.findViewById(R.id.spnRazonArribo);
        spnInstalacionPortuaria = inflate.findViewById(R.id.spnInstalacionPortuaria);
    }

    private void initCore() {
        buildSlideDateTimePicker = ArriboCabotajeActivity.getSlideDateTimePicker();
        arriboOperativoCabotageTO = informationTO.getAvisoArriboCabotageTO().getArriboOperCabotageTO();
        if (informationTO.getAvisoArriboCabotageTO().getIdEstado() == EstadoEntity.ESTADO_VISITADO.getValue()) {
            disableForm();
        }
        setDateTimeField();
        setSpinners();
        setText();
    }

    private void disableForm() {
        txtFechaHoraAreaControl.setEnabled(false);
        txtFechaHoraAtraque.setEnabled(false);
        txtCaladoMaximo.setEnabled(false);
        txtObservaciones.setEnabled(false);
        spnRazonArribo.getSpinner().setEnabled(false);
        spnInstalacionPortuaria.getSpinner().setEnabled(false);
    }

    private void validateForm() {
        if (TextUtils.isEmpty(txtFechaHoraAreaControl.getText())) {
            txtFechaHoraAreaControl.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtFechaHoraAtraque.getText())) {
            txtFechaHoraAtraque.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtCaladoMaximo.getText())) {
            txtCaladoMaximo.setError(getString(R.string.obligatory_field));
        }
    }

    private void setText() {
        if (arriboOperativoCabotageTO.getCalado() != 0) {
            txtCaladoMaximo.setText(String.valueOf(arriboOperativoCabotageTO.getCalado()));
        }
        txtCaladoMaximo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    arriboOperativoCabotageTO.setCalado(Double.valueOf(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        if (arriboOperativoCabotageTO.getObservaciones() != null && !arriboOperativoCabotageTO.getObservaciones().equals("")) {
            txtObservaciones.setText(String.valueOf(arriboOperativoCabotageTO.getObservaciones()));
        }
        txtObservaciones.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    arriboOperativoCabotageTO.setObservaciones(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setSpinners() {
        if(informationTO!=null){
            spnInstalacionPortuaria.setCustomAdapter(new ValorDominioAdapter(getActivity(), android.R.layout.simple_list_item_1, informationTO.getPortInstallationTOS()));
            //Si ya existe una seleccion
            if (!TextUtils.isEmpty(arriboOperativoCabotageTO.getMuelleAtraque())) {
                for (int i = 0; i < informationTO.getPortInstallationTOS().size(); i++) {
                    if (arriboOperativoCabotageTO.getMuelleAtraque()
                            .equals(informationTO.getPortInstallationTOS().get(i).getCodigo())) {
                        spnInstalacionPortuaria.setSelection(i);
                        break;
                    }
                }
            }
        }

        if(informationTO.getReasonArrivalTOS()!=null){
            spnRazonArribo.setCustomAdapter(new ValorDominioAdapter(getActivity(), android.R.layout.simple_list_item_1, informationTO.getReasonArrivalTOS()));
            //Si ya existe una seleccion
            if (!TextUtils.isEmpty(arriboOperativoCabotageTO.getRazonArribo())) {
                for (int i = 0; i < informationTO.getReasonArrivalTOS().size(); i++) {
                    if (arriboOperativoCabotageTO.getRazonArribo().equals(informationTO.getReasonArrivalTOS().get(i).getCodigo())) {
                        spnRazonArribo.setSelection(i);
                        break;
                    }
                }
            }
        }


        //LISTENERS
        spnInstalacionPortuaria.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                PortInstallationTO itemAtPosition = (PortInstallationTO) adapterView.getItemAtPosition(position);
                arriboOperativoCabotageTO.setMuelleAtraque(itemAtPosition.getCodigo());
                arriboOperativoCabotageTO.setNombreMuelleAtraque(itemAtPosition.getMuelle());
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                spnInstalacionPortuaria.setDefaultErrorText(getString(R.string.obligatory_field));
            }
        });

        spnRazonArribo.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                ReasonArrivalTO itemAtPosition = (ReasonArrivalTO) adapterView.getItemAtPosition(position);
                arriboOperativoCabotageTO.setIdRazonArribo(0);
                arriboOperativoCabotageTO.setRazonArribo(itemAtPosition.getDescripcion());
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                spnRazonArribo.setDefaultErrorText(getString(R.string.obligatory_field));
            }
        });

    }

    private void setDateTimeField() {
        if (!TextUtils.isEmpty(arriboOperativoCabotageTO.getFechaIngresoAreaControl())
                && !arriboOperativoCabotageTO.getFechaIngresoAreaControl().equals("null")) {
            try {
                txtFechaHoraAreaControl.setText(ArriboCabotajeActivity.getDateFormatter().format(ArriboCabotajeActivity.getDateFormatter().parse(arriboOperativoCabotageTO.getFechaIngresoAreaControl())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }
        txtFechaHoraAreaControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraAreaControl.setText(ArriboCabotajeActivity.getDateFormatter().format(date));
                        arriboOperativoCabotageTO.setFechaIngresoAreaControl(txtFechaHoraAreaControl.getText().toString());
                    }
                });
                buildSlideDateTimePicker.show();
            }
        });

        if (!TextUtils.isEmpty(arriboOperativoCabotageTO.getFechaAtraque())
                && !arriboOperativoCabotageTO.getFechaAtraque().equals("null")) {
            try {
                txtFechaHoraAtraque.setText(ArriboCabotajeActivity.getDateFormatter().format(ArriboCabotajeActivity.getDateFormatter().parse(arriboOperativoCabotageTO.getFechaAtraque())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }

        txtFechaHoraAtraque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraAtraque.setText(ArriboCabotajeActivity.getDateFormatter().format(date));
                        arriboOperativoCabotageTO.setFechaAtraque(txtFechaHoraAtraque.getText().toString());
                    }
                });
                buildSlideDateTimePicker.show();
            }
        });
    }


    public void updateDataAvtivity() {
        validateForm();
        if (mListener != null) {
            mListener.onFragmentData(TAG, arriboOperativoCabotageTO);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AvisoFragmentInteractionListener) {
            mListener = (AvisoFragmentInteractionListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AvisoFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
