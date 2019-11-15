/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Models.Request;
import Models.Response;
import Models.Exercise;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.IDatabaseFacade;
import persistence.actions.AddExerciseToTrainingProgramAction;
import persistence.actions.CreateTrainingProgramAction;
import persistence.actions.GetAllExerciseAction;
import persistence.actions.RemoveExerciseByIDAction;

/**
 *
 * @author madsh
 */
public class TrainingSchemeRequestHandler extends IRequestHandler{

    public TrainingSchemeRequestHandler(IDatabaseFacade dbFacade) {
        super(dbFacade);
    }

    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();
        System.out.println("traininig");
        switch (request.getRequestType()){
            case CREATE_TRAINING_PROGRAM:
                try {
                    CreateTrainingProgramAction ctpa = new CreateTrainingProgramAction(request.getCredentials().getProfileId(), request.getArgument(RequestArgumentName.PROGRAM_NAME), request.getArgument(RequestArgumentName.PROGRAM_DESCRIPTION));
                    databaseFacade.execute(ctpa);
                    response.addArgument(ResponseArgumentName.NEW_ID, ctpa.getResult());
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
                    Exercise exer = (Exercise)request.getArgument(RequestArgumentName.EXERCISE);
                    
                    AddExerciseToTrainingProgramAction aettpa = new AddExerciseToTrainingProgramAction(
                            exer.getID(), 
                            request.getArgument(RequestArgumentName.PROGRAM_ID),
                            request.getArgument(RequestArgumentName.INDEX)
                    );
                    databaseFacade.execute(aettpa);
                    
                    
                    response.addArgument(ResponseArgumentName.SUCCESS, aettpa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case LOAD_ALL_EXERCISE:
                GetAllExerciseAction allExerciseAction = new GetAllExerciseAction();
                databaseFacade.execute(allExerciseAction);
                response.addArgument(ResponseArgumentName.EXERCISE, allExerciseAction.getResult());
        }
        
        System.out.println(response);
        return response;
    }
    
}
