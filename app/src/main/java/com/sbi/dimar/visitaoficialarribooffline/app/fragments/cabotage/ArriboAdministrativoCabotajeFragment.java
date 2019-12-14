package com.sbi.dimar.visitaoficialarribooffline.app.fragments.cabotage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.satsuware.usefulviews.LabelledSpinner;
import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboCabotajeActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.adapter.SignatureAdapter;
import com.sbi.dimar.visitaoficialarribooffline.app.adapter.ValorDominioAdapter;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RepresentantTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.AvisoFragmentInteractionListener;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.DisplayToastUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.signature.SignatureListener;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.signature.SignaturePickerUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Jenny Galindo
 * <p>
 * The ArriboAdministrativoCabotajeFragment class represents the information of the administrative movement,
 * this information must be filled out by the user
 * <p>
 * A simple {@link Fragment} subclass.
 * Use the {@link ArriboAdministrativoCabotajeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArriboAdministrativoCabotajeFragment extends Fragment {
    public static final String TAG = ArriboAdministrativoCabotajeFragment.class.getName();
    private static final String ARG_PARAM_ADMIN_CAB = "ARG_PARAM_ADMIN_CAB";
    private static AvisoFragmentInteractionListener mListener;
    private SlideDateTimePicker buildSlideDateTimePicker;
    private InformationTO informationTO;
    private AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO arriboAdministrativoCabotageTO;
    private LabelledSpinner spnRepresentante;
    private EditText txtFechaHoraLibrePlatica, txtFechaHoraVisita, txtObservaciones;
    private RadioButton radBtnVisitada;
    private ImageButton btnAddSignature;
    private List<SignatureTO> signatureTOS;
    private List<RepresentantTO> representantTOS;
    private SignatureTO signatureSelected;
    private RecyclerView recyclerViewSigns;

    public ArriboAdministrativoCabotajeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArriboAdministrativoCabotajeFragment.
     */
    public static ArriboAdministrativoCabotajeFragment newInstance(InformationTO informationTO) {
        ArriboAdministrativoCabotajeFragment fragment = new ArriboAdministrativoCabotajeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ADMIN_CAB, informationTO);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * This event fires 2nd, before views are created for the fragment
     * The onCreate method is called when the Fragment instance is being created, or re-created.
     * Use onCreate for any standard setup that does not require the activity to be fully created
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            informationTO = (InformationTO) getArguments().getSerializable(ARG_PARAM_ADMIN_CAB);
        }
    }

    /**
     * The onCreateView method is called when Fragment should create its View object hierarchy,
     * either dynamically or via XML layout inflation.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_arribo_administrativo_cabotaje, container, false);
        initLayout(inflate);
        initCore();
        updateDataAvtivity();
        return inflate;
    }

    private void initLayout(View inflate) {
        txtFechaHoraLibrePlatica = inflate.findViewById(R.id.txtFechaHoraLibrePlatica);
        txtFechaHoraVisita = inflate.findViewById(R.id.txtFechaHoraVisita);
        radBtnVisitada = inflate.findViewById(R.id.radBtnVisitada);
        txtObservaciones = inflate.findViewById(R.id.txtObservaciones);
        spnRepresentante = inflate.findViewById(R.id.spnRepresentante);
        btnAddSignature = inflate.findViewById(R.id.btnAddSignature);
        btnAddSignature.setEnabled(false);
        recyclerViewSigns = inflate.findViewById(R.id.recycler_view_signs);
        recyclerViewSigns.setHasFixedSize(true);
        recyclerViewSigns.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSigns.setItemAnimator(new DefaultItemAnimator());

    }

    private void initCore() {
        buildSlideDateTimePicker = ArriboCabotajeActivity.getSlideDateTimePicker();
        arriboAdministrativoCabotageTO = informationTO.getAvisoArriboCabotageTO().getArriboAdminCabotageTO();
        representantTOS = informationTO.getRepresentantTOS();
        signatureTOS = informationTO.getAvisoArriboCabotageTO().getSignatureTOS();
        if (informationTO.getAvisoArriboCabotageTO().getIdEstado() == EstadoEntity.ESTADO_VISITADO.getValue()) {
            disableForm();
        }
        setDateTimeField();
        setSpinners();
        setText();
        addSignature();
        showSignatures();
    }

    private void disableForm() {
        txtFechaHoraLibrePlatica.setEnabled(false);
        txtFechaHoraVisita.setEnabled(false);
        radBtnVisitada.setEnabled(false);
        txtObservaciones.setEnabled(false);
        spnRepresentante.getSpinner().setEnabled(false);
        btnAddSignature.setEnabled(false);
    }

    private void validateForm() {
        if (TextUtils.isEmpty(txtFechaHoraLibrePlatica.getText())) {
            txtFechaHoraLibrePlatica.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtFechaHoraVisita.getText())) {
            txtFechaHoraVisita.setError(getString(R.string.obligatory_field));
        }
        if (!radBtnVisitada.isChecked()) {
            radBtnVisitada.setError(getString(R.string.obligatory_field));
        }
        if (signatureTOS == null || signatureTOS.isEmpty()) {
            new DisplayToastUtil(getActivity(), getString(R.string.signatures_error)).run();
        }
    }


    private void setText() {
        radBtnVisitada.setChecked(arriboAdministrativoCabotageTO.isVisitada());
        radBtnVisitada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                arriboAdministrativoCabotageTO.setVisitada(isChecked);
            }
        });

        if (!TextUtils.isEmpty(arriboAdministrativoCabotageTO.getObservaciones())) {
            txtObservaciones.setText(arriboAdministrativoCabotageTO.getObservaciones());
        }
        txtObservaciones.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arriboAdministrativoCabotageTO.setObservaciones(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setDateTimeField() {
        if (!TextUtils.isEmpty(arriboAdministrativoCabotageTO.getFechaLibrePlatica())) {
            txtFechaHoraLibrePlatica.setText(arriboAdministrativoCabotageTO.getFechaLibrePlatica());
        }
        txtFechaHoraLibrePlatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraLibrePlatica.setText(ArriboCabotajeActivity.getDateFormatter().format(date));
                        arriboAdministrativoCabotageTO.setFechaLibrePlatica(txtFechaHoraLibrePlatica.getText().toString());
                    }
                });
                buildSlideDateTimePicker.show();
            }
        });
        if (!TextUtils.isEmpty(arriboAdministrativoCabotageTO.getFechaVisita())) {
            txtFechaHoraVisita.setText(arriboAdministrativoCabotageTO.getFechaVisita());
        }
        txtFechaHoraVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraVisita.setText(ArriboCabotajeActivity.getDateFormatter().format(date));
                        arriboAdministrativoCabotageTO.setFechaVisita(txtFechaHoraVisita.getText().toString());
                    }
                });
                buildSlideDateTimePicker.show();
            }
        });
    }

    private void setSpinners() {
        if(representantTOS!=null){
            spnRepresentante.setCustomAdapter(new ValorDominioAdapter(getActivity(), android.R.layout.simple_list_item_1, representantTOS));
            spnRepresentante.setOnItemChosenListener(new LabelledSpinner.OnItemChosenListener() {
                @Override
                public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {
                    RepresentantTO itemAtPosition = (RepresentantTO) adapterView.getItemAtPosition(position);
                    if (itemAtPosition != null) {
                        boolean isSelected = false;
                        for (int i = 0; i < signatureTOS.size(); i++) {
                            if (signatureTOS.get(i).getRepresentantTO().getId().equals(itemAtPosition.getId())) {
                                if (signatureSelected == null) {
                                    signatureSelected = new SignatureTO();
                                }
                                signatureSelected.setRepresentantTO(itemAtPosition);
                                isSelected = true;
                                break;
                            }
                        }
                        if (!isSelected) {
                            signatureSelected = new SignatureTO();
                            signatureSelected.setNumeroAvisoArribo(informationTO.getAvisoArriboCabotageTO().getNumeroAvisoArribo());
                            signatureSelected.setRepresentantTO(itemAtPosition);
                        }
                    }
                    btnAddSignature.setEnabled(true);
                }

                @Override
                public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {
                    btnAddSignature.setEnabled(false);
                }
            });
        }

        /*//Si ya existe una seleccion
        if (ArriboCabotajeActivity.getAvisoArriboInternacionalTO().getArriboAdminInternacionalTO().get != null
                && TextUtils.isEmpty(ArriboCabotajeActivity.getAvisoArriboInternacionalTO().getArriboOperInternacionalTO().getIdPilotoPractico())) {
            for (int i = 0; i < representantTOS.size(); i++) {
                if (ArriboCabotajeActivity.getAvisoArriboInternacionalTO().getArriboOperInternacionalTO().getIdPilotoPractico()
                        .equals(representantTOS.get(i).getId())) {
                    spnRepresentante.setSelection(i);
                    break;
                }
            }
        }*/

    }

    private void addSignature() {
        btnAddSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signatureSelected != null && signatureSelected.getRepresentantTO() != null) {
                    SignaturePickerUtil buildSignature = new SignaturePickerUtil.Builder(getActivity().getSupportFragmentManager())
                            .setSignatureTO(signatureSelected)
                            .build();

                    buildSignature.setListener(new SignatureListener() {
                        @Override
                        public void onSignatureSet(SignatureTO signatureTO) {
                            updateSignatures(signatureTO);
                        }
                    });
                    buildSignature.show();
                } else {
                    new DisplayToastUtil(getActivity(), "debes seleccionar representae");
                }
            }
        });
    }

    private void showSignatures() {
        SignatureAdapter signatureAdapter = new SignatureAdapter(signatureTOS, informationTO.getAvisoArriboCabotageTO().getIdEstado());
        recyclerViewSigns.setAdapter(signatureAdapter);
        signatureAdapter.notifyDataSetChanged();
    }

    private void updateSignatures(SignatureTO signatureTO) {
        boolean isSelected = false;
        if (signatureTOS.isEmpty()) {
            signatureTOS.add(signatureTO);
            isSelected = true;
        }
        for (int i = 0; i < signatureTOS.size(); i++) {
            if (signatureTOS.get(i).getRepresentantTO().getId().equals(signatureTO.getRepresentantTO().getId())) {
                signatureTOS.remove(i);
                signatureTOS.add(signatureTO);
                isSelected = true;
                break;
            }
        }
        if (!isSelected) {
            signatureTOS.add(signatureTO);
        }
        showSignatures();

    }

    public void updateDataAvtivity() {
        validateForm();
        if (mListener != null) {
            informationTO.getAvisoArriboCabotageTO().setSignatureTOS(signatureTOS);
            informationTO.getAvisoArriboCabotageTO().setArriboAdminCabotageTO(arriboAdministrativoCabotageTO);
            mListener.onFragmentData(TAG, informationTO.getAvisoArriboCabotageTO());
        }
    }

    /**
     * This event fires 1st, before creation of fragment or any views
     * The onAttach method is called when the Fragment instance is associated with an Activity.
     * This does not mean the Activity is fully initialized.
     */
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

    /**
     * This method is called when the fragment is no longer connected to the Activity
     * Any references saved in onAttach should be nulled out here to prevent memory leaks.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
