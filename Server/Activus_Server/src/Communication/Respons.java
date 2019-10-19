package Communication;

import java.util.HashMap;

public class Respons {

    private HashMap<ArgumentName, Object> argumentsNameList = new HashMap<ArgumentName, Object>();

    public Respons() {

    }

    public HashMap<ArgumentName, Object> getArgumentsNameList() {
        return argumentsNameList;
    }

    public void addToArgumentsNameList(ArgumentName a, Object o) {
        argumentsNameList.put(a,o);
    }
    public ArgumentName respons(Object o ){
        return (ArgumentName) argumentsNameList.get(o);

    }



}
