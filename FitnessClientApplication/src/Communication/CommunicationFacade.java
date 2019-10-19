/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Exceptions.ConfigFileNotFound;
import LayerInterfaces.ICommunicationFacade;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author madsh
 */
public class CommunicationFacade implements ICommunicationFacade{

    private Socket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private ObjectOutputStream serverRequestStream;
    private ObjectInputStream serverResponseStream;
    
    public CommunicationFacade () throws ConfigFileNotFound, IOException{
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(new File("config.properties")));
        }
        catch (FileNotFoundException ex) {
            throw new ConfigFileNotFound();
        }
        
        serverAddress = InetAddress.getByName(prop.getProperty("serverIpAddress"));
        serverPort = Integer.parseInt(prop.getProperty("serverPort"));
    }
    
    @Override
    public void openConnection() throws IOException {
       socket = new Socket(serverAddress, serverPort);
       serverRequestStream = new ObjectOutputStream(socket.getOutputStream());
       serverResponseStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void closeConnection() {
        try {
            serverRequestStream.close();
            serverResponseStream.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CommunicationFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendRequest(Request request) {
        try {
            serverRequestStream.writeObject(request);
            serverResponseStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CommunicationFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
