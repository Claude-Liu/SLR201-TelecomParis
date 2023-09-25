package persistence;

import java.io.*;

public class HelloData implements Serializable {

    private static final long serialVersionUID = 123456789L;
    private String message;
    private transient String transientMessage;

    public String getMessage(){
        return message;
    }

    public String getTransientMessage(){
        return transientMessage;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public void setTransientMessage(String transientMessage){
        this.transientMessage = transientMessage;
    }
    public void printData(){
        System.out.println("message is: "+message);
        System.out.println("transient message is: "+transientMessage);
    }
}
