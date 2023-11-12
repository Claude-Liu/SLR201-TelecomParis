package philo;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class Waiter {
    private ArrayList<Fork> forks;
    private FileWriter fout;

    public Waiter(ArrayList<Fork> forks, FileWriter fout){
        this.forks=forks;
        this.fout=fout;
    }
    public int leftFork(int id){
        if (id!=0){
        return id-1;}
        else{
            return 4;
        }
    }
    public int rightFork(int id){
        return id;
    }
    
    public synchronized void serve(int clientId){
        int leftFork = leftFork(clientId);
        int rightFork = rightFork(clientId);
        try{
        while(true){
        if (forks.get(leftFork).check() || forks.get(rightFork).check()){
            wait();
        }
        else{
            forks.get(leftFork).use();
            if (this.fout!=null){
                try{fout.write("client "+clientId+" get leftfork. \n");
                    this.fout.flush();}
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("client "+clientId+" get leftfork");}
            forks.get(rightFork).use();
            if (this.fout!=null){
                try{fout.write("client "+clientId+" get rightfork. \n");
                    this.fout.flush();}
                catch (IOException e) {
                    e.printStackTrace();
                }
                }
            else{
                System.out.println("client "+clientId+" get rightfork");}
            notifyAll();
            break;
        }}
        }
        catch(Exception e){}
    }
    public  synchronized void check(int clientId){
        int leftFork = leftFork(clientId);
        int rightFork = rightFork(clientId);
        forks.get(leftFork).put();
        forks.get(rightFork).put();
        notifyAll();
    }
}
