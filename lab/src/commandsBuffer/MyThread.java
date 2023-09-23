package commandsBuffer;

public class MyThread extends Thread{
	private String ThreadName;
	
	public MyThread (String ThreadName){
		this.ThreadName = ThreadName;
	}

	public void run() {
		for (int i = 0 ; i < 100 ; i++) {
		System.out.println(ThreadName + ":" + i) ;
		try { Thread.sleep(50) ; } catch(Exception e) {}
		}
		System.out.println(ThreadName + ": END");
	}
	
	public static void main (String args[]) {
		MyThread myThreadA = new MyThread("Thread A") ;
		MyThread myThreadB = new MyThread("Thread B") ;
		myThreadB.start() ;
		myThreadA.start() ;
		// Waiting for the two threads to die
		try { myThreadA.join() ; } catch(Exception e) {}
		try { myThreadB.join() ; } catch(Exception e) {}
	}
}
