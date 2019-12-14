package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;


import android.database.Cursor;

import com.sbi.dimar.visitaoficialarribooffline.app.to.AgencyTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class defines the methods for the persistence of information in the DIM_OFF_AGE
 * table and makes use of the generic methods defined in the class {@link AbstractFacade}
 */
public interface AgencyDAO {

    List<AgencyTO> getAgencies();

    boolean saveAgencies(List<AgencyTO> agencyTOS);

    AgencyTO cursorToAgency(Cursor cursor);

    boolean deleteAgencies();
}
