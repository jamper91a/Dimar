package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The AvisoArriboCabotageTO class represents the characteristics of an arrival notice object
 */
public class AvisoArriboCabotageTO extends AuditTO implements Serializable {
    //AVISO ARRIBO
    @SerializedName("numeroAvisoArribo")
    @Expose()
    private long numeroAvisoArribo;
    @SerializedName("matricula")
    @Expose()
    private String omiMatricula;
    @SerializedName("idPuertoDestino")
    @Expose()
    private String idPuertoDestino;
    @SerializedName("nombreNave")
    @Expose()
    private String nombreNave;
    @SerializedName("tipoAviso")
    @Expose()
    private String tipoAviso;
    @SerializedName("idPais")
    @Expose()
    private String idPais;
    @SerializedName("pais")
    @Expose()
    private String pais;
    @SerializedName("eta")
    @Expose()
    private String eta;
    @SerializedName("idMuelleOrigen")
    @Expose()
    private String idMuelleOrigen;
    @SerializedName("muelleOrigen")
    @Expose()
    private String muelleOrigen;
    @SerializedName("capitaniaOrigen")
    @Expose()
    private String capitaniaOrigen;
    @SerializedName("idmuelleDestino")
    @Expose()
    private String idmuelleDestino;
    @SerializedName("muelleDestino")
    @Expose()
    private String muelleDestino;
    @SerializedName("capitaniaDestino")
    @Expose()
    private String capitaniaDestino;
    @SerializedName("nombrePuertoDestino")
    @Expose()
    private String nombrePuertoDestino;
    @SerializedName("idEstadoActual")
    @Expose()
    private int idEstado;
    @SerializedName("estado")
    @Expose()
    private String estado;
    @SerializedName("esloraMaxima")
    @Expose()
    private double esloraMaxima;
    @SerializedName("trb")
    @Expose()
    private double trb;
    @SerializedName("trn")
    @Expose()
    private double trn;
    @SerializedName("tripulantesSolicitudZarpe")
    @Expose()
    private int tripulantesSolicitudZarpe;
    @SerializedName("tipoBuque")
    @Expose()
    private String tipoBuque;
    @SerializedName("tipoCarga")
    @Expose()
    private String tipoCarga;
    @SerializedName("cantidadCarga")
    @Expose()
    private double cantidadCarga;
    @SerializedName("cantidadPasajeros")
    @Expose()
    private int cantidadPasajeros;
    @SerializedName("responsable")
    @Expose()
    private String responsable;
    @SerializedName("fecha")
    @Expose()
    private String fecha;
    @SerializedName("idAgencia")
    @Expose()
    private String idAgencia;

    //Firma
    @SerializedName("firmas")
    @Expose(deserialize = false)
    private List<SignatureTO> signatureTOS;

    //PDF
    @SerializedName("archivo")
    @Expose(deserialize = false)
    private PDFTO pdfTO;

    @SerializedName("avisoOperativoNacional")
    @Expose()
    private ArriboOperativoCabotageTO arriboOperCabotageTO;
    @SerializedName("avisoAdministrativoNacional")
    @Expose()
    private ArriboAdministrativoCabotageTO arriboAdminCabotageTO;

    public AvisoArriboCabotageTO() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getIdMuelleOrigen() {
        return idMuelleOrigen;
    }

    public void setIdMuelleOrigen(String idMuelleOrigen) {
        this.idMuelleOrigen = idMuelleOrigen;
    }

    public String getMuelleOrigen() {
        return muelleOrigen;
    }

    public void setMuelleOrigen(String muelleOrigen) {
        this.muelleOrigen = muelleOrigen;
    }

    public String getCapitaniaOrigen() {
        return capitaniaOrigen;
    }

    public void setCapitaniaOrigen(String capitaniaOrigen) {
        this.capitaniaOrigen = capitaniaOrigen;
    }

    public String getIdmuelleDestino() {
        return idmuelleDestino;
    }

    public void setIdmuelleDestino(String idmuelleDestino) {
        this.idmuelleDestino = idmuelleDestino;
    }

    public String getMuelleDestino() {
        return muelleDestino;
    }

    public void setMuelleDestino(String muelleDestino) {
        this.muelleDestino = muelleDestino;
    }

    public String getCapitaniaDestino() {
        return capitaniaDestino;
    }

    public void setCapitaniaDestino(String capitaniaDestino) {
        this.capitaniaDestino = capitaniaDestino;
    }

    public String getIdPuertoDestino() {
        return idPuertoDestino;
    }

    public void setIdPuertoDestino(String idPuertoDestino) {
        this.idPuertoDestino = idPuertoDestino;
    }

    public String getNombrePuertoDestino() {
        return nombrePuertoDestino;
    }

    public void setNombrePuertoDestino(String nombrePuertoDestino) {
        this.nombrePuertoDestino = nombrePuertoDestino;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getEsloraMaxima() {
        return esloraMaxima;
    }

    public void setEsloraMaxima(double esloraMaxima) {
        this.esloraMaxima = esloraMaxima;
    }

    public double getTrb() {
        return trb;
    }

    public void setTrb(double trb) {
        this.trb = trb;
    }

    public double getTrn() {
        return trn;
    }

    public void setTrn(double trn) {
        this.trn = trn;
    }

    public int getTripulantesSolicitudZarpe() {
        return tripulantesSolicitudZarpe;
    }

    public void setTripulantesSolicitudZarpe(int tripulantesSolicitudZarpe) {
        this.tripulantesSolicitudZarpe = tripulantesSolicitudZarpe;
    }

    public String getTipoBuque() {
        return tipoBuque;
    }

    public void setTipoBuque(String tipoBuque) {
        this.tipoBuque = tipoBuque;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public double getCantidadCarga() {
        return cantidadCarga;
    }

    public void setCantidadCarga(double cantidadCarga) {
        this.cantidadCarga = cantidadCarga;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public String getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(String idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public ArriboOperativoCabotageTO getArriboOperCabotageTO() {
        return arriboOperCabotageTO;
    }

    public void setArriboOperCabotageTO(ArriboOperativoCabotageTO arriboOperCabotageTO) {
        this.arriboOperCabotageTO = arriboOperCabotageTO;
    }

    public ArriboAdministrativoCabotageTO getArriboAdminCabotageTO() {
        return arriboAdminCabotageTO;
    }

    public void setArriboAdminCabotageTO(ArriboAdministrativoCabotageTO arriboAdminCabotageTO) {
        this.arriboAdminCabotageTO = arriboAdminCabotageTO;
    }

    public List<SignatureTO> getSignatureTOS() {
        return signatureTOS;
    }

    public void setSignatureTOS(List<SignatureTO> signatureTOS) {
        this.signatureTOS = signatureTOS;
    }

    public PDFTO getPdfTO() {
        return pdfTO;
    }

    public void setPdfTO(PDFTO pdfTO) {
        this.pdfTO = pdfTO;
    }

    /**
     * Created by Jenny Galindo
     * <p>
     * The ArriboOperativoCabotageTO class represents the characteristics of an operational movement object
     */
    public static class ArriboOperativoCabotageTO implements Serializable {
        //ARRIBO OPERATIVO
        @SerializedName("numeroArribo")
        @Expose(serialize = false)
        private long numeroArribo;
        @SerializedName("calado")
        @Expose()
        private double calado;
        @SerializedName("fechaAtraque")
        @Expose()
        private String fechaAtraque;
        @SerializedName("fechaIngresoAreaControl")
        @Expose()
        private String fechaIngresoAreaControl;
        @SerializedName("idRazonArribo")
        @Expose()
        private int idRazonArribo;
        @SerializedName("razonArribo")
        @Expose()
        private String razonArribo;
        @SerializedName("muelleAtraque")
        @Expose()
        private String muelleAtraque;
        @SerializedName("nombreMuelleAtraque")
        @Expose()
        private String nombreMuelleAtraque;
        @Expose()
        @SerializedName("observaciones")
        private String observaciones;
        @SerializedName("idUsuario")
        @Expose()
        private int idUsuario;
        //No esta en el modelo
        @SerializedName("fechaHoraArribo")
        @Expose()
        private String fechaHoraArribo;

        public long getNumeroArribo() {
            return numeroArribo;
        }

        public void setNumeroArribo(long numeroArribo) {
            this.numeroArribo = numeroArribo;
        }

        public double getCalado() {
            return calado;
        }

        public void setCalado(double calado) {
            this.calado = calado;
        }

        public String getFechaAtraque() {
            return fechaAtraque;
        }

        public void setFechaAtraque(String fechaAtraque) {
            this.fechaAtraque = fechaAtraque;
        }

        public String getFechaIngresoAreaControl() {
            return fechaIngresoAreaControl;
        }

        public void setFechaIngresoAreaControl(String fechaIngresoAreaControl) {
            this.fechaIngresoAreaControl = fechaIngresoAreaControl;
        }

        public int getIdRazonArribo() {
            return idRazonArribo;
        }

        public void setIdRazonArribo(int idRazonArribo) {
            this.idRazonArribo = idRazonArribo;
        }

        public String getRazonArribo() {
            return razonArribo;
        }

        public void setRazonArribo(String razonArribo) {
            this.razonArribo = razonArribo;
        }

        public String getMuelleAtraque() {
            return muelleAtraque;
        }

        public void setMuelleAtraque(String muelleAtraque) {
            this.muelleAtraque = muelleAtraque;
        }

        public String getNombreMuelleAtraque() {
            return nombreMuelleAtraque;
        }

        public void setNombreMuelleAtraque(String nombreMuelleAtraque) {
            this.nombreMuelleAtraque = nombreMuelleAtraque;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getFechaHoraArribo() {
            return fechaHoraArribo;
        }

        public void setFechaHoraArribo(String fechaHoraArribo) {
            this.fechaHoraArribo = fechaHoraArribo;
        }
    }

    /**
     * Created by Jenny Galindo
     * <p>
     * The ArriboAdministrativoCabotageTO class represents the characteristics of an administrative movement object
     */
    public static class ArriboAdministrativoCabotageTO implements Serializable {
        //ARRIBO ADMINISTRATIVO
        @SerializedName("idAdministratiivo")
        @Expose()
        private long arriboAdminitrativoId;
        @SerializedName("numeroAvisoArribo")
        @Expose()
        private long numeroAvisoArribo;
        @SerializedName("fechaCreacion")
        @Expose()
        private String fechaCreacion;
        @SerializedName("fechaLIbrePlatica")
        @Expose()
        private String fechaLibrePlatica;
        @SerializedName("fechaVisita")
        @Expose()
        private String fechaVisita;
        @SerializedName("nitAgencia")
        @Expose()
        private String nitAgencia;
        @SerializedName("idUsuario")
        @Expose()
        private int idUsuario;
        @SerializedName("observaciones")
        @Expose()
        private String observaciones;//No obligatorio
        @SerializedName("visitada")
        @Expose()
        private boolean visitada;

        public ArriboAdministrativoCabotageTO() {
        }

        public ArriboAdministrativoCabotageTO(long arriboAdminitrativoId) {
            this.arriboAdminitrativoId = arriboAdminitrativoId;
        }

        public long getArriboAdminitrativoId() {
            return arriboAdminitrativoId;
        }

        public void setArriboAdminitrativoId(long arriboAdminitrativoId) {
            this.arriboAdminitrativoId = arriboAdminitrativoId;
        }

        public long getNumeroAvisoArribo() {
            return numeroAvisoArribo;
        }

        public void setNumeroAvisoArribo(long numeroAvisoArribo) {
            this.numeroAvisoArribo = numeroAvisoArribo;
        }

        public String getFechaCreacion() {
            return fechaCreacion;
        }

        public void setFechaCreacion(String fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public String getFechaLibrePlatica() {
            return fechaLibrePlatica;
        }

        public void setFechaLibrePlatica(String fechaLibrePlatica) {
            this.fechaLibrePlatica = fechaLibrePlatica;
        }

        public String getFechaVisita() {
            return fechaVisita;
        }

        public void setFechaVisita(String fechaVisita) {
            this.fechaVisita = fechaVisita;
        }

        public String getNitAgencia() {
            return nitAgencia;
        }

        public void setNitAgencia(String nitAgencia) {
            this.nitAgencia = nitAgencia;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

        public boolean isVisitada() {
            return visitada;
        }

        public void setVisitada(boolean visitada) {
            this.visitada = visitada;
        }
    }
}
