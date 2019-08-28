import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class writeFile {
    private BufferedWriter bufferedW;
    private StringBuffer stringBuffer = new StringBuffer();


    public writeFile(String file, boolean append){
        try{
            FileWriter fileW = new FileWriter(file,append); // write only for append text on datafile
            this.bufferedW = new BufferedWriter(fileW);
        }
        catch(IOException e){
            Duke.display("\t IOException: \n\t\t error with fileWritter");
        }
    }
    public void write(String s) throws IOException {
        bufferedW.write(s);
        bufferedW.flush();
    }


    public void freeBufferedWriter(){
        try {
            this.bufferedW.close();
        }
        catch (IOException e){
            Duke.display("\t IOException: \n\t\t error when close the bufferedWriter");
        }
    }

}
