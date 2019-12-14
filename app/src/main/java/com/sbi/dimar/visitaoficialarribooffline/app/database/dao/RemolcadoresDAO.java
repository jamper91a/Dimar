package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class defines the own methods for the persistence of information of the table DIM_OFF_REM
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface RemolcadoresDAO {

    boolean create();

    boolean deleteAll();

    List<RemolcadoresTO> findAllRecords();

    RemolcadoresTO cursorToRecordTO(Cursor cursor, String tipoAviso);

    List<RemolcadoresTO> findRemolcadoresByAviso(long numeroAviso, String tipoAviso);
}
