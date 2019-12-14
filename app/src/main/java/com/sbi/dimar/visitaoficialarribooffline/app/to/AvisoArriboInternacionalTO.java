package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The AvisoArriboInternacionalTO class represents the characteristics of an arrival notice object
 */
public class AvisoArriboInternacionalTO extends AuditTO implements Serializable {

    //AVISO ARRIBO
    @SerializedName("numeroAvisoArribo")
    @Expose()
    private long numeroAvisoArribo;
    @SerializedName("omiMatricula")
    @Expose()
    private String omiMatricula;
    @SerializedName("idPuertoDestino")
    @Expose()
    private String idPuertoDestino;
    @SerializedName("nombreNave")
    @Expose()
    private String nombreNave;
    @SerializedName("nombrePuertoDestino")
    @Expose()
    private String nombrePuertoDestino;
    @Expose()
    @SerializedName("eta")
    private String eta;
    @SerializedName("fecha")
    @Expose()
    private String fecha;
    @SerializedName("puertoProcedencia")
    @Expose()
    private String puertoProcedencia;
    @SerializedName("nitAgencia")
    @Expose()
    private String nitAgencia;
    @SerializedName("razonSocial")
    @Expose()
    private String razonSocial;
    @SerializedName("idEstado")
    @Expose()
    private int idEstado;
    @SerializedName("descripcionEstado")
    @Expose()
    private String descripcionEstado;
    @SerializedName("tipoAviso")
    @Expose()
    private String tipoAviso;
    @SerializedName("esloraMaxima")
    @Expose()
    private double esloraMaxima;
    @SerializedName("trb")
    @Expose()
    private double trb;
    @SerializedName("trn")
    @Expose()
    private double trn;
    @SerializedName("pais")
    @Expose()
    private String pais;
    @SerializedName("tripulacionArribo")
    @Expose()
    private int tripulacionArribo;
    @SerializedName("tipoBuque")
    @Expose()
    private String tipoBuque;
    @Expose()
    @SerializedName("tipoCarga")
    private String tipoCarga;
    @Expose()
    @SerializedName("cantidadCarga")
    private double cantidadCarga;
    @Expose()
    @SerializedName("desechosPuerto")
    private boolean desechosPuerto;
    @Expose()
    @SerializedName("cantidadPasajeros")
    private int cantidadPasajeros;
    @Expose()
    @SerializedName("nombreCapitanNave")
    private String nombreCapitanNave;
    @SerializedName("paisBandera")
    @Expose()
    private String paisBandera;
    @SerializedName("idUsuario")
    @Expose()
    private int idUsuario;

    @SerializedName("avisoOperativoInternacional")
    @Expose()
    private ArriboOperativoInternacionalTO arriboOperInternacionalTO;

    @SerializedName("remolcadores")
    @Expose()
    private List<RemolcadoresTO> remolcadoresTO;

    @SerializedName("pilotosPracticosAsistentes")
    @Expose()
    private List<PilotoAsistenteTO> pilotosAsistentesTO;

    @SerializedName("avisoAdministrativoInternacional")
    @Expose()
    private ArriboAdministrativoInternacionalTO arriboAdminInternacionalTO;

    //Firma
    @SerializedName("firmas")
    @Expose(deserialize = false)
    private List<SignatureTO> signatureTOS;

    //PDF
    @SerializedName("archivo")
    @Expose(deserialize = false)
    private PDFTO pdfTO;

//    @SerializedName("remolcadores")
//    private List<TugboatTO> tugboatTOS;


    public AvisoArriboInternacionalTO() {
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

    public String getNombrePuertoDestino() {
        return nombrePuertoDestino;
    }

    public void setNombrePuertoDestino(String nombrePuertoDestino) {
        this.nombrePuertoDestino = nombrePuertoDestino;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPuertoProcedencia() {
        return puertoProcedencia;
    }

    public void setPuertoProcedencia(String puertoProcedencia) {
        this.puertoProcedencia = puertoProcedencia;
    }

    public String getNitAgencia() {
        return nitAgencia;
    }

    public void setNitAgencia(String nitAgencia) {
        this.nitAgencia = nitAgencia;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getTripulacionArribo() {
        return tripulacionArribo;
    }

    public void setTripulacionArribo(int tripulacionArribo) {
        this.tripulacionArribo = tripulacionArribo;
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

    public boolean isDesechosPuerto() {
        return desechosPuerto;
    }

    public void setDesechosPuerto(boolean desechosPuerto) {
        this.desechosPuerto = desechosPuerto;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public String getNombreCapitanNave() {
        return nombreCapitanNave;
    }

    public void setNombreCapitanNave(String nombreCapitanNave) {
        this.nombreCapitanNave = nombreCapitanNave;
    }


    public String getPaisBandera() {
        return paisBandera;
    }

    public void setPaisBandera(String paisBandera) {
        this.paisBandera = paisBandera;
    }

    public ArriboOperativoInternacionalTO getArriboOperInternacionalTO() {
        return arriboOperInternacionalTO;
    }

    public void setArriboOperInternacionalTO(ArriboOperativoInternacionalTO arriboOperInternacionalTO) {
        this.arriboOperInternacionalTO = arriboOperInternacionalTO;
    }

    public ArriboAdministrativoInternacionalTO getArriboAdminInternacionalTO() {
        return arriboAdminInternacionalTO;
    }

    public void setArriboAdminInternacionalTO(ArriboAdministrativoInternacionalTO arriboAdminInternacionalTO) {
        this.arriboAdminInternacionalTO = arriboAdminInternacionalTO;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPuertoDestino() {
        return idPuertoDestino;
    }

    public void setIdPuertoDestino(String idPuertoDestino) {
        this.idPuertoDestino = idPuertoDestino;
    }

    public List<RemolcadoresTO> getRemolcadoresTO() {
        return remolcadoresTO;
    }

    public void setRemolcadoresTO(List<RemolcadoresTO> remolcadoresTO) {
        this.remolcadoresTO = remolcadoresTO;
    }

    public List<PilotoAsistenteTO> getPilotosAsistentesTO() {
        return pilotosAsistentesTO;
    }

    public void setPilotosAsistentesTO(List<PilotoAsistenteTO> pilotosAsistentesTO) {
        this.pilotosAsistentesTO = pilotosAsistentesTO;
    }

    /**
     * Created by Jenny Galindo
     * <p>
     * The ArriboOperativoInternacionalTO class represents the characteristics of an operational movement object
     */
    public static class ArriboOperativoInternacionalTO implements Serializable {

        //ARRIBO OPERATIVO
        @SerializedName("numeroArribo")
        @Expose()
        private long numeroArribo;
        @SerializedName("idPilotoPractico")
        @Expose()
        private String idPilotoPractico;
        @SerializedName("idPilotoEntrenamiento")
        @Expose()
        private String idPilotoEntrenamiento; //No obligatorio
        @SerializedName("idInstalacionportuaria")
        @Expose()
        private String idInstalacionportuaria;
        @SerializedName("idLanchaTransportePiloto")
        @Expose()
        private String idLanchaTransportePiloto; //No obligatorio
        @SerializedName("idRemolcador")
        @Expose()
        private String idRemolcador; //No obligatorio
        @SerializedName("fechaHoraArribo")
        @Expose()
        private String fechaHoraArribo;
        @SerializedName("fechaHoraPilotoAbordoReporte")
        @Expose()
        private String fechaHoraPilotoAbordoReporte;
        @SerializedName("fechaHoraInicioManiobraAtraque")
        @Expose()
        private String fechaHoraInicioManiobraAtraque;
        @SerializedName("fechaHoraFinManiobraAtraque")
        @Expose()
        private String fechaHoraFinManiobraAtraque;
        @SerializedName("idPilotoAtraque")
        @Expose()
        private String idPilotoAtraque;

        @SerializedName("idPilotoEntrenamientoAtraque")
        @Expose()
        private String idPilotoEntrenamientoAtraque; //No obligatorio
        @SerializedName("caladoPopa")
        @Expose()
        private double caladoPopa;
        @SerializedName("caladoProa")
        @Expose()
        private double caladoProa;
        @SerializedName("fechaIngresoAreaControl")
        @Expose()
        private String fechaIngresoAreaControl;


        public ArriboOperativoInternacionalTO() {
        }

        public long getNumeroArribo() {
            return numeroArribo;
        }

        public void setNumeroArribo(long numeroArribo) {
            this.numeroArribo = numeroArribo;
        }

        public String getIdPilotoPractico() {
            return idPilotoPractico;
        }

        public void setIdPilotoPractico(String idPilotoPractico) {
            this.idPilotoPractico = idPilotoPractico;
        }

        public String getIdPilotoEntrenamiento() {
            return idPilotoEntrenamiento;
        }

        public void setIdPilotoEntrenamiento(String idPilotoEntrenamiento) {
            this.idPilotoEntrenamiento = idPilotoEntrenamiento;
        }

        public String getIdInstalacionportuaria() {
            return idInstalacionportuaria;
        }

        public void setIdInstalacionportuaria(String idInstalacionportuaria) {
            this.idInstalacionportuaria = idInstalacionportuaria;
        }

        public String getIdLanchaTransportePiloto() {
            return idLanchaTransportePiloto;
        }

        public void setIdLanchaTransportePiloto(String idLanchaTransportePiloto) {
            this.idLanchaTransportePiloto = idLanchaTransportePiloto;
        }

        public String getIdRemolcador() {
            return idRemolcador;
        }

        public void setIdRemolcador(String idRemolcador) {
            this.idRemolcador = idRemolcador;
        }

        public String getFechaHoraArribo() {
            return fechaHoraArribo;
        }

        public void setFechaHoraArribo(String fechaHoraArribo) {
            this.fechaHoraArribo = fechaHoraArribo;
        }

        public String getFechaHoraPilotoAbordoReporte() {
            return fechaHoraPilotoAbordoReporte;
        }

        public void setFechaHoraPilotoAbordoReporte(String fechaHoraPilotoAbordoReporte) {
            this.fechaHoraPilotoAbordoReporte = fechaHoraPilotoAbordoReporte;
        }

        public String getFechaHoraInicioManiobraAtraque() {
            return fechaHoraInicioManiobraAtraque;
        }

        public void setFechaHoraInicioManiobraAtraque(String fechaHoraInicioManiobraAtraque) {
            this.fechaHoraInicioManiobraAtraque = fechaHoraInicioManiobraAtraque;
        }

        public String getFechaHoraFinManiobraAtraque() {
            return fechaHoraFinManiobraAtraque;
        }

        public void setFechaHoraFinManiobraAtraque(String fechaHoraFinManiobraAtraque) {
            this.fechaHoraFinManiobraAtraque = fechaHoraFinManiobraAtraque;
        }

        public String getIdPilotoAtraque() {
            return idPilotoAtraque;
        }

        public void setIdPilotoAtraque(String idPilotoAtraque) {
            this.idPilotoAtraque = idPilotoAtraque;
        }

        public String getIdPilotoEntrenamientoAtraque() {
            return idPilotoEntrenamientoAtraque;
        }

        public void setIdPilotoEntrenamientoAtraque(String idPilotoEntrenamientoAtraque) {
            this.idPilotoEntrenamientoAtraque = idPilotoEntrenamientoAtraque;
        }

        public double getCaladoPopa() {
            return caladoPopa;
        }

        public void setCaladoPopa(double caladoPopa) {
            this.caladoPopa = caladoPopa;
        }

        public double getCaladoProa() {
            return caladoProa;
        }

        public void setCaladoProa(double caladoProa) {
            this.caladoProa = caladoProa;
        }

        public String getFechaIngresoAreaControl() {
            return fechaIngresoAreaControl;
        }

        public void setFechaIngresoAreaControl(String fechaIngresoAreaControl) {
            this.fechaIngresoAreaControl = fechaIngresoAreaControl;
        }
    }

    /**
     * Created by Jenny Galindo
     * <p>
     * The ArriboAdministrativoCabotageTO class represents the characteristics of an administrative movement object
     */
    public static class ArriboAdministrativoInternacionalTO implements Serializable {
        //ARRIBO ADMINISTRATIVO
        @SerializedName("idAdministratiivo")
        @Expose()
        private long arriboAdminitrativoId;
        @SerializedName("fechaHoraLibrePlatica")
        @Expose()
        private String fechaHoraLibrePlatica;
        @SerializedName("fechaHoraVisita")
        @Expose()
        private String fechaHoraVisita;
        @SerializedName("siLibrePlatica")
        @Expose()
        private int siLibrePlatica;
        @SerializedName("numeroPasajerosTrancito")
        @Expose()
        private int numeroPasajerosTrancito;
        @SerializedName("observaciones")
        @Expose()
        private String observaciones;

        public ArriboAdministrativoInternacionalTO() {
        }

        public long getArriboAdminitrativoId() {
            return arriboAdminitrativoId;
        }

        public void setArriboAdminitrativoId(long arriboAdminitrativoId) {
            this.arriboAdminitrativoId = arriboAdminitrativoId;
        }

        public String getFechaHoraLibrePlatica() {
            return fechaHoraLibrePlatica;
        }

        public void setFechaHoraLibrePlatica(String fechaHoraLibrePlatica) {
            this.fechaHoraLibrePlatica = fechaHoraLibrePlatica;
        }

        public String getFechaHoraVisita() {
            return fechaHoraVisita;
        }

        public void setFechaHoraVisita(String fechaHoraVisita) {
            this.fechaHoraVisita = fechaHoraVisita;
        }

        public int getSiLibrePlatica() {
            return siLibrePlatica;
        }

        public void setSiLibrePlatica(int siLibrePlatica) {
            this.siLibrePlatica = siLibrePlatica;
        }

        public int getNumeroPasajerosTrancito() {
            return numeroPasajerosTrancito;
        }

        public void setNumeroPasajerosTrancito(int numeroPasajerosTrancito) {
            this.numeroPasajerosTrancito = numeroPasajerosTrancito;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }
    }

}
