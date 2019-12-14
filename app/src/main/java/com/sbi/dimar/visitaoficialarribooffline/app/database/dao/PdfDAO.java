package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PDFTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 22/5/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_PDF
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface PdfDAO {

    boolean create();

    boolean deleteAll();

    List<PdfDAO> findAllRecords();

    PDFTO cursorToRecordTO(Cursor cursor, String tipoAviso);

    PDFTO findPdfByAviso(long numeroAviso, String tipoAviso);
}
