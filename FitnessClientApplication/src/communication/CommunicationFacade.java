package Communication;

import Exceptions.ConfigFileNotFound;
import layerInterfaces.ICommunicationFacade;
import models.Request;
import models.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author madsh
 */
public class CommunicationFacade implements ICommunicationFacade {

    private Socket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private ObjectOutputStream serverRequestStream;
    private ObjectInputStream serverResponseStream;

    public CommunicationFacade() throws ConfigFileNotFound, IOException {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(new File("config.properties")));
        } catch (FileNotFoundException ex) {
            throw new ConfigFileNotFound();
        }

        serverAddress = InetAddress.getByName(prop.getProperty("serverIpAddress"));
        serverPort = Integer.parseInt(prop.getProperty("serverPort"));
    }

    private void openConnection() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        serverRequestStream = new ObjectOutputStream(socket.getOutputStream());
        serverResponseStream = new ObjectInputStream(socket.getInputStream());
    }

    private void closeConnection() {
        try {
            serverRequestStream.close();
            serverResponseStream.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CommunicationFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Response sendRequest(Request request) {
        Object response = null;

        try {
            openConnection();
            serverRequestStream.writeObject(request);
            response = serverResponseStream.readObject();
            closeConnection();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CommunicationFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (Response) response;
    }

}