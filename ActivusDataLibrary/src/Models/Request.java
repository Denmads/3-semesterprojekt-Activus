package Models;

import Enums.RequestArgumentName;
import Enums.RequestType;
import Exceptions.ArgumentNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author madsh
 */
public class Request implements Serializable{

    private RequestType type;
    private UUID authToken;
    private int profileId;
    private HashMap<RequestArgumentName, Object> arguments;

    public Request(RequestType type, UUID authToken, int profileId) {
        this.type = type;
        this.authToken = authToken;
        this.profileId = profileId;
        arguments = new HashMap<>();
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
    
    
    
    public void addArgument (RequestArgumentName argName, Object value) {
        arguments.put(argName, value);
    }
    
    public <T> T getArgument (RequestArgumentName argName) throws ArgumentNotFoundException, ClassCastException{
        if (!arguments.containsKey(argName)) {
            throw new ArgumentNotFoundException();
        }
        
        return (T)arguments.get(argName);
    }
}
