package philoServer;

import java.io.FileWriter;
import java.util.ArrayList;

public class TestServer {
    public static void main(String args[]){
        FileWriter fout = null; //just make it simple
        int port=12345;
        // initialize forks and the waiter
        ArrayList<Fork> forks = new ArrayList<Fork>();
        for (int i=0;i<5;i++){
            forks.add(new Fork(i));
        }
        Waiter waiter = new Waiter(forks,fout);

        //initialize server
        Server server = new Server(port,waiter);
        //start server
        server.start();
    }
}
