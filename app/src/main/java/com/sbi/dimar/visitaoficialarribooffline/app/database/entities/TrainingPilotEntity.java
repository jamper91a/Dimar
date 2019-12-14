package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.TrainingPilotTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_PIL_ENT;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ENT_CODIGO_LICENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ENT_ID_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ENT_NOMBRE_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_PIL_ENT;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_PIL_ENT table
 */
public enum TrainingPilotEntity {
    TRAINING_PILOT_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_PIL_ENT;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<TrainingPilotTO> objects) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_PIL_ENT_ID_PILOTO, objects.get(i).getIdPiloto());
                    values.put(COLUMN_DIM_OFF_PIL_ENT_NOMBRE_PILOTO, objects.get(i).getNombrePiloto());
                    contentValues.add(values);

                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_PIL_ENT;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<TrainingPilotTO> objects);
}