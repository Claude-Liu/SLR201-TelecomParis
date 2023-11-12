package philoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.net.Socket;

public class PhiloClient extends Thread{
    // attributes for 
    private int id;
    private int eatTime;
    private int thinkTime;
    private Random random;
    // attributes for sockets
    private String addr;
    private int port;

    public PhiloClient(int id, int port){
        this.addr ="localhost";;
        this.port = port;
        this.id = id;
        this.random = new Random();
    }

    public PhiloClient(int id, int port, String addr){
        this.addr =addr;
        this.port = port;
        this.id = id;
        this.random = new Random();
    }

    public int getid(){
        return id;
    }
    //philosopher think
    public void think(PrintWriter out){
        thinkTime = random.nextInt(256);
        System.out.println("client "+id+" think for "+thinkTime+" ms.");
        try { Thread.sleep(thinkTime) ; } 
        catch(Exception e) {}
        String message = "to eat";
        out.println(message);
        System.out.println("Send to server: "+message);
    }
    //philosopher eat
    public void eat(PrintWriter out){
        eatTime = random.nextInt(256);
        System.out.println("client "+id+" eat for "+eatTime+" ms.");
        try {Thread.sleep(eatTime) ; } 
        catch(Exception e) {}
        //tell the waiter I have finished eating.
        String message = "eaten";
        out.println(message);
        System.out.println("Send to server: "+message);
    }

    public void run(){
        try{
            Socket socket = new Socket(addr,port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // tell the waiter my client ID.
            String message = Integer.toString(id);
            out.println(message);
            System.out.println("Send to server: "+message);
            String serverResponse = in.readLine();
            System.out.println("Received from server: " + serverResponse);
            for (int i=0; i<10; i++){
                think(out);
                //wait for the waiter to inform that I can eat.
                serverResponse = in.readLine();
                System.out.println("Received from server: " + serverResponse);
                eat(out);
                serverResponse = in.readLine();
                System.out.println("Received from server: " + serverResponse);
            }
            socket.close();
            System.out.println(id + ": END");
        }
        catch(IOException e){e.printStackTrace();}
        }        
    }




