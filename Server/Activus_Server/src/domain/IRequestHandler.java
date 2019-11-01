package Domain;

import Models.Request;
import Models.Response;
import persistence.DatabaseFacade;

/**
 * Handles a request and returns the corresponding response
 *
 * @author madsh
 */
public abstract class IRequestHandler {

    protected DatabaseFacade databaseFacade;

    public IRequestHandler(DatabaseFacade dbFacade) {
        databaseFacade = dbFacade;
    }

    public abstract Response handleRequest(Request request);
}