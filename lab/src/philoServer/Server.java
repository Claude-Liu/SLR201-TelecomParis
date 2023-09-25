package philoServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;

import java.io.*;

//import philo.Waiter;

public class Server extends Thread{
    private Waiter waiter;
    private int port;

    public Server(int port, Waiter waiter){
        this.port=port;
        this.waiter=waiter;
    }
    public void run(){
        ExecutorService executor = Executors.newFixedThreadPool(10); // Create a thread pool

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept a client connection
                System.out.println("Client connected: " + clientSocket);

                // Create a new thread to handle the client
                Runnable clientHandler = new ClientHandler(clientSocket, waiter);
                executor.execute(clientHandler);
                //serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Shutdown the thread pool gracefully when done
        }
    }
}

class ClientHandler implements Runnable{
    private Socket clientSocket;
    private Waiter waiter;
    private ArrayList<String> allClientIDs=new ArrayList<String>();//["0","1","2","3","4"]

    public ClientHandler(Socket clientSocket, Waiter waiter){
        this.clientSocket = clientSocket;
        this.waiter = waiter;
        for (int i=0; i<5; i++){
            allClientIDs.add(Integer.toString(i));
        }
    }

    private int String2ID(String StringID){
        int id=100;
        switch(StringID){
            case "0": id=0;break;
            case "1": id=1;break;
            case "2": id=2;break;
            case "3": id=3;break;
            case "4": id=4;break;
        }
        return id;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String clientMessage;
            int clientID=100;//initialize it with an invalid value.
            
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);
                if (allClientIDs.contains(clientMessage)){
                    clientID=String2ID(clientMessage);
                }
                else if (clientMessage=="to eat"){
                    if (clientID==100){System.out.println("wrong client ID.");
                    clientSocket.close();return;}
                    waiter.serve(clientID);
                }
                else {
                    assert clientMessage=="eaten";
                    if (clientID==100){System.out.println("wrong client ID.");
                    clientSocket.close();return;}
                    waiter.check(clientID);
                }
                out.println("go ahead!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


