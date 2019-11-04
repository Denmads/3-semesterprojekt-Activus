package domain;

import Models.Request;
import Models.Response;
import layerInterfaces.IDatabaseFacade;
import persistence.DatabaseFacade;

/**
 * Handles a request and returns the corresponding response
 *
 * @author madsh
 */
public abstract class IRequestHandler {

    protected IDatabaseFacade databaseFacade;

    public IRequestHandler(IDatabaseFacade dbFacade) {
        databaseFacade = dbFacade;
    }

    public abstract Response handleRequest(Request request);
}