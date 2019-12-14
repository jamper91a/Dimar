package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_REM;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM_OMI_MATRICULA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_REM;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_REM table
 */
public enum TugboatEntity {
    TUGBOAT_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_REM;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<TugboatTO> objects) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_REM_OMI_MATRICULA, objects.get(i).getOmiMatricula());
                    values.put(COLUMN_DIM_OFF_REM_NOMBRE_NAVE, objects.get(i).getNombreNave());
                    contentValues.add(values);
                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_REM;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<TugboatTO> objects);
}
