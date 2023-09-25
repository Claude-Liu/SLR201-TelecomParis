package persistence;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataSerializer {
    
    public static void serialize(String file, HelloData data){
        try(
        FileOutputStream fout = new FileOutputStream(file) ;
        ObjectOutputStream out = new ObjectOutputStream(fout) ;
        ){
            out.writeObject(data);
            out.reset();
        }
        catch(IOException e){e.printStackTrace();}
    }
}
