package com.sbi.dimar.visitaoficialarribooffline.app.fragments.utils;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sbi.dimar.visitaoficialarribooffline.app.R;

/**
 * Created by Jenny Galindo on 23/5/2018
 * <p>
 * The ProgressDialogFragment class the process dialog while executing some funcinoality
 */
public class ProgressDialogFragment extends DialogFragment {
    public static final String TAG = ProgressDialogFragment.class.getSimpleName();
    private TextView txtMessage;
    private String message;
    private static final String ARG_PARAM_PROGRESS_DIALOG = "ARG_PARAM_PROGRESS_DIALOG";
    private static ProgressDialogFragment fragment;


    public ProgressDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SignatureFragment.
     */
    public static ProgressDialogFragment newInstance(String message) {
        fragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_PROGRESS_DIALOG, message);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProgressDialogFragment getInstance() {
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(ARG_PARAM_PROGRESS_DIALOG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress_dialog, container, false);
        initLayout(view);
        initCore();
        return view;
    }

    private void initLayout(View view) {
        txtMessage = view.findViewById(R.id.txtMessage);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
    }

    private void initCore() {
        if (!TextUtils.isEmpty(message)) {
            txtMessage.setText(message);
        }
    }
}
