/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import models.Request;
import models.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.IDatabaseFacade;
import models.Exercise;
import models.SetInfo;
import persistence.actions.AddExerciseToTrainingProgramAction;
import persistence.actions.AddSetAction;
import persistence.actions.CreateTrainingProgramAction;
import persistence.actions.DeleteTrainingProgramAction;
import persistence.actions.GetAllExerciseAction;
import persistence.actions.GetAllProgramsForUser;
import persistence.actions.RemoveExerciseAction;
import persistence.actions.RemoveSetAction;
import persistence.actions.UpdateExerciseIndexAction;
import persistence.actions.UpdateProgramInfoAction;
import persistence.actions.UpdateSetIndexAction;

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
                    int exerciseId = request.getArgument(RequestArgumentName.EXERCISE_ID);
                    int programId = request.getArgument(RequestArgumentName.PROGRAM_ID);
                    
                    RemoveExerciseAction rebia = new RemoveExerciseAction(exerciseId, programId);
                    databaseFacade.execute(rebia);
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
                            exer.getIndexInProgram()
                    );
                    databaseFacade.execute(aettpa);
                    
                    response.addArgument(ResponseArgumentName.NEW_ID, aettpa.getResult());
                    response.addArgument(ResponseArgumentName.SUCCESS, aettpa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case LOAD_ALL_EXERCISE:
                GetAllExerciseAction allExerciseAction = new GetAllExerciseAction();
                databaseFacade.execute(allExerciseAction);
                response.addArgument(ResponseArgumentName.EXERCISE, allExerciseAction.getResult());
                break;
                
            case UPDATE_EXERCISE_INDEX:
                try {
                    int exerciseId = request.getArgument(RequestArgumentName.EXERCISE_ID);
                    int programId = request.getArgument(RequestArgumentName.PROGRAM_ID);
                    int newIndex = request.getArgument(RequestArgumentName.INDEX);
                    
                    UpdateExerciseIndexAction updateAction = new UpdateExerciseIndexAction(exerciseId, programId, newIndex);
                    databaseFacade.execute(updateAction);
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(TrainingSchemeRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            
            case ADD_SET_TO_EXERCISE:
                try {
                    int exerciseId = request.getArgument(RequestArgumentName.EXERCISE_ID);
                    int programId = request.getArgument(RequestArgumentName.PROGRAM_ID);
                    SetInfo set = (SetInfo)request.getArgument(RequestArgumentName.SET_INFO);
                    
                    AddSetAction addSetAction = new AddSetAction(exerciseId, programId, set);
                    databaseFacade.execute(addSetAction);
                    
                    response.addArgument(ResponseArgumentName.NEW_ID, addSetAction.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(TrainingSchemeRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case UPDATE_SET_INDEX:
                try {
                    int setId = request.getArgument(RequestArgumentName.SET_ID);
                    int newIndex = request.getArgument(RequestArgumentName.INDEX);
                    
                    UpdateSetIndexAction updateAction = new UpdateSetIndexAction(setId, newIndex);
                    databaseFacade.execute(updateAction);
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(TrainingSchemeRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case REMOVE_SET:
                try {
                    int setId = request.getArgument(RequestArgumentName.SET_ID);
                    
                    RemoveSetAction removeAction = new RemoveSetAction(setId);
                    databaseFacade.execute(removeAction);
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(TrainingSchemeRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case REMOVE_TRAINING_PROGRAM:
                try {
                    int programId = request.getArgument(RequestArgumentName.PROGRAM_ID);
                    
                    DeleteTrainingProgramAction deleteAction = new DeleteTrainingProgramAction(programId);
                    databaseFacade.execute(deleteAction);
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(TrainingSchemeRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case GET_ALL_TRAINING_PROGRAMS:
                try {
                    int profileId = request.getArgument(RequestArgumentName.PROFILE_ID);
                    
                    GetAllProgramsForUser getAction = new GetAllProgramsForUser(profileId);
                    databaseFacade.execute(getAction);
                    
                    response.addArgument(ResponseArgumentName.TRAININGPROGRAM, getAction.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(TrainingSchemeRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case UPDATE_PROGRAM_INFO:
                try {
                    String name = request.getArgument(RequestArgumentName.PROGRAM_NAME);
                    String description = request.getArgument(RequestArgumentName.PROGRAM_DESCRIPTION);
                    int programId = request.getArgument(RequestArgumentName.PROGRAM_ID);
                    
                    UpdateProgramInfoAction updateAction = new UpdateProgramInfoAction(programId, name, description);
                    databaseFacade.execute(updateAction);
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(TrainingSchemeRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        
        return response;
    }
    
}
