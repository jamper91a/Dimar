package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 27/4/2018
 * <p>
 * The PDFTO class represents the characteristics of the PDF object
 */
public class PDFTO implements Serializable {

    @SerializedName("idPDF")
    @Expose(deserialize = false)
    private long idPDF;
    @SerializedName("nombreArchivo")
    @Expose(deserialize = false)
    private String nombreArchivo;
    @SerializedName("archivo")
    @Expose(deserialize = false)
    private String archivo;
    @SerializedName("numeroAvisoArribo")
    @Expose(deserialize = false)
    private long numeroAvisoArribo;

    public PDFTO() {
    }

    public long getIdPDF() {
        return idPDF;
    }

    public void setIdPDF(long idPDF) {
        this.idPDF = idPDF;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public long getNumeroAvisoArribo() {
        return numeroAvisoArribo;
    }

    public void setNumeroAvisoArribo(long numeroAvisoArribo) {
        this.numeroAvisoArribo = numeroAvisoArribo;
    }
}
