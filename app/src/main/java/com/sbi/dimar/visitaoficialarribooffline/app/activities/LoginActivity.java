package com.sbi.dimar.visitaoficialarribooffline.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.services.database.DatabaseService;
import com.sbi.dimar.visitaoficialarribooffline.app.services.web.WebService;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AuditTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.PermissionsUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs.DialogUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs.ProgressDialogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_NOT_CONNECTED;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.PERMISSION_REQUEST_CODE_MULTIPLE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_ERROR_NO_CONTROL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_ERROR_SYMBOL;

/**
 * Created by Jenny Galindo
 * <p>
 * The LoginActivity class represents the login process of a user previously registered in the SITMAR and with a certain profile.
 * Additionally it contains validation method in user and password, validation of necessary permissions of the application
 */
public class LoginActivity extends AppCompatActivity {
    private final Activity activity = LoginActivity.this;
    private static final String TAG = LoginActivity.class.getName();
    private SessionManager sessionManager;
    private WebService webService;
    private EditText txtUser, txtPassword;
    private ProgressDialogUtil progressDialogUtil;
    private UserTO userTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        initLayout();
        initCore();
    }

    private void initLayout() {
        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        Button btnLogin = findViewById(R.id.btnContinue);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    getUser();
                }
            }
        });
    }

    private void initCore() {
        DatabaseService databaseAccess = DatabaseService.getInstance(this);
        databaseAccess.getUsers();
        sessionManager = SessionManager.getInstance(activity);
        progressDialogUtil = new ProgressDialogUtil.Builder(getSupportFragmentManager())
                .build();
        PermissionsUtil.permissionReview(activity);
        webService = WebService.getInstance();
        if (!webService.validateService()) {
            AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
            DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
        }
    }

    private boolean validateForm() {
        boolean validForm = true;

        if (TextUtils.isEmpty(txtUser.getText().toString()) && txtUser.getText().toString().trim().length() <= 0) {
            txtUser.setError(getString(R.string.obligatory_field));
            validForm = false;
        }
        if (TextUtils.isEmpty(txtPassword.getText().toString()) && txtPassword.getText().toString().trim().length() <= 0) {
            txtPassword.setError(getString(R.string.obligatory_field));
            validForm = false;
        }
        return validForm;
    }

    private void getUser() {
        progressDialogUtil.show();
        if (webService.validateService()) {
            Call getUserCall = webService.getUserCall(txtUser.getText().toString(), txtPassword.getText().toString());
            if (getUserCall != null) {
                getUserCall.enqueue(new Callback<UserTO>() {
                    @Override
                    public void onResponse(Call<UserTO> call, Response<UserTO> response) {
                        if (response != null && response.isSuccessful() && response.errorBody() == null) {
                            if (!String.valueOf(response.body().getCodigoMensaje()).contains(URL_WS_ERROR_SYMBOL)) {
                                userTO = new UserTO();
                                userTO = response.body();
                                saveUser(userTO);

                                progressDialogUtil.dismiss();

                                //Inicia Actividad de Filtro
                                goFilterActivity();
                            } else {

                                progressDialogUtil.dismiss();
                                call.cancel();
                                AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, response.body().getMensaje());
                                Log.e(TAG, auditTO.getAudit());
                                Toast.makeText(LoginActivity.this, "Usuario/Contrasena incorrectos",Toast.LENGTH_SHORT).show();
//                                DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                                //Inicia Actividad de Filtro
                                //goFilterActivity();
                            }
                        } else {

                            progressDialogUtil.dismiss();
                            AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
                            call.cancel();
                            Log.e(TAG, auditTO.getAudit());
//                            DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserTO> call, Throwable throwable) {
                        call.cancel();
                        progressDialogUtil.dismiss();
                        Log.e(TAG, throwable != null ? throwable.getMessage() : getString(R.string.ws_connection_error));
                        AuditTO auditTO;
                        if (sessionManager.getNetworkStatusValue().equals(String.valueOf(NET_NOT_CONNECTED))) {
                            auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, sessionManager.getNetworkStatus());
                        } else {
                            auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
                        }
//                        DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                    }
                });
            } else {
                AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
                Log.e(TAG, auditTO.getAudit());
//                DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                progressDialogUtil.dismiss();
            }
        } else {
            AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
            Log.e(TAG, auditTO.getAudit());
//            DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
            progressDialogUtil.dismiss();
        }

    }

    private void saveUser(UserTO userTO) {
        if (!sessionManager.getIsUserLoggedIn()) {
            sessionManager.saveUser(userTO);
            sessionManager.setIsUserLoggedIn(true);
        }
    }

    private void goFilterActivity() {
        startActivity(new Intent(activity, FilterActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * predefined method to check run time permissions list call back
     *
     * @param permissions:  contains the list of requested permissions
     * @param grantResults: contains granted and un granted permissions result list
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isDenine = false;
        int multiplePermissionCounter = 0;
        if (requestCode == PERMISSION_REQUEST_CODE_MULTIPLE) {

            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    multiplePermissionCounter++;
                    isDenine = true;
                    break;
                }
            }

            if (isDenine) {
                if (multiplePermissionCounter >= 2) {
                    PermissionsUtil.dialogOnPermissionDenied(activity);
                } else {
                    PermissionsUtil.permissionReview(activity);
                }
            } else {
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onBackPressed() {
        if (sessionManager != null && sessionManager.getIsUserLoggedIn()) {
            DialogUtil.alertDialogActionSignOff(activity);
        } else {
            finishAffinity();
        }
    }
}
