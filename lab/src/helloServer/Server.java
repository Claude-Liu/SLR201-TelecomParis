package helloServer;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

class Server extends Thread {
    private int port;
    public Server(int port){
        this.port=port;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is waiting for client connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Buffered character input stream
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // Buffered character output stream
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                String clientMessage = in.readLine();
                System.out.println("Received from client: " + clientMessage);

                String response = "Hello " + clientMessage + "!";
                out.println(response);

                clientSocket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
