package Domain;

import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Models.Request;
import Models.Response;
import persistence.DatabaseFacade;
import persistence.actions.RemoveExerciseByIDAction;

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
        switch(request.getRequestType()){
            case REMOVE_EXERCISE: 
                RemoveExerciseByIDAction rebia = new RemoveExerciseByIDAction(request.getArgument(RequestArgumentName.EXERCISE_ID));
                Response response = new Response();
                response.addArgument(ResponseArgumentName.SUCCESS, rebia.getResult());
        }
    }

}