package Domain.serviceInterfaces;

import Domain.TrainingScheme.Exercise;
import Enums.ServiceType;
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
        type = ServiceType.TRAININGSCHEME;
    }

    public abstract boolean createNewTrainingProgram(String programName);

    public abstract Exercise LoadExercise();
    
    public abstract boolean addExercise(Exercise exercise);
    
    public abstract boolean removeExercise(Exercise exercise);
    
    
    
}