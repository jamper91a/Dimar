package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The class ReasonArrivalTO represents the characteristics of the object of each reason of arrival
 */
public class ReasonArrivalTO implements Serializable {
    @SerializedName("codigo")
    @Expose(serialize = false)
    private String codigo;
    @SerializedName("descripcion")
    @Expose(serialize = false)
    private String descripcion;

    public ReasonArrivalTO(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
