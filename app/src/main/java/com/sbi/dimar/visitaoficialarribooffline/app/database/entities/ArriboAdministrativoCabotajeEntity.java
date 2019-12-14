package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_AAD_CAB;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_ARRIBO_ADMIN;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_DIM_OFF_USR_ID_USUARIO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_FECHA_LIBRE_PLATICA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_FECHA_VISITA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_NIT_AGENCIA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_OBSERVACIONES;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_AAD_CAB_VISITADA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_AAD_CAB;

/**
 * Created by Jenny Galindo on 27/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_AAD_CAB table
 */
public enum ArriboAdministrativoCabotajeEntity {

    ARRIBO_ADMINISTRATIVO_CABOTAJE_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_AAD_CAB;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<AvisoArriboCabotageTO> objects, UserTO userTO) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_AAD_CAB_ARRIBO_ADMIN, objects.get(i).getArriboAdminCabotageTO().getArriboAdminitrativoId() == 0 ? (int) (Math.random() * 5000 + 1) : objects.get(i).getArriboAdminCabotageTO().getArriboAdminitrativoId());
                    values.put(COLUMN_DIM_OFF_AAD_CAB_FECHA_LIBRE_PLATICA, objects.get(i).getArriboAdminCabotageTO().getFechaLibrePlatica());
                    values.put(COLUMN_DIM_OFF_AAD_CAB_FECHA_VISITA, objects.get(i).getArriboAdminCabotageTO().getFechaVisita());
                    values.put(COLUMN_DIM_OFF_AAD_CAB_NIT_AGENCIA, objects.get(i).getArriboAdminCabotageTO().getNitAgencia());
                    values.put(COLUMN_DIM_OFF_AAD_CAB_OBSERVACIONES, objects.get(i).getArriboAdminCabotageTO().getObservaciones());
                    values.put(COLUMN_DIM_OFF_AAD_CAB_VISITADA, objects.get(i).getArriboAdminCabotageTO().isVisitada());
                    values.put(COLUMN_DIM_OFF_AAD_CAB_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());

                    if (objects.get(i).getArriboAdminCabotageTO().getIdUsuario() == 0) {
                        objects.get(i).getArriboAdminCabotageTO().setIdUsuario(userTO.getIdUsuario());
                    }
                    values.put(COLUMN_DIM_OFF_AAD_CAB_DIM_OFF_USR_ID_USUARIO, objects.get(i).getArriboAdminCabotageTO().getIdUsuario());
                    contentValues.add(values);
                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_AAD_CAB;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<AvisoArriboCabotageTO> objects, UserTO userTO);
}
