package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_AAD_INT;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_ARRIBO_ADMIN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_LIBRE_PLATICA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_VISITA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_NUMERO_PASAJEROS_TRANSITO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_OBSERVACIONES;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_INT_SI_LIBRE_PLATICA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_AAD_INT;

/**
 * Created by Jenny Galindo on 27/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_AAD_INT table
 */
public enum ArriboAdministrativoInternacionalEntity {

    ARRIBO_ADMINISTRATIVO_INTERNACIONAL_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_AAD_INT;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<AvisoArriboInternacionalTO> objects) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_AAD_INT_ARRIBO_ADMIN, objects.get(i).getArriboAdminInternacionalTO().getArriboAdminitrativoId() == 0 ? (int) (Math.random() * 5000 + 1) : objects.get(i).getArriboAdminInternacionalTO().getArriboAdminitrativoId());
                    values.put(COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_LIBRE_PLATICA, objects.get(i).getArriboAdminInternacionalTO().getFechaHoraLibrePlatica());
                    values.put(COLUMN_DIM_OFF_AAD_INT_FECHA_HORA_VISITA, objects.get(i).getArriboAdminInternacionalTO().getFechaHoraVisita());
                    values.put(COLUMN_DIM_OFF_AAD_INT_SI_LIBRE_PLATICA, objects.get(i).getArriboAdminInternacionalTO().getSiLibrePlatica());
                    values.put(COLUMN_DIM_OFF_AAD_INT_NUMERO_PASAJEROS_TRANSITO, objects.get(i).getArriboAdminInternacionalTO().getNumeroPasajerosTrancito());
                    values.put(COLUMN_DIM_OFF_AAD_INT_OBSERVACIONES, objects.get(i).getArriboAdminInternacionalTO().getObservaciones());
                    values.put(COLUMN_DIM_OFF_AAD_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    contentValues.add(values);
                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_AAD_INT;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<AvisoArriboInternacionalTO> objects);
}
