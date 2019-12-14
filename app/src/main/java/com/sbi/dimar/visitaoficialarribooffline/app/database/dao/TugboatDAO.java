package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class defines the own methods for the persistence of information of the table DIM_OFF_REM
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface TugboatDAO {

    boolean create();

    boolean deleteAll();

    List<TugboatTO> findAllRecords();

    TugboatTO cursorToRecordTO(Cursor cursor);
}
