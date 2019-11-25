package domain.serviceInterfaces;

import models.Exercise;
import Enums.ServiceType;
import models.TrainingProgram;
import java.util.ArrayList;
import layerInterfaces.ICommunicationFacade;
import layerInterfaces.IDomainFacade;
import layerInterfaces.IService;
import java.util.List;
import models.SetInfo;

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
    
    public abstract boolean addExercise(Exercise exercise, TrainingProgram program);
    
    public abstract boolean removeExercise(int exerciseId, int programId);
    
    protected abstract void loadAllExercise();
    
    public abstract void exerciseForTodayDone(Exercise exercise);
    
    public abstract void updateExerciseIndex(int exerciseId, int programId, int newIndex);
    
    public abstract void addSetToExercise(int exerciseId, int programId, SetInfo set);
    
    public abstract void updateSetIndex(int setId, int newIndex);
    
    public abstract void removeSet(int setId);
    
    public abstract void deleteTrainingPogram(int programId);
    
    public abstract List<TrainingProgram> getAllTrainingPrograms();
    
    public abstract void updateTrainingProgramInfo (int programId, String name, String description);
    
    public List<Exercise> getAllExercises() {
        return allExercises;
    }
}