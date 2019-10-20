package Models;

import Enums.ResponseArguementName;
import Exceptions.ArguementNotFoundException;
import java.util.HashMap;

public class Response {

    private HashMap<ResponseArguementName, Object> arguements = new HashMap<ResponseArguementName, Object>();

    public Response() {

    }

    public HashMap<ResponseArguementName, Object> getArgumentsNameList() {
        return arguements;
    }

    public void addArgument(ResponseArguementName argName, Object value) {
        arguements.put(argName, value);
    }

    public Object GetArguement(ResponseArguementName argName) throws ArguementNotFoundException {
        if (!arguements.containsKey(argName)) {
            throw new ArguementNotFoundException();
        }

        return arguements.get(argName);
    }

}