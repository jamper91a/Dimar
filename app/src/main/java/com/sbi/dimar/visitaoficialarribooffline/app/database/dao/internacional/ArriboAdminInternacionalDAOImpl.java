package com.sbi.dimar.visitaoficialarribooffline.app.database.dao.internacional;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AbstractFacade;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;

import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.ArriboAdministrativoInternacionalEntity.ARRIBO_ADMINISTRATIVO_INTERNACIONAL_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_ARRIBO_ADMIN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_LIBRE_PLATICA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_VISITA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_NUMERO_PASAJEROS_TRANSITO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_OBSERVACIONES;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_SI_LIBRE_PLATICA;

/**
 * Created by Jenny Galindo on 11/5/2018
 *
 * This class implements the interface {@link ArriboAdminInternacionalDAO}, it is responsible for obtaining data from the database.
 */
public class ArriboAdminInternacionalDAOImpl extends AbstractFacade implements ArriboAdminInternacionalDAO {
    private static final String TAG = ArriboAdminInternacionalDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public ArriboAdminInternacionalDAOImpl(SQLiteDatabase sqLiteDatabase, List<AvisoArriboInternacionalTO> avisoArriboInternacionalTOS) {
        super(ARRIBO_ADMINISTRATIVO_INTERNACIONAL_BD.table(), ARRIBO_ADMINISTRATIVO_INTERNACIONAL_BD.columnsWhitValues(avisoArriboInternacionalTOS), ARRIBO_ADMINISTRATIVO_INTERNACIONAL_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean updateRecord(long numeroArriboAdminInt) {
        return false;
    }

    @Override
    public List<AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO> findAllRecords() {
        return null;
    }

    @Override
    public AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO findArriboAdminById(long numeroAviso) {
        Log.i(TAG, "findArriboAdminById");
        AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO arriboAdministrativoInternacionalTO = new AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        String[] whereArgs = {String.valueOf(numeroAviso)};
        Cursor cursor = super.findAllByArguments(whereClause.toString(), whereArgs);
        if (cursor != null && cursor.moveToFirst() && !cursor.isClosed()) {
            arriboAdministrativoInternacionalTO = cursorToRecordTO(cursor);
            cursor.close();
        }
        return arriboAdministrativoInternacionalTO;
    }

    @Override
    public AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO ");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO arrAdminInternacionalTO = new AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO();
                arrAdminInternacionalTO.setArriboAdminitrativoId(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_INT_ARRIBO_ADMIN)));
                arrAdminInternacionalTO.setFechaHoraLibrePlatica(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_LIBRE_PLATICA)));
                arrAdminInternacionalTO.setFechaHoraVisita(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_VISITA)));
                arrAdminInternacionalTO.setSiLibrePlatica(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_INT_SI_LIBRE_PLATICA)));
                arrAdminInternacionalTO.setNumeroPasajerosTrancito(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_INT_NUMERO_PASAJEROS_TRANSITO)));
                arrAdminInternacionalTO.setObservaciones(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_INT_OBSERVACIONES)));
                //arrAdminInternacionalTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO)));
                return arrAdminInternacionalTO;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
