package com.sbi.dimar.visitaoficialarribooffline.app.fragments.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.BitmapSerializableUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.DisplayToastUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.signature.SignatureListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Jenny Galindo
 * <p>
 * The SignatureFragment class represents the information necessary to complete the signature of the official
 * <p>
 * A fragment with
 * Use the {@link SignatureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignatureFragment extends DialogFragment {
    public static final String TAG = SignatureFragment.class.getSimpleName();
    private static SignatureListener mListener;
    private SignaturePad signaturePad;
    private ImageButton mClearButton, btnSaveSignature;
    private static SignatureTO signatureTO;
    private static final String ARG_PARAM_SIGNATURE = "ARG_PARAM_SIGNATURE";
    private EditText txtRepresentantSelected, txtNombreRepresentante, txtEmailRepresentante, txtIdentificacionRepresentate;

    public SignatureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SignatureFragment.
     */
    public static SignatureFragment newInstance(SignatureListener listener, SignatureTO signatureTO) {
        SignatureFragment fragment = new SignatureFragment();
        mListener = listener;
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_SIGNATURE, signatureTO);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            signatureTO = (SignatureTO) getArguments().getSerializable(ARG_PARAM_SIGNATURE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signature, container, false);
        initLayout(view);
        initCore();
        return view;
    }

    private void initLayout(View view) {
        txtRepresentantSelected = view.findViewById(R.id.txtRepresentantSelected);
        txtNombreRepresentante = view.findViewById(R.id.txtNombreRepresentante);
        txtEmailRepresentante = view.findViewById(R.id.txtEmailRepresentante);
        txtIdentificacionRepresentate = view.findViewById(R.id.txtIdentificacionRepresentate);
        signaturePad = view.findViewById(R.id.signature_pad);
        mClearButton = view.findViewById(R.id.clear_button);
        btnSaveSignature = view.findViewById(R.id.btnSaveSignature);
    }

    private void initCore() {
        txtRepresentantSelected.setText(signatureTO.getRepresentantTO().getNombre());
        if (signatureTO.getNombreFuncionario() != null && !TextUtils.isEmpty(signatureTO.getNombreFuncionario())) {
            txtNombreRepresentante.setText(signatureTO.getNombreFuncionario());
        }
        if (signatureTO.getEmailFuncionario() != null && !TextUtils.isEmpty(signatureTO.getEmailFuncionario())) {
            txtEmailRepresentante.setText(signatureTO.getEmailFuncionario());
        }
        if (signatureTO.getNumeroIdentificacionFuncionario() != null && !TextUtils.isEmpty(signatureTO.getNumeroIdentificacionFuncionario())) {
            txtIdentificacionRepresentate.setText(signatureTO.getNumeroIdentificacionFuncionario());
        }
        if (signatureTO.getFirmaFuncionario() != null && !TextUtils.isEmpty(signatureTO.getFirmaFuncionario())) {
            signaturePad.setSignatureBitmap(BitmapSerializableUtil.stringToBitMap(signatureTO.getFirmaFuncionario()));
        }

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                btnSaveSignature.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                //btnSaveSignature.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        initButtons();


    }

    private void initButtons() {
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener == null) {
                    throw new NullPointerException(
                            "Listener no longer exists for mClearButton");
                }
                txtNombreRepresentante.setText("");
                txtEmailRepresentante.setText("");
                txtIdentificacionRepresentate.setText("");
                signaturePad.clear();
                signatureTO.setFirmaFuncionario(null);
                //dismiss();
            }
        });

        btnSaveSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener == null) {
                    throw new NullPointerException(
                            "Listener no longer exists for btnSaveSignature");
                }

                if (validateForm()) {
                    signatureTO.setNombreFuncionario(txtNombreRepresentante.getText().toString().toUpperCase());
                    signatureTO.setEmailFuncionario(txtEmailRepresentante.getText().toString());
                    signatureTO.setNumeroIdentificacionFuncionario(txtIdentificacionRepresentate.getText().toString());
                    Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
                    signatureTO.setFirmaFuncionario(BitmapSerializableUtil.bitMapToString(signatureBitmap));
                    mListener.onSignatureSet(signatureTO);
                    dismiss();
                }


            /*if (addJpgSignatureToGallery(signatureBitmap)) {
                Toast.makeText(MainActivity.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
            }
            if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                Toast.makeText(MainActivity.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
            }*/
            }
        });
    }


    private boolean validateForm() {
        boolean isValid = true;
        if (TextUtils.isEmpty(txtRepresentantSelected.getText())) {
            isValid = false;
            txtRepresentantSelected.setError(getString(R.string.obligatory_field));
        }
        if (TextUtils.isEmpty(txtNombreRepresentante.getText())) {
            isValid = false;
            txtNombreRepresentante.setError(getString(R.string.obligatory_field));
        } else {
            if (!txtNombreRepresentante.getText().toString().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+")) {
                isValid = false;
                txtNombreRepresentante.setError(getString(R.string.format_invalid));
            } else {
                if (txtNombreRepresentante.getText().toString().length() < 5) {
                    isValid = false;
                    txtNombreRepresentante.setError(getString(R.string.format_invalid));
                }
            }
        }
        if (TextUtils.isEmpty(txtEmailRepresentante.getText())) {
            isValid = false;
            txtEmailRepresentante.setError(getString(R.string.obligatory_field));
        } else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmailRepresentante.getText().toString()).matches()) {
                isValid = false;
                txtEmailRepresentante.setError(getString(R.string.format_invalid));
            }
        }
        if (TextUtils.isEmpty(txtIdentificacionRepresentate.getText())) {
            isValid = false;
            txtIdentificacionRepresentate.setError(getString(R.string.obligatory_field));
        } else {
            if (txtIdentificacionRepresentate.getText().toString().length() < 6 || txtIdentificacionRepresentate.getText().toString().length() > 10) {
                isValid = false;
                txtIdentificacionRepresentate.setError(getString(R.string.format_invalid_identificacion));
            }
        }
        if (signaturePad.isEmpty()) {
            isValid = false;
            new DisplayToastUtil(getActivity(), getString(R.string.signatur_error)).run();
        }
        return isValid;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        //MainActivity.this.sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
