package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.RepresentantTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.RepresentantEntity.REPRESENTANT_BD;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class implements the interface {@link RepresentantDAO}, is responsible for obtaining data from the database.
 */
public class RepresentantDAOImpl extends AbstractFacade implements RepresentantDAO {
    private static final String TAG = RepresentantDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public RepresentantDAOImpl(SQLiteDatabase sqLiteDatabase, List<RepresentantTO> representantTOS) {
        super(REPRESENTANT_BD.table(), REPRESENTANT_BD.columnsWhitValues(representantTOS), REPRESENTANT_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<RepresentantTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<RepresentantTO> representantTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            representantTOS = new ArrayList<>();
            do {
                RepresentantTO representantTO = cursorToRecordTO(cursor);
                if (representantTO != null) {
                    representantTOS.add(representantTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return representantTOS;
    }

    @Override
    public RepresentantTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                return new RepresentantTO(cursor.getString(0), cursor.getString(1));
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
