package com.sbi.dimar.visitaoficialarribooffline.app.to;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The TrainingPilotTO class represents the characteristics of the object of each training pilot
 */
public class TrainingPilotTO extends PracticalPilotTO implements Serializable {

    public TrainingPilotTO(String idPiloto, String nombrePiloto, String codigoLicencia) {
        super(idPiloto, nombrePiloto, codigoLicencia);
    }

}
