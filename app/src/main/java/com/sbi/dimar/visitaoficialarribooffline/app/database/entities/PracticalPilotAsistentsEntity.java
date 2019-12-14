package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PracticalPilotTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_PIL_PRA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_PIL_PRA_ASI;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_PRA_ASI_CODIGO_LICENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_PRA_ASI_ID_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_PRA_ASI_NOMBRE_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_PRA_ID_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_PRA_NOMBRE_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_PIL_PRA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_PIL_PRA_ASI;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_PIL_PRA table
 */
public enum PracticalPilotAsistentsEntity {

    PRACTICAL_PILOT_ASISTENTS_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_PIL_PRA_ASI;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<PracticalPilotTO> objects) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_PIL_PRA_ASI_ID_PILOTO, objects.get(i).getIdPiloto());
                    values.put(COLUMN_DIM_OFF_PIL_PRA_ASI_NOMBRE_PILOTO, objects.get(i).getNombrePiloto());
                    values.put(COLUMN_DIM_OFF_PIL_PRA_ASI_CODIGO_LICENCIA, objects.get(i).getCodigoLicencia());
                    contentValues.add(values);

                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_PIL_PRA_ASI;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<PracticalPilotTO> objects);
}

