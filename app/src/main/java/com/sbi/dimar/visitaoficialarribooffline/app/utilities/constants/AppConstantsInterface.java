package com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants;
/**
 *Created by Jenny Galindo
 *
 * The AppConstantsInterface class contains the transversal constants used in the application
 */
public interface AppConstantsInterface {

    String APP_PREFERENCIAS = "APP_VISITAOFICIALARRIBOOFFLNE_PREFERENCIAS";
    String PACKAGE_NAME = "com.sbi.dimar.visitaoficialarribooffline.app";
    String ERROR="ERROR";
    String COMMA=",";
    String DATE_FORMAT= "yyyy-MM-dd";
    String DATE_HOUR_FORMAT= "yyyy-MM-dd HH:mm";


    /*PARAMETROS PREFERENCIA*/
    String PREF_USR = "PREFERENCIA_USUARIO";
    String PREF_USR_LOGGED = "PREF_USR_IS_LOGGED_IN";
    String PREF_NET_STATUS = "PREF_NET_STATUS";
    String PREF_NET_STATUS_VALUE = "PREF_NET_STATUS_VALUE";
    String PREF_DAT_SAVED = "PREF_DAT_SAVED";



    /*PERMISSION CONTANTS*/
    enum PermissionConstants {
        PERMISSION_READ_PHONE_STATE,
        PERMISSION_WRITE_EXTERNAL_STORAGE, PERMISSION_READ_EXTERNAL_STORAGE,
    }
    int multiplePermissionCounter = 0;
    int PERMISSION_REQUEST_CODE_MULTIPLE = 42695;

    /*NETWORK*/
    int NET_NOT_CONNECTED = 0;
    String NET_CONNECTED = "1";
    int NET_TYPE_WIFI=1;
    int NET_TYPE_MOBILE=2;
    int NET_NOTIFICATION=100001;
    String NET_STATUS="com.sbi.dimar.visitaoficialarribooffline.networkStatus";

    /*INTENTS KEYS*/
    String NUMBER_AVISO_ARRIBO="NUMBER_AVISO_ARRIBO";
    String TYPE_AVISO_ARRIBO="TYPE_AVISO_ARRIBO";
    String TYPE_AVISO_ARRIBO_NACIONAL="Nacional";
    String TYPE_AVISO_ARRIBO_INTERNACIONAL="Internacional";


}
