package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataUnserializer {
    public static HelloData unSerialize(String file){
        HelloData data= new HelloData();
        try(
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fin);
        ){
            try{data = (HelloData)in.readObject();}
            catch(ClassNotFoundException e){e.printStackTrace();}
        }
        catch(IOException e ){e.printStackTrace();}
        return data;
    }
}
