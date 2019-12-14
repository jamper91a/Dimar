package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 27/4/2018
 *
 * The SignatureTO class represents the characteristics of the object of each signature of an official
 */
public class SignatureTO implements Serializable {

    @SerializedName("idFirma")
    @Expose(deserialize = false)
    private int idFirma;
    @Expose(deserialize = false)
    @SerializedName("idRepresentante")
    private int idRepresentante;
    private RepresentantTO representantTO;
    @SerializedName("emailFuncionario")
    @Expose(deserialize = false)
    private String emailFuncionario;
    @SerializedName("nombreFuncionario")
    @Expose()
    private String nombreFuncionario;
    @SerializedName("numeroIdentificacionFuncionario")
    @Expose(deserialize = false)
    private String numeroIdentificacionFuncionario;
    @SerializedName("firmaFuncionario")
    @Expose(deserialize = false)
    private String firmaFuncionario;
    @SerializedName("numeroAvisoArribo")
    @Expose(deserialize = false)
    private long numeroAvisoArribo;

    @Expose(serialize = false, deserialize = false)
    private byte[] imageByteArray;

    public SignatureTO() {
    }

    public int getIdFirma() {
        return idFirma;
    }

    public void setIdFirma(int idFirma) {
        this.idFirma = idFirma;
    }

    public RepresentantTO getRepresentantTO() {
        return representantTO;
    }

    public void setRepresentantTO(RepresentantTO representantTO) {
        this.representantTO = representantTO;
        if (representantTO != null) {
            setIdRepresentante(Integer.parseInt(representantTO.getId()));
        }
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public String getNombreFuncionario() {
        return nombreFuncionario;
    }

    public void setNombreFuncionario(String nombreFuncionario) {
        this.nombreFuncionario = nombreFuncionario;
    }

    public String getNumeroIdentificacionFuncionario() {
        return numeroIdentificacionFuncionario;
    }

    public void setNumeroIdentificacionFuncionario(String numeroIdentificacionFuncionario) {
        this.numeroIdentificacionFuncionario = numeroIdentificacionFuncionario;
    }

    public String getFirmaFuncionario() {
        return firmaFuncionario;
    }

    public void setFirmaFuncionario(String firmaFuncionario) {
        this.firmaFuncionario = firmaFuncionario;
    }

    public long getNumeroAvisoArribo() {
        return numeroAvisoArribo;
    }

    public void setNumeroAvisoArribo(long numeroAvisoArribo) {
        this.numeroAvisoArribo = numeroAvisoArribo;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }

    public int getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(int idRepresentante) {
        this.idRepresentante = idRepresentante;
    }
}
