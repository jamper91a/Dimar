package com.sbi.dimar.visitaoficialarribooffline.app.database.dao.cabotaje;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AbstractFacade;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class defines the own methods for the persistence of information of the table DIM_OFF_AVA_CAB
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface AvisoArriboCabotajeDAO {

    boolean create();

    boolean deleteAll();

    boolean updateRecord(long numeroAviso, String tipoAviso);

    List<AvisoArriboCabotageTO> findAllRecords();

    List<AvisoArriboCabotageTO> findAllRecordsByState();

    AvisoArriboCabotageTO findAvisoById(long numeroAviso, String tipoAviso);

    AvisoArriboCabotageTO cursorToRecordTO(Cursor cursor);
}
