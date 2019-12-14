package com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants;

/**
 * Created by Jenny Galindo
 * <p>
 * The DBConstants class contains the constants for the application database
 */
public interface DBConstants {

    /*CONFIG*/
    String DATABASE_NAME = "DIMAR_OFFLINE_V1.5.db";
    String DATABASE_NAME_BACKUP = "DIMAR_OFFLINE_BACKUP_V1.1";
    String DATABASE_NAME_SUB_PATH = "/databases/";
    int DATABASE_VERSION = 1;

    /*TABLAS*/

    /*AVISO ARRIBO CABOTAJE*/
    String TABLE_DIM_OFF_AVA_CAB = "DIM_OFF_AVA_CAB";
    String COLUMN_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO = "DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_AVA_CAB_MATRICULA = "DIM_OFF_AVA_CAB_MATRICULA";
    String COLUMN_DIM_OFF_AVA_CAB_NOMBRE_NAVE = "DIM_OFF_AVA_CAB_NOMBRE_NAVE";
    String COLUMN_DIM_OFF_AVA_CAB_ID_PAIS = "DIM_OFF_AVA_CAB_ID_PAIS";
    String COLUMN_DIM_OFF_AVA_CAB_PAIS = "DIM_OFF_AVA_CAB_PAIS";
    String COLUMN_DIM_OFF_AVA_CAB_ETA = "DIM_OFF_AVA_CAB_ETA";
    String COLUMN_DIM_OFF_AVA_CAB_MUELLE_ORIGEN = "DIM_OFF_AVA_CAB_MUELLE_ORIGEN";
    String COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_ORIGEN = "DIM_OFF_AVA_CAB_CAPITANIA_ORIGEN";
    String COLUMN_DIM_OFF_AVA_CAB_MUELLE_DESTINO = "DIM_OFF_AVA_CAB_MUELLE_DESTINO";
    String COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_DESTINO = "DIM_OFF_AVA_CAB_CAPITANIA_DESTINO";
    String COLUMN_DIM_OFF_AVA_CAB_NOMBRE_PUERTO_DESTINO = "DIM_OFF_AVA_CAB_NOMBRE_PUERTO_DESTINO";
    String COLUMN_DIM_OFF_AVA_CAB_TIPO_AVISO = "DIM_OFF_AVA_CAB_TIPO_AVISO";
    String COLUMN_DIM_OFF_AVA_CAB_ESLORA_MAXIMA = "DIM_OFF_AVA_CAB_ESLORA_MAXIMA";
    String COLUMN_DIM_OFF_AVA_CAB_TRB = "DIM_OFF_AVA_CAB_TRB";
    String COLUMN_DIM_OFF_AVA_CAB_TRN = "DIM_OFF_AVA_CAB_TRN";
    String COLUMN_DIM_OFF_AVA_CAB_TRIPULANTES_SOLICITUD_ZARPE = "DIM_OFF_AVA_CAB_TRIPULANTES_SOLICITUD_ZARPE";
    String COLUMN_DIM_OFF_AVA_CAB_TIPO_BUQUE = "DIM_OFF_AVA_CAB_TIPO_BUQUE";
    String COLUMN_DIM_OFF_AVA_CAB_TIPO_CARGA = "DIM_OFF_AVA_CAB_TIPO_CARGA";
    String COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_CARGA = "DIM_OFF_AVA_CAB_CANTIDAD_CARGA";
    String COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_PASAJEROS = "DIM_OFF_AVA_CAB_CANTIDAD_PASAJEROS";
    String COLUMN_DIM_OFF_AVA_CAB_RESPONSABLE_NAVE = "DIM_OFF_AVA_CAB_RESPONSABLE_NAVE";
    String COLUMN_DIM_OFF_AVA_CAB_RAZON_SOCIAL = "DIM_OFF_AVA_CAB_RAZON_SOCIAL";
    String COLUMN_DIM_OFF_AVA_CAB_FECHA_AVISO = "DIM_OFF_AVA_CAB_FECHA_AVISO";
    //ARRIBO OPERATIVO
    String COLUMN_DIM_OFF_AVA_CAB_NUMERO_ARRIBO = "DIM_OFF_AVA_CAB_NUMERO_ARRIBO";
    String COLUMN_DIM_OFF_AVA_CAB_CALADO = "DIM_OFF_AVA_CAB_CALADO";
    String COLUMN_DIM_OFF_AVA_CAB_FECHA_ATRAQUE = "DIM_OFF_AVA_CAB_FECHA_ATRAQUE";
    String COLUMN_DIM_OFF_AVA_CAB_FECHA_INGRESO_AREA_CONTROL = "DIM_OFF_AVA_CAB_FECHA_INGRESO_AREA_CONTROL";
    String COLUMN_DIM_OFF_AVA_CAB_ID_ESTADO_ACTUAL = "DIM_OFF_AVA_CAB_ID_ESTADO_ACTUAL";
    String COLUMN_DIM_OFF_AVA_CAB_ESTADO = "DIM_OFF_AVA_CAB_ESTADO";
    String COLUMN_DIM_OFF_AVA_CAB_ID_RAZON_ARRIBO = "DIM_OFF_AVA_CAB_ID_RAZON_ARRIBO";
    String COLUMN_DIM_OFF_AVA_CAB_RAZON_ARRIBO = "DIM_OFF_AVA_CAB_RAZON_ARRIBO";
    String COLUMN_DIM_OFF_AVA_CAB_ID_MUELLE_ATRAQUE = "DIM_OFF_AVA_CAB_ID_MUELLE_ATRAQUE";
    String COLUMN_DIM_OFF_AVA_CAB_MUELLE_ATRAQUE = "DIM_OFF_AVA_CAB_MUELLE_ATRAQUE";
    String COLUMN_DIM_OFF_AVA_CAB_OBSERVACIONES = "DIM_OFF_AVA_CAB_OBSERVACIONES";
    String COLUMN_DIM_OFF_AVA_CAB_FECHA_HORA_ARRIBO = "DIM_OFF_AVA_CAB_FECHA_HORA_ARRIBO";
    String COLUMN_DIM_OFF_AVA_CAB_ID_PUERTO_DESTINO = "DIM_OFF_AVA_CAB_ID_PUERTO_DESTINO";

    String COLUMN_DIM_OFF_AVA_CAB_DIM_OFF_USR_ID_USUARIO = "DIM_OFF_USR_ID_USUARIO";

    String[] COLUMNS_DIM_OFF_AVA_CAB = {
            COLUMN_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_AVA_CAB_MATRICULA,
            COLUMN_DIM_OFF_AVA_CAB_NOMBRE_NAVE,
            COLUMN_DIM_OFF_AVA_CAB_ID_PAIS,
            COLUMN_DIM_OFF_AVA_CAB_PAIS,
            COLUMN_DIM_OFF_AVA_CAB_ETA,
            COLUMN_DIM_OFF_AVA_CAB_MUELLE_ORIGEN,
            COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_ORIGEN,
            COLUMN_DIM_OFF_AVA_CAB_MUELLE_DESTINO,
            COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_DESTINO,
            COLUMN_DIM_OFF_AVA_CAB_NOMBRE_PUERTO_DESTINO,
            COLUMN_DIM_OFF_AVA_CAB_TIPO_AVISO,
            COLUMN_DIM_OFF_AVA_CAB_ESLORA_MAXIMA,
            COLUMN_DIM_OFF_AVA_CAB_TRB,
            COLUMN_DIM_OFF_AVA_CAB_TRN,
            COLUMN_DIM_OFF_AVA_CAB_TRIPULANTES_SOLICITUD_ZARPE,
            COLUMN_DIM_OFF_AVA_CAB_TIPO_BUQUE,
            COLUMN_DIM_OFF_AVA_CAB_TIPO_CARGA,
            COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_CARGA,
            COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_PASAJEROS,
            COLUMN_DIM_OFF_AVA_CAB_RESPONSABLE_NAVE,
            COLUMN_DIM_OFF_AVA_CAB_RAZON_SOCIAL,
            COLUMN_DIM_OFF_AVA_CAB_FECHA_AVISO,
            COLUMN_DIM_OFF_AVA_CAB_NUMERO_ARRIBO,
            COLUMN_DIM_OFF_AVA_CAB_CALADO,
            COLUMN_DIM_OFF_AVA_CAB_FECHA_ATRAQUE,
            COLUMN_DIM_OFF_AVA_CAB_FECHA_INGRESO_AREA_CONTROL,
            COLUMN_DIM_OFF_AVA_CAB_ID_ESTADO_ACTUAL,
            COLUMN_DIM_OFF_AVA_CAB_ESTADO,
            COLUMN_DIM_OFF_AVA_CAB_ID_RAZON_ARRIBO,
            COLUMN_DIM_OFF_AVA_CAB_RAZON_ARRIBO,
            COLUMN_DIM_OFF_AVA_CAB_ID_MUELLE_ATRAQUE,
            COLUMN_DIM_OFF_AVA_CAB_MUELLE_ATRAQUE,
            COLUMN_DIM_OFF_AVA_CAB_OBSERVACIONES,
            COLUMN_DIM_OFF_AVA_CAB_FECHA_HORA_ARRIBO,
            COLUMN_DIM_OFF_AVA_CAB_ID_PUERTO_DESTINO,
            COLUMN_DIM_OFF_AVA_CAB_DIM_OFF_USR_ID_USUARIO};

    /*AVISO ARRIBO INTERNACIONAL*/
    String TABLE_DIM_OFF_AVA_INT = "DIM_OFF_AVA_INT";
    String COLUMN_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO = "DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_AVA_INT_OMI_MATRICULA = "DIM_OFF_AVA_INT_OMI_MATRICULA";
    String COLUMN_DIM_OFF_AVA_INT_NOMBRE_NAVE = "DIM_OFF_AVA_INT_NOMBRE_NAVE";
    String COLUMN_DIM_OFF_AVA_INT_NOMBRE_PUERTO_DESTINO = "DIM_OFF_AVA_INT_NOMBRE_PUERTO_DESTINO";
    String COLUMN_DIM_OFF_AVA_INT_ETA = "DIM_OFF_AVA_INT_ETA";
    String COLUMN_DIM_OFF_AVA_INT_FECHA = "DIM_OFF_AVA_INT_FECHA";
    String COLUMN_DIM_OFF_AVA_INT_PUERTO_PROCEDENCIA = "DIM_OFF_AVA_INT_PUERTO_PROCEDENCIA";
    String COLUMN_DIM_OFF_AVA_INT_NIT_AGENCIA = "DIM_OFF_AVA_INT_NIT_AGENCIA";
    String COLUMN_DIM_OFF_AVA_INT_RAZON_SOCIAL = "DIM_OFF_AVA_INT_RAZON_SOCIAL";
    String COLUMN_DIM_OFF_AVA_INT_ID_ESTADO = "DIM_OFF_AVA_INT_ID_ESTADO";
    String COLUMN_DIM_OFF_AVA_INT_DESCRIPCION_ESTADO = "DIM_OFF_AVA_INT_DESCRIPCION_ESTADO";
    String COLUMN_DIM_OFF_AVA_INT_TIPO_AVISO = "DIM_OFF_AVA_INT_TIPO_AVISO";
    String COLUMN_DIM_OFF_AVA_INT_ESLORA_MAXIMA = "DIM_OFF_AVA_INT_ESLORA_MAXIMA";
    String COLUMN_DIM_OFF_AVA_INT_TRB = "DIM_OFF_AVA_INT_TRB";
    String COLUMN_DIM_OFF_AVA_INT_TRN = "DIM_OFF_AVA_INT_TRN";
    String COLUMN_DIM_OFF_AVA_INT_PAIS = "DIM_OFF_AVA_INT_PAIS";
    String COLUMN_DIM_OFF_AVA_INT_TRIPULACION_ARRIBO = "DIM_OFF_AVA_INT_TRIPULACION_ARRIBO";
    String COLUMN_DIM_OFF_AVA_INT_TIPO_BUQUE = "DIM_OFF_AVA_INT_TIPO_BUQUE";
    String COLUMN_DIM_OFF_AVA_INT_TIPO_CARGA = "DIM_OFF_AVA_INT_TIPO_CARGA";
    String COLUMN_DIM_OFF_AVA_INT_CANTIDAD_CARGA = "DIM_OFF_AVA_INT_CANTIDAD_CARGA";
    String COLUMN_DIM_OFF_AVA_INT_MERCANCIAS_PELIGROSAS = "DIM_OFF_AVA_INT_MERCANCIAS_PELIGROSAS";
    String COLUMN_DIM_OFF_AVA_INT_CANTIDAD_PASAJEROS = "DIM_OFF_AVA_INT_CANTIDAD_PASAJEROS";
    String COLUMN_DIM_OFF_AVA_INT_NOMBRE_CAPITAN_NAVE = "DIM_OFF_AVA_INT_NOMBRE_CAPITAN_NAVE";
    String COLUMN_DIM_OFF_AVA_INT_CALADO_POPA = "DIM_OFF_AVA_INT_CALADO_POPA";
    String COLUMN_DIM_OFF_AVA_INT_CALADO_PROA = "DIM_OFF_AVA_INT_CALADO_PROA";
    String COLUMN_DIM_OFF_AVA_INT_PAIS_BANDERA = "DIM_OFF_AVA_INT_PAIS_BANDERA";
    //ARRIBO OPERATIVO
    String COLUMN_DIM_OFF_AVA_INT_NUMERO_ARRIBO = "DIM_OFF_AVA_INT_NUMERO_ARRIBO";
    String COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_PRACTICO = "DIM_OFF_AVA_INT_ID_PILOTO_PRACTICO";
    String COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO = "DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO";
    String COLUMN_DIM_OFF_AVA_INT_ID_INSTALACION_PORTUARIA = "DIM_OFF_AVA_INT_ID_INSTALACION_PORTUARIA";
    String COLUMN_DIM_OFF_AVA_INT_ID_LANCHA_TRANSPORTE_PILOTO = "DIM_OFF_AVA_INT_ID_LANCHA_TRANSPORTE_PILOTO";
    String COLUMN_DIM_OFF_AVA_INT_ID_REMOLCADOR = "DIM_OFF_AVA_INT_ID_REMOLCADOR";
    String COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_ARRIBO = "DIM_OFF_AVA_INT_FECHA_HORA_ARRIBO";
    String COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_PILOTO_ABORDO_REPORTE = "DIM_OFF_AVA_INT_FECHA_HORA_PILOTO_ABORDO_REPORTE";
    String COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_INICIO_MANIOBRA_ATRAQUE = "DIM_OFF_AVA_INT_FECHA_HORA_INICIO_MANIOBRA_ATRAQUE";
    String COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_FIN_MANIOBRA_ATRAQUE = "DIM_OFF_AVA_INT_FECHA_HORA_FIN_MANIOBRA_ATRAQUE";
    String COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ATRAQUE = "DIM_OFF_AVA_INT_ID_PILOTO_ATRAQUE";
    String COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ASI_ATRAQUE = "DIM_OFF_AVA_INT_ID_PILOTO_ASI_ATRAQUE";
    String COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO_ATRAQUE = "DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO_ATRAQUE";
    String COLUMN_DIM_OFF_AVA_INT_FECHA_INGRESO_AREA_CONTROL = "DIM_OFF_AVA_INT_FECHA_INGRESO_AREA_CONTROL";
    String COLUMN_DIM_OFF_AVA_INT_ID_PUERTO_DESTINO = "DIM_OFF_AVA_INT_ID_PUERTO_DESTINO";

    String COLUMN_DIM_OFF_AVA_INT_DIM_OFF_USR_ID_USUARIO = "DIM_OFF_USR_ID_USUARIO";

    String[] COLUMNS_DIM_OFF_AVA_INT = {
            COLUMN_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_AVA_INT_OMI_MATRICULA,
            COLUMN_DIM_OFF_AVA_INT_NOMBRE_NAVE,
            COLUMN_DIM_OFF_AVA_INT_NOMBRE_PUERTO_DESTINO,
            COLUMN_DIM_OFF_AVA_INT_ETA,
            COLUMN_DIM_OFF_AVA_INT_FECHA,
            COLUMN_DIM_OFF_AVA_INT_PUERTO_PROCEDENCIA,
            COLUMN_DIM_OFF_AVA_INT_NIT_AGENCIA,
            COLUMN_DIM_OFF_AVA_INT_RAZON_SOCIAL,
            COLUMN_DIM_OFF_AVA_INT_ID_ESTADO,
            COLUMN_DIM_OFF_AVA_INT_DESCRIPCION_ESTADO,
            COLUMN_DIM_OFF_AVA_INT_TIPO_AVISO,
            COLUMN_DIM_OFF_AVA_INT_ESLORA_MAXIMA,
            COLUMN_DIM_OFF_AVA_INT_TRB,
            COLUMN_DIM_OFF_AVA_INT_TRN,
            COLUMN_DIM_OFF_AVA_INT_PAIS,
            COLUMN_DIM_OFF_AVA_INT_TRIPULACION_ARRIBO,
            COLUMN_DIM_OFF_AVA_INT_TIPO_BUQUE,
            COLUMN_DIM_OFF_AVA_INT_TIPO_CARGA,
            COLUMN_DIM_OFF_AVA_INT_CANTIDAD_CARGA,
            COLUMN_DIM_OFF_AVA_INT_MERCANCIAS_PELIGROSAS,
            COLUMN_DIM_OFF_AVA_INT_CANTIDAD_PASAJEROS,
            COLUMN_DIM_OFF_AVA_INT_NOMBRE_CAPITAN_NAVE,
            COLUMN_DIM_OFF_AVA_INT_CALADO_POPA,
            COLUMN_DIM_OFF_AVA_INT_CALADO_PROA,
            COLUMN_DIM_OFF_AVA_INT_PAIS_BANDERA,
            //ARRIBO OPERATIVO
            COLUMN_DIM_OFF_AVA_INT_NUMERO_ARRIBO,
            COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_PRACTICO,
            COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO,
            COLUMN_DIM_OFF_AVA_INT_ID_INSTALACION_PORTUARIA,
            COLUMN_DIM_OFF_AVA_INT_ID_LANCHA_TRANSPORTE_PILOTO,
            COLUMN_DIM_OFF_AVA_INT_ID_REMOLCADOR,
            COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_ARRIBO,
            COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_PILOTO_ABORDO_REPORTE,
            COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_INICIO_MANIOBRA_ATRAQUE,
            COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_FIN_MANIOBRA_ATRAQUE,
            COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ATRAQUE,
            COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ASI_ATRAQUE,
            COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO_ATRAQUE,
            COLUMN_DIM_OFF_AVA_INT_FECHA_INGRESO_AREA_CONTROL,
            COLUMN_DIM_OFF_AVA_INT_ID_PUERTO_DESTINO,

            COLUMN_DIM_OFF_AVA_INT_DIM_OFF_USR_ID_USUARIO
    };

    /*ARRIBO ADMINISTRATIVO CABOTAJE*/
    String TABLE_DIM_OFF_AAD_CAB = "DIM_OFF_AAD_CAB";
    String COLUMN_DIM_OFF_AAD_CAB_ARRIBO_ADMIN = "DIM_OFF_AAD_CAB_ARRIBO_ADMIN";
    String COLUMN_DIM_OFF_AAD_CAB_FECHA_CREACION = "DIM_OFF_AAD_CAB_FECHA_CREACION";
    String COLUMN_DIM_OFF_AAD_CAB_FECHA_LIBRE_PLATICA = "DIM_OFF_AAD_CAB_FECHA_LIBRE_PLATICA";
    String COLUMN_DIM_OFF_AAD_CAB_FECHA_VISITA = "DIM_OFF_AAD_CAB_FECHA_VISITA";
    String COLUMN_DIM_OFF_AAD_CAB_NIT_AGENCIA = "DIM_OFF_AAD_CAB_NIT_AGENCIA";
    String COLUMN_DIM_OFF_AAD_CAB_OBSERVACIONES = "DIM_OFF_AAD_CAB_OBSERVACIONES";
    String COLUMN_DIM_OFF_AAD_CAB_DIM_OFF_USR_ID_USUARIO = "DIM_OFF_USR_ID_USUARIO";
    String COLUMN_DIM_OFF_AAD_CAB_VISITADA = "DIM_OFF_AAD_CAB_VISITADA";
    String COLUMN_DIM_OFF_AAD_CAB_DIM_OFF_AVA_CAB_NUMERO_ARRIBO = "DIM_OFF_AVA_CAB_NUMERO_ARRIBO";
    String COLUMN_DIM_OFF_AAD_CAB_NUMERO_AVISO_ARRIBO = "DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO";
    String[] COLUMNS_DIM_OFF_AAD_CAB = {
            COLUMN_DIM_OFF_AAD_CAB_ARRIBO_ADMIN,
            COLUMN_DIM_OFF_AAD_CAB_FECHA_CREACION,
            COLUMN_DIM_OFF_AAD_CAB_FECHA_LIBRE_PLATICA,
            COLUMN_DIM_OFF_AAD_CAB_FECHA_VISITA,
            COLUMN_DIM_OFF_AAD_CAB_NIT_AGENCIA,
            COLUMN_DIM_OFF_AAD_CAB_OBSERVACIONES,
            COLUMN_DIM_OFF_AAD_CAB_DIM_OFF_USR_ID_USUARIO,
            COLUMN_DIM_OFF_AAD_CAB_VISITADA,
            //COLUMN_DIM_OFF_AAD_CAB_DIM_OFF_AVA_CAB_NUMERO_ARRIBO,
            COLUMN_DIM_OFF_AAD_CAB_NUMERO_AVISO_ARRIBO
    };

    /*ARRIBO ADMINISTRATIVO INTERNACIONAL*/
    String TABLE_DIM_OFF_AAD_INT = "DIM_OFF_AAD_INT";
    String COLUMN_DIM_OFF_AAD_INT_ARRIBO_ADMIN = "DIM_OFF_AAD_INT_ARRIBO_ADMIN";
    String COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_LIBRE_PLATICA = "DIM_OFF_AAD_INT_FECHA_HORA_LIBRE_PLATICA";
    String COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_VISITA = "DIM_OFF_AAD_INT_FECHA_HORA_VISITA";
    String COLUMN_DIM_OFF_AAD_INT_SI_LIBRE_PLATICA = "DIM_OFF_AAD_INT_SI_LIBRE_PLATICA";
    String COLUMN_DIM_OFF_AAD_INT_NUMERO_PASAJEROS_TRANSITO = "DIM_OFF_AAD_INT_NUMERO_PASAJEROS_TRANSITO";
    String COLUMN_DIM_OFF_AAD_INT_OBSERVACIONES = "DIM_OFF_AAD_INT_OBSERVACIONES";
    String COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO = "DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO";
    //String COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_ARRIBO = "DIM_OFF_AVA_INT_NUMERO_ARRIBO";

    String[] COLUMNS_DIM_OFF_AAD_INT = {
            COLUMN_DIM_OFF_AAD_INT_ARRIBO_ADMIN,
            COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_LIBRE_PLATICA,
            COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_VISITA,
            COLUMN_DIM_OFF_AAD_INT_SI_LIBRE_PLATICA,
            COLUMN_DIM_OFF_AAD_INT_NUMERO_PASAJEROS_TRANSITO,
            COLUMN_DIM_OFF_AAD_INT_OBSERVACIONES,
            COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO,
            //COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_ARRIBO
    };

    /*SIGNATURE*/
    String TABLE_DIM_OFF_FIR = "DIM_OFF_FIR";
    String COLUMN_DIM_OFF_FIR_ID = "DIM_OFF_FIR_ID";
    String COLUMN_DIM_OFF_FIR_ID_REPRESENTANTE = "DIM_OFF_FIR_ID_REPRESENTANTE";
    String COLUMN_DIM_OFF_FIR_EMAIL = "DIM_OFF_FIR_EMAIL";
    String COLUMN_DIM_OFF_FIR_NUMERO_AVISO_ARRIBO = "DIM_OFF_FIR_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_FIR_NOMBRE_FUNCIONARIO = "DIM_OFF_FIR_NOMBRE_FUNCIONARIO";
    String COLUMN_DIM_OFF_FIR_CEDULA_FUNCIONARIO = "DIM_OFF_FIR_CEDULA_FUNCIONARIO";
    String COLUMN_DIM_OFF_FIR_FIRMA = "DIM_OFF_FIR_FIRMA";
    //FK
    String COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO = "DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_ARRIBO = "DIM_OFF_AVA_CAB_NUMERO_ARRIBO";
    String COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO = "DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_ARRIBO = "DIM_OFF_AVA_INT_NUMERO_ARRIBO";
    String[] COLUMNS_DIM_OFF_FIR = {
            COLUMN_DIM_OFF_FIR_ID,
            COLUMN_DIM_OFF_FIR_ID_REPRESENTANTE,
            COLUMN_DIM_OFF_FIR_EMAIL,
            COLUMN_DIM_OFF_FIR_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_FIR_NOMBRE_FUNCIONARIO,
            COLUMN_DIM_OFF_FIR_CEDULA_FUNCIONARIO,
            COLUMN_DIM_OFF_FIR_FIRMA,
            COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_ARRIBO,
            COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_ARRIBO
    };


    /*PDF*/
    String TABLE_DIM_OFF_PDF = "DIM_OFF_PDF";
    String COLUMN_DIM_OFF_PDF_ID = "DIM_OFF_PDF_ID";
    String COLUMN_DIM_OFF_PDF_NOMBRE_ARCHIVO = "DIM_OFF_PDF_NOMBRE_ARCHIVO";
    String COLUMN_DIM_OFF_PDF_ARCHIVO = "DIM_OFF_PDF_ARCHIVO";
    //FK
    String COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO = "DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_ARRIBO = "DIM_OFF_AVA_CAB_NUMERO_ARRIBO";
    String COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO = "DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_ARRIBO = "DIM_OFF_AVA_INT_NUMERO_ARRIBO";
    String[] COLUMNS_DIM_OFF_PDF = {
            COLUMN_DIM_OFF_PDF_ID,
            COLUMN_DIM_OFF_PDF_NOMBRE_ARCHIVO,
            COLUMN_DIM_OFF_PDF_ARCHIVO,
            COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO,
            //COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_ARRIBO,
            COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO,
            //COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_ARRIBO
    };

    /*AGENCY*/
    String TABLE_DIM_OFF_AGE = "DIM_OFF_AGE";
    String COLUMN_DIM_OFF_AGE_NIT = "DIM_OFF_AGE_NIT";
    String COLUMN_DIM_OFF_AGE_RAZON_SOCIAL = "DIM_OFF_AGE_RAZON_SOCIAL";
    String[] COLUMNS_DIM_OFF_AGE = {
            COLUMN_DIM_OFF_AGE_NIT,
            COLUMN_DIM_OFF_AGE_RAZON_SOCIAL
    };

    /*PILOT TRANSPORT BOAT*/
    String TABLE_DIM_OFF_LAN_TRA_PIL = "DIM_OFF_LAN_TRA_PIL";
    String COLUMN_DIM_OFF_LAN_TRA_PIL_OMI_MATRICULA = "DIM_OFF_LAN_TRA_PIL_OMI_MATRICULA";
    String COLUMN_DIM_OFF_LAN_TRA_PIL_NOMBRE_NAVE = "DIM_OFF_LAN_TRA_PIL_NOMBRE_NAVE";
    String[] COLUMNS_DIM_OFF_LAN_TRA_PIL = {
            COLUMN_DIM_OFF_LAN_TRA_PIL_OMI_MATRICULA,
            COLUMN_DIM_OFF_LAN_TRA_PIL_NOMBRE_NAVE
    };

    /*PORT INSTALLATION*/
    String TABLE_DIM_OFF_INS_POR = "DIM_OFF_INS_POR";
    String COLUMN_DIM_OFF_INS_POR_CODIGO = "DIM_OFF_INS_POR_CODIGO";
    String COLUMN_DIM_OFF_INS_POR_MUELLE = "DIM_OFF_INS_POR_MUELLE";
    String[] COLUMNS_DIM_OFF_INS_POR = {
            COLUMN_DIM_OFF_INS_POR_CODIGO,
            COLUMN_DIM_OFF_INS_POR_MUELLE
    };

    /*PRACTICAL PILOT*/
    String TABLE_DIM_OFF_PIL_PRA = "DIM_OFF_PIL_PRA";
    String COLUMN_DIM_OFF_PIL_PRA_ID_PILOTO = "DIM_OFF_PIL_PRA_ID_PILOTO";
    String COLUMN_DIM_OFF_PIL_PRA_NOMBRE_PILOTO = "DIM_OFF_PIL_PRA_NOMBRE_PILOTO";
    String COLUMN_DIM_OFF_PIL_PRA_CODIGO_LICENCIA = "DIM_OFF_PIL_PRA_CODIGO_LICENCIA";
    String[] COLUMNS_DIM_OFF_PIL_PRA = {
            COLUMN_DIM_OFF_PIL_PRA_ID_PILOTO,
            COLUMN_DIM_OFF_PIL_PRA_NOMBRE_PILOTO,
            COLUMN_DIM_OFF_PIL_PRA_CODIGO_LICENCIA
    };

    /*PRACTICAL PILOT ASISTENTS*/
    String TABLE_DIM_OFF_PIL_PRA_ASI = "DIM_OFF_PIL_PRA_ASI";
    String COLUMN_DIM_OFF_PIL_PRA_ASI_ID_PILOTO = "DIM_OFF_PIL_PRA_ASI_ID_PILOTO";
    String COLUMN_DIM_OFF_PIL_PRA_ASI_NOMBRE_PILOTO = "DIM_OFF_PIL_PRA_ASI_NOMBRE_PILOTO";
    String COLUMN_DIM_OFF_PIL_PRA_ASI_CODIGO_LICENCIA = "DIM_OFF_PIL_PRA_ASI_CODIGO_LICENCIA";
    String[] COLUMNS_DIM_OFF_PIL_PRA_ASI = {
            COLUMN_DIM_OFF_PIL_PRA_ASI_ID_PILOTO,
            COLUMN_DIM_OFF_PIL_PRA_ASI_NOMBRE_PILOTO,
            COLUMN_DIM_OFF_PIL_PRA_ASI_CODIGO_LICENCIA
    };

    /*REASON ARRIVAL*/
    String TABLE_DIM_OFF_RAZ_ARR = "DIM_OFF_RAZ_ARR";
    String COLUMN_DIM_OFF_RAZ_ARR_CODIGO = "DIM_OFF_RAZ_ARR_CODIGO";
    String COLUMN_DIM_OFF_RAZ_ARR_DESCRIPCION = "DIM_OFF_RAZ_ARR_DESCRIPCION";
    String[] COLUMNS_DIM_OFF_RAZ_ARR = {
            COLUMN_DIM_OFF_RAZ_ARR_CODIGO,
            COLUMN_DIM_OFF_RAZ_ARR_DESCRIPCION
    };

    /*REPRESENTANT*/
    String TABLE_DIM_OFF_REP = "DIM_OFF_REP";
    String COLUMN_DIM_OFF_REP_ID = "DIM_OFF_REP_ID";
    String COLUMN_DIM_OFF_REP_NOMBRE = "DIM_OFF_REP_NOMBRE";
    String[] COLUMNS_DIM_OFF_REP = {
            COLUMN_DIM_OFF_REP_ID,
            COLUMN_DIM_OFF_REP_NOMBRE
    };

    /*TRAINING PILOT*/
    String TABLE_DIM_OFF_PIL_ENT = "DIM_OFF_PIL_ENT";
    String COLUMN_DIM_OFF_PIL_ENT_ID_PILOTO = "DIM_OFF_PIL_ENT_ID_PILOTO";
    String COLUMN_DIM_OFF_PIL_ENT_NOMBRE_PILOTO = "DIM_OFF_PIL_ENT_NOMBRE_PILOTO";
    String COLUMN_DIM_OFF_PIL_ENT_CODIGO_LICENCIA = "DIM_OFF_PIL_ENT_CODIGO_LICENCIA";
    String[] COLUMNS_DIM_OFF_PIL_ENT = {
            COLUMN_DIM_OFF_PIL_ENT_ID_PILOTO,
            COLUMN_DIM_OFF_PIL_ENT_NOMBRE_PILOTO,
            COLUMN_DIM_OFF_PIL_ENT_CODIGO_LICENCIA
    };

    /*TUG BOAT*/
    String TABLE_DIM_OFF_REM = "DIM_OFF_REM";
    String COLUMN_DIM_OFF_REM_OMI_MATRICULA = "DIM_OFF_REM_OMI_MATRICULA";
    String COLUMN_DIM_OFF_REM_NOMBRE_NAVE = "DIM_OFF_REM_NOMBRE_NAVE";
    String[] COLUMNS_DIM_OFF_REM = {
            COLUMN_DIM_OFF_REM_OMI_MATRICULA,
            COLUMN_DIM_OFF_REM_NOMBRE_NAVE
    };

    /*REMOLCADORES*/
    String TABLE_DIM_OFF_REM2 = "DIM_OFF_REM2";
    String COLUMN_DIM_OFF_REM2_OMI_MATRICULA = "COLUMN_DIM_OFF_REM2_OMI_MATRICULA";
    String COLUMN_DIM_OFF_REM2_NOMBRE_NAVE = "COLUMN_DIM_OFF_REM2_NOMBRE_NAVE";
    String COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO = "COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO = "COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_ARRIBO = "COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_ARRIBO";
    String COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_ARRIBO = "COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_ARRIBO";
    String[] COLUMNS_DIM_OFF_REM2 = {
            COLUMN_DIM_OFF_REM2_OMI_MATRICULA,
            COLUMN_DIM_OFF_REM2_NOMBRE_NAVE,
            COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_ARRIBO,
            COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_ARRIBO
    };

    /*Pilotos Asistentes*/
    String TABLE_DIM_OFF_PIL_ASI = "DIM_OFF_PIL_ASI";
    String COLUMN_DIM_OFF_PIL_ASI_ID_PILOTO = "DIM_OFF_PIL_ASI_ID_PILOTO";
    String COLUMN_DIM_OFF_PIL_ASI_NOMBRE_PILOTO = "DIM_OFF_PIL_ASI_NOMBRE_PILOTO";
    String COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_AVISO_ARRIBO = "COLUMN_DIM_OFF_ASI_AVA_CAB_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_AVISO_ARRIBO = "COLUMN_DIM_OFF_ASI_AVA_INT_NUMERO_AVISO_ARRIBO";
    String COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_ARRIBO = "COLUMN_DIM_OFF_ASI_AVA_CAB_NUMERO_ARRIBO";
    String COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_ARRIBO = "COLUMN_DIM_OFF_ASI_AVA_INT_NUMERO_ARRIBO";
    String COLUMN_DIM_OFF_PIL_ASI_CODIGO_LICENCIA = "COLUMN_DIM_OFF_PIL_ASI_CODIGO_ASISTENCIA";
    String[] COLUMNS_DIM_OFF_PIL_ASI = {
            COLUMN_DIM_OFF_PIL_ASI_ID_PILOTO,
            COLUMN_DIM_OFF_PIL_ASI_NOMBRE_PILOTO,
            COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_AVISO_ARRIBO,
            COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_ARRIBO,
            COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_ARRIBO,
            COLUMN_DIM_OFF_PIL_ASI_CODIGO_LICENCIA
    };
}