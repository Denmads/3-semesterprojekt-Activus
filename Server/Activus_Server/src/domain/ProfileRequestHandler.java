package domain;

import Enums.RequestArgumentName;
import static Enums.RequestType.DELETE_ACCOUNT;
import Enums.ResponseArgumentName;
import Enums.SearchType;
import Exceptions.ArgumentNotFoundException;
import java.util.ArrayList;
import java.util.List;
import models.Request;
import models.Response;
import persistence.actions.UpdatePasswordAction;
import persistence.actions.UpdateProfileAction;
import persistence.actions.UpdateUsernameAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.IDatabaseFacade;
import org.jooq.TableField;
import persistence.actions.AcceptBuddyRequest;
import persistence.actions.DeleteAccountAction;
import persistence.actions.DennyBuddyRequest;
import persistence.actions.GetAllBuddys;
import persistence.actions.IsBuddyAction;
import persistence.actions.SearchAction;
import persistence.actions.SendBuddyRequest;
import persistence.actions.SetStatsAction;
import static persistence.database.generated.Tables.PROFILE;

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
//                try {
//                    SetStatsAction ssa = new SetStatsAction(request.getArgument(RequestArgumentName.PROFILE_ID), request.getArgument(RequestArgumentName.EXERCISE_ID), request.getArgument(RequestArgumentName.PROGRAM_TIME_TAKEN), request.getArgument(RequestArgumentName.PROGRAM_REPS), request.getArgument(RequestArgumentName.PROGRAM_SETS), request.getArgument(RequestArgumentName.PROGRAM_WEIGHT), request.getArgument(RequestArgumentName.PROGRAM_DATE));
//                    response.addArgument(ResponseArgumentName.SUCCESS, ssa.getResult());
//                } catch (ArgumentNotFoundException | ClassCastException ex) {
//                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }
                break;
            case UPDATE_PROFILE:
                try {
                    List<Object> changes = new ArrayList<>();
                    List<TableField> tableFields = new ArrayList<>();
                    changes.add(request.getArgument(RequestArgumentName.PROFILE_GYM));
                    tableFields.add(PROFILE.GYM);
                    changes.add(request.getArgument(RequestArgumentName.PROFILE_CITY));
                    tableFields.add(PROFILE.CITY);
                    changes.add(request.getArgument(RequestArgumentName.PROFILE_AGE));
                    tableFields.add(PROFILE.AGE);
                    changes.add(request.getArgument(RequestArgumentName.PROFILE_FIRST_NAME));
                    tableFields.add(PROFILE.FIRST_NAME);
                    changes.add(request.getArgument(RequestArgumentName.PROFILE_LAST_NAME));
                    tableFields.add(PROFILE.LAST_NAME);
                    changes.add(request.getArgument(RequestArgumentName.PROFILE_GENDER));
                    tableFields.add(PROFILE.GENDER);
                    changes.add(request.getArgument(RequestArgumentName.PROFILE_COUNTRY));
                    tableFields.add(PROFILE.COUNTRY);
                    changes.add(request.getArgument(RequestArgumentName.PROFILE_ACTIVE_BUDDY));
                    tableFields.add(PROFILE.ACTIVEBUDDY);
                    
                    UpdateProfileAction upa = new UpdateProfileAction(request.getArgument(RequestArgumentName.PROFILE_ID), changes, tableFields);
                    databaseFacade.execute(upa);
                    response.addArgument(ResponseArgumentName.SUCCESS, upa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case DELETE_ACCOUNT:
                try {
                    DeleteAccountAction daa = new DeleteAccountAction(request.getArgument(RequestArgumentName.USERNAME), request.getCredentials().getLoginId());
                    databaseFacade.execute(daa);
                    response.addArgument(ResponseArgumentName.SUCCESS, daa.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SEARCH:
                try {
                    SearchAction sa = null;

                    if (request.getArgument(RequestArgumentName.SEARCH_TYPE).equals(SearchType.AGE)) {
                        sa = new SearchAction(request.getArgument(RequestArgumentName.TEXT), RequestArgumentName.PROFILE_AGE);
                    } else if (request.getArgument(RequestArgumentName.SEARCH_TYPE).equals(SearchType.CITY)) {
                        sa = new SearchAction(request.getArgument(RequestArgumentName.TEXT), RequestArgumentName.PROFILE_CITY);
                    } else if (request.getArgument(RequestArgumentName.SEARCH_TYPE).equals(SearchType.GENDER)) {
                        sa = new SearchAction(request.getArgument(RequestArgumentName.TEXT), RequestArgumentName.PROFILE_GENDER);
                    }

                    databaseFacade.execute(sa);
                    response.addArgument(ResponseArgumentName.SUCCESS, sa.getResult());
                    
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case GET_ALL_BUDDYS:
                try {
                    GetAllBuddys gab = new GetAllBuddys(request.getArgument (request.getArgument(RequestArgumentName.PROFILE_ID)));
                    databaseFacade.execute(gab);
                    response.addArgument(ResponseArgumentName.SUCCESS, gab.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex){
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case ACCEPT_BUDDY_REQUEST:
                try {
                    AcceptBuddyRequest abr = new AcceptBuddyRequest(request.getArgument(RequestArgumentName.BUDDY_ID), request.getArgument(RequestArgumentName.PROFILE_ID));
                    databaseFacade.execute(abr);
                    response.addArgument(ResponseArgumentName.SUCCESS, abr.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex){
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SEND_BUDDY_REQUEST:
                try {
                    SendBuddyRequest sbr = new SendBuddyRequest(request.getArgument(RequestArgumentName.BUDDY_ID), request.getArgument(RequestArgumentName.PROFILE_ID));
                    databaseFacade.execute(sbr);
                    response.addArgument(ResponseArgumentName.SUCCESS, sbr.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex){
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case DENNY_BUDDY_REQUEST:
                try {
                    DennyBuddyRequest dbr = new DennyBuddyRequest(request.getArgument(RequestArgumentName.BUDDY_ID), request.getArgument(RequestArgumentName.PROFILE_ID));
                    databaseFacade.execute(dbr);
                    response.addArgument(ResponseArgumentName.SUCCESS, dbr.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex){
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case IS_BUDDY:
                try {
                    IsBuddyAction iba = new IsBuddyAction(request.getArgument(RequestArgumentName.BUDDY_ID), request.getArgument(RequestArgumentName.PROFILE_ID));
                    databaseFacade.execute(iba);
                    response.addArgument(ResponseArgumentName.SUCCESS, iba.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex){
                    Logger.getLogger(ProfileRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return response;

    }
}
