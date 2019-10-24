package Domain;

import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Models.Request;
import Models.Response;
import persistence.DatabaseFacade;
import persistence.actions.RemoveExerciseByIDAction;
import persistence.actions.AddExerciseToTrainingProgramAction;

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
        switch(request.getRequestType()){
            case REMOVE_EXERCISE: 
                RemoveExerciseByIDAction rebia = new RemoveExerciseByIDAction(request.getArgument(RequestArgumentName.EXERCISE_ID));
                response.addArgument(ResponseArgumentName.SUCCESS, rebia.getResult());
                break;
            case ADD_EXERCISE:
                AddExerciseToTrainingProgramAction aettpa = new AddExerciseToTrainingProgramAction(request.getArgument(RequestArgumentName.EXERCISE_ID),request.getArgument(RequestArgumentName.PROGRAM_ID));
                response.addArgument(ResponseArgumentName.SUCCESS, aettpa.getResult());
                break;
        }
    }

}