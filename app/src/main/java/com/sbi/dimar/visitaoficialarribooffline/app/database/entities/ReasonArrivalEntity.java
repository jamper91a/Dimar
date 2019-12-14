package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.ReasonArrivalTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_RAZ_ARR;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_RAZ_ARR_CODIGO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_RAZ_ARR_DESCRIPCION;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_RAZ_ARR;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_RAZ_ARR table
 */
public enum ReasonArrivalEntity {

    REASON_ARRIVAL_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_RAZ_ARR;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<ReasonArrivalTO> objects) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_RAZ_ARR_CODIGO, objects.get(i).getCodigo());
                    values.put(COLUMN_DIM_OFF_RAZ_ARR_DESCRIPCION, objects.get(i).getDescripcion());
                    contentValues.add(values);

                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_RAZ_ARR;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<ReasonArrivalTO> objects);
}
