package com.sbi.dimar.visitaoficialarribooffline.app.database.dao.internacional;

import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AbstractFacade;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 11/5/2018
 * <p>
 * This class defines the methods for the persistence of information in the table DIM_OFF_AAD_INT
 * and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface ArriboAdminInternacionalDAO {
    boolean create();

    boolean deleteAll();

    boolean updateRecord(long numeroArriboAdminInt);

    List<AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO> findAllRecords();

    AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO findArriboAdminById(long numeroAviso);

    AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO cursorToRecordTO(Cursor cursor);
}
