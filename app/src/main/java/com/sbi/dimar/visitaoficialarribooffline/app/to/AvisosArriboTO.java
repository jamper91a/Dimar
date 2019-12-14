package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jenny Galindo on 4/5/2018
 * <p>
 * The AvisosArriboTO class represents the characteristics of an object international arrival notice and cabotaje
 */
public class AvisosArriboTO extends AuditTO implements Serializable {

    //AVISO ARRIBO
    @Expose(serialize = false, deserialize = false)
    private long numeroAvisoArribo;
    @Expose(serialize = false, deserialize = false)
    private String omiMatricula;

    @Expose(serialize = false, deserialize = false)
    private String nombreNave;
    @Expose(serialize = false, deserialize = false)
    private String tipoAviso;
    @Expose(serialize = false, deserialize = false)
    private int estado;

    @SerializedName("avisosArriboInternacional")
    @Expose()
    private List<AvisoArriboInternacionalTO> avisoArriboInternacionalTOS;
    @Expose()
    @SerializedName("avisosArriboNacional")
    private List<AvisoArriboCabotageTO> avisoArriboCabotageTOS;


    public AvisosArriboTO() {
    }


    public List<AvisoArriboInternacionalTO> getAvisoArriboInternacionalTOS() {
        return avisoArriboInternacionalTOS;
    }

    public void setAvisoArriboInternacionalTOS(List<AvisoArriboInternacionalTO> avisoArriboInternacionalTOS) {
        this.avisoArriboInternacionalTOS = avisoArriboInternacionalTOS;
    }

    public List<AvisoArriboCabotageTO> getAvisoArriboCabotageTOS() {
        return avisoArriboCabotageTOS;
    }

    public void setAvisoArriboCabotageTOS(List<AvisoArriboCabotageTO> avisoArriboCabotageTOS) {
        this.avisoArriboCabotageTOS = avisoArriboCabotageTOS;
    }

    public long getNumeroAvisoArribo() {
        return numeroAvisoArribo;
    }

    public void setNumeroAvisoArribo(long numeroAvisoArribo) {
        this.numeroAvisoArribo = numeroAvisoArribo;
    }

    public String getOmiMatricula() {
        return omiMatricula;
    }

    public void setOmiMatricula(String omiMatricula) {
        this.omiMatricula = omiMatricula;
    }

    public String getNombreNave() {
        return nombreNave;
    }

    public void setNombreNave(String nombreNave) {
        this.nombreNave = nombreNave;
    }

    public String getTipoAviso() {
        return tipoAviso;
    }

    public void setTipoAviso(String tipoAviso) {
        this.tipoAviso = tipoAviso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
