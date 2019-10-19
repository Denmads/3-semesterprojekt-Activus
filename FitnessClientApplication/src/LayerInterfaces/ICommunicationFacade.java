/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerInterfaces;

import Communication.Request;
import java.io.IOException;

/**
 *
 * @author madsh
 */
public interface ICommunicationFacade {
    public void openConnection() throws IOException;
    public void closeConnection();
    public void sendRequest(Request request);
}