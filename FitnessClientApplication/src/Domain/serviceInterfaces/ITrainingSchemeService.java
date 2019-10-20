package Domain.serviceInterfaces;

import Domain.TraningScheme.Exercise;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;

/**
 *
 * @author Victor
 */
public abstract class ITrainingSchemeService extends IService{

    public ITrainingSchemeService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }
    
    public abstract boolean createNewTrainingScheme(String schemeName);

    public abstract boolean createNewTrainingProgram(String programName);

    public abstract Exercise LoadExercise();
}