package com.sbi.dimar.visitaoficialarribooffline.app.database.dao.internacional;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AbstractFacade;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_AVA_INT
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface AvisoArriboInternacionalDAO {

    boolean create();

    boolean deleteAll();

    boolean updateRecord(long numeroAviso, String tipoAviso);

    List<AvisoArriboInternacionalTO> findAllRecords();

    List<AvisoArriboInternacionalTO> findAllRecordsByState();

    AvisoArriboInternacionalTO findAvisoById(long numeroAviso, String tipoAviso);

    AvisoArriboInternacionalTO cursorToRecordTO(Cursor cursor);
}
