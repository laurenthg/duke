import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    private BufferedReader bufferedR;
    private Ui ui;

    public ReadFile(String file, Ui ui){
        this.ui=ui;
        try {
            FileReader fileReader = new FileReader(file);
            this.bufferedR = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException e){
            ui.display("\t FileNotFoundException: \n\t\t the " + file +" isn't found ");
        }
        /*
        catch (IOException e){
            Duke.display("\t IOException: \n\t\t error with fileReader");
        }
        */
    }

    public BufferedReader getBufferedReader() {
        return bufferedR;
    }

    public void freeBufferedReader(){
        try {
            this.bufferedR.close();
        }
        catch (IOException e){
            ui.display("\t IOException: \n\t\t error when close the bufferedReader");
        }
    }
}
