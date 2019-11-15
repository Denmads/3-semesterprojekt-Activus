package domain.serviceInterfaces;

import Models.Exercise;
import Enums.ServiceType;
import Models.TrainingProgram;
import java.util.ArrayList;
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
    
    protected ArrayList<Exercise> allExercises;

    public ITrainingSchemeService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
        type = ServiceType.TRAININGSCHEME;
    }

    public abstract boolean createNewTrainingProgram(TrainingProgram program);

    public abstract Exercise LoadExercise();
    
    public abstract boolean addExercise(Exercise exercise, TrainingProgram program);
    
    public abstract boolean removeExercise(Exercise exercise);
    
    public abstract void loadAllExercise();
    
    public abstract void exerciseForTodayDone(Exercise exercise);
    
    public List<Exercise> getAllExercises() {
        return allExercises;
    }
}