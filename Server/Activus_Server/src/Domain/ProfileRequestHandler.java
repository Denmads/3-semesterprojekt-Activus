package Domain;

import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Models.Request;
import Models.Response;
import Persistence.Actions.UpdatePasswordAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.DatabaseFacade;
import persistence.actions.RemoveExerciseByIDAction;
import persistence.actions.AddExerciseToTrainingProgramAction;
import persistence.actions.CreateTrainingProgramAction;

/**
 *
 * @author madsh
 */
public class ProfileRequestHandler extends IRequestHandler {

    public ProfileRequestHandler(DatabaseFacade dbFacade) {
        super(dbFacade);
    }

    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();
        switch (request.getRequestType()) {
            case REMOVE_EXERCISE:
                try {
                    RemoveExerciseByIDAction rebia = new RemoveExerciseByIDAction(request.getArgument(RequestArgumentName.EXERCISE_ID));
                    response.addArgument(ResponseArgumentName.SUCCESS, rebia.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case ADD_EXERCISE:

                try {
                    AddExerciseToTrainingProgramAction aettpa = new AddExerciseToTrainingProgramAction(request.getArgument(RequestArgumentName.EXERCISE_ID), request.getArgument(RequestArgumentName.PROGRAM_ID));
                    response.addArgument(ResponseArgumentName.SUCCESS, aettpa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case UPDATE_PASSWORD:

                try {
                    UpdatePasswordAction upa = new UpdatePasswordAction(request.getArgument(RequestArgumentName.USERNAME), request.getArgument(RequestArgumentName.PASSWORD));
                    response.addArgument(ResponseArgumentName.SUCCESS, upa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case CREATE_TRAINING_PROGRAM:
        
            try {
                CreateTrainingProgramAction ctpa = new CreateTrainingProgramAction(request.getArgument(RequestArgumentName.PROGRAM_OWNER_ID),request.getArgument(RequestArgumentName.PROGRAM_NAME),request.getArgument(RequestArgumentName.PROGRAM_DESCRIPTION));
                response.addArgument(ResponseArgumentName.SUCCESS, ctpa.getResult());
            } catch (ArgumentNotFoundException | ClassCastException ex) {
                Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
        }
        //UPDATE RETURN STATEMENT LATER!!!
        return null;
    }

}
