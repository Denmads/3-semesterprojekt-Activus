package Domain;

import Models.Request;
import Models.Response;
import persistence.DatabaseFacade;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}