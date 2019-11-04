package domain;

import domain.IRequestHandler;
import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Models.Request;
import Models.Response;
import java.util.ArrayList;
import java.util.List;
import persistence.actions.UpdatePasswordAction;
import persistence.actions.UpdateProfileAction;
import persistence.actions.UpdateUsernameAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.TableField;
import persistence.DatabaseFacade;
import persistence.actions.DeleteAccountAction;
import persistence.actions.SetStatsAction;
import static persistence.database.generated.Tables.PROFILE;

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
                    //changes.add(request.getArgument(RequestArgumentName.PROFILE_SPORT));
                    //tableFields.add(PROFILE.SPORT);

                    UpdateProfileAction upa = new UpdateProfileAction(request.getArgument(RequestArgumentName.PROFILE_ID), changes, tableFields);
                    databaseFacade.execute(upa);
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
