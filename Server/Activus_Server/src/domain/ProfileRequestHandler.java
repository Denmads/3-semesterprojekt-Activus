package domain;

import domain.IRequestHandler;
import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import models.Request;
import models.Response;
import persistence.actions.UpdatePasswordAction;
import persistence.actions.UpdateProfileAction;
import persistence.actions.UpdateUsernameAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.IDatabaseFacade;
import persistence.DatabaseFacade;
import persistence.actions.DeleteAccountAction;
import persistence.actions.SetStatsAction;

/**
 *
 * @author madsh
 */
public class ProfileRequestHandler extends IRequestHandler {
    
    public ProfileRequestHandler(IDatabaseFacade dbFacade) {
        super(dbFacade);
    }
    
    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();
        switch (request.getRequestType()) {
            
            case UPDATE_PASSWORD:
                try {
                    UpdatePasswordAction upa = new UpdatePasswordAction(request.getArgument(RequestArgumentName.USERNAME), request.getArgument(RequestArgumentName.PASSWORD));
                    response.addArgument(ResponseArgumentName.SUCCESS, upa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            
            case UPDATE_USERNAME:
                try {
                    UpdateUsernameAction uua = new UpdateUsernameAction(request.getArgument(RequestArgumentName.USERNAME), request.getArgument(RequestArgumentName.USER_ID));
                    response.addArgument(ResponseArgumentName.SUCCESS, uua);
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SET_STAT:                
                try {
                    SetStatsAction ssa = new SetStatsAction(request.getArgument(RequestArgumentName.PROFILE_ID), request.getArgument(RequestArgumentName.EXERCISE_ID), request.getArgument(RequestArgumentName.PROGRAM_TIME_TAKEN), request.getArgument(RequestArgumentName.PROGRAM_REPS), request.getArgument(RequestArgumentName.PROGRAM_SETS), request.getArgument(RequestArgumentName.PROGRAM_WEIGHT), request.getArgument(RequestArgumentName.PROGRAM_DATE));
                    response.addArgument(ResponseArgumentName.SUCCESS, ssa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case UPDATE_PROFILE:
                try {
                    //FIGURE OUT: The arguments for the UpdateProfileAction constructor.
                    UpdateProfileAction upa = new UpdateProfileAction(request.getArgument(RequestArgumentName.PROFILE_ID), request.getArgument(RequestArgumentName.PROFILE_LIST_OF_CHANGES), request.getArgument(RequestArgumentName.PROFILE_LIST_OF_TABLE_FIELDS));
                    response.addArgument(ResponseArgumentName.SUCCESS, upa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case DELETE_ACCOUNT:
                try {
                    DeleteAccountAction daa = new DeleteAccountAction(request.getArgument(RequestArgumentName.PROFILE_ID));
                    response.addArgument(ResponseArgumentName.SUCCESS, daa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        //UPDATE RETURN STATEMENT LATER!!!
        return null;
    }
    
}
