package com.sbi.dimar.visitaoficialarribooffline.app.database.dao.internacional;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AbstractFacade;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.AvisoArriboInternacionalEntity.AVISO_ARRIBO_INTERNACIONAL_BD;
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

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class implements the interface {@link AvisoArriboInternacionalDAO}, is responsible for obtaining data from the database.
 */
public class AvisoArriboInternacionalDAOImpl extends AbstractFacade implements AvisoArriboInternacionalDAO {
    private static final String TAG = AvisoArriboInternacionalDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public AvisoArriboInternacionalDAOImpl(SQLiteDatabase sqLiteDatabase, List<AvisoArriboInternacionalTO> avisoArriboInternacionalTOS, UserTO userTO) {
        super(AVISO_ARRIBO_INTERNACIONAL_BD.table(), AVISO_ARRIBO_INTERNACIONAL_BD.columnsWhitValues(avisoArriboInternacionalTOS, userTO), AVISO_ARRIBO_INTERNACIONAL_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<AvisoArriboInternacionalTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<AvisoArriboInternacionalTO> avisoArriboInternacionalTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            avisoArriboInternacionalTOS = new ArrayList<>();
            do {
                AvisoArriboInternacionalTO avisoArriboInternacionalTO = cursorToRecordTO(cursor);
                if (avisoArriboInternacionalTO != null) {
                    avisoArriboInternacionalTOS.add(avisoArriboInternacionalTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return avisoArriboInternacionalTOS;
    }

    @Override
    public List<AvisoArriboInternacionalTO> findAllRecordsByState() {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMN_DIM_OFF_AVA_INT_ID_ESTADO).append(" = ? \n");
        String[] whereArgs = {String.valueOf(EstadoEntity.ESTADO_VISITADO.getValue())};
        Cursor cursor = super.findAllByArguments(whereClause.toString(), whereArgs);
        List<AvisoArriboInternacionalTO> avisoArriboInternacionalTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            avisoArriboInternacionalTOS = new ArrayList<>();
            do {
                AvisoArriboInternacionalTO avisoArriboInternacionalTO = cursorToRecordTO(cursor);
                if (avisoArriboInternacionalTO != null) {
                    avisoArriboInternacionalTOS.add(avisoArriboInternacionalTO);
                }
                Log.i(TAG, "findAllRecordsByState");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return avisoArriboInternacionalTOS;
    }


    @Override
    public AvisoArriboInternacionalTO findAvisoById(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "findAvisoById");
        AvisoArriboInternacionalTO avisoArriboInternacionalTO = null;
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMN_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        whereClause.append("AND \n");
        whereClause.append(COLUMN_DIM_OFF_AVA_INT_TIPO_AVISO).append(" = ? \n");
        String[] whereArgs = {String.valueOf(numeroAviso), tipoAviso};

        Cursor cursor = super.findAllByArguments(whereClause.toString(), whereArgs);

        if (cursor != null && cursor.moveToFirst() && !cursor.isClosed()) {
            avisoArriboInternacionalTO = cursorToRecordTO(cursor);
            cursor.close();
        }
        return avisoArriboInternacionalTO;
    }

    @Override
    public boolean updateRecord(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "updateRecord");
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMN_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        whereClause.append("AND \n");
        whereClause.append(COLUMN_DIM_OFF_AVA_INT_TIPO_AVISO).append(" = ? \n");
        String[] whereArgs = {String.valueOf(numeroAviso), tipoAviso};
        return super.updateRecord(whereClause.toString(), whereArgs);
    }

    @Override
    public AvisoArriboInternacionalTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO ");
        try {
            if (cursor != null && cursor.getCount() > 0) {

                AvisoArriboInternacionalTO avisoArriboInternacionalTO = new AvisoArriboInternacionalTO();
                avisoArriboInternacionalTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO)));
                avisoArriboInternacionalTO.setOmiMatricula(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_OMI_MATRICULA)));
                avisoArriboInternacionalTO.setNombreNave(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_NOMBRE_NAVE)));
                avisoArriboInternacionalTO.setTipoAviso(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_TIPO_AVISO)));
                avisoArriboInternacionalTO.setCantidadCarga(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_CANTIDAD_CARGA)));
                avisoArriboInternacionalTO.setCantidadPasajeros(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_CANTIDAD_PASAJEROS)));
                avisoArriboInternacionalTO.setDescripcionEstado(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_DESCRIPCION_ESTADO)));
                avisoArriboInternacionalTO.setIdEstado(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_ESTADO)));
                avisoArriboInternacionalTO.setEsloraMaxima(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ESLORA_MAXIMA)));
                avisoArriboInternacionalTO.setEta(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ETA)));
                avisoArriboInternacionalTO.setFecha(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_FECHA)));
                avisoArriboInternacionalTO.setDesechosPuerto(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_MERCANCIAS_PELIGROSAS))));
                avisoArriboInternacionalTO.setNitAgencia(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_NIT_AGENCIA)));
                avisoArriboInternacionalTO.setNombreCapitanNave(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_NOMBRE_CAPITAN_NAVE)));
                avisoArriboInternacionalTO.setNombreNave(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_NOMBRE_NAVE)));
                avisoArriboInternacionalTO.setNombrePuertoDestino(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_NOMBRE_PUERTO_DESTINO)));
                avisoArriboInternacionalTO.setPais(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_PAIS)));
                avisoArriboInternacionalTO.setPaisBandera(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_PAIS_BANDERA)));
                avisoArriboInternacionalTO.setPuertoProcedencia(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_PUERTO_PROCEDENCIA)));
                avisoArriboInternacionalTO.setRazonSocial(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_RAZON_SOCIAL)));
                avisoArriboInternacionalTO.setTipoBuque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_TIPO_BUQUE)));
                avisoArriboInternacionalTO.setTipoCarga(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_TIPO_CARGA)));
                avisoArriboInternacionalTO.setTrb(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_TRB)));
                avisoArriboInternacionalTO.setTripulacionArribo(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_TRIPULACION_ARRIBO)));
                avisoArriboInternacionalTO.setTrn(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_TRN)));
                avisoArriboInternacionalTO.setIdUsuario(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_DIM_OFF_USR_ID_USUARIO)));
                avisoArriboInternacionalTO.setIdPuertoDestino(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_PUERTO_DESTINO)));

                AvisoArriboInternacionalTO.ArriboOperativoInternacionalTO arrOperInternacionalTO = new AvisoArriboInternacionalTO.ArriboOperativoInternacionalTO();

                arrOperInternacionalTO.setNumeroArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_NUMERO_ARRIBO)));
                arrOperInternacionalTO.setIdPilotoPractico(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_PRACTICO)));
                arrOperInternacionalTO.setIdPilotoEntrenamiento(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO)));
                arrOperInternacionalTO.setIdInstalacionportuaria(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_INSTALACION_PORTUARIA)));
                arrOperInternacionalTO.setIdLanchaTransportePiloto(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_LANCHA_TRANSPORTE_PILOTO)));
                arrOperInternacionalTO.setIdRemolcador(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_REMOLCADOR)));
                arrOperInternacionalTO.setFechaHoraArribo(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_ARRIBO)));
                arrOperInternacionalTO.setFechaHoraPilotoAbordoReporte(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_PILOTO_ABORDO_REPORTE)));
                arrOperInternacionalTO.setFechaHoraInicioManiobraAtraque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_INICIO_MANIOBRA_ATRAQUE)));
                arrOperInternacionalTO.setFechaHoraFinManiobraAtraque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_FECHA_HORA_FIN_MANIOBRA_ATRAQUE)));
                arrOperInternacionalTO.setIdPilotoAtraque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ATRAQUE)));
                arrOperInternacionalTO.setIdPilotoEntrenamientoAtraque(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_ID_PILOTO_ENTRENAMIENTO_ATRAQUE)));
                arrOperInternacionalTO.setCaladoProa(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_CALADO_PROA)));
                arrOperInternacionalTO.setCaladoPopa(cursor.getDouble(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_CALADO_POPA)));
                arrOperInternacionalTO.setFechaIngresoAreaControl(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AVA_INT_FECHA_INGRESO_AREA_CONTROL)));
                avisoArriboInternacionalTO.setArriboOperInternacionalTO(arrOperInternacionalTO);

                AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO arrAdminInternacionalTO = new AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO();
                avisoArriboInternacionalTO.setArriboAdminInternacionalTO(arrAdminInternacionalTO);

                return avisoArriboInternacionalTO;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
