package com.sbi.dimar.visitaoficialarribooffline.app.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jenny Galindo on 23/4/2018
 * <p>
 * The InformationTO class represents the characteristics of the objects that contain the complete
 * information in the query of the arrival warnings web service
 */
public class InformationTO extends AuditTO implements Serializable {

    @SerializedName("agencias")
    @Expose(serialize = false)
    private List<AgencyTO> agencyTOS;

    @SerializedName("infoAvisoArribos")
    @Expose()
    private AvisosArriboTO avisosArriboTO;

    @SerializedName("instalacionesPortuarias")
    @Expose(serialize = false)
    private List<PortInstallationTO> portInstallationTOS;

    @SerializedName("lanchasTranspotepilotos")
    @Expose(serialize = false)
    private List<PilotTransportBoatTO> pilotTransportBoatTOS;

    @SerializedName("pilotosEntrenamiento")
    @Expose(serialize = false)
    private List<TrainingPilotTO> trainingPilotTOS;

    @SerializedName("pilotosPracticos")
    @Expose(serialize = false)
    private List<PracticalPilotTO> practicalPilotTOS;

    @SerializedName("pilotosPracticosAsistentes")
    @Expose(serialize = false)
    private List<PracticalPilotTO> practicalAsistentsPilotTOS;

    @SerializedName("razonArribo")
    @Expose(serialize = false)
    private List<ReasonArrivalTO> reasonArrivalTOS;

    @SerializedName("remolcadores")
    @Expose(serialize = false)
    private List<TugboatTO> tugboatTOS;

    @SerializedName("pilotosAsistentes")
    @Expose(serialize = false)
    private List<PilotoAsistenteTO> pilotoAsistenteTOS;

    @SerializedName("representantes")
    @Expose(serialize = false)
    private List<RepresentantTO> representantTOS;

    @Expose(serialize = false)
    private AvisoArriboInternacionalTO avisoArriboInternacionalTO;

    @Expose(serialize = false)
    private AvisoArriboCabotageTO avisoArriboCabotageTO;

    public List<AgencyTO> getAgencyTOS() {
        return agencyTOS;
    }

    public void setAgencyTOS(List<AgencyTO> agencyTOS) {
        this.agencyTOS = agencyTOS;
    }

    public List<PortInstallationTO> getPortInstallationTOS() {
        return portInstallationTOS;
    }

    public void setPortInstallationTOS(List<PortInstallationTO> portInstallationTOS) {
        this.portInstallationTOS = portInstallationTOS;
    }

    public List<PilotTransportBoatTO> getPilotTransportBoatTOS() {
        return pilotTransportBoatTOS;
    }

    public void setPilotTransportBoatTOS(List<PilotTransportBoatTO> pilotTransportBoatTOS) {
        this.pilotTransportBoatTOS = pilotTransportBoatTOS;
    }

    public List<TrainingPilotTO> getTrainingPilotTOS() {
        return trainingPilotTOS;
    }

    public void setTrainingPilotTOS(List<TrainingPilotTO> trainingPilotTOS) {
        this.trainingPilotTOS = trainingPilotTOS;
    }

    public List<PracticalPilotTO> getPracticalPilotTOS() {
        return practicalPilotTOS;
    }

    public void setPracticalPilotTOS(List<PracticalPilotTO> practicalPilotTOS) {
        this.practicalPilotTOS = practicalPilotTOS;
    }

    public List<PracticalPilotTO> getPracticalAsistentsPilotTOS() {
        return practicalAsistentsPilotTOS;
    }

    public void setPracticalAsistentsPilotTOS(List<PracticalPilotTO> practicalAsistentsPilotTOS) {
        this.practicalAsistentsPilotTOS = practicalAsistentsPilotTOS;
    }

    public List<ReasonArrivalTO> getReasonArrivalTOS() {
        return reasonArrivalTOS;
    }

    public void setReasonArrivalTOS(List<ReasonArrivalTO> reasonArrivalTOS) {
        this.reasonArrivalTOS = reasonArrivalTOS;
    }

    public List<TugboatTO> getTugboatTOS() {
        return tugboatTOS;
    }

    public void setTugboatTOS(List<TugboatTO> tugboatTOS) {
        this.tugboatTOS = tugboatTOS;
    }

    public List<PilotoAsistenteTO> getPilotoAsistenteTOS() {
        return pilotoAsistenteTOS;
    }

    public void setPilotoAsistenteTOS(List<PilotoAsistenteTO> pilotoAsistenteTOS) {
        this.pilotoAsistenteTOS = pilotoAsistenteTOS;
    }

    public List<RepresentantTO> getRepresentantTOS() {
        return representantTOS;
    }

    public void setRepresentantTOS(List<RepresentantTO> representantTOS) {
        this.representantTOS = representantTOS;
    }

    public AvisosArriboTO getAvisosArriboTO() {
        return avisosArriboTO;
    }

    public AvisoArriboInternacionalTO getAvisoArriboInternacionalTO() {
        return avisoArriboInternacionalTO;
    }

    public void setAvisoArriboInternacionalTO(AvisoArriboInternacionalTO avisoArriboInternacionalTO) {
        this.avisoArriboInternacionalTO = avisoArriboInternacionalTO;
    }

    public AvisoArriboCabotageTO getAvisoArriboCabotageTO() {
        return avisoArriboCabotageTO;
    }

    public void setAvisoArriboCabotageTO(AvisoArriboCabotageTO avisoArriboCabotageTO) {
        this.avisoArriboCabotageTO = avisoArriboCabotageTO;
    }
}
