package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.ReasonArrivalTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.ReasonArrivalEntity.REASON_ARRIVAL_BD;

/**
 * Created by Jenny Galindo on 26/4/2018
 * This class implements the interface {@link ReasonArrivalDAO}, is responsible for obtaining data from the database.
 */
public class ReasonArrivalDAOImpl extends AbstractFacade implements ReasonArrivalDAO {

    private static final String TAG = ReasonArrivalDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public ReasonArrivalDAOImpl(SQLiteDatabase sqLiteDatabase, List<ReasonArrivalTO> reasonArrivalTOS) {
        super(REASON_ARRIVAL_BD.table(), REASON_ARRIVAL_BD.columnsWhitValues(reasonArrivalTOS), REASON_ARRIVAL_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<ReasonArrivalTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<ReasonArrivalTO> reasonArrivalTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            reasonArrivalTOS = new ArrayList<>();
            do {
                ReasonArrivalTO reasonArrivalTO = cursorToRecordTO(cursor);
                if (reasonArrivalTO != null) {
                    reasonArrivalTOS.add(reasonArrivalTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return reasonArrivalTOS;
    }

    @Override
    public ReasonArrivalTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                return new ReasonArrivalTO(cursor.getString(0), cursor.getString(1));
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
