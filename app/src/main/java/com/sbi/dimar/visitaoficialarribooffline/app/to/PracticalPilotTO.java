package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The PracticalPilotTO class represents the characteristics of the object of each practical pilot
 */
public class PracticalPilotTO implements Serializable {
    @SerializedName("idPiloto")
    @Expose(serialize = false)
    private String idPiloto;
    @SerializedName("nombrePiloto")
    @Expose(serialize = false)
    private String nombrePiloto;
    @SerializedName("codigoLicencia")
    @Expose()
    private String codigoLicencia;

    public PracticalPilotTO(String idPiloto, String nombrePiloto, String codigoLicencia) {
        this.idPiloto = idPiloto;
        this.nombrePiloto = nombrePiloto;
        this.codigoLicencia = codigoLicencia;
    }

    public String getIdPiloto() {
        return idPiloto;
    }

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public String getCodigoLicencia() {
        return codigoLicencia;
    }

    @Override
    public String toString() {
        return nombrePiloto;
    }
}
