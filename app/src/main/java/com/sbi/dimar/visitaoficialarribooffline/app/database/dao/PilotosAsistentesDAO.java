package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotoAsistenteTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class defines the own methods for the persistence of information of the table DIM_OFF_REM
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface PilotosAsistentesDAO {

    boolean create();

    boolean deleteAll();

    List<PilotoAsistenteTO> findAllRecords();

    PilotoAsistenteTO cursorToRecordTO(Cursor cursor, String tipoAviso);

    List<PilotoAsistenteTO> findPilotosAsistentesByAviso(long numeroAviso, String tipoAviso);
}
