package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The UserTO class represents the characteristics of the user object
 */
public class UserTO extends AuditTO implements Serializable {

    @SerializedName("codigoCapitania")
    @Expose(serialize = false)
    private String codigoCapitania;
    @SerializedName("email")
    @Expose(serialize = false)
    private String email;
    @SerializedName("idLogin")
    @Expose(serialize = false)
    private String idLogin;
    @SerializedName("idPerfil")
    @Expose(serialize = false)
    private Integer idPerfil;
    @SerializedName("idUsuario")
    @Expose(serialize = false)
    private Integer idUsuario;
    @SerializedName("nombre")
    @Expose(serialize = false)
    private String nombre;
    @SerializedName("nombreCapitania")
    @Expose(serialize = false)
    private String nombreCapitania;
    @SerializedName("perfil")
    @Expose(serialize = false)
    private String perfil;

    public UserTO() {
    }

    public String getCodigoCapitania() {
        return codigoCapitania;
    }

    public void setCodigoCapitania(String codigoCapitania) {
        this.codigoCapitania = codigoCapitania;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(String idLogin) {
        this.idLogin = idLogin;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCapitania() {
        return nombreCapitania;
    }

    public void setNombreCapitania(String nombreCapitania) {
        this.nombreCapitania = nombreCapitania;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "UserTO{" +
                " codigoCapitania='" + codigoCapitania + '\'' +
                ", email='" + email + '\'' +
                ", idLogin='" + idLogin + '\'' +
                ", idPerfil=" + idPerfil +
                ", idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", nombreCapitania='" + nombreCapitania + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
