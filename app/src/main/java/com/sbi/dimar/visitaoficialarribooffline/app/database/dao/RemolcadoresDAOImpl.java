package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.util.ArrayUtils;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.RemolcadoresEntity.REMOLCADORES_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.RepresentantEntity.REPRESENTANT_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.SignatureEntity.SIGNATURE_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_ID_REPRESENTANTE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_OMI_MATRICULA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM_OMI_MATRICULA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REP_ID;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class implements the interface {@link TugboatDAO}, is responsible for obtaining data from the database.
 */
public class RemolcadoresDAOImpl extends AbstractFacade implements RemolcadoresDAO {
    private static final String TAG = RemolcadoresDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }


    public RemolcadoresDAOImpl(SQLiteDatabase sqLiteDatabase, List<RemolcadoresTO> remolcadoresTOS, String tipoAviso) {
        super(REMOLCADORES_BD.table(), REMOLCADORES_BD.columnsWhitValues(remolcadoresTOS, tipoAviso), REMOLCADORES_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<RemolcadoresTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<RemolcadoresTO> tugboatTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            tugboatTOS = new ArrayList<>();
            do {
                RemolcadoresTO tugboatTO = cursorToRecordTO(cursor, null);
                if (tugboatTO != null) {
                    tugboatTOS.add(tugboatTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return tugboatTOS;
    }

    @Override
    public List<RemolcadoresTO> findRemolcadoresByAviso(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "findSignaturesByAviso");

        String tables = REMOLCADORES_BD.table();

        String[] columns = REMOLCADORES_BD.columnsNames();

        StringBuilder whereClause = new StringBuilder();
        if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
            whereClause.append(COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        } else {
            if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                whereClause.append(COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO).append(" = ? \n");
            }
        }

        String[] whereArgs = {String.valueOf(numeroAviso)};

        Cursor cursor = super.findAllByArgumentsWhitJoin(tables, columns, whereClause.toString(), whereArgs);
        List<RemolcadoresTO> remolcadoresTOS = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                RemolcadoresTO remolcadoresTO = cursorToRecordTO(cursor, tipoAviso);
                if (remolcadoresTO != null && !findRemolcadore(remolcadoresTO.getIdRemolcador(), remolcadoresTOS)) {
                    remolcadoresTOS.add(remolcadoresTO);
                }
            } while (cursor.moveToNext());
        }
        return remolcadoresTOS;
    }

    public boolean findRemolcadore(String idRemolcador, List<RemolcadoresTO> remolcadoresTOS){
        for(RemolcadoresTO aux : remolcadoresTOS) {
            if(aux.getIdRemolcador().equals(idRemolcador)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public RemolcadoresTO cursorToRecordTO(Cursor cursor, String tipoAviso) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                RemolcadoresTO remolcadoresTO = new RemolcadoresTO();
                if (!TextUtils.isEmpty(tipoAviso)) {
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                        remolcadoresTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO)));
                    } else {
                        if (tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                            remolcadoresTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO)));
                        }
                    }
                }
                remolcadoresTO.setIdRemolcador(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_REM2_OMI_MATRICULA)));
                remolcadoresTO.setNombreRemolcador(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_REM2_NOMBRE_NAVE)));
                return remolcadoresTO;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
