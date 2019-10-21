/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Persistence.DatabaseConnection;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author steff
 */
public class TcpServer {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    final int PORT = 4598;

    public void start() {

        try {

            serverSocket = new ServerSocket(PORT);
            System.out.println("Wating for client on port "+ PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        boolean running = true;
        while (running) {
            try {

                socket = serverSocket.accept();


            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            if(socket.isConnected()){
                System.out.println("Client Connedtet");
                new ServerRequest(socket).start();
            }else{
                System.out.println("wating for Client");
            }

        }
        
        try {
            DatabaseConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
