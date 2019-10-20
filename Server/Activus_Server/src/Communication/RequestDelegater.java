package Communication;

import Enums.RequestType;
import Models.Request;
import Models.Response;

public class RequestDelegater {

    RequestType[] authenticationTypes = {RequestType.CREATE_NEW_USER, RequestType.LOGIN, RequestType.LOGOUT};
    RequestType[] profileRequestTypes = {RequestType.ACCEPT_BUDDY_REQUEST, RequestType.DELETE_ACCOUNT, RequestType.FOLLOW_PROFILE, RequestType.FOLLOW_TRAINING_PROGRAM, 
                                         RequestType.GET_PROFILE, RequestType.REMOVE_STAT, RequestType.SEARCH, RequestType.SEND_BUDDY_REQUEST, RequestType.SET_GOAL, 
                                         RequestType.SET_STAT, RequestType.UPDATE_PASSWORD, RequestType.UPDATE_PROFILE, RequestType.UPDATE_USERNAME};
    RequestType[] trainingSchemeTypes = {};
    RequestType[] chatTypes = {RequestType.SEND_MESSAGE};
    
    public RequestDelegater () {
        
    }

    public Response delegate (Request request) {
        
    }
}
