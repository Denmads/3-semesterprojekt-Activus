package communication;

import layerInterfaces.IRequestDelegater;
import models.Request;
import models.Response;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                responseStream.writeObject(new Response());
            } catch (IOException ex) {
                Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setRequestDelegater(IRequestDelegater requestDelegater) {
        this.requestDelegater = requestDelegater;
    }

    
}