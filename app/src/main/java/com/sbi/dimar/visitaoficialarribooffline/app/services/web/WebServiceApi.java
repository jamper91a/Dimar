package com.sbi.dimar.visitaoficialarribooffline.app.services.web;

import android.util.Log;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.WSConstants.URL_WS_BASE;

/**
 * Created by Jenny Galindo
 * <p>
 * The WebServiceApi class contains the instance of the Retrofit library, which is the one that allows communication to web services
 * For more information visit {@link Retrofit}
 */
public class WebServiceApi {

    private static final String TAG = WebServiceApi.class.getSimpleName();

    private static Retrofit retrofit = null;

    static Retrofit getClient() {
        Log.i(TAG, "call getClient");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        GsonBuilder builder = new GsonBuilder()
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL_WS_BASE)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .client(client)
                .build();

        return retrofit;
    }
}