package domain.trainingScheme;

import models.Exercise;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Enums.ServiceType;
import Exceptions.ArgumentNotFoundException;
import Exceptions.ServiceNotFoundException;
import models.Profile;
import models.Request;
import models.Response;
import domain.serviceInterfaces.IProfileService;
import domain.serviceInterfaces.ITrainingSchemeService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.ICommunicationFacade;
import layerInterfaces.IDomainFacade;
import models.SetInfo;
import models.TrainingProgram;

/**
 *
 * @author Victor
 */
public class TrainingSchemeService extends ITrainingSchemeService {

    public TrainingSchemeService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
        loadAllExercise();
    }

    @Override
    public boolean createNewTrainingProgram(TrainingProgram program) {
        try {
            Request request = createRequest(RequestType.CREATE_TRAINING_PROGRAM);
            request.addArgument(RequestArgumentName.PROGRAM_NAME, program.getName());
            request.addArgument(RequestArgumentName.PROGRAM_DESCRIPTION, program.getDescription());
            Response response = communicationLayer.sendRequest(request);
            try {
                program.setId((int)response.getArgument(ResponseArgumentName.NEW_ID));
            } catch (ArgumentNotFoundException ex) {
                Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean addExercise(Exercise exercise, TrainingProgram program) {
        try {
            Request request = createRequest(RequestType.ADD_EXERCISE);
            request.addArgument(RequestArgumentName.EXERCISE, exercise);
            request.addArgument(RequestArgumentName.PROGRAM_ID, program.getId());
            Response res = communicationLayer.sendRequest(request);
            
            try {
                List<Integer> ids = (List<Integer>)res.getArgument(ResponseArgumentName.NEW_ID);
                
                for (int i = 0; i < ids.size(); i++) {
                    exercise.getSetInfo().get(i).setId(ids.get(i));
                }
            }
            catch (ArgumentNotFoundException ex) {
                
            }
            
            return true;
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean removeExercise(int exerciseId, int programId) {
        try {
            Request request = createRequest(RequestType.REMOVE_EXERCISE);
            request.addArgument(RequestArgumentName.EXERCISE_ID, exerciseId);
            request.addArgument(RequestArgumentName.PROGRAM_ID, programId);
            communicationLayer.sendRequest(request);
            return true;
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Object returnResponseObject(RequestType requestType, ResponseArgumentName responseArguementName) {
        Object object = null;
        try {
            Request request = createRequest(requestType);
            Response response = communicationLayer.sendRequest(request);
            object = response.getArgument(responseArguementName);
        } catch (ServiceNotFoundException | ClassCastException | ArgumentNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }

    @Override
    protected final void loadAllExercise() {

        try {

            Request request = createRequest(RequestType.LOAD_ALL_EXERCISE);
            Response response = communicationLayer.sendRequest(request);
            allExercises = (ArrayList<Exercise>) response.getArgument(ResponseArgumentName.EXERCISE);

        } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void exerciseForTodayDone(Exercise exercise) {
        try {
            Profile pro = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile();

            Request req = createRequest(RequestType.REMOVE_EXERCISE_FOR_TODAY);
            req.addArgument(RequestArgumentName.PROFILE_ID, pro.getProfileId());
            req.addArgument(RequestArgumentName.EXERCISE, exercise);

            communicationLayer.sendRequest(req);

        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updateExerciseIndex(int exerciseId, int programId, int newIndex) {
        try {
            Request req = createRequest(RequestType.UPDATE_EXERCISE_INDEX);
            req.addArgument(RequestArgumentName.EXERCISE_ID, exerciseId);
            req.addArgument(RequestArgumentName.PROGRAM_ID, programId);
            req.addArgument(RequestArgumentName.INDEX, newIndex);
            communicationLayer.sendRequest(req);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addSetToExercise(int exerciseId, int programId, SetInfo set) {
        try {
            Request req = createRequest(RequestType.ADD_SET_TO_EXERCISE);
            req.addArgument(RequestArgumentName.EXERCISE_ID, exerciseId);
            req.addArgument(RequestArgumentName.PROGRAM_ID, programId);
            req.addArgument(RequestArgumentName.SET_INFO, set);
            Response res = communicationLayer.sendRequest(req);
            set.setId((int)res.getArgument(ResponseArgumentName.NEW_ID));
        } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateSetIndex(int setId, int newIndex) {
        try {
            Request req = createRequest(RequestType.UPDATE_SET_INDEX);
            req.addArgument(RequestArgumentName.SET_ID, setId);
            req.addArgument(RequestArgumentName.INDEX, newIndex);
            communicationLayer.sendRequest(req);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeSet(int setId) {
        try {
            Request req = createRequest(RequestType.REMOVE_SET);
            req.addArgument(RequestArgumentName.SET_ID, setId);
            communicationLayer.sendRequest(req);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteTrainingPogram(int programId) {
        try {
            Request req = createRequest(RequestType.REMOVE_TRAINING_PROGRAM);
            req.addArgument(RequestArgumentName.PROGRAM_ID, programId);
            communicationLayer.sendRequest(req);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TrainingProgram> getAllTrainingPrograms() {
        try {
            int profileId = domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId();
            
            Request req = createRequest(RequestType.GET_ALL_TRAINING_PROGRAMS);
            req.addArgument(RequestArgumentName.PROFILE_ID, profileId);
            Response res = communicationLayer.sendRequest(req);
            return (List<TrainingProgram>)res.getArgument(ResponseArgumentName.TRAININGPROGRAM);
        } catch (ServiceNotFoundException | ArgumentNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }
    
    public void updateTrainingProgramInfo (int programId, String name, String description) {
        try {
            Request req = createRequest(RequestType.UPDATE_PROGRAM_INFO);
            req.addArgument(RequestArgumentName.PROGRAM_ID, programId);
            req.addArgument(RequestArgumentName.PROGRAM_NAME, name);
            req.addArgument(RequestArgumentName.PROGRAM_DESCRIPTION, description);
            communicationLayer.sendRequest(req);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}