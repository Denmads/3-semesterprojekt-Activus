package Domain;

import Enums.RequestType;
import Models.Request;
import Models.Response;

public class RequestDelegater {

    RequestType[] authenticationTypes = {RequestType.CREATE_NEW_USER, RequestType.LOGIN, RequestType.LOGOUT};
    RequestType[] profileRequestTypes = {RequestType.ACCEPT_BUDDY_REQUEST, RequestType.DELETE_ACCOUNT, RequestType.FOLLOW_PROFILE, RequestType.FOLLOW_TRAINING_PROGRAM, 
                                         RequestType.GET_PROFILE, RequestType.REMOVE_STAT, RequestType.SEARCH, RequestType.SEND_BUDDY_REQUEST, RequestType.SET_GOAL, 
                                         RequestType.SET_STAT, RequestType.UPDATE_PASSWORD, RequestType.UPDATE_PROFILE, RequestType.UPDATE_USERNAME};
    RequestType[] trainingSchemeRequestTypes = {};
    RequestType[] chatRequestTypes = {RequestType.SEND_MESSAGE};
    
    
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
        
        if (authenticateRequest(request)) {
        
            if (contains(authenticationTypes, request)) {
                response = requestHandlers[0].handleRequest(request);
            }
            else if (contains(profileRequestTypes, request)) {

            }
            else if (contains(trainingSchemeRequestTypes, request)) {

            }
            else if (contains(chatRequestTypes, request)) {

            }
        
        }
        else {
            //TODO: Create response object with Authentiation Error
        }
        
        return response;
    }
    
    private boolean authenticateRequest (Request request) {
        return false;
    }
}
