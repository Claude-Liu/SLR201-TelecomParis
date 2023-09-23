package philo;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TestPhilo {
    public static void main(String args[]){
        String outfile;
        FileWriter fout;
        if (args.length==1) {
            outfile="../data/"+args[0];
            try{
            fout = new FileWriter(outfile);}
            catch(IOException e){e.printStackTrace();fout=null;}
        }
        else  fout = null;

        ArrayList<Fork> forks = new ArrayList<Fork>();
        for (int i=0;i<5;i++){
            forks.add(new Fork(i));
        }
        Waiter waiter = new Waiter(forks,fout);
        Client[] clients = new Client[5];
        for (int i=0;i<5;i++){
            clients[i]=new Client(i,waiter,fout);
        }
        for (int i=0;i<5;i++){
            clients[i].start();
        }
        for (int i=0;i<5;i++){
            try{clients[i].join();}
            catch(Exception e){}
        }
    }
}
