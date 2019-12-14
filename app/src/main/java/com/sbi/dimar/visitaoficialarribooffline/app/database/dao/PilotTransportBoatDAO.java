package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;


import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotTransportBoatTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class defines the own methods for the persistence of information of the table DIM_OFF_LAN_TRA_PIL
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface PilotTransportBoatDAO {

    boolean create();

    boolean deleteAll();

    List<PilotTransportBoatTO> findAllRecords();

    PilotTransportBoatTO cursorToRecordTO(Cursor cursor);
}
