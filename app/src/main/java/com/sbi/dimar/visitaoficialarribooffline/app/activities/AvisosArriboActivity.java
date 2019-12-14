package com.sbi.dimar.visitaoficialarribooffline.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.adapter.AvisoArriboAdapter;
import com.sbi.dimar.visitaoficialarribooffline.app.services.database.DatabaseService;
import com.sbi.dimar.visitaoficialarribooffline.app.services.web.WebService;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AuditTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisosArriboTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.DisplayToastUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs.DialogUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs.ProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_NOT_CONNECTED;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_ERROR_NO_AVISOS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_ERROR_NO_CONTROL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_ERROR_SYMBOL;

/**
 * Created by Jenny Galindo
 * <p>
 * The AvisosArriboActivity class represents the international and cabotage arrival warning information,
 * in addition the synchronization and data deletion functionality are presented and continue.
 */
public class AvisosArriboActivity extends AppCompatActivity {
    private final Activity activity = AvisosArriboActivity.this;
    private static final String TAG = AvisosArriboActivity.class.getName();
    private SessionManager sessionManager;
    private WebService webService;
    private DatabaseService databaseService;
    private RecyclerView mRecyclerView;
    private FloatingActionButton fabSignOff, fabSynchronize;
    private ProgressDialogUtil progressDialogUtil;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_arribo);
        initLayout();
        initCore();
        getAvisos();
    }

    private void initLayout() {
        setupToolbar();
        mRecyclerView = findViewById(R.id.recycler_view_avisos);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fabSignOff = findViewById(R.id.fabSignOff);
        fabSynchronize = findViewById(R.id.fabSynchronize);
    }

    private void initCore() {
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                super.handleMessage(msg);
                progressDialogUtil.dismiss();
            }
        };
        sessionManager = SessionManager.getInstance(activity);
        webService = WebService.getInstance();
        databaseService = DatabaseService.getInstance(activity);
        progressDialogUtil = new ProgressDialogUtil.Builder(getSupportFragmentManager()).build();

        fabSignOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionManager != null && sessionManager.getIsUserLoggedIn()) {
                    DialogUtil.alertDialogActionSignOff(activity);
                } else {
                    finishAffinity();
                }
            }
        });
        fabSynchronize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialogUtil.show();
                new Thread() {
                    public void run() {
                        synchronizeArribos();
                    }
                }.start();
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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

    private void getAvisos() {
        AvisosArriboTO avisos = databaseService.getAvisosArribo();
        List<AvisosArriboTO> avisosArriboTOS = new ArrayList<>();
        AvisosArriboTO avisosArriboTO;
        if (avisos.getAvisoArriboInternacionalTOS() != null) {
            for (int i = 0; i < avisos.getAvisoArriboInternacionalTOS().size(); i++) {
                avisosArriboTO = new AvisosArriboTO();
                avisosArriboTO.setNumeroAvisoArribo(avisos.getAvisoArriboInternacionalTOS().get(i).getNumeroAvisoArribo());
                avisosArriboTO.setOmiMatricula(avisos.getAvisoArriboInternacionalTOS().get(i).getOmiMatricula());
                avisosArriboTO.setTipoAviso(avisos.getAvisoArriboInternacionalTOS().get(i).getTipoAviso());
                avisosArriboTO.setNombreNave(avisos.getAvisoArriboInternacionalTOS().get(i).getNombreNave());
                avisosArriboTO.setEstado(avisos.getAvisoArriboInternacionalTOS().get(i).getIdEstado());
                avisosArriboTOS.add(avisosArriboTO);
            }
        }
        if (avisos.getAvisoArriboCabotageTOS() != null) {
            for (int i = 0; i < avisos.getAvisoArriboCabotageTOS().size(); i++) {
                avisosArriboTO = new AvisosArriboTO();
                avisosArriboTO.setNumeroAvisoArribo(avisos.getAvisoArriboCabotageTOS().get(i).getNumeroAvisoArribo());
                avisosArriboTO.setOmiMatricula(avisos.getAvisoArriboCabotageTOS().get(i).getOmiMatricula());
                avisosArriboTO.setTipoAviso(avisos.getAvisoArriboCabotageTOS().get(i).getTipoAviso());
                avisosArriboTO.setNombreNave(avisos.getAvisoArriboCabotageTOS().get(i).getNombreNave());
                avisosArriboTO.setEstado(avisos.getAvisoArriboCabotageTOS().get(i).getIdEstado());
                avisosArriboTOS.add(avisosArriboTO);
            }
        }

        AvisoArriboAdapter avisoArriboAdapter = new AvisoArriboAdapter(activity, avisosArriboTOS);
        mRecyclerView.setAdapter(avisoArriboAdapter);
    }

    private void synchronizeArribos() {
        if (webService.validateService()) {
            //Check Arrivals
            AvisosArriboTO allArribos = databaseService.getAllArribos();
            if (allArribos != null && (allArribos.getAvisoArriboCabotageTOS() != null || allArribos.getAvisoArriboInternacionalTOS() != null)) {
                //Eliminar informacion duplicada de remolcadores
                Call<AvisosArriboTO> getAvisosArriboCall = webService.synchronizeArrivalsCall(allArribos);
                if (getAvisosArriboCall != null) {
                    getAvisosArriboCall.enqueue(new Callback<AvisosArriboTO>() {
                        @Override
                        public void onResponse(Call<AvisosArriboTO> call, final Response<AvisosArriboTO> response) {
                            if (response != null && response.isSuccessful() && response.errorBody() == null) {
                                if (!String.valueOf(response.body().getCodigoMensaje()).contains(URL_WS_ERROR_SYMBOL)) {
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            new DisplayToastUtil(activity, response.body().getMensaje()).run();
                                        }
                                    });
                                    databaseService.deleteAllInfo();
                                    sessionManager.clearSharedPrefs();
                                    handler.sendEmptyMessage(0);
                                    finishAffinity();
                                    startActivity(new Intent(activity, MainActivity.class));
                                } else {
                                    handler.sendEmptyMessage(0);
                                    call.cancel();
//                                    new DisplayToastUtil(activity, "Sincronizacion realizada con exito").run();
//                                    AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, "Sincronizacion realizada con exito");
                                    final AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, response.body().getMensaje());
//                                    Log.e(TAG, auditTO.getAudit());
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                                        }
                                    });
                                }
                            } else {
                                handler.sendEmptyMessage(0);
                                final AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
                                call.cancel();
//                                new DisplayToastUtil(activity, "Sincronizacion realizada con exito").run();
//                                Log.e(TAG, auditTO.getAudit());
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<AvisosArriboTO> call, Throwable throwable) {
                            call.cancel();
                            handler.sendEmptyMessage(0);
                            new DisplayToastUtil(activity, "Error sincronizando").run();
//                            Log.e(TAG, throwable != null ? throwable.getMessage() : getString(R.string.ws_connection_error));
//                            AuditTO auditTO;
//                            if (sessionManager.getNetworkStatusValue().equals(String.valueOf(NET_NOT_CONNECTED))) {
//                                auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, sessionManager.getNetworkStatus());
//                            } else {
//                                auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
//                            }
//                            runOnUiThread(new Runnable() {
//                                public void run() {
//                                    DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
//                                }
//                            });
                        }
                    });
                } else {
                    new DisplayToastUtil(activity, "No hay informacion para sincronizar").run();
//                    handler.sendEmptyMessage(0);
//                    AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
//                    Log.e(TAG, auditTO.getAudit());
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
//                        }
//                    });
                }

            } else {
                new DisplayToastUtil(activity, "No hay informacion para sincronizar").run();
//                handler.sendEmptyMessage(0);
//                AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_AVISOS, getString(R.string.empty_arribos_error));
//                Log.e(TAG, auditTO.getAudit());
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
//                    }
//                });
            }
        } else {
            new DisplayToastUtil(activity, "No paso validaci'on").run();
//            handler.sendEmptyMessage(0);
//            AuditTO auditTO = new AuditTO(URL_WS_ERROR_NO_CONTROL, getString(R.string.ws_connection_error));
//            Log.e(TAG, auditTO.getAudit());
//            runOnUiThread(new Runnable() {
//                public void run() {
//                    DialogUtil.alertDialogInfo(activity, getString(R.string.ERROR), auditTO.getMensaje());
//                }
//            });
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
