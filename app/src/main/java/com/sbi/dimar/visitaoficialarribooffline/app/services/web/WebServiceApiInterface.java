package com.sbi.dimar.visitaoficialarribooffline.app.services.web;

import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisosArriboTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Jenny Galindo
 * <p>
 * The WebServiceApiInterface class contains the definition of the communication methods with the web service used in the application
 */
public interface WebServiceApiInterface {

    @GET(WSConstants.URL_WS_GET_USER)
    Call<UserTO> getWSUser(@Path("user") String user, @Path("password") String password);

    @GET(WSConstants.URL_WS_GET_AVISOS_ARRIBO)
    Call<InformationTO> getWSAvisosArribo(@Path("user") int user, @Path("startDate") String startDate, @Path("endDate") String endDate);

//    @FormUrlEncoded
    @POST(WSConstants.URL_WS_PUT_AVISOS)
    Call<AvisosArriboTO> synchronizeArrivals(@Body AvisosArriboTO avisosArriboTO);
}
