package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.RepresentantTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_REP;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REP_ID;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REP_NOMBRE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_REP;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_REP table
 */
public enum RepresentantEntity {

    REPRESENTANT_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_REP;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<RepresentantTO> objects) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_REP_ID, objects.get(i).getId());
                    values.put(COLUMN_DIM_OFF_REP_NOMBRE, objects.get(i).getNombre());
                    contentValues.add(values);

                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_REP;
        }
    };

    public abstract String table();


    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<RepresentantTO> objects);
}
