package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 27/4/2018
 * <p>
 * This class defines the methods for the persistence of information in the DIM_OFF_FIR
 * table and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface SignatureDAO {

    boolean create();

    boolean deleteAll();

    List<SignatureTO> findAllRecords();

    SignatureTO cursorToRecordTO(Cursor cursor, String tipoAviso);

    List<SignatureTO> findSignaturesByAviso(long numeroAviso, String tipoAviso);
}