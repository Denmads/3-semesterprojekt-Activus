/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Models.Request;
import Models.Response;
import persistence.DatabaseFacade;

/**
 *
 * @author madsh
 */
public class TrainingSchemeRequestHandler extends IRequestHandler{

    public TrainingSchemeRequestHandler(DatabaseFacade dbFacade) {
        super(dbFacade);
    }

    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();
        switch (request.getRequestType()){
            case CREATE_TRAINING_PROGRAM:{
                
            }
            case ADD_EXERCISE:{
                
            }   
            case RECEIVE_EXERCISE:{
                
            }
            case REMOVE_EXERCISE:{
                
            }
            case LOAD_ALL_EXERCISE:{
                
            }
            
        }
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
