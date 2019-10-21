package Server;

import Communication.TcpServer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author steff
 */
public class Activus_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TcpServer a = new TcpServer();
        a.start();
    }

}