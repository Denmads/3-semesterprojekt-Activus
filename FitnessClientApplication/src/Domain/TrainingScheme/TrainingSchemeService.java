package Domain.TrainingScheme;

import Domain.serviceInterfaces.ITrainingSchemeService;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;

/**
 *
 * @author Victor
 */
public class TrainingSchemeService extends ITrainingSchemeService {

    public TrainingSchemeService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }
    
    @Override
    public boolean createNewTrainingScheme(String schemeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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