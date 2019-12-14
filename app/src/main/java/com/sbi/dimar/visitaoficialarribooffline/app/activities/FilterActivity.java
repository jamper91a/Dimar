package com.sbi.dimar.visitaoficialarribooffline.app.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.services.database.DatabaseService;
import com.sbi.dimar.visitaoficialarribooffline.app.services.web.WebService;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AuditTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.DisplayToastUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.PermissionsUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs.DialogUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs.ProgressDialogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.DATE_FORMAT;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_NOT_CONNECTED;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_ERROR_NO_AVISOS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_ERROR_NO_CONTROL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_ERROR_SYMBOL;

/**
 * Created by Jenny Galindo
 * <p>
 * The FilterActivity class represents the necessary filters to check the arrival warnings from the web service,
 * according to the filters and the logged in user
 */
public class FilterActivity extends AppCompatActivity {
    private final Activity activity = FilterActivity.this;
    private static final String TAG = FilterActivity.class.getName();
    private SessionManager sessionManager;
    private WebService webService;
    private DatabaseService databaseService;
    private EditText txtStartDateFilter, txtEndDateFilter;
    private Button btnFilterContinue;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog dateStartPickerDialog, dateEndPickerDialog;
    private UserTO userTO;
    private ProgressDialogUtil progressDialogUtil;
    boolean callFromStartDate = false, callFromEndDate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initLayout();
        initCore();
    }

    private void initLayout() {
        setupToolbar();
        progressDialogUtil = new ProgressDialogUtil.Builder(getSupportFragmentManager()).build();
        dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        txtStartDateFilter = findViewById(R.id.txtStartDateFilter);
        txtEndDateFilter = findViewById(R.id.txtEndDateFilter);
        setDateTimeField();
        btnFilterContinue = findViewById(R.id.btnFilterContinue);
        btnFilterContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    getAvisosArribo();
                }
            }
        });

    }

    private void initCore() {

        sessionManager = SessionManager.getInstance(activity);
        webService = WebService.getInstance();

        //Get user from session
        if (sessionManager.getIsUserLoggedIn()) {
            userTO = sessionManager.getUser();
            if (userTO == null || userTO.getIdUsuario() == 0) {
                userTO = null;
                btnFilterContinue.setClickable(false);
                new DisplayToastUtil(activity, getString(R.string.user_not_logged_in)).run();
                startActivity(new Intent(activity, LoginActivity.class));
                finish();
            }
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.drawable.toolbar_dimar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @SuppressLint("NewApi")
    private void setDateTimeField() {
        Calendar calendar = Calendar.getInstance();
        final Calendar minDate = Calendar.getInstance(), maxDate = Calendar.getInstance();

        //region startDate
        dateStartPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
//                //Se modifica el dataPicker final para que la fecha minima sea la fecha seleccionada en el datapicker inicial
//                dateEndPickerDialog.getDatePicker().setMinDate(newDate.getTimeInMillis());
                txtStartDateFilter.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dateStartPickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        try {
            calendar.setTimeInMillis(System.currentTimeMillis());
            dateStartPickerDialog.getDatePicker().init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                @Override
                public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    Log.d("Date", "Year=" + year + " Month=" + (monthOfYear + 1) + " day=" + dayOfMonth);
                    if(callFromEndDate){
                        callFromEndDate = false;
                        return;
                    }
                    callFromStartDate = true;

                    // Force picker update
                    minDate.set(year - 1, monthOfYear, dayOfMonth);
                    dateEndPickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());  // Calls onDateChange from the other picker

                    callFromStartDate = true;

                    // Update the picker to the desired date
                    minDate.set(year, monthOfYear, dayOfMonth);
                    dateEndPickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

                }
            });
//            dateStartPickerDialog.getDatePicker().setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//                @Override
//                public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
//
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtStartDateFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == txtStartDateFilter) {
                    dateStartPickerDialog.show();
                }
            }
        });

        //endregion

        //region enddate
        dateEndPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtEndDateFilter.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        dateEndPickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        try {
            dateEndPickerDialog.getDatePicker().init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                @Override
                public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    if(callFromStartDate) {
                        callFromStartDate = false;
                        return;
                    }

                    callFromEndDate = true;

                    maxDate.set(year + 1, monthOfYear, dayOfMonth);
                    dateStartPickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

                    callFromEndDate = true;

                    maxDate.set(year, monthOfYear, dayOfMonth);
                    dateStartPickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

                }
            });
//            dateEndPickerDialog.getDatePicker().setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//                @Override
//                public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
//                    if(callFromStartDate) {
//                        callFromStartDate = false;
//                        return;
//                    }
//
//                    callFromEndDate = true;
//
//                    maxDate.set(year + 1, monthOfYear, dayOfMonth);
//                    dateStartPickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
//
//                    callFromEndDate = true;
//
//                    maxDate.set(year, monthOfYear, dayOfMonth);
//                    dateStartPickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtEndDateFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == txtEndDateFilter) {

                    dateEndPickerDialog.show();
                }
            }
        });
        //endregion

    }

    private boolean validateForm() {
        boolean validForm = true;

        if (TextUtils.isEmpty(txtStartDateFilter.getText().toString())) {
            txtStartDateFilter.setError(getString(R.string.obligatory_field));
            validForm = false;
        }
        if (TextUtils.isEmpty(txtEndDateFilter.getText().toString())) {
            txtEndDateFilter.setError(getString(R.string.obligatory_field));
            validForm = false;
        }
        if (!TextUtils.isEmpty(txtEndDateFilter.getText().toString())
                && !TextUtils.isEmpty(txtStartDateFilter.getText().toString())) {
            try {
                Date parseStartDate = dateFormatter.parse(txtStartDateFilter.getText().toString());
                Date parseEndDate = dateFormatter.parse(txtEndDateFilter.getText().toString());
                if (parseEndDate.before(parseStartDate)) {
                    validForm = false;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            new DisplayToastUtil(activity, getString(R.string.end_date_error)).run();
                        }
                    });
                }
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error parseando las fechas");
            }
        }
        return validForm;
    }

    private void getAvisosArribo() {
        progressDialogUtil.show();
        if (webService.validateService()) {
            Call<InformationTO> getAvisosArriboCall = webService.getAvisosArriboCall(userTO.getIdUsuario(), txtStartDateFilter.getText().toString(), txtEndDateFilter.getText().toString());
            if (getAvisosArriboCall != null) {
                getAvisosArriboCall.enqueue(new Callback<InformationTO>() {
                    @Override
                    public void onResponse(Call<InformationTO> call, Response<InformationTO> response) {
                        if (response != null && response.isSuccessful() && response.errorBody() == null) {
                            if (!String.valueOf(response.body().getCodigoMensaje()).contains(URL_WS_ERROR_SYMBOL)) {

                                //validar avisos de arribo
                                if (response.body().getAvisosArriboTO() != null
                                        && ((response.body().getAvisosArriboTO().getAvisoArriboInternacionalTOS() != null && !response.body().getAvisosArriboTO().getAvisoArriboInternacionalTOS().isEmpty())
                                        || (response.body().getAvisosArriboTO().getAvisoArriboCabotageTOS() != null && !response.body().getAvisosArriboTO().getAvisoArriboCabotageTOS().isEmpty()))) {

                                    //Save infromation into BD
                                    databaseService = DatabaseService.getInstance(activity);
                                    if (databaseService.saveAvisosArribo(response.body(), userTO)) {
                                        sessionManager.setIsDataSaved(true);
                                        //Guarda info de almacenamiento
                                        progressDialogUtil.dismiss();
                                        //Inicia Actividad de Avisos
                                        goAvisosActivity();
                                    } else {
                                        DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), getString(R.string.save_info_error));
                                    }
                                } else {
                                    progressDialogUtil.dismiss();
                                    call.cancel();
                                    AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_AVISOS, response.body().getMensaje());
                                    Log.e(TAG, auditTO.getAudit());
                                    new DisplayToastUtil(activity, getString(R.string.empty_info_error)).run();
                                }
                            } else {
                                progressDialogUtil.dismiss();
                                call.cancel();
                                AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, response.body().getMensaje());
                                Log.e(TAG, auditTO.getAudit());
                                DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                            }
                        } else {
                            progressDialogUtil.dismiss();
                            AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
                            call.cancel();
                            Log.e(TAG, auditTO.getAudit());
                            DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                        }
                    }

                    @Override
                    public void onFailure(Call<InformationTO> call, Throwable throwable) {
                        call.cancel();
                        progressDialogUtil.dismiss();
                        Log.e(TAG, throwable != null ? throwable.getMessage() : getString(R.string.ws_connection_error));
                        AuditTO auditTO;
                        if (sessionManager.getNetworkStatusValue().equals(String.valueOf(NET_NOT_CONNECTED))) {
                            auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, sessionManager.getNetworkStatus());
                        } else {
                            auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
                        }
                        DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());

                    }
                });
            } else {
                AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
                Log.e(TAG, auditTO.getAudit());
                DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                progressDialogUtil.dismiss();
            }
        } else {
            AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
            Log.e(TAG, auditTO.getAudit());
            DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
            progressDialogUtil.dismiss();
        }
    }


    private void goAvisosActivity() {
        startActivity(new Intent(activity, AvisosArriboActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PermissionsUtil.permissionReview(activity);
    }

    @Override
    public void onBackPressed() {
        if (sessionManager != null && sessionManager.getIsUserLoggedIn()) {
            DialogUtil.alertDialogActionSignOff(activity);
            // finishAffinity();
        } else {
            startActivity(new Intent(activity, MainActivity.class));
        }

    }
}
