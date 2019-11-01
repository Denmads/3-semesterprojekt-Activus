/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Enums.RequestArgumentName;
import static Enums.RequestType.CREATE_NEW_USER;
import static Enums.RequestType.LOGIN;
import static Enums.RequestType.LOGOUT;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Models.Request;
import Models.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.DatabaseFacade;
import persistence.actions.CreateNewUserAction;
import persistence.actions.GetProfileByLoginIdAction;
import persistence.actions.LogoutAction;
import persistence.actions.VerifyLoginAction;

/**
 *
 * @author madsh
 */
public class AuthenticationRequestHandler extends IRequestHandler {

    public AuthenticationRequestHandler(DatabaseFacade dbFacade) {
        super(dbFacade);
    }

    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();

        if (request.getRequestType().equals(CREATE_NEW_USER)) {
            try {
                CreateNewUserAction cnua = new CreateNewUserAction(request.getArgument(RequestArgumentName.FIRST_NAME), request.getArgument(RequestArgumentName.LAST_NAME), request.getArgument(RequestArgumentName.USERNAME), request.getArgument(RequestArgumentName.PASSWORD));
                databaseFacade.execute(cnua);
                response.addArgument(ResponseArgumentName.SUCCESS, cnua.getResult());
            } catch (ArgumentNotFoundException | ClassCastException ex) {
                Logger.getLogger(AuthenticationRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getRequestType().equals(LOGIN)) {
            try {
                VerifyLoginAction vla = new VerifyLoginAction(request.getArgument(RequestArgumentName.USERNAME), request.getArgument(RequestArgumentName.PASSWORD));
                databaseFacade.execute(vla);
                if (vla.hasResult()) {
                    GetProfileByLoginIdAction gpblia = new GetProfileByLoginIdAction(vla.getResult().getUserId());
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
