package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PortInstallationTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_INS_POR;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_INS_POR_CODIGO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_INS_POR_MUELLE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_INS_POR;

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_INS_POR table
 */
public enum PortInstallationEntity {

    PORT_INSTALLATION_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_INS_POR;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<PortInstallationTO> objects) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_INS_POR_CODIGO, objects.get(i).getCodigo());
                    values.put(COLUMN_DIM_OFF_INS_POR_MUELLE, objects.get(i).getMuelle());
                    contentValues.add(values);

                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_INS_POR;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<PortInstallationTO> objects);
}
