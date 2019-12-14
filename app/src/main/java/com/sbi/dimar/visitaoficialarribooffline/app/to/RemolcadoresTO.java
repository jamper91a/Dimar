package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * The AgencyTO class represents the characteristics of an agency object
 */
public class RemolcadoresTO implements Serializable {
    @SerializedName("idRemolcador")
    @Expose()
    private String idRemolcador;
    @SerializedName("nombreRemolcador")
    @Expose()
    private String nombreRemolcador;
    @SerializedName("numeroAvisoArribo")
    @Expose(deserialize = false)
    private long numeroAvisoArribo;

    public RemolcadoresTO() {
    }

    public RemolcadoresTO(String idRemolcador, String nombreRemolcador, long numeroAvisoArribo) {
        this.idRemolcador = idRemolcador;
        this.nombreRemolcador = nombreRemolcador;
        this.numeroAvisoArribo = numeroAvisoArribo;
    }

    public String getIdRemolcador() {
        return idRemolcador;
    }

    public void setIdRemolcador(String idRemolcador) {
        this.idRemolcador = idRemolcador;
    }

    public String getNombreRemolcador() {
        return nombreRemolcador;
    }

    public void setNombreRemolcador(String nombreRemolcador) {
        this.nombreRemolcador = nombreRemolcador;
    }

    public long getNumeroAvisoArribo() {
        return numeroAvisoArribo;
    }

    public void setNumeroAvisoArribo(long numeroAvisoArribo) {
        this.numeroAvisoArribo = numeroAvisoArribo;
    }
}
