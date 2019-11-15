package domain.trainingScheme;

import Models.Exercise;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Enums.ServiceType;
import Exceptions.ArgumentNotFoundException;
import Exceptions.ServiceNotFoundException;
import Models.Profile;
import Models.Request;
import Models.Response;
import Models.TrainingProgram;
import domain.serviceInterfaces.IProfileService;
import domain.serviceInterfaces.ITrainingSchemeService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.ICommunicationFacade;
import layerInterfaces.IDomainFacade;

/**
 *
 * @author Victor
 */
public class TrainingSchemeService extends ITrainingSchemeService {

    public TrainingSchemeService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
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
    public Exercise LoadExercise() {
        return (Exercise) returnResponseObject(RequestType.RECEIVE_EXERCISE, ResponseArgumentName.EXERCISE);
    }

    @Override
    public boolean addExercise(Exercise exercise, TrainingProgram program) {
        try {
            Request request = createRequest(RequestType.ADD_EXERCISE);
            request.addArgument(RequestArgumentName.EXERCISE, exercise);
            request.addArgument(RequestArgumentName.PROGRAM_ID, program.getId());
            request.addArgument(RequestArgumentName.INDEX, exercise.getIndexInProgram());
            communicationLayer.sendRequest(request);
            return true;
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean removeExercise(Exercise exercise) {
        try {
            Request request = createRequest(RequestType.REMOVE_EXERCISE);
            request.addArgument(RequestArgumentName.EXERCISE, exercise);
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
    public void loadAllExercise() {

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

}