package philoServer;

public class TestClient {
    public static void main(String args[]){
        int port=12345;
        String addr = "localhost";
        if (args.length==1){addr = args[0];}  
            
        //initialize clients
        PhiloClient[] philoClients = new PhiloClient[5];
        for (int i=0;i<5;i++){
            philoClients[i]=new PhiloClient(i,port,addr);
        }
        //start clients
        for (int i=0;i<5;i++){
            philoClients[i].start();
        }
    }
}
