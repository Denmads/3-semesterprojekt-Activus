/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Exceptions.ArguementNotFoundException;
import LayerInterfaces.Enums.RequestArguementName;
import LayerInterfaces.Enums.RequestType;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author madsh
 */
public class Request {

    private RequestType type;
    private UUID authToken;
    private int profileId;
    private HashMap<RequestArguementName, Object> arguements;

    public Request(RequestType type, UUID authToken, int profileId) {
        this.type = type;
        this.authToken = authToken;
        this.profileId = profileId;
        arguements = new HashMap<>();
    }

    public RequestType getRequestType() {
        return type;
    }

    public UUID getAuthenticationToken() {
        return authToken;
    }

    public int getProfileId() {
        return profileId;
    }
    
    
    
    public void AddArgument (RequestArguementName argName, Object value) {
        arguements.put(argName, value);
    }
    
    public <T> T GetArguement (RequestArguementName argName) throws ArguementNotFoundException, ClassCastException{
        if (!arguements.containsKey(argName)) {
            throw new ArguementNotFoundException();
        }
        
        return (T)arguements.get(argName);
    }
}
