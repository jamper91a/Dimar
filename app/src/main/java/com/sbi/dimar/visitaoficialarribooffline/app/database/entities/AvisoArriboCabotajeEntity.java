package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_AVA_CAB;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_CALADO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_CARGA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_PASAJEROS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_DESTINO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_ORIGEN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_DIM_OFF_USR_ID_USUARIO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_ESLORA_MAXIMA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_ESTADO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_ETA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_FECHA_ATRAQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_FECHA_AVISO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_FECHA_HORA_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_FECHA_INGRESO_AREA_CONTROL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_ID_ESTADO_ACTUAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_ID_MUELLE_ATRAQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_ID_PAIS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_ID_PUERTO_DESTINO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_ID_RAZON_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_MATRICULA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_MUELLE_ATRAQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_MUELLE_DESTINO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_MUELLE_ORIGEN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_NOMBRE_PUERTO_DESTINO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_NUMERO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_OBSERVACIONES;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_PAIS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_RAZON_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_RAZON_SOCIAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_RESPONSABLE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_TIPO_AVISO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_TIPO_BUQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_TIPO_CARGA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_TRB;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_TRIPULANTES_SOLICITUD_ZARPE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_CAB_TRN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_AVA_CAB;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_AVA_CAB table
 */
public enum AvisoArriboCabotajeEntity {

    AVISO_ARRIBO_CABOTAJE_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_AVA_CAB;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<AvisoArriboCabotageTO> objects, UserTO userTO) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_MATRICULA, objects.get(i).getOmiMatricula());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_NOMBRE_NAVE, objects.get(i).getNombreNave());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_ID_PAIS, objects.get(i).getIdPais());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_PAIS, objects.get(i).getPais());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_ETA, objects.get(i).getEta());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_MUELLE_ORIGEN, objects.get(i).getMuelleOrigen());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_ORIGEN, objects.get(i).getCapitaniaOrigen());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_MUELLE_DESTINO, objects.get(i).getMuelleDestino());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_DESTINO, objects.get(i).getCapitaniaDestino());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_NOMBRE_PUERTO_DESTINO, objects.get(i).getNombrePuertoDestino());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_TIPO_AVISO, objects.get(i).getTipoAviso());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_ESLORA_MAXIMA, objects.get(i).getEsloraMaxima());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_TRB, objects.get(i).getTrb());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_TRN, objects.get(i).getTrn());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_TRIPULANTES_SOLICITUD_ZARPE, objects.get(i).getTripulantesSolicitudZarpe());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_TIPO_BUQUE, objects.get(i).getTipoBuque());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_TIPO_CARGA, objects.get(i).getTipoCarga());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_CARGA, objects.get(i).getCantidadCarga());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_PASAJEROS, objects.get(i).getCantidadPasajeros());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_RESPONSABLE_NAVE, objects.get(i).getResponsable());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_ID_ESTADO_ACTUAL, objects.get(i).getIdEstado());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_ESTADO, objects.get(i).getEstado());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_RAZON_SOCIAL, objects.get(i).getIdAgencia());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_FECHA_AVISO, objects.get(i).getFecha());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_ID_PUERTO_DESTINO, objects.get(i).getIdPuertoDestino());

                    //ARRIBO OPERATIVO
                    values.put(COLUMN_DIM_OFF_AVA_CAB_NUMERO_ARRIBO, objects.get(i).getArriboOperCabotageTO().getNumeroArribo() == 0 ? (int) (Math.random() * 5000 + 1) : objects.get(i).getArriboOperCabotageTO().getNumeroArribo());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_CALADO, objects.get(i).getArriboOperCabotageTO().getCalado());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_FECHA_ATRAQUE, objects.get(i).getArriboOperCabotageTO().getFechaAtraque());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_FECHA_INGRESO_AREA_CONTROL, objects.get(i).getArriboOperCabotageTO().getFechaIngresoAreaControl());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_ID_RAZON_ARRIBO, objects.get(i).getArriboOperCabotageTO().getIdRazonArribo());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_RAZON_ARRIBO, objects.get(i).getArriboOperCabotageTO().getRazonArribo());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_ID_MUELLE_ATRAQUE, objects.get(i).getArriboOperCabotageTO().getMuelleAtraque());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_MUELLE_ATRAQUE, objects.get(i).getArriboOperCabotageTO().getNombreMuelleAtraque());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_OBSERVACIONES, objects.get(i).getArriboOperCabotageTO().getObservaciones());
                    values.put(COLUMN_DIM_OFF_AVA_CAB_FECHA_HORA_ARRIBO, objects.get(i).getArriboOperCabotageTO().getFechaHoraArribo());

                    if (objects.get(i).getArriboOperCabotageTO().getIdUsuario() == 0) {
                        objects.get(i).getArriboOperCabotageTO().setIdUsuario(userTO.getIdUsuario());
                    }
                    values.put(COLUMN_DIM_OFF_AVA_CAB_DIM_OFF_USR_ID_USUARIO, objects.get(i).getArriboOperCabotageTO().getIdUsuario());

                    contentValues.add(values);
                    //break;
                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_AVA_CAB;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<AvisoArriboCabotageTO> objects, UserTO userTO);
}
