package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PracticalPilotTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.PracticalPilotEntity.PRACTICAL_PILOT_BD;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class implements the interface {@link PracticalPilotDAO}, is responsible for obtaining data from the database.
 */
public class PracticalPilotDAOImpl extends AbstractFacade implements PracticalPilotDAO {

    private static final String TAG = PracticalPilotDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public PracticalPilotDAOImpl(SQLiteDatabase sqLiteDatabase, List<PracticalPilotTO> practicalPilotTOS) {
        super(PRACTICAL_PILOT_BD.table(), PRACTICAL_PILOT_BD.columnsWhitValues(practicalPilotTOS), PRACTICAL_PILOT_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<PracticalPilotTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<PracticalPilotTO> practicalPilotTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            practicalPilotTOS = new ArrayList<>();
            do {
                PracticalPilotTO practicalPilotTO = cursorToRecordTO(cursor);
                if (practicalPilotTO != null) {
                    practicalPilotTOS.add(practicalPilotTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return practicalPilotTOS;
    }

    @Override
    public PracticalPilotTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                return new PracticalPilotTO(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
