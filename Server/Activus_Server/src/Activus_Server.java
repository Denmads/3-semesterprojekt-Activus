

import communication.TcpServer;
import domain.RequestDelegater;

/**
 * Start th server
 *
 * @author steff
 */
public class Activus_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        RequestDelegater delegater = new RequestDelegater();
        TcpServer a = new TcpServer();
        a.start(delegater);
    }

}