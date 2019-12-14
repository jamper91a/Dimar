package com.sbi.dimar.visitaoficialarribooffline.app.services.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AgencyDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.AgencyDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PdfDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PdfDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PilotTransportBoatDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PilotTransportBoatDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PilotosAsistentesDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PilotosAsistentesDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PortInstallationDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PortInstallationDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PracticalPilotAsistentsDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PracticalPilotAsistentsDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PracticalPilotDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.PracticalPilotDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.ReasonArrivalDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.ReasonArrivalDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.RemolcadoresDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.RemolcadoresDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.RepresentantDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.RepresentantDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.SignatureDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.SignatureDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.TrainingPilotDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.TrainingPilotDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.TugboatDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.TugboatDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.UserDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.cabotaje.ArriboAdminCabotajeDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.cabotaje.ArriboAdminCabotajeDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.cabotaje.AvisoArriboCabotajeDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.cabotaje.AvisoArriboCabotajeDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.internacional.ArriboAdminInternacionalDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.internacional.ArriboAdminInternacionalDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.internacional.AvisoArriboInternacionalDAO;
import com.sbi.dimar.visitaoficialarribooffline.app.database.dao.internacional.AvisoArriboInternacionalDAOImpl;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.DatabaseHelper;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AgencyTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboCabotageTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisoArriboInternacionalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisosArriboTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.InformationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PDFTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotTransportBoatTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotoAsistenteTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PortInstallationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PracticalPilotTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.ReasonArrivalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RepresentantTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TrainingPilotTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.PdfUtil;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;

/**
 * Created by Jenny Galindo
 * <p>
 * The DatabaseService class represents the service that communicates with each implementation of the tables
 */
public class DatabaseService {
    private static final String TAG = DatabaseService.class.getSimpleName();
    private final Context context;
    private SQLiteDatabase database;
    private final DatabaseHelper dbHelper;
    private static DatabaseService instance;

    public DatabaseService(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseService getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseService(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    void open() {
        this.database = dbHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public UserTO getUsers() {
        open();
        UserDAO userDAO = new UserDAO(database);
        UserTO userTO = userDAO.getUser();
        close();
        return userTO;
    }

    public boolean saveAvisosArribo(InformationTO informationTO, UserTO userTO) {
        boolean saveAll = false;
        if (deleteAllInfo()) {
            if (saveAgencies(informationTO.getAgencyTOS())) {
                saveAll = true;
            }
            if (savePortInstallation(informationTO.getPortInstallationTOS())) {
                saveAll = true;
            }
            if (savePilotTransportBoat(informationTO.getPilotTransportBoatTOS())) {
                saveAll = true;
            }
            if (savePracticalPilot(informationTO.getPracticalPilotTOS())) {
                saveAll = true;
            }
            if (savePracticalAsitentsPilot(informationTO.getPracticalAsistentsPilotTOS())) {
                saveAll = true;
            }
            if (saveReasonsArrival(informationTO.getReasonArrivalTOS())) {
                saveAll = true;
            }
            if (saveRepresentants(informationTO.getRepresentantTOS())) {
                saveAll = true;
            }
            if (saveTrainingPilots(informationTO.getTrainingPilotTOS())) {
                saveAll = true;
            }
            if (saveTugboats(informationTO.getTugboatTOS())) {
                saveAll = true;
            }
            if (saveAvisosArriboCabotaje(informationTO.getAvisosArriboTO().getAvisoArriboCabotageTOS(), userTO)) {
                saveAll = true;
            }
            if (saveAvisosArriboInternacional(informationTO.getAvisosArriboTO().getAvisoArriboInternacionalTOS(), userTO)) {
                saveAll = true;
            }
        }
        return saveAll;
    }


    private boolean saveAgencies(List<AgencyTO> agencyTOS) {
        open();
        AgencyDAO agencyDAO = new AgencyDAOImpl(database);
        boolean saveAll;
        do {
            saveAll = agencyDAO.saveAgencies(agencyTOS);
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteAgencies() {
        open();
        AgencyDAO agencyDAO = new AgencyDAOImpl(database);
        boolean deleteAll;
        do {
            deleteAll = agencyDAO.deleteAgencies();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<AgencyTO> getAgencies() {
        open();
        AgencyDAO agencyDAO = new AgencyDAOImpl(database);
        List<AgencyTO> agencies = agencyDAO.getAgencies();
        close();
        return agencies;
    }

    private boolean savePortInstallation(List<PortInstallationTO> portInstallationTOS) {
        open();
        PortInstallationDAO portInstallationDAO = new PortInstallationDAOImpl(database, portInstallationTOS);
        boolean saveAll;
        do {
            saveAll = portInstallationDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deletePortInstallations() {
        open();
        PortInstallationDAO portInstallationDAO = new PortInstallationDAOImpl(database, null);
        boolean deleteAll;
        do {
            deleteAll = portInstallationDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<PortInstallationTO> getPortInstallations() {
        open();
        PortInstallationDAO portInstallationDAO = new PortInstallationDAOImpl(database, null);
        List<PortInstallationTO> portInstallationTOS = portInstallationDAO.findAllRecords();
        close();
        return portInstallationTOS;
    }

    private boolean savePilotTransportBoat(List<PilotTransportBoatTO> pilotTransportBoatTOS) {
        open();
        PilotTransportBoatDAO pilotTransportBoatDAO = new PilotTransportBoatDAOImpl(database, pilotTransportBoatTOS);
        boolean saveAll;
        do {
            saveAll = pilotTransportBoatDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deletePilotTransportBoats() {
        open();
        PilotTransportBoatDAO pilotTransportBoatDAO = new PilotTransportBoatDAOImpl(database, null);
        boolean deleteAll;
        do {
            deleteAll = pilotTransportBoatDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<PilotTransportBoatTO> getPilotTransportBoats() {
        open();
        PilotTransportBoatDAO pilotTransportBoatDAO = new PilotTransportBoatDAOImpl(database, null);
        List<PilotTransportBoatTO> pilotTransportBoatTOS = pilotTransportBoatDAO.findAllRecords();
        close();
        return pilotTransportBoatTOS;
    }

    private boolean savePracticalPilot(List<PracticalPilotTO> practicalPilotTOS) {
        open();
        PracticalPilotDAO practicalPilotDAO = new PracticalPilotDAOImpl(database, practicalPilotTOS);
        boolean saveAll;
        do {
            saveAll = practicalPilotDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean savePracticalAsitentsPilot(List<PracticalPilotTO> practicalPilotTOS) {
        open();
        PracticalPilotAsistentsDAO practicalPilotDAO = new PracticalPilotAsistentsDAOImpl(database, practicalPilotTOS);
        boolean saveAll;
        do {
//            saveAll = ((PracticalPilotAsistentsDAOImpl) practicalPilotDAO).createIfDoesNotExist();
            saveAll =  practicalPilotDAO.createIfDoesNotExist();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deletePracticalPilots() {
        open();
        PracticalPilotDAO practicalPilotDAO = new PracticalPilotDAOImpl(database, null);
        boolean deleteAll;
        do {
            deleteAll = practicalPilotDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<PracticalPilotTO> getPracticalPilots() {
        open();
        PracticalPilotDAO practicalPilotDAO = new PracticalPilotDAOImpl(database, null);
        List<PracticalPilotTO> practicalPilotTOS = practicalPilotDAO.findAllRecords();
        close();
        return practicalPilotTOS;
    }

    private List<PracticalPilotTO> getPracticalAsistentsPilots() {
        open();
        PracticalPilotAsistentsDAO practicalPilotDAO = new PracticalPilotAsistentsDAOImpl(database, null);
        List<PracticalPilotTO> practicalPilotTOS = practicalPilotDAO.findAllRecords();
        close();
        return practicalPilotTOS;
    }

    private boolean saveReasonsArrival(List<ReasonArrivalTO> reasonArrivalTOS) {
        open();
        ReasonArrivalDAO reasonArrivalDAO = new ReasonArrivalDAOImpl(database, reasonArrivalTOS);
        boolean saveAll;
        do {
            saveAll = reasonArrivalDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteReasonsArrival() {
        open();
        ReasonArrivalDAO reasonArrivalDAO = new ReasonArrivalDAOImpl(database, null);
        boolean deleteAll;
        do {
            deleteAll = reasonArrivalDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<ReasonArrivalTO> getReasonsArrival() {
        open();
        ReasonArrivalDAO reasonArrivalDAO = new ReasonArrivalDAOImpl(database, null);
        List<ReasonArrivalTO> reasonArrivalTOS = reasonArrivalDAO.findAllRecords();
        close();
        return reasonArrivalTOS;
    }

    private boolean saveRepresentants(List<RepresentantTO> representantTOS) {
        open();
        RepresentantDAO representantDAO = new RepresentantDAOImpl(database, representantTOS);
        boolean saveAll;
        do {
            saveAll = representantDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteRepresentants() {
        open();
        RepresentantDAO representantDAO = new RepresentantDAOImpl(database, null);
        boolean deleteAll;
        do {
            deleteAll = representantDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<RepresentantTO> getRepresentants() {
        open();
        RepresentantDAO representantDAO = new RepresentantDAOImpl(database, null);
        List<RepresentantTO> representantTOS = representantDAO.findAllRecords();
        close();
        return representantTOS;
    }

    private boolean saveTrainingPilots(List<TrainingPilotTO> trainingPilotTOS) {
        open();
        TrainingPilotDAO trainingPilotDAO = new TrainingPilotDAOImpl(database, trainingPilotTOS);
        boolean saveAll;
        do {
//            saveAll = trainingPilotDAO.create();
            saveAll = trainingPilotDAO.createIfDoesNotExist();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteTrainingPilots() {
        open();
        TrainingPilotDAO trainingPilotDAO = new TrainingPilotDAOImpl(database, null);
        boolean deleteAll;
        do {
            deleteAll = trainingPilotDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<TrainingPilotTO> getTrainingPilots() {
        open();
        TrainingPilotDAO trainingPilotDAO = new TrainingPilotDAOImpl(database, null);
        List<TrainingPilotTO> trainingPilotTOS = trainingPilotDAO.findAllRecords();
        close();
        return trainingPilotTOS;
    }

    private boolean saveTugboats(List<TugboatTO> tugboatTOS) {
        open();
        TugboatDAO tugboatDAO = new TugboatDAOImpl(database, tugboatTOS);
        boolean saveAll;
        do {
            saveAll = tugboatDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteTugboats() {
        open();
        TugboatDAO tugboatDAO = new TugboatDAOImpl(database, null);
        boolean deleteAll;
        do {
            deleteAll = tugboatDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }
    private boolean deleteRemolcadores() {
        open();
        RemolcadoresDAO remolcadoresDAO = new RemolcadoresDAOImpl(database, null, null);
        boolean deleteAll;
        do {
            deleteAll = remolcadoresDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private boolean deletePilotosAsistentes() {
        open();
        PilotosAsistentesDAO pilotosAsistentesDAO = new PilotosAsistentesDAOImpl(database, null, null);
        boolean deleteAll;
        do {
            deleteAll = pilotosAsistentesDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<TugboatTO> getTugboats() {
        open();
        TugboatDAO tugboatDAO = new TugboatDAOImpl(database, null);
        List<TugboatTO> tugboatTOS = tugboatDAO.findAllRecords();
        close();
        return tugboatTOS;
    }

    private boolean saveAvisosArriboCabotaje(List<AvisoArriboCabotageTO> avisoArriboCabotageTOS, UserTO userTO) {
        open();
        AvisoArriboCabotajeDAO avisoArriboCabotajeDAO = new AvisoArriboCabotajeDAOImpl(database, avisoArriboCabotageTOS, userTO);
        boolean saveAll;
        do {
            saveAll = avisoArriboCabotajeDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteAvisosArriboCabotaje() {
        open();
        AvisoArriboCabotajeDAO avisoArriboCabotajeDAO = new AvisoArriboCabotajeDAOImpl(database, null, null);
        boolean deleteAll;
        do {
            deleteAll = avisoArriboCabotajeDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<AvisoArriboCabotageTO> getAvisosArriboCabotaje() {
        open();
        AvisoArriboCabotajeDAO avisoArriboCabotajeDAO = new AvisoArriboCabotajeDAOImpl(database, null, null);
        List<AvisoArriboCabotageTO> allRecords = avisoArriboCabotajeDAO.findAllRecords();
        close();
        return allRecords;
    }

    private boolean saveAvisosArriboInternacional(List<AvisoArriboInternacionalTO> avisoArriboInternacionalTOS, UserTO userTO) {
        open();
        AvisoArriboInternacionalDAO avisoArriboInternacionalDAO = new AvisoArriboInternacionalDAOImpl(database, avisoArriboInternacionalTOS, userTO);
        boolean saveAll;
        do {
            saveAll = avisoArriboInternacionalDAO.create();
        } while (!saveAll);
        for(AvisoArriboInternacionalTO avisoArriboInternacionalTO: avisoArriboInternacionalTOS){
            for(RemolcadoresTO remolcadoresTO: avisoArriboInternacionalTO.getRemolcadoresTO()){
                remolcadoresTO.setNumeroAvisoArribo(avisoArriboInternacionalTO.getNumeroAvisoArribo());
                remolcadoresTO.setNombreRemolcador(remolcadoresTO.getNombreRemolcador()+" / "+remolcadoresTO.getIdRemolcador());
            }
            saveRemolcadoresAvisoArrivoInternacional(avisoArriboInternacionalTO.getRemolcadoresTO(),avisoArriboInternacionalTO.getTipoAviso());
        }
        for(AvisoArriboInternacionalTO avisoArriboInternacionalTO: avisoArriboInternacionalTOS){
            for(PilotoAsistenteTO pilotoAsistenteTO: avisoArriboInternacionalTO.getPilotosAsistentesTO()){
                pilotoAsistenteTO.setNumeroAvisoArribo(avisoArriboInternacionalTO.getNumeroAvisoArribo());
            }
            savePilotosAsistentesAvisoArrivoInternacional(avisoArriboInternacionalTO.getPilotosAsistentesTO(),avisoArriboInternacionalTO.getTipoAviso());
        }
        close();
        return saveAll;
    }

    private boolean savePilotosAsistentesAvisoArrivoInternacional(List<PilotoAsistenteTO> pilotoAsistenteTOS, String tipoAviso) {

        PilotosAsistentesDAO pilotosAsistentesDAO = new PilotosAsistentesDAOImpl(database, pilotoAsistenteTOS, tipoAviso);
        boolean saveAll;
        do {
            try {
                saveAll = pilotosAsistentesDAO.create();
            } catch (Exception e) {
                return false;
            }
        } while (!saveAll);
        return saveAll;
    }

    private boolean saveRemolcadoresAvisoArrivoInternacional(List<RemolcadoresTO> remolcadoresTOS, String tipoAviso) {

        RemolcadoresDAO signatureDAO = new RemolcadoresDAOImpl(database, remolcadoresTOS, tipoAviso);
        boolean saveAll;
        do {
            try {
                saveAll = signatureDAO.create();
            } catch (Exception e) {
                return false;
            }
        } while (!saveAll);
        return saveAll;
    }

    private boolean deleteAvisosArriboInternacional() {
        open();
        AvisoArriboInternacionalDAO avisoArriboInternacionalDAO = new AvisoArriboInternacionalDAOImpl(database, null, null);
        boolean deleteAll;
        do {
            deleteAll = avisoArriboInternacionalDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<AvisoArriboInternacionalTO> getAvisosArriboInternacional() {
        open();
        AvisoArriboInternacionalDAO avisoArriboInternacionalDAO = new AvisoArriboInternacionalDAOImpl(database, null, null);
        List<AvisoArriboInternacionalTO> allRecords = avisoArriboInternacionalDAO.findAllRecords();
        close();
        return allRecords;
    }

    public boolean deleteAllInfo() {
        boolean deleteAll = false;
        if (deleteAgencies()) {
            deleteAll = true;
        }
        if (deletePortInstallations()) {
            deleteAll = true;
        }
        if (deletePilotTransportBoats()) {
            deleteAll = true;
        }
        if (deletePracticalPilots()) {
            deleteAll = true;
        }
        if (deleteReasonsArrival()) {
            deleteAll = true;
        }
        if (deleteRepresentants()) {
            deleteAll = true;
        }
        if (deleteTrainingPilots()) {
            deleteAll = true;
        }
        if (deleteTugboats()) {
            deleteAll = true;
        }
        if (deleteRemolcadores()) {
            deleteAll = true;
        }

        if(deletePilotosAsistentes()){
            deleteAll = true;
        }
        if (deleteAvisosArriboCabotaje()) {
            deleteAll = true;
        }
        if (deleteAvisosArriboInternacional()) {
            deleteAll = true;
        }
        if (deleteSignatures()) {
            deleteAll = true;
        }
        if (deletePDFs()) {
            deleteAll = true;
        }
        if (deleteAdminArriboInternacional()) {
            deleteAll = true;
        }
        if (deleteAdminArriboCabotaje()) {
            deleteAll = true;
        }

        return deleteAll;

    }

    public boolean saveArriboInternacional(AvisoArriboInternacionalTO avisoArriboInternacionalTO, UserTO userTO) {
        open();
        boolean savedArribo;
        boolean savedArriboAdmin;
        boolean savedSignature;
        boolean savedRemolcadores;
        boolean savedPilotosAsistentes;
        boolean savedPDF;

        //Aviso y Oper
        List<AvisoArriboInternacionalTO> tos = new ArrayList<AvisoArriboInternacionalTO>();
        tos.add(avisoArriboInternacionalTO);
        AvisoArriboInternacionalDAO avisoArriboInternacionalDAO = new AvisoArriboInternacionalDAOImpl(database, tos, userTO);
        savedArribo = avisoArriboInternacionalDAO.updateRecord(avisoArriboInternacionalTO.getNumeroAvisoArribo(), avisoArriboInternacionalTO.getTipoAviso());
        close();

        //Admin
        savedArriboAdmin = saveAdminArriboInternacional(avisoArriboInternacionalTO);

        //Firmas
        savedSignature = saveSignatures(avisoArriboInternacionalTO.getSignatureTOS(), avisoArriboInternacionalTO.getTipoAviso());

        //PDF
        savedPDF = savePDF(avisoArriboInternacionalTO.getPdfTO(), avisoArriboInternacionalTO.getTipoAviso());

        //savedRemolcadores
        savedRemolcadores = saveRemolcadores(avisoArriboInternacionalTO.getRemolcadoresTO(), avisoArriboInternacionalTO.getTipoAviso());

        //save Pilotos asistentes
        savedPilotosAsistentes = savePilotosAsistentes(avisoArriboInternacionalTO.getPilotosAsistentesTO(), avisoArriboInternacionalTO.getTipoAviso());
        return savedArribo && savedArriboAdmin && savedSignature && savedPDF && savedRemolcadores && savedPilotosAsistentes;
    }

    public boolean saveArriboCabotaje(AvisoArriboCabotageTO avisoArriboCabotageTO, UserTO userTO) {
        open();
        boolean savedArribo;
        boolean savedArriboAdmin;
        boolean savedSignature;
        boolean savedPDF;
        boolean savedRemolcadores;
        //Aviso y Oper
        List<AvisoArriboCabotageTO> tos = new ArrayList<AvisoArriboCabotageTO>();
        tos.add(avisoArriboCabotageTO);
        AvisoArriboCabotajeDAO avisoArriboCabotajeDAO = new AvisoArriboCabotajeDAOImpl(database, tos, userTO);
        savedArribo = avisoArriboCabotajeDAO.updateRecord(avisoArriboCabotageTO.getNumeroAvisoArribo(), avisoArriboCabotageTO.getTipoAviso());
        close();

        //Admin
        savedArriboAdmin = saveAdminArriboCabotaje(avisoArriboCabotageTO, userTO);

        //Firmas
        savedSignature = saveSignatures(avisoArriboCabotageTO.getSignatureTOS(), avisoArriboCabotageTO.getTipoAviso());

        //PDF
        savedPDF = savePDF(avisoArriboCabotageTO.getPdfTO(), avisoArriboCabotageTO.getTipoAviso());


        return savedArribo && savedArriboAdmin && savedSignature && savedPDF;
    }

    private boolean saveSignatures(List<SignatureTO> signatureTOS, String tipoAviso) {
        open();
        SignatureDAO signatureDAO = new SignatureDAOImpl(database, signatureTOS, tipoAviso);
        boolean saveAll;
        do {
            saveAll = signatureDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean saveRemolcadores(List<RemolcadoresTO> remolcadoresTOS, String tipoAviso) {
        open();
        RemolcadoresDAO signatureDAO = new RemolcadoresDAOImpl(database, remolcadoresTOS, tipoAviso);
        boolean saveAll;
        do {
            try {
                saveAll = signatureDAO.create();
            } catch (Exception e) {
                return false;
            }
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean savePilotosAsistentes(List<PilotoAsistenteTO> pilotoAsistenteTOS, String tipoAviso) {
        open();
        PilotosAsistentesDAO signatureDAO = new PilotosAsistentesDAOImpl(database, pilotoAsistenteTOS, tipoAviso);
        boolean saveAll;
        do {
            try {
                saveAll = signatureDAO.create();
            } catch (Exception e) {
                return false;
            }
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteSignatures() {
        open();
        SignatureDAO signatureDAO = new SignatureDAOImpl(database, null, null);
        boolean deleteAll;
        do {
            deleteAll = signatureDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private List<SignatureTO> getSignaturesByAviso(long numeroAviso, String tipoAviso) {
        open();
        SignatureDAO signatureDAO = new SignatureDAOImpl(database, null, tipoAviso);
        List<SignatureTO> signatureTOS = signatureDAO.findSignaturesByAviso(numeroAviso, tipoAviso);
        close();
        return signatureTOS;
    }
    private List<RemolcadoresTO> getRemolcadoresByAviso(long numeroAviso, String tipoAviso) {
        open();
        RemolcadoresDAO remolcadoresDAO = new RemolcadoresDAOImpl(database, null, tipoAviso);
        List<RemolcadoresTO> remolcadoresTOS = remolcadoresDAO.findRemolcadoresByAviso(numeroAviso, tipoAviso);
        close();
        return remolcadoresTOS;
    }

    private List<PilotoAsistenteTO> getPilotosAsistentesByAviso(long numeroAviso, String tipoAviso) {
        open();
        PilotosAsistentesDAO pilotosAsistentesDAO = new PilotosAsistentesDAOImpl(database, null, tipoAviso);
        List<PilotoAsistenteTO> pilotoAsistenteTOS = pilotosAsistentesDAO.findPilotosAsistentesByAviso(numeroAviso, tipoAviso);
        close();
        return pilotoAsistenteTOS;
    }

    private boolean savePDF(PDFTO pdfTO, String tipoAviso) {
        open();
        List<PDFTO> pdfTOS = new ArrayList<>();
        pdfTOS.add(pdfTO);
        PdfDAO pdfDAO = new PdfDAOImpl(database, pdfTOS, tipoAviso);
        boolean saveAll;
        do {
            saveAll = pdfDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deletePDFs() {
        open();
        PdfDAO pdfDAO = new PdfDAOImpl(database, null, null);
        boolean deleteAll;
        do {
            deleteAll = pdfDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private PDFTO getPdfByAviso(long numeroAviso, String tipoAviso) {
        open();
        PdfDAO pdfDAO = new PdfDAOImpl(database, null, tipoAviso);
        PDFTO pdfTO = pdfDAO.findPdfByAviso(numeroAviso, tipoAviso);
        close();
        return pdfTO;
    }

    private boolean saveAdminArriboInternacional(AvisoArriboInternacionalTO arriboAdministrativoInternacionalTO) {
        open();
        List<AvisoArriboInternacionalTO> tos = new ArrayList<AvisoArriboInternacionalTO>();
        tos.add(arriboAdministrativoInternacionalTO);
        ArriboAdminInternacionalDAO arriboAdminInternacionalDAO = new ArriboAdminInternacionalDAOImpl(database, tos);
        boolean saveAll;
        do {
            saveAll = arriboAdminInternacionalDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteAdminArriboInternacional() {
        open();
        ArriboAdminInternacionalDAO arriboAdminInternacionalDAO = new ArriboAdminInternacionalDAOImpl(database, null);
        boolean deleteAll;
        do {
            deleteAll = arriboAdminInternacionalDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO getAdminArriboInternacional(long numeroAviso) {
        open();
        ArriboAdminInternacionalDAO arriboAdminInternacionalDAO = new ArriboAdminInternacionalDAOImpl(database, null);
        AvisoArriboInternacionalTO.ArriboAdministrativoInternacionalTO arriboAdmin = arriboAdminInternacionalDAO.findArriboAdminById(numeroAviso);
        close();
        return arriboAdmin;
    }

    private boolean saveAdminArriboCabotaje(AvisoArriboCabotageTO arriboCabotageTO, UserTO userTO) {
        open();
        List<AvisoArriboCabotageTO> tos = new ArrayList<AvisoArriboCabotageTO>();
        tos.add(arriboCabotageTO);
        ArriboAdminCabotajeDAO arriboAdminCabotajeDAO = new ArriboAdminCabotajeDAOImpl(database, tos, userTO);
        boolean saveAll;
        do {
            saveAll = arriboAdminCabotajeDAO.create();
        } while (!saveAll);
        close();
        return saveAll;
    }

    private boolean deleteAdminArriboCabotaje() {
        open();
        ArriboAdminCabotajeDAO arriboAdminCabotajeDAO = new ArriboAdminCabotajeDAOImpl(database, null, null);
        boolean deleteAll;
        do {
            deleteAll = arriboAdminCabotajeDAO.deleteAll();
        } while (!deleteAll);
        close();
        return deleteAll;
    }

    private AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO getAdminArriboCabotaje(long numeroAviso) {
        open();
        ArriboAdminCabotajeDAO arriboAdminCabotajeDAO = new ArriboAdminCabotajeDAOImpl(database, null, null);
        AvisoArriboCabotageTO.ArriboAdministrativoCabotageTO arriboAdmin = arriboAdminCabotajeDAO.findArriboAdminById(numeroAviso);
        close();
        return arriboAdmin;
    }

    public AvisosArriboTO getAvisosArribo() {
        AvisosArriboTO avisosArriboTO = new AvisosArriboTO();
        avisosArriboTO.setAvisoArriboCabotageTOS(getAvisosArriboCabotaje());
        avisosArriboTO.setAvisoArriboInternacionalTOS(getAvisosArriboInternacional());
        return avisosArriboTO;
    }

    private AvisoArriboCabotageTO getAvisoArriboCabotage(long numeroAviso, String tipoAviso) {
        open();
        AvisoArriboCabotajeDAO avisoArriboCabotajeDAO = new AvisoArriboCabotajeDAOImpl(database, null, null);
        AvisoArriboCabotageTO avisoArriboCabotageTO = avisoArriboCabotajeDAO.findAvisoById(numeroAviso, tipoAviso);
        close();
        return avisoArriboCabotageTO;
    }

    private AvisoArriboInternacionalTO getAvisoArriboInternacional(long numeroAviso, String tipoAviso) {
        open();
        AvisoArriboInternacionalDAO avisoArriboInternacionalDAO = new AvisoArriboInternacionalDAOImpl(database, null, null);
        AvisoArriboInternacionalTO avisoArriboInternacionalTO = avisoArriboInternacionalDAO.findAvisoById(numeroAviso, tipoAviso);
        close();
        return avisoArriboInternacionalTO;
    }

    public InformationTO getAllInfoInternacional(long numeroAviso, String tipoAviso) {
        InformationTO informationTO = new InformationTO();
        //Aviso y Oper
        informationTO.setAvisoArriboInternacionalTO(getAvisoArriboInternacional(numeroAviso, tipoAviso));
        if (informationTO.getAvisoArriboInternacionalTO() != null) {
            //Admin
            informationTO.getAvisoArriboInternacionalTO().setArriboAdminInternacionalTO(getAdminArriboInternacional(numeroAviso));
            //Signatures
            informationTO.getAvisoArriboInternacionalTO().setSignatureTOS(getSignaturesByAviso(numeroAviso, tipoAviso));

            informationTO.getAvisoArriboInternacionalTO().setRemolcadoresTO(getRemolcadoresByAviso(numeroAviso, tipoAviso));
            informationTO.getAvisoArriboInternacionalTO().setPilotosAsistentesTO(getPilotosAsistentesByAviso(numeroAviso, tipoAviso));
        }

        informationTO.setAgencyTOS(getAgencies());
        informationTO.setPilotTransportBoatTOS(getPilotTransportBoats());
        informationTO.setPortInstallationTOS(getPortInstallations());
        informationTO.setPracticalPilotTOS(getPracticalPilots());
        informationTO.setPracticalAsistentsPilotTOS(getPracticalAsistentsPilots());
        informationTO.setRepresentantTOS(getRepresentants());
        informationTO.setTrainingPilotTOS(getTrainingPilots());
        informationTO.setTugboatTOS(getTugboats());
        return informationTO;
    }

    public InformationTO getAllInfoCabotaje(long numeroAviso, String tipoAviso) {
        InformationTO informationTO = new InformationTO();
        informationTO.setAvisoArriboCabotageTO(getAvisoArriboCabotage(numeroAviso, tipoAviso));

        //Aviso y Oper
        if (informationTO.getAvisoArriboCabotageTO() != null) {
            //Admin
            informationTO.getAvisoArriboCabotageTO().setArriboAdminCabotageTO(getAdminArriboCabotaje(numeroAviso));
            //Signatures
            informationTO.getAvisoArriboCabotageTO().setSignatureTOS(getSignaturesByAviso(numeroAviso, tipoAviso));
        }
        informationTO.setAgencyTOS(getAgencies());
        informationTO.setPilotTransportBoatTOS(getPilotTransportBoats());
        informationTO.setPortInstallationTOS(getPortInstallations());
        informationTO.setPracticalPilotTOS(getPracticalPilots());
        informationTO.setPracticalAsistentsPilotTOS(getPracticalAsistentsPilots());
        informationTO.setReasonArrivalTOS(getReasonsArrival());
        informationTO.setRepresentantTOS(getRepresentants());
        informationTO.setTrainingPilotTOS(getTrainingPilots());
        informationTO.setTugboatTOS(getTugboats());
        return informationTO;

    }

    private List<AvisoArriboCabotageTO> getAvisosArriboCabotajeByState() {
        open();
        AvisoArriboCabotajeDAO avisoArriboCabotajeDAO = new AvisoArriboCabotajeDAOImpl(database, null, null);
        List<AvisoArriboCabotageTO> allRecords = avisoArriboCabotajeDAO.findAllRecordsByState();
        close();
        return allRecords;
    }

    private List<AvisoArriboInternacionalTO> getAvisosArriboInternacionalByState() {
        open();
        AvisoArriboInternacionalDAO avisoArriboInternacionalDAO = new AvisoArriboInternacionalDAOImpl(database, null, null);
        List<AvisoArriboInternacionalTO> allRecords = avisoArriboInternacionalDAO.findAllRecordsByState();
        close();
        return allRecords;
    }

    public AvisosArriboTO getAllArribos() {
        AvisosArriboTO avisosArriboTO = new AvisosArriboTO();

        avisosArriboTO.setAvisoArriboCabotageTOS(getAvisosArriboCabotajeByState());

        if (avisosArriboTO.getAvisoArriboCabotageTOS() != null && !avisosArriboTO.getAvisoArriboCabotageTOS().isEmpty()) {
            for (int i = 0; i < avisosArriboTO.getAvisoArriboCabotageTOS().size(); i++) {
                //Admin
                avisosArriboTO.getAvisoArriboCabotageTOS().get(i).setArriboAdminCabotageTO(getAdminArriboCabotaje(avisosArriboTO.getAvisoArriboCabotageTOS().get(i).getNumeroAvisoArribo()));
                //Signatures
                avisosArriboTO.getAvisoArriboCabotageTOS().get(i).setSignatureTOS(getSignaturesByAviso(avisosArriboTO.getAvisoArriboCabotageTOS().get(i).getNumeroAvisoArribo(), TYPE_AVISO_ARRIBO_NACIONAL));
                //PDF
                avisosArriboTO.getAvisoArriboCabotageTOS().get(i).setPdfTO(getPdfByAviso(avisosArriboTO.getAvisoArriboCabotageTOS().get(i).getNumeroAvisoArribo(), TYPE_AVISO_ARRIBO_NACIONAL));
                //Get Serializable bytes from PDF
                if (avisosArriboTO.getAvisoArriboCabotageTOS().get(i).getPdfTO() != null) {
                    PdfUtil.getPDF(context, avisosArriboTO.getAvisoArriboCabotageTOS().get(i).getPdfTO());
                }
            }
        } /*else {
            avisosArriboTO.setAvisoArriboCabotageTOS(new ArrayList<>());
        }*/

        avisosArriboTO.setAvisoArriboInternacionalTOS(getAvisosArriboInternacionalByState());
        if (avisosArriboTO.getAvisoArriboInternacionalTOS() != null && !avisosArriboTO.getAvisoArriboInternacionalTOS().isEmpty()) {
            for (int i = 0; i < avisosArriboTO.getAvisoArriboInternacionalTOS().size(); i++) {
                //Admin
                avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).setArriboAdminInternacionalTO(getAdminArriboInternacional(avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).getNumeroAvisoArribo()));
                //Signatures
                avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).setSignatureTOS(getSignaturesByAviso(avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).getNumeroAvisoArribo(), TYPE_AVISO_ARRIBO_INTERNACIONAL));
                //PDF
                avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).setPdfTO(getPdfByAviso(avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).getNumeroAvisoArribo(), TYPE_AVISO_ARRIBO_INTERNACIONAL) != null ? getPdfByAviso(avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).getNumeroAvisoArribo(), TYPE_AVISO_ARRIBO_INTERNACIONAL) : new PDFTO());
                //Remolcadores
                avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).setRemolcadoresTO(getRemolcadoresByAviso(avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).getNumeroAvisoArribo(), TYPE_AVISO_ARRIBO_INTERNACIONAL));
                //Pilotos Asistentes
                avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).setPilotosAsistentesTO(getPilotosAsistentesByAviso(avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).getNumeroAvisoArribo(), TYPE_AVISO_ARRIBO_INTERNACIONAL));
                //Get Serializable bytes from PDF
                if (avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).getPdfTO() != null) {
                    PdfUtil.getPDF(context, avisosArriboTO.getAvisoArriboInternacionalTOS().get(i).getPdfTO());
                }
            }
        } /*else {
            avisosArriboTO.setAvisoArriboInternacionalTOS(new ArrayList<>());
        }*/

        return avisosArriboTO;
    }
}
