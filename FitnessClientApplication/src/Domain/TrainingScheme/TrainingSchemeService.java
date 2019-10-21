package Domain.TrainingScheme;

import Domain.serviceInterfaces.ITrainingSchemeService;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import Models.Request;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class TrainingSchemeService extends ITrainingSchemeService {

    public TrainingSchemeService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }

    @Override
    public boolean createNewTrainingProgram(String programName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Exercise LoadExercise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}