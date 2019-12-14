package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotoAsistenteTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.PilotosAsistentesEntity.PILOTOS_ASISTENTES_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.RemolcadoresEntity.REMOLCADORES_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_CODIGO_LICENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_ID_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_NOMBRE_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_OMI_MATRICULA;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class implements the interface {@link TugboatDAO}, is responsible for obtaining data from the database.
 */
public class PilotosAsistentesDAOImpl extends AbstractFacade implements PilotosAsistentesDAO {
    private static final String TAG = PilotosAsistentesDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }


    public PilotosAsistentesDAOImpl(SQLiteDatabase sqLiteDatabase, List<PilotoAsistenteTO> pilotoAsistenteTOS, String tipoAviso) {
        super(PILOTOS_ASISTENTES_BD.table(), PILOTOS_ASISTENTES_BD.columnsWhitValues(pilotoAsistenteTOS, tipoAviso), PILOTOS_ASISTENTES_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<PilotoAsistenteTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<PilotoAsistenteTO> pilotoAsistenteTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            pilotoAsistenteTOS = new ArrayList<>();
            do {
                PilotoAsistenteTO pilotoAsistenteTO = cursorToRecordTO(cursor, null);
                if (pilotoAsistenteTO != null) {
                    pilotoAsistenteTOS.add(pilotoAsistenteTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return pilotoAsistenteTOS;
    }


    @Override
    public PilotoAsistenteTO cursorToRecordTO(Cursor cursor, String tipoAviso) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                PilotoAsistenteTO pilotoAsistenteTO = new PilotoAsistenteTO();
                if (!TextUtils.isEmpty(tipoAviso)) {
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                        pilotoAsistenteTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_AVISO_ARRIBO)));
                    } else {
                        if (tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                            pilotoAsistenteTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_AVISO_ARRIBO)));
                        }
                    }
                }
                pilotoAsistenteTO.setIdPiloto(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_PIL_ASI_ID_PILOTO)));
                pilotoAsistenteTO.setNombrePiloto(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_PIL_ASI_NOMBRE_PILOTO)));
                pilotoAsistenteTO.setCodigoLicencia(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_PIL_ASI_CODIGO_LICENCIA)));
                return pilotoAsistenteTO;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    public List<PilotoAsistenteTO> findPilotosAsistentesByAviso(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "findSignaturesByAviso");

        String tables = PILOTOS_ASISTENTES_BD.table();

        String[] columns = PILOTOS_ASISTENTES_BD.columnsNames();

        StringBuilder whereClause = new StringBuilder();
        if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
            whereClause.append(COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        } else {
            if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                whereClause.append(COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_AVISO_ARRIBO).append(" = ? \n");
            }
        }

        String[] whereArgs = {String.valueOf(numeroAviso)};

        Cursor cursor = super.findAllByArgumentsWhitJoin(tables, columns, whereClause.toString(), whereArgs);
        List<PilotoAsistenteTO> pilotoAsistenteTOS = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                PilotoAsistenteTO pilotoAsistenteTO = cursorToRecordTO(cursor, tipoAviso);
                if (pilotoAsistenteTO != null && !findPilotosAsistentes(pilotoAsistenteTO.getIdPiloto(), pilotoAsistenteTOS)) {
                    pilotoAsistenteTOS.add(pilotoAsistenteTO);
                }
            } while (cursor.moveToNext());
        }
        return pilotoAsistenteTOS;
    }
    public boolean findPilotosAsistentes(String idPiloto, List<PilotoAsistenteTO> pilotoAsistenteTOS){
        for(PilotoAsistenteTO aux : pilotoAsistenteTOS) {
            if(aux.getIdPiloto().equals(idPiloto)) {
                return true;
            }
        }
        return false;

    }
}
