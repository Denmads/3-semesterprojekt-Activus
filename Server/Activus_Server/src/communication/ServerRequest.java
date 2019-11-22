package Communication;

import layerInterfaces.IRequestDelegater;
import models.Request;
import models.Response;
import java.io.*;
import java.net.Socket;

/**
 * A thread that handles a single request
 * 
 * @author madsh
 */
public class ServerRequest extends Thread {

    private final Socket socket;
    
    private IRequestDelegater requestDelegater;

    public ServerRequest(Socket clientSocket) {
        this.socket = clientSocket;
        //Show IP address of connected client.
        System.out.println("Connected to " + this.socket.getInetAddress().toString());
    }

    @Override
    public void run() {
        ObjectInput requestStream = null;
        ObjectOutputStream responseStream = null;
        try {
            requestStream = new ObjectInputStream(socket.getInputStream());
            responseStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }

        Request request = null;

        try {
            request = (Request) requestStream.readObject();
            //Handle Request and get respsone object
            Response response = requestDelegater.delegate(request);
            //Send object back and close
            responseStream.writeObject(response);

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    public void setRequestDelegater(IRequestDelegater requestDelegater) {
        this.requestDelegater = requestDelegater;
    }

    
}