package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

/**
 * Created by Jenny Galindo on 15/5/2018
 * <p>
 * This class that defines the content of the arrival announcement status
 */
public enum EstadoEntity {

    ESTADO_VISITADO(100, "ESTADO_VISITADO");

    private final int value;
    private final String description;


    EstadoEntity(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescrption() {
        return description;
    }
}
