/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Enums.RequestArgumentName;
import static Enums.RequestType.CREATE_NEW_USER;
import static Enums.RequestType.DELETE_ACCOUNT;
import static Enums.RequestType.LOGIN;
import static Enums.RequestType.LOGOUT;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import models.Request;
import models.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.IDatabaseFacade;
import persistence.actions.CreateNewUserAction;
import persistence.actions.DeleteAccountAction;
import persistence.actions.GetProfileByLoginIdAction;
import persistence.actions.IsUsernameUniqueAction;
import persistence.actions.LogoutAction;
import persistence.actions.VerifyLoginAction;

/**
 *
 * @author madsh
 */
public class AuthenticationRequestHandler extends IRequestHandler {
    
    public AuthenticationRequestHandler(IDatabaseFacade dbFacade) {
        super(dbFacade);
    }
    
    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();
        
        if (request.getRequestType().equals(CREATE_NEW_USER)) {
            try {
                IsUsernameUniqueAction uniqueAction = new IsUsernameUniqueAction(request.getArgument(RequestArgumentName.USERNAME));
                databaseFacade.execute(uniqueAction);
                if (uniqueAction.getResult()) {
                    CreateNewUserAction cnua = new CreateNewUserAction(request.getArgument(RequestArgumentName.FIRST_NAME), request.getArgument(RequestArgumentName.LAST_NAME), request.getArgument(RequestArgumentName.USERNAME), request.getArgument(RequestArgumentName.PASSWORD), request.getArgument(RequestArgumentName.PROFILE_CITY), request.getArgument(RequestArgumentName.PROFILE_AGE), request.getArgument(RequestArgumentName.PROFILE_GENDER));
                    databaseFacade.execute(cnua);
                    response.addArgument(ResponseArgumentName.ERRORS, "");
                } else {
                    response.addArgument(ResponseArgumentName.ERRORS, "Username already exists!");
                }
            } catch (ArgumentNotFoundException | ClassCastException ex) {
                Logger.getLogger(AuthenticationRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getRequestType().equals(LOGIN)) {
            try {
                VerifyLoginAction vla = new VerifyLoginAction(request.getArgument(RequestArgumentName.USERNAME), request.getArgument(RequestArgumentName.PASSWORD));
                databaseFacade.execute(vla);
                System.out.println(vla.hasResult());
                if (vla.hasResult()) {
                    GetProfileByLoginIdAction gpblia = new GetProfileByLoginIdAction(vla.getResult().getLoginId());
                    databaseFacade.execute(gpblia);
                    response.addArgument(ResponseArgumentName.CREDENTIALS, vla.getResult());
                    response.addArgument(ResponseArgumentName.PROFILE, gpblia.getResult());
                }
            } catch (ArgumentNotFoundException | ClassCastException ex) {
                Logger.getLogger(AuthenticationRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getRequestType().equals(LOGOUT)) {
            try {
                LogoutAction la = new LogoutAction(request.getArgument(RequestArgumentName.USER_ID));
                databaseFacade.execute(la);
                response.addArgument(ResponseArgumentName.SUCCESS, la.getResult());
            } catch (ArgumentNotFoundException | ClassCastException ex) {
                Logger.getLogger(AuthenticationRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return response;
    }
}