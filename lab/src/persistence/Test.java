package persistence;

public class Test {
    public static void main(String args[]){
        String outfile;
        if (args.length==1){
            outfile = args[0];
        }
        else{throw new IllegalArgumentException("Invalid argument value");}

        HelloData data = new HelloData();
        data.setMessage("message which could be serialized.");
        data.setTransientMessage("message which could not be serialized.");
        data.printData();

        DataSerializer.serialize(outfile, data);
        String infile = outfile;
        data = DataUnserializer.unSerialize(infile);
        data.printData();
    }
}
