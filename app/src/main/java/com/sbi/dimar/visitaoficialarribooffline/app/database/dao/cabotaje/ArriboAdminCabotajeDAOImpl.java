package com.sbi.dimar.visitaoficialarribooffline.app.database.dao.cabotaje;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AbstractFacade;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;

import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.ArriboAdministrativoCabotajeEntity.ARRIBO_ADMINISTRATIVO_CABOTAJE_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_ARRIBO_ADMIN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_DIM_OFF_USR_ID_USUARIO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_FECHA_CREACION;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_FECHA_LIBRE_PLATICA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_FECHA_VISITA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_NIT_AGENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_OBSERVACIONES;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_VISITADA;

/**
 * Created by Jenny Galindo on 15/5/2018
 * <p>
 * This class implements the interface {@link ArriboAdminCabotajeDAO}, is responsible for obtaining data from the database.
 */
public class ArriboAdminCabotajeDAOImpl extends AbstractFacade implements ArriboAdminCabotajeDAO {
    private static final String TAG = ArriboAdminCabotajeDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public ArriboAdminCabotajeDAOImpl(SQLiteDatabase sqLiteDatabase, List<AvisoArriboCabotageTO> avisoArriboCabotageTOS, UserTO userTO) {
        super(ARRIBO_ADMINISTRATIVO_CABOTAJE_BD.table(), ARRIBO_ADMINISTRATIVO_CABOTAJE_BD.columnsWhitValues(avisoArriboCabotageTOS, userTO), ARRIBO_ADMINISTRATIVO_CABOTAJE_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean updateRecord(long numeroArriboAdminInt) {
        return false;
    }

    @Override
    public List<AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO> findAllRecords() {
        return null;
    }

    @Override
    public AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO findArriboAdminById(long numeroAviso) {
        Log.i(TAG, "findArriboAdminById");
        AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO arriboAdministrativoCabotageTO = new AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMN_DIM_OFF_AAD_CAB_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        String[] whereArgs = {String.valueOf(numeroAviso)};
        Cursor cursor = super.findAllByArguments(whereClause.toString(), whereArgs);
        if (cursor != null && cursor.moveToFirst() && !cursor.isClosed()) {
            arriboAdministrativoCabotageTO = cursorToRecordTO(cursor);
            cursor.close();
        }
        return arriboAdministrativoCabotageTO;
    }

    @Override
    public AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO ");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO arrAdminCabotageTO = new AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO();
                arrAdminCabotageTO.setArriboAdminitrativoId(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_ARRIBO_ADMIN)));
                arrAdminCabotageTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_NUMERO_AVISO_ARRIBO)));
                arrAdminCabotageTO.setFechaCreacion(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_FECHA_CREACION)));
                arrAdminCabotageTO.setFechaLibrePlatica(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_FECHA_LIBRE_PLATICA)));
                arrAdminCabotageTO.setFechaVisita(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_FECHA_VISITA)));
                arrAdminCabotageTO.setNitAgencia(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_NIT_AGENCIA)));
                arrAdminCabotageTO.setIdUsuario(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_DIM_OFF_USR_ID_USUARIO)));
                arrAdminCabotageTO.setObservaciones(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_OBSERVACIONES)));
                arrAdminCabotageTO.setVisitada(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_CAB_VISITADA)) == 1);
                return arrAdminCabotageTO;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;

        }
    }
}
