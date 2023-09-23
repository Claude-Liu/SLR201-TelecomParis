package helloServer;

import java.io.*;
import java.net.Socket;

class Client extends Thread {

    private String addr;
    private int port;

    public Client(String addr, int port){
        this.addr = addr;
        this.port = port;
    }

    @Override
    public void run() {
        try {

            Socket socket = new Socket(addr, port);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message = "World";
            out.println(message);
            System.out.println("Sent to server: " + message);

            String serverResponse = in.readLine();
            System.out.println("Received from server: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


