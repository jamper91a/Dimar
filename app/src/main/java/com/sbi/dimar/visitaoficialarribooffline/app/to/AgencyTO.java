package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * The AgencyTO class represents the characteristics of an agency object
 */
public class AgencyTO implements Serializable {
    @SerializedName("nit")
    @Expose(serialize = false)
    private String nit;
    @SerializedName("razonSocial")
    @Expose(serialize = false)
    private String razonSocial;

    public AgencyTO(String nit, String razonSocial) {
        this.nit = nit;
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

}
