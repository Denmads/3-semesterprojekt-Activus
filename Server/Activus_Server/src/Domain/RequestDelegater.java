package Domain;

import Enums.RequestType;
import Enums.ServiceType;
import Models.Request;
import Models.Response;

public class RequestDelegater {
    
    IRequestHandler[] requestHandlers = new IRequestHandler[4];
    
    public RequestDelegater () {
        requestHandlers[0] = new AuthenticationRequestHandler();
    }

    private boolean contains (RequestType[] typeArray, Request request) {
        for (int i = 0; i < typeArray.length; i++) {
            if (typeArray[i] == request.getRequestType()) {
                return true;
            }
        }
        
        return false;
    }
    
    public Response delegate (Request request) {
        
        Response response = null;
        
        if (request.getServiceType() == ServiceType.AUTHENTICATION) {
            response = requestHandlers[0].handleRequest(request);
        }
        else 
        {
            if (authenticateRequest(request)) {
                response = requestHandlers[request.getServiceType().ordinal()].handleRequest(request);
            }
            else {
                //TODO: Create response object with Authentiation Error
            }
        }
        
        return response;
    }
    
    private boolean authenticateRequest (Request request) {
        return false;
    }
}
