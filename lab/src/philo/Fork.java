package philo;

public class Fork {
    private int id;
    private boolean used = false;

    public Fork(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    public synchronized boolean check(){
        return this.used;
    }
    public synchronized void use(){
        this.used=true;
    }
    public synchronized void put(){
        this.used=false;
    }
}
