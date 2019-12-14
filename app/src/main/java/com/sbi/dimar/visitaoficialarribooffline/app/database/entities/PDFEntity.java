package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

import android.content.ContentValues;
import android.text.TextUtils;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PDFTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMNS_DIM_OFF_PDF;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_ARCHIVO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_ID;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_NOMBRE_ARCHIVO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.TABLE_DIM_OFF_PDF;

/**
 * Created by Jenny Galindo on 27/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_PDF table
 */
public enum PDFEntity {

    PDF_BD() {
        @Override
        public String table() {
            return TABLE_DIM_OFF_PDF;
        }

        @Override
        public List<ContentValues> columnsWhitValues(List<PDFTO> objects, String tipoAviso) {
            if (objects != null) {
                List<ContentValues> contentValues = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_DIM_OFF_PDF_ID, objects.get(i).getIdPDF() == 0 ? (int) (Math.random() * 5000 + 1) : objects.get(i).getIdPDF());
                    values.put(COLUMN_DIM_OFF_PDF_NOMBRE_ARCHIVO, objects.get(i).getNombreArchivo());
                    values.put(COLUMN_DIM_OFF_PDF_ARCHIVO, objects.get(i).getArchivo());

                    if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                        values.put(COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
                    }
                    if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                        values.put(COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO, objects.get(i).getNumeroAvisoArribo());
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
            return COLUMNS_DIM_OFF_PDF;
        }
    };

    public abstract String table();

    public abstract String[] columnsNames();

    public abstract List<ContentValues> columnsWhitValues(List<PDFTO> objects, String tipoAviso);
}
