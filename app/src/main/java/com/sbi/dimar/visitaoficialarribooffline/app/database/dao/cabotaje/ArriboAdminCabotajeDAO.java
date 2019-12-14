package com.sbi.dimar.visitaoficialarribooffline.app.database.dao.cabotaje;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AbstractFacade;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 15/5/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_AAD_CAB
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface ArriboAdminCabotajeDAO {

    boolean create();

    boolean deleteAll();

    boolean updateRecord(long numeroArriboAdminInt);

    List<AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO> findAllRecords();

    AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO findArriboAdminById(long numeroAviso);

    AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO cursorToRecordTO(Cursor cursor);

}
