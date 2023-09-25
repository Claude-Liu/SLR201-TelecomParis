package philoServer;
import java.io.FileWriter;
import java.util.ArrayList;
//import philo.Fork;
//import philo.Waiter;

public class Test {
    public static void main(String args[]){
        FileWriter fout = null; //just make it simple
        int port=12345;
        ArrayList<Fork> forks = new ArrayList<Fork>();
        for (int i=0;i<5;i++){
            forks.add(new Fork(i));
        }
        Waiter waiter = new Waiter(forks,fout);

        //initialize server
        Server server = new Server(port,waiter);
        //initialize clients
        PhiloClient[] philoClients = new PhiloClient[5];
        for (int i=0;i<5;i++){
            philoClients[i]=new PhiloClient(i,port);
        }
        //start server
        server.start();
        //start clients
        for (int i=0;i<5;i++){
            philoClients[i].start();
        }
    }
}
