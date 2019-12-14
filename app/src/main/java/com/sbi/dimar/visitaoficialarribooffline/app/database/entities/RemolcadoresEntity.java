package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_REM;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_REM2;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_OMI_MATRICULA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM_OMI_MATRICULA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_REM;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_REM2;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_REM table
 */
public enum RemolcadoresEntity {
    REMOLCADORES_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_REM2;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<RemolcadoresTO> objects, String tipoAviso) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_REM2_OMI_MATRICULA, objects.get(i).getIdRemolcador());
                    values.put(COLUMN_DIM_OFF_REM2_NOMBRE_NAVE, objects.get(i).getNombreRemolcador());
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                        values.put(COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    }
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                        values.put(COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    }
                    contentValues.add(values);
                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_REM2;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<RemolcadoresTO> objects, String tipoAviso);
}
