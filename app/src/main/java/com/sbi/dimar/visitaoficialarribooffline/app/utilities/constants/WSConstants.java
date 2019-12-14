package com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants;

/**
 * Created by Jenny Galindo
 * <p>
 * The WSConstants class contains the transversal constants used in the communication of web service
 */
public interface WSConstants {

    String URL_WS_BASE = "http://app.dimar.mil.co/WSSitmar/Service1.svc/";
//    String URL_WS_BASE = "http://tsi.dimar.mil.co:8087/WSSitmar/Service1.svc/";
    String URL_WS_GET_USER = "ObtenerUsuario/{user}/{password}";
    String URL_WS_GET_AVISOS_ARRIBO = "AvisosArribo/{user}/{startDate}/{endDate}";
    String URL_WS_PUT_AVISOS = "Sincronizar";
    int URL_WS_ERROR_CONEXION = -205;
    int URL_WS_ERROR_NO_CONTROL = -204;
    int URL_WS_ERROR_NO_AVISOS = -203;
    String URL_WS_ERROR_SYMBOL = "-";
}
