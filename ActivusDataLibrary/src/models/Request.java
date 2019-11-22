package models;

import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ServiceType;
import Exceptions.ArgumentNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * Model class for a request
 *
 * @author madsh
 */
public class Request implements Serializable{

    private ServiceType serviceType;
    private RequestType type;
    private CredentialsContainer credentials;
    private HashMap<RequestArgumentName, Object> arguments;

    public Request(RequestType type, CredentialsContainer credentials, ServiceType serviceType) {
        this.type = type;
        this.credentials = credentials;
        this.serviceType = serviceType;
        arguments = new HashMap<>();
    }

    public ServiceType getServiceType () {
        return serviceType;
    }
    
    public RequestType getRequestType() {
        return type;
    }

    public CredentialsContainer getCredentials () {
        return credentials;
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
