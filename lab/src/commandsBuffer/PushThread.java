package commandsBuffer;

public class PushThread extends Thread{
	private CommandsBuffer commandsBuffer;
	
	public PushThread(CommandsBuffer commandsBuffer) {
		this.commandsBuffer = commandsBuffer;
	}
	
	@Override
	public synchronized void run() {
		for(int i=0; i<100; i++) {
			String command = "command"+i;
			commandsBuffer.pushCommand(command);
			System.out.println("round"+i+": push command: "+ command);
			try { Thread.sleep(50) ; } catch(Exception e) {}
		}
		}
}

