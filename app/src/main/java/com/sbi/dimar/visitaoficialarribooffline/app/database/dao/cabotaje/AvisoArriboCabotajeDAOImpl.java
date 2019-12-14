package com.sbi.dimar.visitaoficialarribooffline.app.database.dao.cabotaje;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AbstractFacade;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.AvisoArriboCabotajeEntity.AVISO_ARRIBO_CABOTAJE_BD;
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

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class implements the interface {@link AvisoArriboCabotajeDAO}, is responsible for obtaining data from the database.
 */
public class AvisoArriboCabotajeDAOImpl extends AbstractFacade implements AvisoArriboCabotajeDAO {
    private static final String TAG = AvisoArriboCabotajeDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public AvisoArriboCabotajeDAOImpl(SQLiteDatabase sqLiteDatabase, List<AvisoArriboCabotageTO> avisoArriboCabotageTOS, UserTO userTO) {
        super(AVISO_ARRIBO_CABOTAJE_BD.table(), AVISO_ARRIBO_CABOTAJE_BD.columnsWhitValues(avisoArriboCabotageTOS, userTO), AVISO_ARRIBO_CABOTAJE_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<AvisoArriboCabotageTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<AvisoArriboCabotageTO> avisoArriboCabotageTOS = null;
        if (cursor != null && cursor.moveToFirst() && !cursor.isClosed()) {
            avisoArriboCabotageTOS = new ArrayList<>();
            do {
                AvisoArriboCabotageTO avisoArriboCabotageTO = cursorToRecordTO(cursor);
                if (avisoArriboCabotageTO != null) {
                    avisoArriboCabotageTOS.add(avisoArriboCabotageTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return avisoArriboCabotageTOS;
    }

    @Override
    public List<AvisoArriboCabotageTO> findAllRecordsByState() {
        List<AvisoArriboCabotageTO> avisoArriboCabotageTOS = null;
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMN_DIM_OFF_AVA_CAB_ID_ESTADO_ACTUAL).append(" = ? \n");
        String[] whereArgs = {String.valueOf(EstadoEntity.ESTADO_VISITADO.getValue())};
        Cursor cursor = super.findAllByArguments(whereClause.toString(), whereArgs);

        if (cursor != null && cursor.moveToFirst() && !cursor.isClosed()) {
            avisoArriboCabotageTOS = new ArrayList<>();
            do {
                AvisoArriboCabotageTO avisoArriboCabotageTO = cursorToRecordTO(cursor);
                if (avisoArriboCabotageTO != null) {
                    avisoArriboCabotageTOS.add(avisoArriboCabotageTO);
                }
                Log.i(TAG, "findAllRecordsByState");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return avisoArriboCabotageTOS;
    }

    @Override
    public boolean updateRecord(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "updateRecord");
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMN_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        whereClause.append("AND \n");
        whereClause.append(COLUMN_DIM_OFF_AVA_CAB_TIPO_AVISO).append(" = ? \n");
        String[] whereArgs = {String.valueOf(numeroAviso), tipoAviso};
        return super.updateRecord(whereClause.toString(), whereArgs);
    }


    @Override
    public AvisoArriboCabotageTO findAvisoById(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "findAvisoById");
        AvisoArriboCabotageTO avisoArriboCabotageTO = null;
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMN_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        whereClause.append("AND \n");
        whereClause.append(COLUMN_DIM_OFF_AVA_CAB_TIPO_AVISO).append(" = ? \n");
        String[] whereArgs = {String.valueOf(numeroAviso), tipoAviso};

        Cursor cursor = super.findAllByArguments(whereClause.toString(), whereArgs);

        if (cursor != null && cursor.moveToFirst() && !cursor.isClosed()) {
            avisoArriboCabotageTO = cursorToRecordTO(cursor);
        }
        cursor.close();

        return avisoArriboCabotageTO;
    }

    @Override
    public AvisoArriboCabotageTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO ");
        try {
            if (cursor != null && cursor.getCount() > 0) {

                AvisoArriboCabotageTO avisoArriboCabotageTO = new AvisoArriboCabotageTO();
                avisoArriboCabotageTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO)));
                avisoArriboCabotageTO.setOmiMatricula(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_MATRICULA)));
                avisoArriboCabotageTO.setNombreNave(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_NOMBRE_NAVE)));
                avisoArriboCabotageTO.setTipoAviso(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_TIPO_AVISO)));
                avisoArriboCabotageTO.setIdPais(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_ID_PAIS)));
                avisoArriboCabotageTO.setPais(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_PAIS)));
                avisoArriboCabotageTO.setEta(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_ETA)));
                avisoArriboCabotageTO.setMuelleOrigen(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_MUELLE_ORIGEN)));
                avisoArriboCabotageTO.setCapitaniaOrigen(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_ORIGEN)));
                avisoArriboCabotageTO.setMuelleDestino(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_MUELLE_DESTINO)));
                avisoArriboCabotageTO.setCapitaniaDestino(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_CAPITANIA_DESTINO)));
                avisoArriboCabotageTO.setNombrePuertoDestino(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_NOMBRE_PUERTO_DESTINO)));
                avisoArriboCabotageTO.setEsloraMaxima(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_ESLORA_MAXIMA)));
                avisoArriboCabotageTO.setTrb(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_TRB)));
                avisoArriboCabotageTO.setTrn(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_TRN)));
                avisoArriboCabotageTO.setTripulantesSolicitudZarpe(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_TRIPULANTES_SOLICITUD_ZARPE)));
                avisoArriboCabotageTO.setTipoBuque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_TIPO_BUQUE)));
                avisoArriboCabotageTO.setTipoCarga(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_TIPO_CARGA)));
                avisoArriboCabotageTO.setCantidadCarga(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_CARGA)));
                avisoArriboCabotageTO.setCantidadPasajeros(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_CANTIDAD_PASAJEROS)));
                avisoArriboCabotageTO.setResponsable(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_RESPONSABLE_NAVE)));
                avisoArriboCabotageTO.setIdEstado(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_ID_ESTADO_ACTUAL)));
                avisoArriboCabotageTO.setEstado(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_ESTADO)));
                avisoArriboCabotageTO.setIdAgencia(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_RAZON_SOCIAL)));
                avisoArriboCabotageTO.setIdPuertoDestino(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_ID_PUERTO_DESTINO)));
                avisoArriboCabotageTO.setFecha(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_FECHA_AVISO)));

                //ARRIBO OPERATIVO
                AvisoArriboCabotageTO.ArriboOperativoCabotageTO operativoCabotageTO = new AvisoArriboCabotageTO.ArriboOperativoCabotageTO();
                operativoCabotageTO.setNumeroArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_NUMERO_ARRIBO)));
                operativoCabotageTO.setCalado(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_CALADO)));
                operativoCabotageTO.setFechaAtraque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_FECHA_ATRAQUE)));
                operativoCabotageTO.setFechaIngresoAreaControl(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_FECHA_INGRESO_AREA_CONTROL)));
                operativoCabotageTO.setIdRazonArribo(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_ID_RAZON_ARRIBO)));
                operativoCabotageTO.setRazonArribo(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_RAZON_ARRIBO)));
                operativoCabotageTO.setMuelleAtraque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_ID_MUELLE_ATRAQUE)));
                operativoCabotageTO.setNombreMuelleAtraque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_MUELLE_ATRAQUE)));
                operativoCabotageTO.setObservaciones(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_OBSERVACIONES)));
                operativoCabotageTO.setFechaHoraArribo(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_FECHA_HORA_ARRIBO)));
                operativoCabotageTO.setIdUsuario(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_CAB_DIM_OFF_USR_ID_USUARIO)));
                avisoArriboCabotageTO.setArriboOperCabotageTO(operativoCabotageTO);

                return avisoArriboCabotageTO;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
