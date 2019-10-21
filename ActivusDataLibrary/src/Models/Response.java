package Models;

import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import java.util.HashMap;

public class Response {

    private HashMap<ResponseArgumentName, Object> arguements = new HashMap<ResponseArgumentName, Object>();

    public void addArgument(ResponseArgumentName argName, Object value) {
        arguements.put(argName, value);
    }

    public Object getArguement(ResponseArgumentName argName) throws ArgumentNotFoundException {
        if (!arguements.containsKey(argName)) {
            throw new ArgumentNotFoundException();
        }

        return arguements.get(argName);
    }

}