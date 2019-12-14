package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PracticalPilotTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_PIL_PRA
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface PracticalPilotAsistentsDAO {

    boolean create();
    boolean createIfDoesNotExist();

    boolean deleteAll();

    List<PracticalPilotTO> findAllRecords();

    PracticalPilotTO cursorToRecordTO(Cursor cursor);
}
