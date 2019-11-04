package Communication;

import Domain.RequestDelegater;
import LayerInterfaces.IRequestDelegater;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listens for clients to connect and then gives them a new thread to handle their request
 *
 * @author steff
 */
public class TcpServer {

    //Creating a server socket to wait for a connection over a network.
    private ServerSocket serverSocket = null;
    //Creating a socket to retrieve the clients requests.
    private Socket sock = null;
    final int PORT = 4598;
    
    private IRequestDelegater requestDelegater;

    public void start() {
        requestDelegater = new RequestDelegater();

        try {
            //Opening a connection on the chosen PORT.
            serverSocket = new ServerSocket(PORT);
            //Show that the server is waiting for a connection.
            System.out.println("Wating for client on port " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean running = true;
        while (running) {
            try {
                //Accept the connection from the client to the socket object.
                sock = serverSocket.accept();

            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            //If a connection to the client is made, then handle the clients request.
            if (sock.isConnected()) {
                //Show that the client is connected.
                System.out.println("Client Connected");
                //Handle the clients request.
                ServerRequest serverRequest = new ServerRequest(sock);
                serverRequest.setDaemon(true);
                serverRequest.setRequestDelegater(requestDelegater);
                serverRequest.start();
            } else {
                //If no connection is made continue waiting for a connection.
                System.out.println("waiting for Client");
            }

        }
    }

}