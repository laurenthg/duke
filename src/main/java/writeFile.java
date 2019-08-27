import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class writeFile {
    private BufferedWriter bufferedW;
    private StringBuffer stringBuffer = new StringBuffer();


    public writeFile(String file){
        try{
            FileWriter fileW = new FileWriter(file);
            this.bufferedW = new BufferedWriter(fileW);
        }
        catch(IOException e){
            Duke.display("\t IOException: \n\t\t error with fileWritter");
        }
    }

    public BufferedWriter getBufferedWriter(){
        return bufferedW;
    }

    public void freeBufferedWriter(){
        try {
            this.bufferedW.close();
        }
        catch (IOException e){
            Duke.display("\t IOException: \n\t\t error when close the bufferedWriter");
        }
    }

    public void replaceToFile(String oldLine,String newLine){
        int startIndex = stringBuffer.indexOf(oldLine); //index of start of the oldline in the file
        int endIndex = startIndex + oldLine.length(); // index of end of the oldline in the file
        stringBuffer.replace(startIndex,endIndex,newLine);
    }
}
