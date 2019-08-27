import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readFile {
    private BufferedReader bufferedR;

    public readFile(String file){
        try {
            FileReader fileReader = new FileReader(file);
            this.bufferedR = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException e){
            Duke.display("\t FileNotFoundException: \n\t\t the " + file +" isn't found ");
        }
        /*
        catch (IOException e){
            Duke.display("\t IOException: \n\t\t error with fileReader");
        }
        */
    }

    public void freeBufferedReader(){
        try {
            this.bufferedR.close();
        }
        catch (IOException e){
            Duke.display("\t IOException: \n\t\t error when close the bufferedReader");
        }
    }
}
