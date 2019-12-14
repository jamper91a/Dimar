package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 *
 * The PilotTransportBoatTO class represents the object characteristics of each pilot transport boat
 */
public class PilotTransportBoatTO implements Serializable {

    @SerializedName("nombreNave")
    @Expose(serialize = false)
    private String nombreNave;
    @SerializedName("omiMatricula")
    @Expose(serialize = false)
    private String omiMatricula;

    public PilotTransportBoatTO(String nombreNave, String omiMatricula) {
        this.nombreNave = nombreNave;
        this.omiMatricula = omiMatricula;
    }

    public String getNombreNave() {
        return nombreNave;
    }

    public String getOmiMatricula() {
        return omiMatricula;
    }

    @Override
    public String toString() {
        return nombreNave;
    }
}
