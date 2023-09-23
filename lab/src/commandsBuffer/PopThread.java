package commandsBuffer;

public class PopThread extends Thread{
	private CommandsBuffer commandsBuffer;
	
	public PopThread(CommandsBuffer commandsBuffer) {
		this.commandsBuffer = commandsBuffer;
	}
	
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			String command=commandsBuffer.popCommand();
			System.out.println("round"+i+": pop command: " + command);
			try { Thread.sleep(50) ; } catch(Exception e) {}
		}
	}
}


