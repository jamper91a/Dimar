package com.sbi.dimar.visitaoficialarribooffline.app.fragments.internacional;

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
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.satsuware.usefulviews.LabelledSpinner;
import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboInternacionalActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.adapter.SignatureAdapter;
import com.sbi.dimar.visitaoficialarribooffline.app.adapter.ValorDominioAdapter;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
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
 * The ArriboAdministrativoInternacionalFragment class represents the information of the administrative movement,
 * this information must be filled out by the user
 * <p>
 * A simple {@link Fragment} subclass.
 * Use the {@link ArriboAdministrativoInternacionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArriboAdministrativoInternacionalFragment extends Fragment {
    public static final String TAG = ArriboAdministrativoInternacionalFragment.class.getName();
    private static final String ARG_PARAM_ADMIN_INT = "ARG_PARAM_ADMIN_INT";
    private InformationTO informationTO;
    private AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO arriboAdministrativoInternacionalTO;
    private static AvisoFragmentInteractionListener mListener;
    private SlideDateTimePicker buildSlideDateTimePicker;
    private LabelledSpinner spnRepresentante;
    private EditText txtFechaHoraLibrePlatica,
            txtNumeroPasajerosTransito,
            txtObservaciones;
    private ImageButton btnAddSignature;
    private List<SignatureTO> signatureTOS;
    private List<RepresentantTO> representantTOS;
    private SignatureTO signatureSelected;
    private RecyclerView recyclerViewSigns;

    public ArriboAdministrativoInternacionalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArriboAdministrativoInternacionalFragment.
     */
    public static ArriboAdministrativoInternacionalFragment newInstance(InformationTO informationTO) {
        ArriboAdministrativoInternacionalFragment fragment = new ArriboAdministrativoInternacionalFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ADMIN_INT, informationTO);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            informationTO = (InformationTO) getArguments().getSerializable(ARG_PARAM_ADMIN_INT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_arribo_administrativo_internacional, container, false);
        initLayout(inflate);
        initCore();
        updateDataAvtivity();
        return inflate;
    }

    private void initLayout(View inflate) {
        txtFechaHoraLibrePlatica = inflate.findViewById(R.id.txtFechaHoraLibrePlatica);
        txtNumeroPasajerosTransito = inflate.findViewById(R.id.txtNumeroPasajerosTransito);
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
        buildSlideDateTimePicker = ArriboInternacionalActivity.getSlideDateTimePicker();
        arriboAdministrativoInternacionalTO = informationTO.getAvisoArriboInternacionalTO().getArriboAdminInternacionalTO();
        representantTOS = informationTO.getRepresentantTOS();
        signatureTOS = informationTO.getAvisoArriboInternacionalTO().getSignatureTOS();
        if (informationTO.getAvisoArriboInternacionalTO().getIdEstado() == EstadoEntity.ESTADO_VISITADO.getValue()) {
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
        txtNumeroPasajerosTransito.setEnabled(false);
        txtObservaciones.setEnabled(false);
        spnRepresentante.getSpinner().setEnabled(false);
        btnAddSignature.setVisibility(View.GONE);
    }

    private void validateForm() {
        if (TextUtils.isEmpty(txtFechaHoraLibrePlatica.getText())) {
            txtFechaHoraLibrePlatica.setError(getString(R.string.obligatory_field));
        }
        if (signatureTOS == null || signatureTOS.isEmpty()) {
            new DisplayToastUtil(getActivity(), getString(R.string.signatures_error)).run();
        }
    }

    private void setText() {
        if (arriboAdministrativoInternacionalTO.getNumeroPasajerosTrancito() != 0) {
            txtNumeroPasajerosTransito.setText(String.valueOf(arriboAdministrativoInternacionalTO.getNumeroPasajerosTrancito()));
        }
        txtNumeroPasajerosTransito.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    arriboAdministrativoInternacionalTO.setNumeroPasajerosTrancito(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (!TextUtils.isEmpty(arriboAdministrativoInternacionalTO.getObservaciones())) {
            txtObservaciones.setText(String.valueOf(arriboAdministrativoInternacionalTO.getObservaciones()));
        }
        txtObservaciones.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arriboAdministrativoInternacionalTO.setObservaciones(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setDateTimeField() {
        if (!TextUtils.isEmpty(arriboAdministrativoInternacionalTO.getFechaHoraLibrePlatica())) {
            txtFechaHoraLibrePlatica.setText(String.valueOf(arriboAdministrativoInternacionalTO.getFechaHoraLibrePlatica()));
        }
        txtFechaHoraLibrePlatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSlideDateTimePicker.setListener(new SlideDateTimeListener() {
                    @Override
                    public void onDateTimeSet(Date date) {
                        txtFechaHoraLibrePlatica.setText(ArriboInternacionalActivity.getDateFormatter().format(date));
                        arriboAdministrativoInternacionalTO.setFechaHoraLibrePlatica(txtFechaHoraLibrePlatica.getText().toString());
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
                            signatureSelected.setNumeroAvisoArribo(informationTO.getAvisoArriboInternacionalTO().getNumeroAvisoArribo());
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
        if (ArriboInternacionalActivity.getAvisoArriboInternacionalTO().getArriboAdminInternacionalTO().get != null
                && TextUtils.isEmpty(ArriboInternacionalActivity.getAvisoArriboInternacionalTO().getArriboOperInternacionalTO().getIdPilotoPractico())) {
            for (int i = 0; i < representantTOS.size(); i++) {
                if (ArriboInternacionalActivity.getAvisoArriboInternacionalTO().getArriboOperInternacionalTO().getIdPilotoPractico()
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
        SignatureAdapter signatureAdapter = new SignatureAdapter(signatureTOS, informationTO.getAvisoArriboInternacionalTO().getIdEstado());
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
            informationTO.getAvisoArriboInternacionalTO().setSignatureTOS(signatureTOS);
            informationTO.getAvisoArriboInternacionalTO().setArriboAdminInternacionalTO(arriboAdministrativoInternacionalTO);
            mListener.onFragmentData(TAG, informationTO.getAvisoArriboInternacionalTO());
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
