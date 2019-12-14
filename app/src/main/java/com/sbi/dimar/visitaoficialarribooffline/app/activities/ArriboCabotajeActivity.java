package com.sbi.dimar.visitaoficialarribooffline.app.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.fragments.cabotage.ArriboAdministrativoCabotajeFragment;
import com.sbi.dimar.visitaoficialarribooffline.app.fragments.cabotage.ArriboOperativoCabotajeFragment;
import com.sbi.dimar.visitaoficialarribooffline.app.fragments.cabotage.AvisoArriboCabotajeFragment;
import com.sbi.dimar.visitaoficialarribooffline.app.services.database.DatabaseService;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PDFTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.AvisoFragmentInteractionListener;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.DisplayToastUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.PdfUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs.DateTimeDialogUtil;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs.ProgressDialogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity.ESTADO_VISITADO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.DATE_HOUR_FORMAT;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NUMBER_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO;

/**
 * Created by Jenny Galindo
 * <p>
 * The ArribosCabotajeActividad class represents the arrival information, it also contains the validation methods
 * for the form (operative and administrative), the call to the PDF generation method {@link PdfUtil},
 * and contains 3 fragments that represent each of the important options of the arrival notice.
 * {@link AvisoArriboCabotajeFragment}, {@link ArriboOperativoCabotajeFragment}, {@link ArriboAdministrativoCabotajeFragment}
 */
public class ArriboCabotajeActivity extends AppCompatActivity implements AvisoFragmentInteractionListener {
    private final Activity activity = ArriboCabotajeActivity.this;
    public static final String TAG = ArriboCabotajeActivity.class.getName();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private AvisoArriboCabotageTO arriboCabotageTO;
    private SessionManager sessionManager;
    private DatabaseService databaseService;
    private static SimpleDateFormat dateFormatter;
    private static InformationTO informationTO;
    private static SlideDateTimePicker buildSlideDateTimePicker;
    private FloatingActionButton fabSaveCabotaje;
    private ProgressDialogUtil progressDialogUtil;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arribo_cabotaje);
        initCore();
        getInfo();
        initLayout();
    }

    private void initLayout() {
        setupToolbar();
        progressDialogUtil = new ProgressDialogUtil.Builder(getSupportFragmentManager()).build();


        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        fabSaveCabotaje = findViewById(R.id.fabSaveCabotaje);
        if (arriboCabotageTO.getIdEstado() == EstadoEntity.ESTADO_VISITADO.getValue()) {
            fabSaveCabotaje.setVisibility(View.GONE);
        }

        fabSaveCabotaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "dialog SAVEINFORMACION called");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, android.R.style.ThemeOverlay_Material_Dialog);
                alertDialogBuilder.setTitle(R.string.ALERT);
                alertDialogBuilder.setMessage(getString(R.string.info_guardar_arribo));
                alertDialogBuilder.setPositiveButton(activity.getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                progressDialogUtil.show();

                                new Thread() {
                                    public void run() {
                                        if (validateForm()) {
                                            if (saveArribo()) {
                                                handler.sendEmptyMessage(0);
                                                runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        new DisplayToastUtil(activity, getString(R.string.exito_guardar_arribo, String.valueOf(arriboCabotageTO.getNumeroAvisoArribo()))).run();
                                                    }
                                                });

                                                //Inicia Actividad de Avisos
                                                goAvisosActivity();
                                            } else {
                                                handler.sendEmptyMessage(0);
                                            }
                                        } else {
                                            handler.sendEmptyMessage(0);
                                        }
                                    }
                                }.start();
                            }
                        });
                alertDialogBuilder.setNegativeButton(activity.getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                handler.sendEmptyMessage(0);
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();

            }
        });
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

    private void initCore() {
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                super.handleMessage(msg);
                progressDialogUtil.dismiss();
            }
        };

        sessionManager = SessionManager.getInstance(activity);
        databaseService = DatabaseService.getInstance(activity);
        dateFormatter = new SimpleDateFormat(DATE_HOUR_FORMAT, Locale.US);
        buildSlideDateTimePicker = DateTimeDialogUtil.getBuildSlideDateTimePicker(getSupportFragmentManager());
    }

    private void getInfo() {
        long numeroAviso = (long) getIntent().getSerializableExtra(NUMBER_AVISO_ARRIBO);
        String tipoAviso = (String) getIntent().getSerializableExtra(TYPE_AVISO_ARRIBO);
        informationTO = databaseService.getAllInfoCabotaje(numeroAviso, tipoAviso);
        arriboCabotageTO = informationTO.getAvisoArriboCabotageTO();
    }

    private boolean validateForm() {
        boolean isValidOper = true;
        boolean isValidAdmin = true;

        //Operativo
        AvisoArriboCabotageTO.ArriboOperativoCabotageTO arriboOperCabotageTO = arriboCabotageTO.getArriboOperCabotageTO();

        if (TextUtils.isEmpty(arriboOperCabotageTO.getFechaIngresoAreaControl())) {
            isValidOper = false;
        }

        if (TextUtils.isEmpty(arriboOperCabotageTO.getFechaAtraque())) {
            isValidOper = false;
        }

        arriboOperCabotageTO.setFechaHoraArribo(getDateFormatter().format(new Date()));

        //Administrativa
        AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO arriboAdminCabotageTO = arriboCabotageTO.getArriboAdminCabotageTO();
        if (TextUtils.isEmpty(arriboAdminCabotageTO.getFechaLibrePlatica())) {
            isValidAdmin = false;
        }
        if (TextUtils.isEmpty(arriboAdminCabotageTO.getFechaVisita())) {
            isValidAdmin = false;
        }
        if (!arriboAdminCabotageTO.isVisitada()) {
            isValidAdmin = false;
        }
        arriboAdminCabotageTO.setFechaCreacion(getDateFormatter().format(new Date()));

        //Firmas
        if (arriboCabotageTO.getSignatureTOS() == null || arriboCabotageTO.getSignatureTOS().isEmpty()
                || arriboCabotageTO.getSignatureTOS().size() < 1) {
            isValidAdmin = false;
            runOnUiThread(new Runnable() {
                public void run() {
                    new DisplayToastUtil(activity, getString(R.string.signatures_error)).run();
                }
            });
        }

        //Estado
        arriboCabotageTO.setIdEstado(ESTADO_VISITADO.getValue());

        if (!isValidOper) {
            runOnUiThread(new Runnable() {
                public void run() {
                    new DisplayToastUtil(activity, getString(R.string.error_arribo_operativo)).run();
                }
            });
        }

        if (!isValidAdmin) {
            runOnUiThread(new Runnable() {
                public void run() {
                    new DisplayToastUtil(activity, getString(R.string.error_arribo_administrativo)).run();
                }
            });
        }
        return isValidAdmin && isValidOper;
    }

    private boolean saveArribo() {
        final PDFTO pdfto = PdfUtil.generatePDF(TAG, arriboCabotageTO, activity);
        if (pdfto == null) {
            //ERROR GENERANDO PDF
            runOnUiThread(new Runnable() {
                public void run() {
                    new DisplayToastUtil(activity, getString(R.string.error_voa_pdf)).run();
                }
            });
            return false;
        } else {
            arriboCabotageTO.setPdfTO(pdfto);
            runOnUiThread(new Runnable() {
                public void run() {
                    new DisplayToastUtil(activity, getString(R.string.exito_voa_pdf, pdfto.getNombreArchivo())).run();
                }
            });
            return databaseService.saveArriboCabotaje(arriboCabotageTO, sessionManager.getUser());
        }
    }

    private void goAvisosActivity() {
        startActivity(new Intent(activity, AvisosArriboActivity.class));
        finish();
    }

    public static SlideDateTimePicker getSlideDateTimePicker() {
        return buildSlideDateTimePicker;
    }

    public static SimpleDateFormat getDateFormatter() {
        return dateFormatter;
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(activity, AvisosArriboActivity.class));
    }

    @Override
    public void onFragmentData(String TAG, Object data) {
        if (TAG.equals(ArriboAdministrativoCabotajeFragment.TAG)) {
            //Do something with 'data' that comes from fragment1
            AvisoArriboCabotageTO data1 = (AvisoArriboCabotageTO) data;
            arriboCabotageTO.setArriboAdminCabotageTO(data1.getArriboAdminCabotageTO());
            arriboCabotageTO.setSignatureTOS(data1.getSignatureTOS());

        } else if (TAG.equals(ArriboOperativoCabotajeFragment.TAG)) {
            //Do something with 'data' that comes from fragment2
            AvisoArriboCabotageTO.ArriboOperativoCabotageTO operativoCabotageTO = (AvisoArriboCabotageTO.ArriboOperativoCabotageTO) data;
            arriboCabotageTO.setArriboOperCabotageTO(operativoCabotageTO);
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AvisoArriboCabotajeFragment.newInstance(informationTO);
                case 1:
                    return ArriboOperativoCabotajeFragment.newInstance(informationTO);
                case 2:
                    return ArriboAdministrativoCabotajeFragment.newInstance(informationTO);
                default:
                    return AvisoArriboCabotajeFragment.newInstance(informationTO);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    private void nextPage() {
        int currentPage = mViewPager.getCurrentItem();
        int totalPages = mViewPager.getAdapter().getCount();

        int nextPage = currentPage + 1;
        if (nextPage >= totalPages) {
            // We can't go forward anymore.
            // Loop to the first page. If you don't want looping just
            // return here.
            nextPage = 0;
        }

        mViewPager.setCurrentItem(nextPage, true);
    }

    private void previousPage() {
        int currentPage = mViewPager.getCurrentItem();
        int totalPages = mViewPager.getAdapter().getCount();

        int previousPage = currentPage - 1;
        if (previousPage < 0) {
            // We can't go back anymore.
            // Loop to the last page. If you don't want looping just
            // return here.
            previousPage = totalPages - 1;
        }

        mViewPager.setCurrentItem(previousPage, true);
    }
}
