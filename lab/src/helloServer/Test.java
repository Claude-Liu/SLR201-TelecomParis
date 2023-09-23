package helloServer;

public class Test {
    public static void main(String args[]) {
        int port=1234;
        if (args.length!=1) {throw new IllegalArgumentException("Invalid argument value");}
        String portString = args[0];
        try {
            port = Integer.parseInt(portString);
            System.out.println("Parsed int value: " + port);
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer format: " + portString);
        }

        Server server = new Server(port);
        server.start();
        String localHost = "localhost";
        Client client = new Client(localHost, port);
        client.start();
    }
}
