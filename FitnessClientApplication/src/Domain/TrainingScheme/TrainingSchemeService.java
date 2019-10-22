package Domain.TrainingScheme;

import Domain.serviceInterfaces.ITrainingSchemeService;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import Models.Request;
import Models.Response;
import java.util.List;
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
        try {
            Request request = createRequest(RequestType.CREATE_TRAINING_PROGRAM);
            request.addArgument(RequestArgumentName.PROGRAM_NAME, programName);
            communicationLayer.sendRequest(request);
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
    public boolean addExercise(Exercise exercise) {
        try {
            Request request = createRequest(RequestType.ADD_EXERCISE);
            request.addArgument(RequestArgumentName.EXERCISE, exercise);
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

   private Object returnResponseObject(RequestType requestType,ResponseArgumentName responseArguementName){
        Object object=null;
        try {
            Request request = createRequest(requestType);
            Response response = communicationLayer.sendRequest(request);
            object= response.getArgument(responseArguementName);
        } catch (ServiceNotFoundException | ClassCastException | ArgumentNotFoundException ex) {
            Logger.getLogger(TrainingSchemeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }

    @Override
    public List<Exercise> loadAllExercise() {
        returnResponseObject(RequestType., ResponseArgumentName.CREDENTIALS)
    }

}