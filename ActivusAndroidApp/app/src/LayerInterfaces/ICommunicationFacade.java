package LayerInterfaces;

import Models.Request;
import Models.Response;

/**
 *
 * @author madsh
 */
public interface ICommunicationFacade {

    public Response sendRequest(Request request);
}