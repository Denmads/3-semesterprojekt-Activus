package Communication.Connection;

import Communication.ObjectValidation;

import java.io.*;
import java.net.Socket;

public class ServerRequest extends Thread {

    protected Socket socket;

    public ServerRequest(Socket clientSocket) {
        this.socket = clientSocket;
        System.out.println("Cennectet to "+this.socket.getInetAddress().toString());
    }

    public void run() {
        InputStream inp = null;
        ObjectInput brinp = null;
        ObjectOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new ObjectInputStream(inp);
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }

        Object line;
        while (true) {
            try {
                line = brinp.readObject();

                if ((line == null)) {
                    socket.close();
                    return;
                } else {
                    ObjectValidation a = new ObjectValidation(line);

                    //out.writeBytes(line + "\n\r");
                    //out.flush();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
    }

}
