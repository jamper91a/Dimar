package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The RepresentantTO class represents the characteristics of the object of each representative
 */
public class RepresentantTO implements Serializable {
    @SerializedName("id")
    @Expose()
    private String id;
    @SerializedName("nombre")
    @Expose()
    private String nombre;

    public RepresentantTO() {
    }

    public RepresentantTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public RepresentantTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
