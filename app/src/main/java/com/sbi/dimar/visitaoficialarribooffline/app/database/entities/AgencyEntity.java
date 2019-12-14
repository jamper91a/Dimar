package com.sbi.dimar.visitaoficialarribooffline.app.database.entities;

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class that defines the contents of the DIM_OFF_AGE table
 */
public enum AgencyEntity {

    TABLE_DIM_OFF_AGE("DIM_OFF_AGE"),
    COLUMN_DIM_OFF_AGE_NIT("DIM_OFF_AGE_NIT"),
    COLUMN_DIM_OFF_AGE_RAZON_SOCIAL("DIM_OFF_AGE_RAZON_SOCIAL");

    private final String value;

    AgencyEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}