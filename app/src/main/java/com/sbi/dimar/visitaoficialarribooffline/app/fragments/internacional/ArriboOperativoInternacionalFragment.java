package com.sbi.dimar.visitaoficialarribooffline.app.fragments.internacional;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.satsuware.usefulviews.LabelledSpinner;
import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboInternacionalActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.adapter.ValorDominioAdapter;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotTransportBoatTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotoAsistenteTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PortInstallationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PracticalPilotTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TrainingPilotTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.AvisoFragmentInteractionListener;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.DecimalDigitsInputFilter;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.MultiSelectionSpinnerPilotosAsistentes;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.MultiSelectionSpinnerRemolcadores;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jenny Galindo
 * <p>
 * The class ArriboOperativoInternacionalFragment represents the information of the operational movement,
 * this information may come pre-filled or not and may be modified by the user
 * <p>
 * A simple {@link Fragment} subclass.
 * Use the {@link ArriboOperativoInternacionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArriboOperativoInternacionalFragment extends Fragment {
    public static final String TAG = ArriboOperativoInternacionalFragment.class.getName();
    private static final String ARG_PARAM_INT = "ARG_PARAM_INT";
    private static AvisoFragmentInteractionListener mListener;
    private AvisoArriboInternacionalTO.ArriboOperativoInternacionalTO arriboOperativoInternacionalTO;
//    private LabelledSpinner spnPilotoPractico, spnPilotoEntrenamiento, spnInstalacionPortuaria, spnLanchaTransportePiloto, spnPilotoAtraque, spnPilotoEntrenamientoAtraque;
    private LabelledSpinner spnInstalacionPortuaria, spnLanchaTransportePiloto, spnPilotoAtraque, spnPilotoEntrenamientoAtraque;
    private MultiSelectionSpinnerRemolcadores spnRemolcador;
    private MultiSelectionSpinnerPilotosAsistentes spnPilotosAsistentes;
    private TextView txtPilotosAsistentesSeleccionados;
    private EditText txtIntCaladoPopa, txtIntCaladoProa, txtFechaHoraAreaControl, txtFechaHoraArribo, txtFechaHoraFinManiobraAtraque, txtFechaHoraInicioManiobraAtraque, txtFechaHoraPilotoAbordoReporte, titlePilotosAsistentes;
    private SlideDateTimePicker buildSlideDateTimePicker;
    private InformationTO informationTO;


    public ArriboOperativoInternacionalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArriboOperativoInternacionalFragment.
     */
    public static ArriboOperativoInternacionalFragment newInstance(InformationTO informationTO) {
        ArriboOperativoInternacionalFragment fragment = new ArriboOperativoInternacionalFragment();
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
        View inflate = inflater.inflate(R.layout.fragment_arribo_operativo_internacional, container, false);
        initLayout(inflate);
        initCore();
        updateDataAvtivity();
        return inflate;
    }

    private void initLayout(View inflate) {
        txtFechaHoraAreaControl = inflate.findViewById(R.id.txtFechaHoraAreaControl);
        txtFechaHoraArribo = inflate.findViewById(R.id.txtFechaHoraArribo);
//        spnPilotoPractico = inflate.findViewById(R.id.spnPilotoPractico);
//        spnPilotoEntrenamiento = inflate.findViewById(R.id.spnPilotoEntrenamiento);
        txtFechaHoraPilotoAbordoReporte = inflate.findViewById(R.id.txtFechaHoraPilotoAbordoReporte);
        txtIntCaladoPopa = inflate.findViewById(R.id.txtIntCaladoPopa);
//        txtIntCaladoPopa.addTextChangedListener(new MaskWatcher("###.##"));
        txtIntCaladoPopa.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,2)});
        txtIntCaladoProa = inflate.findViewById(R.id.txtIntCaladoProa);
//        txtIntCaladoProa.addTextChangedListener(new MaskWatcher("###.##"));
        txtIntCaladoProa.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,2)});
        spnLanchaTransportePiloto = inflate.findViewById(R.id.spnLanchaTransportePiloto);

        txtFechaHoraInicioManiobraAtraque = inflate.findViewById(R.id.txtFechaHoraInicioManiobraAtraque);
        txtFechaHoraFinManiobraAtraque = inflate.findViewById(R.id.txtFechaHoraFinManiobraAtraque);
        spnPilotoAtraque = inflate.findViewById(R.id.spnPilotoAtraque);
        spnPilotoEntrenamientoAtraque = inflate.findViewById(R.id.spnPilotoEntrenamientoAtraque);
        spnInstalacionPortuaria = inflate.findViewById(R.id.spnInstalacionPortuaria);

        spnRemolcador = inflate.findViewById(R.id.spnRemolcador);

        SessionManager sessionManager = SessionManager.getInstance(getContext());
        spnPilotosAsistentes = inflate.findViewById(R.id.spnPilotosAsistentes);
        txtPilotosAsistentesSeleccionados = inflate.findViewById(R.id.txtPilotosAsistentesSeleccionados);
        titlePilotosAsistentes = inflate.findViewById(R.id.titlePilotosAsistentes);
        if(!sessionManager.getUser().getNombreCapitania().equals("BUENAVENTURA")){
            spnPilotosAsistentes.setVisibility(View.GONE);
            txtPilotosAsistentesSeleccionados.setVisibility(View.GONE);
            titlePilotosAsistentes.setVisibility(View.GONE);
        }
    }

    private void initCore() {
        buildSlideDateTimePicker = ArriboInternacionalActivity.getSlideDateTimePicker();
        arriboOperativoInternacionalTO = informationTO.getAvisoArriboInternacionalTO().getArriboOperInternacionalTO();
        if (informationTO.getAvisoArriboInternacionalTO().getIdEstado() == EstadoEntity.ESTADO_VISITADO.getValue()) {
            disableForm();
        }
        setDateTimeField();
        setSpinners();
        setText();
    }

    private void disableForm() {
        txtFechaHoraAreaControl.setEnabled(false);
        txtFechaHoraArribo.setEnabled(false);
//        spnPilotoPractico.getSpinner().setEnabled(false);
//        spnPilotoEntrenamiento.getSpinner().setEnabled(false);
        txtFechaHoraPilotoAbordoReporte.setEnabled(false);
        txtIntCaladoPopa.setEnabled(false);
        txtIntCaladoProa.setEnabled(false);
        spnLanchaTransportePiloto.getSpinner().setEnabled(false);
        txtFechaHoraInicioManiobraAtraque.setEnabled(false);
        txtFechaHoraFinManiobraAtraque.setEnabled(false);
        spnPilotoAtraque.getSpinner().setEnabled(false);
        spnPilotoEntrenamientoAtraque.getSpinner().setEnabled(false);
        spnInstalacionPortuaria.getSpinner().setEnabled(false);
        spnRemolcador.setEnabled(false);
        spnPilotosAsistentes.setEnabled(false);
        txtPilotosAsistentesSeleccionados.setEnabled(false);
    }


    private void validateForm() {
        if (TextUtils.isEmpty(txtFechaHoraAreaControl.getText())) {
            txtFechaHoraAreaControl.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtFechaHoraArribo.getText())) {
            txtFechaHoraArribo.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtFechaHoraPilotoAbordoReporte.getText())) {
            txtFechaHoraPilotoAbordoReporte.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtIntCaladoPopa.getText())) {
            txtIntCaladoPopa.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtIntCaladoProa.getText())) {
            txtIntCaladoProa.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtFechaHoraInicioManiobraAtraque.getText())) {
            txtFechaHoraInicioManiobraAtraque.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtFechaHoraFinManiobraAtraque.getText())) {
            txtFechaHoraFinManiobraAtraque.setError(getString(R.string.obligatory_field));
        }

        PracticalPilotTO practicalPilotTO1 = (PracticalPilotTO) spnPilotoAtraque.getSpinner().getSelectedItem();
        if (practicalPilotTO1 == null || TextUtils.isEmpty(practicalPilotTO1.getIdPiloto())) {
            spnPilotoAtraque.setDefaultErrorText(getString(R.string.obligatory_field));
        }
        PortInstallationTO portInstallationTO = (PortInstallationTO) spnInstalacionPortuaria.getSpinner().getSelectedItem();
        if (portInstallationTO == null || TextUtils.isEmpty(portInstallationTO.getCodigo())) {
            spnInstalacionPortuaria.setDefaultErrorText(getString(R.string.obligatory_field));
        }
        /*
         new DisplayToastUtil(getActivity(), getString(R.string.signatures_error)).run();
         */
    }

    private void setText() {
        if (arriboOperativoInternacionalTO.getCaladoPopa() != 0) {
            txtIntCaladoPopa.setText(String.valueOf(arriboOperativoInternacionalTO.getCaladoPopa()));
        }
        txtIntCaladoPopa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    arriboOperativoInternacionalTO.setCaladoPopa(Double.valueOf(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        if (arriboOperativoInternacionalTO.getCaladoProa() != 0) {
            txtIntCaladoProa.setText(String.valueOf(arriboOperativoInternacionalTO.getCaladoProa()));
        }
        txtIntCaladoProa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    arriboOperativoInternacionalTO.setCaladoProa(Double.valueOf(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setSpinners() {
        if(informationTO.getPortInstallationTOS()!=null){
            spnInstalacionPortuaria.setCustomAdapter(new ValorDominioAdapter(getActivity(), android.R.layout.simple_list_item_1, informationTO.getPortInstallationTOS()));
            //Si ya existe una seleccion
            if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getIdInstalacionportuaria())) {
                for (int i = 0; i < informationTO.getPortInstallationTOS().size(); i++) {
                    if (arriboOperativoInternacionalTO.getIdInstalacionportuaria()
                            .equals(informationTO.getPortInstallationTOS().get(i).getCodigo())) {
                        spnInstalacionPortuaria.setSelection(i);
                        break;
                    }
                }
            }
        }

        if(informationTO.getPilotTransportBoatTOS()!=null){
            spnLanchaTransportePiloto.setCustomAdapter(new ValorDominioAdapter(getActivity(), android.R.layout.simple_list_item_1, informationTO.getPilotTransportBoatTOS()));
            //Si ya existe una seleccion
            if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getIdLanchaTransportePiloto())) {
                for (int i = 0; i < informationTO.getPilotTransportBoatTOS().size(); i++) {
                    if (arriboOperativoInternacionalTO.getIdLanchaTransportePiloto()
                            .equals(informationTO.getPilotTransportBoatTOS().get(i).getOmiMatricula())) {
                        spnLanchaTransportePiloto.setSelection(i);
                        break;
                    }
                }
            }
        }

        if(informationTO.getPracticalPilotTOS()!=null){
            spnPilotoAtraque.setCustomAdapter(new ValorDominioAdapter(getActivity(), android.R.layout.simple_list_item_1, informationTO.getPracticalPilotTOS()));
            //Si ya existe una seleccion
            if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getIdPilotoAtraque())) {
                for (int i = 0; i < informationTO.getPracticalPilotTOS().size(); i++) {
                    if (arriboOperativoInternacionalTO.getIdPilotoAtraque()
                            .equals(informationTO.getPracticalPilotTOS().get(i).getIdPiloto())) {
                        spnPilotoAtraque.setSelection(i);
                        break;
                    }
                }
            }
        }




        if (informationTO.getTrainingPilotTOS() != null) {

            spnPilotoEntrenamientoAtraque.setCustomAdapter(new ValorDominioAdapter(getActivity(), android.R.layout.simple_list_item_1, informationTO.getTrainingPilotTOS()));
            //Si ya existe una seleccion
            if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getIdPilotoEntrenamientoAtraque())) {
                for (int i = 0; i < informationTO.getTrainingPilotTOS().size(); i++) {
                    if (arriboOperativoInternacionalTO.getIdPilotoEntrenamientoAtraque()
                            .equals(informationTO.getTrainingPilotTOS().get(i).getIdPiloto())) {
                        spnPilotoEntrenamientoAtraque.setSelection(i);
                        break;
                    }
                }
            }
        }

        ArrayList<TugboatTO> remolcadores = new ArrayList<TugboatTO>(informationTO.getTugboatTOS());
        remolcadores.add(0,new TugboatTO("Seleccione...","Seleccione..."));
        spnRemolcador.setItems(remolcadores);

        ArrayList<TugboatTO> selectedRemolcadores = new ArrayList<>();
        selectedRemolcadores.add(new TugboatTO("Seleccione...","Seleccione...") );
        spnRemolcador.setSelection(selectedRemolcadores);

        ArrayList<PracticalPilotTO> pilotosAsistentes = new ArrayList<PracticalPilotTO>(informationTO.getPracticalAsistentsPilotTOS());
        spnPilotosAsistentes.setItems(pilotosAsistentes);

        ArrayList<PilotoAsistenteTO> selectedPilotosAsistentes = new ArrayList<>();
        spnPilotosAsistentes.setSelection(selectedPilotosAsistentes);


        if(informationTO.getAvisoArriboInternacionalTO().getRemolcadoresTO()!=null){
            spnRemolcador.setSelectionRemolcadores(informationTO.getAvisoArriboInternacionalTO().getRemolcadoresTO());
        }

        if(informationTO.getAvisoArriboInternacionalTO().getPilotosAsistentesTO()!=null){
            spnPilotosAsistentes.setSelectionPilotosAsistentes(informationTO.getAvisoArriboInternacionalTO().getPilotosAsistentesTO());
            //Change the size
            txtPilotosAsistentesSeleccionados.setText(spnPilotosAsistentes.getSelectedItems().toString());
        }



        spnInstalacionPortuaria.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                PortInstallationTO itemAtPosition = (PortInstallationTO) adapterView.getItemAtPosition(position);
                arriboOperativoInternacionalTO.setIdInstalacionportuaria(itemAtPosition.getCodigo());
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                spnInstalacionPortuaria.setDefaultErrorText(getString(R.string.obligatory_field));
            }
        });


        spnLanchaTransportePiloto.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                PilotTransportBoatTO itemAtPosition = (PilotTransportBoatTO) adapterView.getItemAtPosition(position);
                arriboOperativoInternacionalTO.setIdLanchaTransportePiloto(itemAtPosition.getOmiMatricula());
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                spnLanchaTransportePiloto.setDefaultErrorText(getString(R.string.obligatory_field));
            }
        });


        spnRemolcador.clickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                informationTO.getAvisoArriboInternacionalTO().setRemolcadoresTO(spnRemolcador.getSelectedRemolcadores(informationTO.getAvisoArriboInternacionalTO().getNumeroAvisoArribo()));

            }
        });

        spnPilotosAsistentes.clickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                informationTO.getAvisoArriboInternacionalTO().setPilotosAsistentesTO(spnPilotosAsistentes.getSelectedPilotosAsistentes(informationTO.getAvisoArriboInternacionalTO().getNumeroAvisoArribo()));

            }
        });

        spnPilotoAtraque.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                PracticalPilotTO itemAtPosition = (PracticalPilotTO) adapterView.getItemAtPosition(position);
                arriboOperativoInternacionalTO.setIdPilotoAtraque(itemAtPosition.getIdPiloto());
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                spnPilotoAtraque.setDefaultErrorText(getString(R.string.obligatory_field));
            }
        });


        spnPilotoEntrenamientoAtraque.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
            @Override
            public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                TrainingPilotTO itemAtPosition = (TrainingPilotTO) adapterView.getItemAtPosition(position);
                arriboOperativoInternacionalTO.setIdPilotoEntrenamientoAtraque(itemAtPosition.getIdPiloto());
                informationTO.getAvisoArriboInternacionalTO().getArriboOperInternacionalTO().setIdPilotoEntrenamientoAtraque(itemAtPosition.getIdPiloto());
            }

            @Override
            public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
            }
        });

    }

    private void setDateTimeField() {
        if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getFechaIngresoAreaControl())
                && !arriboOperativoInternacionalTO.getFechaIngresoAreaControl().equals("null")) {
            try {
                txtFechaHoraAreaControl.setText(ArriboInternacionalActivity.getDateFormatter().format(ArriboInternacionalActivity.getDateFormatter().parse(arriboOperativoInternacionalTO.getFechaIngresoAreaControl())));
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
                        txtFechaHoraAreaControl.setText(ArriboInternacionalActivity.getDateFormatter().format(date));
                        arriboOperativoInternacionalTO.setFechaIngresoAreaControl(txtFechaHoraAreaControl.getText().toString());
                    }
                });
                buildSlideDateTimePicker.show();
            }
        });


        if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getFechaHoraArribo())
                && !arriboOperativoInternacionalTO.getFechaHoraArribo().equals("null")) {
            try {
                txtFechaHoraArribo.setText(ArriboInternacionalActivity.getDateFormatter().format(ArriboInternacionalActivity.getDateFormatter().parse(arriboOperativoInternacionalTO.getFechaHoraArribo())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }
        txtFechaHoraArribo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraArribo.setText(ArriboInternacionalActivity.getDateFormatter().format(date));
                        arriboOperativoInternacionalTO.setFechaHoraArribo(txtFechaHoraArribo.getText().toString());

                    }
                });
                buildSlideDateTimePicker.show();
            }
        });
        if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getFechaHoraInicioManiobraAtraque())
                && !arriboOperativoInternacionalTO.getFechaHoraInicioManiobraAtraque().equals("null")) {
            try {
                txtFechaHoraInicioManiobraAtraque.setText(ArriboInternacionalActivity.getDateFormatter().format(ArriboInternacionalActivity.getDateFormatter().parse(arriboOperativoInternacionalTO.getFechaHoraInicioManiobraAtraque())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }
        txtFechaHoraInicioManiobraAtraque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraInicioManiobraAtraque.setText(ArriboInternacionalActivity.getDateFormatter().format(date));
                        arriboOperativoInternacionalTO.setFechaHoraArribo(txtFechaHoraArribo.getText().toString());
                        arriboOperativoInternacionalTO.setFechaHoraInicioManiobraAtraque(txtFechaHoraInicioManiobraAtraque.getText().toString());
                    }
                });
                buildSlideDateTimePicker.show();
            }
        });

        if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getFechaHoraFinManiobraAtraque())
                && !arriboOperativoInternacionalTO.getFechaHoraFinManiobraAtraque().equals("null")) {
            try {
                txtFechaHoraFinManiobraAtraque.setText(ArriboInternacionalActivity.getDateFormatter().format(ArriboInternacionalActivity.getDateFormatter().parse(arriboOperativoInternacionalTO.getFechaHoraFinManiobraAtraque())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }
        txtFechaHoraFinManiobraAtraque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraFinManiobraAtraque.setText(ArriboInternacionalActivity.getDateFormatter().format(date));
                        arriboOperativoInternacionalTO.setFechaHoraFinManiobraAtraque(txtFechaHoraFinManiobraAtraque.getText().toString());

                    }
                });
                buildSlideDateTimePicker.show();
            }
        });
        if (!TextUtils.isEmpty(arriboOperativoInternacionalTO.getFechaHoraPilotoAbordoReporte())
                && !arriboOperativoInternacionalTO.getFechaHoraPilotoAbordoReporte().equals("null")) {
            try {
                txtFechaHoraPilotoAbordoReporte.setText(ArriboInternacionalActivity.getDateFormatter().format(ArriboInternacionalActivity.getDateFormatter().parse(arriboOperativoInternacionalTO.getFechaHoraPilotoAbordoReporte())));
            } catch (Exception e) {
                Log.e(TAG, "Format String date");
            }
        }
        txtFechaHoraPilotoAbordoReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraPilotoAbordoReporte.setText(ArriboInternacionalActivity.getDateFormatter().format(date));
                        arriboOperativoInternacionalTO.setFechaHoraPilotoAbordoReporte(txtFechaHoraPilotoAbordoReporte.getText().toString());

                    }
                });
                buildSlideDateTimePicker.show();
            }
        });

    }

    public void updateDataAvtivity() {
        validateForm();
        if (mListener != null) {
            mListener.onFragmentData(TAG, arriboOperativoInternacionalTO);
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
    public int dpToPx(int dp) {
        float density = getContext().getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

}
