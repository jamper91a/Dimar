package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.TrainingPilotTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.TrainingPilotEntity.TRAINING_PILOT_BD;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class implements the interface {@link TrainingPilotDAO}, is responsible for obtaining data from the database.
 */
public class TrainingPilotDAOImpl extends AbstractFacade implements TrainingPilotDAO {
    private static final String TAG = TrainingPilotDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public TrainingPilotDAOImpl(SQLiteDatabase sqLiteDatabase, List<TrainingPilotTO> trainingPilotTOS) {
        super(TRAINING_PILOT_BD.table(), TRAINING_PILOT_BD.columnsWhitValues(trainingPilotTOS), TRAINING_PILOT_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<TrainingPilotTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<TrainingPilotTO> trainingPilotTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            trainingPilotTOS = new ArrayList<>();
            do {
                TrainingPilotTO trainingPilotTO = cursorToRecordTO(cursor);
                if (trainingPilotTO != null) {
                    trainingPilotTOS.add(trainingPilotTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return trainingPilotTOS;
    }

    @Override
    public TrainingPilotTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                return new TrainingPilotTO(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
