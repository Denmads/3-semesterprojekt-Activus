/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import models.Request;
import models.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.IDatabaseFacade;
import persistence.DatabaseFacade;

/**
 *
 * @author madsh
 */
public class TrainingSchemeRequestHandler extends IRequestHandler{

    public TrainingSchemeRequestHandler(DatabaseFacade dbFacade) {
        super(dbFacade);
    }

    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();
        System.out.println("traininig");
        switch (request.getRequestType()){
            case CREATE_TRAINING_PROGRAM:
                try {
                    CreateTrainingProgramAction ctpa = new CreateTrainingProgramAction(request.getArgument(RequestArgumentName.PROGRAM_OWNER_ID), request.getArgument(RequestArgumentName.PROGRAM_NAME), request.getArgument(RequestArgumentName.PROGRAM_DESCRIPTION));
                    response.addArgument(ResponseArgumentName.SUCCESS, ctpa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
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
            case LOAD_ALL_EXERCISE:
                GetAllExerciseAction allExerciseAction = new GetAllExerciseAction();
                databaseFacade.execute(allExerciseAction);
                System.out.println(allExerciseAction.hasResult());
                response.addArgument(ResponseArgumentName.EXERCISE, allExerciseAction.getResult());
        }
        
        System.out.println(response);
        return response;
    }
    
}