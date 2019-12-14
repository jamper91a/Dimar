package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PortInstallationTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_INS_POR
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface PortInstallationDAO {
    boolean create();

    boolean deleteAll();

    List<PortInstallationTO> findAllRecords();

    PortInstallationTO cursorToRecordTO(Cursor cursor);
}
