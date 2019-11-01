package domain.serviceInterfaces;

import Models.Exercise;
import Enums.ServiceType;
import layerInterfaces.ICommunicationFacade;
import layerInterfaces.IDomainFacade;
import layerInterfaces.IService;
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
    
    public abstract void exerciseForTodayDone(Exercise exercise);
    
}