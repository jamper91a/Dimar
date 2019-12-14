package com.sbi.dimar.visitaoficialarribooffline.app.services.web;

import android.util.Log;

import com.google.gson.Gson;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisosArriboTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;

import retrofit2.Call;

/**
 * Created by Jenny Galindo
 * <p>
 * The WebService class represents the call to the {@link WebServiceApiInterface} interface which communicates with the web services
 */
public class WebService {
    private static final String TAG = WebService.class.getName();
    private static WebService instance;

    private WebServiceApiInterface webServiceApiInterface;

    public static WebService getInstance() {
        if (instance == null) {
            instance = new WebService();
        }
        return instance;
    }


    public boolean validateService() {
        try {
            this.webServiceApiInterface = WebServiceApi.getClient().create(WebServiceApiInterface.class);
            return true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public Call<UserTO> getUserCall(String user, String password) {
        return webServiceApiInterface.getWSUser(user, password);
    }

    public Call<InformationTO> getAvisosArriboCall(int user, String startDate, String endDate) {
        return webServiceApiInterface.getWSAvisosArribo(user, startDate, endDate);
    }

    public Call<AvisosArriboTO> synchronizeArrivalsCall(AvisosArriboTO avisosArriboTO) {
//
//
//
//        if( avisosArriboTO.getAvisoArriboCabotageTOS()!= null){
//
//            for (AvisoArriboCabotageTO aux :
//                    avisosArriboTO.getAvisoArriboCabotageTOS()) {
//                aux.setPdfTO(null);
//                aux.setSignatureTOS(null);
////                aux.getPdfTO().setArchivo("");
//                for (SignatureTO sto:aux.getSignatureTOS()
//                ) {
//                    sto.setFirmaFuncionario("");
//
//                }
//
//            }
//        }
//        if(avisosArriboTO.getAvisoArriboInternacionalTOS() !=null){
//
//            for(AvisoArriboInternacionalTO aux: avisosArriboTO.getAvisoArriboInternacionalTOS()){
//                aux.setPdfTO(null);
////                aux.getPdfTO().setArchivo("");
//                for (SignatureTO sto:aux.getSignatureTOS()
//                     ) {
//                    sto.setFirmaFuncionario("");
//
//                }
////                aux.setSignatureTOS(null);
//            }
//        }
        Call<AvisosArriboTO> call = webServiceApiInterface.synchronizeArrivals(avisosArriboTO);
        return call;
    }
}
