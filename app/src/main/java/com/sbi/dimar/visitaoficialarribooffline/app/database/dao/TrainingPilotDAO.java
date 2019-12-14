package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.TrainingPilotTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_PIL_ENT
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface TrainingPilotDAO {

    boolean create();
    boolean createIfDoesNotExist();

    boolean deleteAll();

    List<TrainingPilotTO> findAllRecords();

    TrainingPilotTO cursorToRecordTO(Cursor cursor);
}
