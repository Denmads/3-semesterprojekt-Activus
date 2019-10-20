package Communication;

import Enums.RequestArguementName;
import Enums.RequestType;
import Exceptions.ArguementNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author madsh
 */
public class Request implements Serializable {

    private RequestType type;
    private UUID authToken;
    private int userId;
    private HashMap<RequestArguementName, Object> arguements;

    public Request(RequestType type, UUID authToken, int userId) {
        this.type = type;
        this.authToken = authToken;
        this.userId = userId;
        arguements = new HashMap<>();
    }

    public RequestType getRequestType() {
        return type;
    }

    public UUID getAuthenticationToken() {
        return authToken;
    }

    public int getUserId() {
        return userId;
    }

    public void AddArgument(RequestArguementName argName, Object value) {
        arguements.put(argName, value);
    }

    public <T> T GetArguement(RequestArguementName argName) throws ArguementNotFoundException, ClassCastException {
        if (!arguements.containsKey(argName)) {
            throw new ArguementNotFoundException();
        }

        return (T) arguements.get(argName);
    }
}