package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotTransportBoatTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.PilotTransportBoatEntity.PILOT_TRANSPORT_BOAT_BD;


/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class implements the interface {@link PilotTransportBoatDAO}, is responsible for obtaining data from the database.
 */
public class PilotTransportBoatDAOImpl extends AbstractFacade implements PilotTransportBoatDAO {
    private static final String TAG = PilotTransportBoatDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }


    public PilotTransportBoatDAOImpl(SQLiteDatabase sqLiteDatabase, List<PilotTransportBoatTO> pilotTransportBoatTOS) {
        super(PILOT_TRANSPORT_BOAT_BD.table(), PILOT_TRANSPORT_BOAT_BD.columnsWhitValues(pilotTransportBoatTOS), PILOT_TRANSPORT_BOAT_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }


    public List<PilotTransportBoatTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<PilotTransportBoatTO> pilotTransportBoatTOs = null;
        if (cursor != null && cursor.moveToFirst()) {
            pilotTransportBoatTOs = new ArrayList<>();
            do {
                PilotTransportBoatTO pilotTransportBoatTO = cursorToRecordTO(cursor);
                if (pilotTransportBoatTO != null) {
                    pilotTransportBoatTOs.add(pilotTransportBoatTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return pilotTransportBoatTOs;
    }

    public PilotTransportBoatTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                return new PilotTransportBoatTO(cursor.getString(0), cursor.getString(1));
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
