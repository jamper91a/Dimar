package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The PortInstallationTO class represents the characteristics of the object of each dock
 */
public class PortInstallationTO implements Serializable {

    @SerializedName("codigo")
    @Expose(serialize = false)
    private String codigo;
    @SerializedName("muelle")
    @Expose(serialize = false)
    private String muelle;

    public PortInstallationTO(String codigo, String muelle) {
        this.codigo = codigo;
        this.muelle = muelle;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMuelle() {
        return muelle;
    }

    @Override
    public String toString() {
        return muelle;
    }
}
