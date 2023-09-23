package philo;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Client extends Thread{
    private int id;
    private Waiter waiter;
    private int eatTime;
    private int thinkTime;
    private Random random;
    private FileWriter fout;


    public Client(int id, Waiter waiter, FileWriter fout){
        this.id = id; 
        this.waiter = waiter;
        this.random = new Random();
        this.fout = fout;
    }

    public int getid(){
        return id;
    }
    public void think(){
        thinkTime = random.nextInt(256);
        if (this.fout != null){
            try{this.fout.write("client "+id+" think for "+thinkTime+" ms.\n");
                this.fout.flush();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }}
        else{
            System.out.println("client "+id+" think for "+thinkTime+" ms.");}
        try { Thread.sleep(thinkTime) ; } catch(Exception e) {}
    }
    public void eat(){
        eatTime = random.nextInt(256);
        if (this.fout != null){
            try{this.fout.write("client "+id+" eat for "+eatTime+" ms.\n");
            this.fout.flush();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }}
        else{
            System.out.println("client "+id+" eat for "+eatTime+" ms.");}
        try {Thread.sleep(eatTime) ; } catch(Exception e) {}
        waiter.check(id);
    }
    @Override
    public void run(){
        for (int i=0; i<10; i++){
            think();
            waiter.serve(id);
            eat();
        }
        //waiter.printForks();
        if (this.fout != null){
            try{this.fout.write(id + ": END. \n");
                this.fout.flush();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }}
        System.out.println(id + ": END");
    }
}
