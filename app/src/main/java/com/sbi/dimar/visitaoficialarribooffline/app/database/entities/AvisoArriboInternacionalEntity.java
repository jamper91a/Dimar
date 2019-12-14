package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.RemolcadoresDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.RemolcadoresDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_AVA_INT;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_CALADO_POPA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_CALADO_PROA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_CANTIDAD_CARGA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_CANTIDAD_PASAJEROS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_DESCRIPCION_ESTADO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_DIM_OFF_USR_ID_USUARIO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ESLORA_MAXIMA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ETA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_FECHA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_FIN_MANIOBRA_ATRAQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_INICIO_MANIOBRA_ATRAQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_PILOTO_ABORDO_REPORTE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_FECHA_INGRESO_AREA_CONTROL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_ESTADO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_INSTALACION_PORTUARIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_LANCHA_TRANSPORTE_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ASI_ATRAQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ATRAQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO_ATRAQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_PRACTICO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_PUERTO_DESTINO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_ID_REMOLCADOR;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_MERCANCIAS_PELIGROSAS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_NIT_AGENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_NOMBRE_CAPITAN_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_NOMBRE_PUERTO_DESTINO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_NUMERO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_OMI_MATRICULA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_PAIS;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_PAIS_BANDERA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_PUERTO_PROCEDENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_RAZON_SOCIAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_TIPO_AVISO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_TIPO_BUQUE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_TIPO_CARGA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_TRB;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_TRIPULACION_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AVA_INT_TRN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_AVA_INT;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_AVA_INT table
 */
public enum AvisoArriboInternacionalEntity {
    AVISO_ARRIBO_INTERNACIONAL_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_AVA_INT;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<AvisoArriboInternacionalTO> objects, UserTO userTO) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    values.put(COLUMN_DIM_OFF_AVA_INT_OMI_MATRICULA, objects.get(i).getOmiMatricula());
                    values.put(COLUMN_DIM_OFF_AVA_INT_NOMBRE_NAVE, objects.get(i).getNombreNave());
                    values.put(COLUMN_DIM_OFF_AVA_INT_NOMBRE_PUERTO_DESTINO, objects.get(i).getNombrePuertoDestino());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ETA, objects.get(i).getEta());
                    values.put(COLUMN_DIM_OFF_AVA_INT_FECHA, objects.get(i).getFecha());
                    values.put(COLUMN_DIM_OFF_AVA_INT_PUERTO_PROCEDENCIA, objects.get(i).getPuertoProcedencia());
                    values.put(COLUMN_DIM_OFF_AVA_INT_NIT_AGENCIA, objects.get(i).getNitAgencia());
                    values.put(COLUMN_DIM_OFF_AVA_INT_RAZON_SOCIAL, objects.get(i).getRazonSocial());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ID_ESTADO, objects.get(i).getIdEstado());
                    values.put(COLUMN_DIM_OFF_AVA_INT_DESCRIPCION_ESTADO, objects.get(i).getDescripcionEstado());
                    values.put(COLUMN_DIM_OFF_AVA_INT_TIPO_AVISO, objects.get(i).getTipoAviso());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ESLORA_MAXIMA, objects.get(i).getEsloraMaxima());
                    values.put(COLUMN_DIM_OFF_AVA_INT_TRB, objects.get(i).getTrb());
                    values.put(COLUMN_DIM_OFF_AVA_INT_TRN, objects.get(i).getTrn());
                    values.put(COLUMN_DIM_OFF_AVA_INT_PAIS, objects.get(i).getPais());
                    values.put(COLUMN_DIM_OFF_AVA_INT_TRIPULACION_ARRIBO, objects.get(i).getTripulacionArribo());
                    values.put(COLUMN_DIM_OFF_AVA_INT_TIPO_BUQUE, objects.get(i).getTipoBuque());
                    values.put(COLUMN_DIM_OFF_AVA_INT_TIPO_CARGA, objects.get(i).getTipoCarga());
                    values.put(COLUMN_DIM_OFF_AVA_INT_CANTIDAD_CARGA, objects.get(i).getCantidadCarga());
                    values.put(COLUMN_DIM_OFF_AVA_INT_MERCANCIAS_PELIGROSAS, objects.get(i).isDesechosPuerto());
                    values.put(COLUMN_DIM_OFF_AVA_INT_CANTIDAD_PASAJEROS, objects.get(i).getCantidadPasajeros());
                    values.put(COLUMN_DIM_OFF_AVA_INT_NOMBRE_CAPITAN_NAVE, objects.get(i).getNombreCapitanNave());
                    values.put(COLUMN_DIM_OFF_AVA_INT_PAIS_BANDERA, objects.get(i).getPaisBandera());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ID_PUERTO_DESTINO, objects.get(i).getIdPuertoDestino());

                    //usuario
                    if (objects.get(i).getIdUsuario() == 0) {
                        objects.get(i).setIdUsuario(userTO.getIdUsuario());
                    }
                    values.put(COLUMN_DIM_OFF_AVA_INT_DIM_OFF_USR_ID_USUARIO, objects.get(i).getIdUsuario());


                    //ARRIBO OPERATIVO
                    values.put(COLUMN_DIM_OFF_AVA_INT_NUMERO_ARRIBO, objects.get(i).getArriboOperInternacionalTO().getNumeroArribo() == 0 ? (int) (Math.random() * 5000 + 1) : objects.get(i).getArriboOperInternacionalTO().getNumeroArribo());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_PRACTICO, objects.get(i).getArriboOperInternacionalTO().getIdPilotoPractico());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO, objects.get(i).getArriboOperInternacionalTO().getIdPilotoEntrenamiento());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ATRAQUE, objects.get(i).getArriboOperInternacionalTO().getIdPilotoAtraque());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO_ATRAQUE, objects.get(i).getArriboOperInternacionalTO().getIdPilotoEntrenamientoAtraque());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ID_INSTALACION_PORTUARIA, objects.get(i).getArriboOperInternacionalTO().getIdInstalacionportuaria());
                    values.put(COLUMN_DIM_OFF_AVA_INT_ID_LANCHA_TRANSPORTE_PILOTO, objects.get(i).getArriboOperInternacionalTO().getIdLanchaTransportePiloto());
                    values.put(COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_ARRIBO, objects.get(i).getArriboOperInternacionalTO().getFechaHoraArribo());
                    values.put(COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_PILOTO_ABORDO_REPORTE, objects.get(i).getArriboOperInternacionalTO().getFechaHoraPilotoAbordoReporte());
                    values.put(COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_INICIO_MANIOBRA_ATRAQUE, objects.get(i).getArriboOperInternacionalTO().getFechaHoraInicioManiobraAtraque());
                    values.put(COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_FIN_MANIOBRA_ATRAQUE, objects.get(i).getArriboOperInternacionalTO().getFechaHoraFinManiobraAtraque());
                    values.put(COLUMN_DIM_OFF_AVA_INT_CALADO_POPA, objects.get(i).getArriboOperInternacionalTO().getCaladoPopa());
                    values.put(COLUMN_DIM_OFF_AVA_INT_CALADO_PROA, objects.get(i).getArriboOperInternacionalTO().getCaladoProa());
                    values.put(COLUMN_DIM_OFF_AVA_INT_FECHA_INGRESO_AREA_CONTROL, objects.get(i).getArriboOperInternacionalTO().getFechaIngresoAreaControl());


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
            return COLUMNS_DIM_OFF_AVA_INT;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<AvisoArriboInternacionalTO> objects, UserTO userTO);
}
