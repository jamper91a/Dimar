package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The AuditTO class represents the characteristics of an audit object
 */
public class AuditTO implements Serializable {

    @SerializedName("codigoMensaje")
    @Expose(serialize = false)
    private int codigoMensaje;
    @SerializedName("mensaje")
    @Expose(serialize = false)
    private String mensaje;

    public AuditTO() {
    }

    public AuditTO(int codigoMensaje, String mensaje) {
        this.codigoMensaje = codigoMensaje;
        this.mensaje = mensaje;
    }

    public int getCodigoMensaje() {
        return codigoMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAudit() {
        return getCodigoMensaje() + " ; " + getMensaje();

    }
}
