/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
        while (true) {
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
    }


}
