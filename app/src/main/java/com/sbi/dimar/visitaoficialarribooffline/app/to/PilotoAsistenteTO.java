package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The PracticalPilotTO class represents the characteristics of the object of each practical pilot
 */
public class PilotoAsistenteTO implements Serializable {
    @SerializedName("idPiloto")
    @Expose()
    private String idPiloto;
    @SerializedName("nombrePiloto")
    @Expose()
    private String nombrePiloto;
    @SerializedName("numeroAvisoArribo")
    @Expose(deserialize = false)
    private long numeroAvisoArribo;
    @SerializedName("codigoLicencia")
    @Expose()
    private String codigoLicencia;

    public PilotoAsistenteTO() {
    }



    public PilotoAsistenteTO(String idPiloto, String nombrePiloto, long numeroAvisoArribo, String codigoLicencia) {
        this.idPiloto = idPiloto;
        this.nombrePiloto = nombrePiloto;
        this.numeroAvisoArribo = numeroAvisoArribo;
        this.codigoLicencia = codigoLicencia;
    }

    public String getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public long getNumeroAvisoArribo() {
        return numeroAvisoArribo;
    }

    public void setNumeroAvisoArribo(long numeroAvisoArribo) {
        this.numeroAvisoArribo = numeroAvisoArribo;
    }

    public String getCodigoLicencia() {
        return codigoLicencia;
    }

    public void setCodigoLicencia(String codigoLicencia) {
        this.codigoLicencia = codigoLicencia;
    }
}
