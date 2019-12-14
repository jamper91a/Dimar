package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.RepresentantTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_REP
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface RepresentantDAO {

    boolean create();

    boolean deleteAll();

    List<RepresentantTO> findAllRecords();

    RepresentantTO cursorToRecordTO(Cursor cursor);
}
