package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.TugboatEntity.TUGBOAT_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM_OMI_MATRICULA;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class implements the interface {@link TugboatDAO}, is responsible for obtaining data from the database.
 */
public class TugboatDAOImpl extends AbstractFacade implements TugboatDAO {
    private static final String TAG = TugboatDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }


    public TugboatDAOImpl(SQLiteDatabase sqLiteDatabase, List<TugboatTO> tugboatTOS) {
        super(TUGBOAT_BD.table(), TUGBOAT_BD.columnsWhitValues(tugboatTOS), TUGBOAT_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<TugboatTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<TugboatTO> tugboatTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            tugboatTOS = new ArrayList<>();
            do {
                TugboatTO tugboatTO = cursorToRecordTO(cursor);
                if (tugboatTO != null) {
                    tugboatTOS.add(tugboatTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return tugboatTOS;
    }

    @Override
    public TugboatTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                return new TugboatTO(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_REM_OMI_MATRICULA)), cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_REM_NOMBRE_NAVE)));
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
