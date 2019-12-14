package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotoAsistenteTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_PIL_ASI;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_REM2;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_CODIGO_LICENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_ID_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PIL_ASI_NOMBRE_PILOTO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_NOMBRE_NAVE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REM2_OMI_MATRICULA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_PIL_ASI;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_REM2;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_REM table
 */
public enum PilotosAsistentesEntity {
    PILOTOS_ASISTENTES_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_PIL_ASI;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<PilotoAsistenteTO> objects, String tipoAviso) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_PIL_ASI_ID_PILOTO, objects.get(i).getIdPiloto());
                    values.put(COLUMN_DIM_OFF_PIL_ASI_NOMBRE_PILOTO, objects.get(i).getNombrePiloto());
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                        values.put(COLUMN_DIM_OFF_PIL_ASI_AVA_CAB_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    }
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                        values.put(COLUMN_DIM_OFF_PIL_ASI_AVA_INT_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    }
                    values.put(COLUMN_DIM_OFF_PIL_ASI_CODIGO_LICENCIA, objects.get(i).getCodigoLicencia());
                    contentValues.add(values);
                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_PIL_ASI;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<PilotoAsistenteTO> objects, String tipoAviso);
}
