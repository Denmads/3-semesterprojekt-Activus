package LayerInterfaces;

import Communication.Request;
import Models.Response;

/**
 *
 * @author madsh
 */
public interface ICommunicationFacade {

    public Response sendRequest(Request request);
}