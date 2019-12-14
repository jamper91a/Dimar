package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.ReasonArrivalTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_RAZ_ARR
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface ReasonArrivalDAO {

    boolean create();

    boolean deleteAll();

    List<ReasonArrivalTO> findAllRecords();

    ReasonArrivalTO cursorToRecordTO(Cursor cursor);
}
