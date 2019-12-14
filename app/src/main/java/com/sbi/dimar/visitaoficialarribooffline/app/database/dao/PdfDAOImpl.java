package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PDFTO;

import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.PDFEntity.PDF_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_ARCHIVO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_ID;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_PDF_NOMBRE_ARCHIVO;


/**
 * Created by Jenny Galindo on 22/5/2018
 * <p>
 * This class implements the interface {@link PdfDAO}, is responsible for obtaining data from the database.
 */
public class PdfDAOImpl extends AbstractFacade implements PdfDAO {
    private static final String TAG = PdfDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }


    public PdfDAOImpl(SQLiteDatabase sqLiteDatabase, List<PDFTO> pdfTOS, String tipoAviso) {
        super(PDF_BD.table(), PDF_BD.columnsWhitValues(pdfTOS, tipoAviso), PDF_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<PdfDAO> findAllRecords() {
        return null;
    }

    @Override
    public PDFTO findPdfByAviso(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "findArriboAdminById");
        PDFTO pdfTO = null;
        StringBuilder whereClause = new StringBuilder();
        if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
            whereClause.append(COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        } else {
            if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                whereClause.append(COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO).append(" = ? \n");
            }
        }

        String[] whereArgs = {String.valueOf(numeroAviso)};
        Cursor cursor = super.findAllByArguments(whereClause.toString(), whereArgs);
        if (cursor != null && cursor.moveToFirst() && !cursor.isClosed()) {
            pdfTO = cursorToRecordTO(cursor, tipoAviso);
            cursor.close();
        }
        return pdfTO;
    }

    @Override
    public PDFTO cursorToRecordTO(Cursor cursor, String tipoAviso) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                PDFTO pdfTO = new PDFTO();

                pdfTO.setIdPDF(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_PDF_ID)));
                if (!TextUtils.isEmpty(tipoAviso)) {
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                        pdfTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO)));
                    } else {
                        if (tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                            pdfTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_PDF_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO)));
                        }
                    }
                }
                pdfTO.setNombreArchivo(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_PDF_NOMBRE_ARCHIVO)));
                pdfTO.setArchivo(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_PDF_ARCHIVO)));
                return pdfTO;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }


}
