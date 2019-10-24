package Domain.serviceInterfaces;

import Models.Exercise;
import Enums.ServiceType;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;
import java.util.List;

/**
 * The service provinding methods related to Creating and editing trainingSchemes and trainingProgram
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
    
    public abstract List<Exercise> loadAllExercise();
    
    
    
}