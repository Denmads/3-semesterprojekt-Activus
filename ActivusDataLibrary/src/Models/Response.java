package Models;

import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Model class for response
 * 
 * @author madsh
 */
public class Response  implements Serializable{

    private HashMap<ResponseArgumentName, Object> arguements = new HashMap<>();

    public void addArgument(ResponseArgumentName argName, Object value) {
        arguements.put(argName, value);
    }

    public Object getArgument(ResponseArgumentName argName) throws ArgumentNotFoundException {
        if (!arguements.containsKey(argName)) {
            throw new ArgumentNotFoundException();
        }

        return arguements.get(argName);
    }

}