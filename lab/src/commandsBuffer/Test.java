package commandsBuffer;

public class Test {
    public static void main(String args[]){
        CommandsBuffer commandsBuffer = new CommandsBuffer();
        PopThread popThread = new PopThread(commandsBuffer);
        PushThread pushThread = new PushThread(commandsBuffer);

        popThread.start();
        pushThread.start();

        try{popThread.join();} catch(Exception e){}
        try{pushThread.join();} catch(Exception e){}
    }
}
