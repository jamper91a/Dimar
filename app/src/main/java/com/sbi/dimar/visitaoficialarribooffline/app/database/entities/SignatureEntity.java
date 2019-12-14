package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;

import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_FIR;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_CEDULA_FUNCIONARIO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_EMAIL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_FIRMA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_ID;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_ID_REPRESENTANTE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_NOMBRE_FUNCIONARIO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_FIR;

/**
 * Created by Jenny Galindo on 27/4/2018
 *
 * This class that defines the contents of the DIM_OFF_FIR table
 */
public enum SignatureEntity {

    SIGNATURE_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_FIR;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<SignatureTO> objects, String tipoAviso) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_FIR_ID, objects.get(i).getIdFirma() == 0 ? (int) (Math.random() * 5000 + 1) : objects.get(i).getIdFirma());
                    values.put(COLUMN_DIM_OFF_FIR_EMAIL, objects.get(i).getEmailFuncionario());
                    values.put(COLUMN_DIM_OFF_FIR_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    values.put(COLUMN_DIM_OFF_FIR_NOMBRE_FUNCIONARIO, objects.get(i).getNombreFuncionario());
                    values.put(COLUMN_DIM_OFF_FIR_CEDULA_FUNCIONARIO, objects.get(i).getNumeroIdentificacionFuncionario());
                    values.put(COLUMN_DIM_OFF_FIR_FIRMA, objects.get(i).getFirmaFuncionario());

                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                        values.put(COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    }
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                        values.put(COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    }

                    //Representante
                    values.put(COLUMN_DIM_OFF_FIR_ID_REPRESENTANTE, objects.get(i).getRepresentantTO().getId());

                    contentValues.add(values);

                }
                return contentValues;
            } else {
                return null;
            }
        }

        @Override
        public String[] columnsNames() {
            return COLUMNS_DIM_OFF_FIR;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<SignatureTO> objects, String tipoAviso);
}

