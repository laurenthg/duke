import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private WriteFile appendWrite;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public List<Task> load(Parser parser, Ui ui) throws IOException, InexistentDateException{ // load the initial data file
        ReadFile rFile = new ReadFile(this.filePath,ui);// reader for initialization of tasks list
        BufferedReader bufferedReader = rFile.getBufferedReader();
        List<Task> tasks = new ArrayList<>();
        String readLine = bufferedReader.readLine();
        String[] line;
        String mark = "";
        while (readLine != null && !readLine.isBlank()) {
            line = readLine.split("//");
            mark = line[2];
            switch (line[1]) {
                case "[T]":
                    tasks.add(new TodoTask(line[3], mark));
                    break;
                case "[D]":
                    tasks.add(new DeadlinesTask(line[3], mark, parser.stringToDate(line[4].substring(4).trim(),ui)));
                    break;
                case "[E]":
                    tasks.add(
                            new EventsTask(line[3], mark, parser.stringToDate(line[4].substring(4),ui), parser.stringToDate(line[5],ui)));
            }
            readLine = bufferedReader.readLine();
        }
        rFile.freeBufferedReader();
        return tasks;
    }

    public String getFilePath() {
        return filePath;
    }

    public WriteFile getAppendWrite() {
        return appendWrite;
    }

    public void getNewAppendWrite(String file, Ui ui){
        this.appendWrite = new WriteFile(file,true,ui);
    }

    public String getDeleteTaskString(Task removedTask, int index , Ui ui, int tasksSize){
        String text="" , line ="", oldLine =(index+1)+"//"+removedTask.getTag() ,
                newLine ="";
        ReadFile readFile = new ReadFile(this.filePath,ui);// reader to read before change the data file
        BufferedReader bufferedR = readFile.getBufferedReader();
        try{
            for (int i = 0 ; i< tasksSize+1 ; i++){ // one task have been just removed
                line = bufferedR.readLine();
                if (!line.contains(oldLine)){
                    if (i > index ) { // we should minus 1 to each line after the line removed
                        line = line.replace( (i+1) +"//", (i)+"//"  ) + "\n";
                        text += line ;
                    }
                    else{
                        text+= line +"\n";
                    }
                }
            }
        }
        catch(IOException e){
            ui.display("\t IOException: \n\t\t error when reading the whole file");
        }
        readFile.freeBufferedReader(); //close the reader
        return text;
    }


    public String getDoneString(TaskList tasks , int index, Ui ui){ //returns String by replacing the done task
        Task task = tasks.get(index);
        String text="" , line, oldLine =(index+1)+"//"+task.getTag()+"//"+"[✗]" ,
                newLine =(index+1)+"//"+task.getTag()+"//"+task.getMark();
        ReadFile readFile = new ReadFile(this.filePath,ui);// reader to read before change the data file
        BufferedReader bufferedR = readFile.getBufferedReader();
        try{
            while ((line = bufferedR.readLine()) != null) {
                if (line.contains(oldLine)){
                    line = line.replace(oldLine,newLine);
                }
                text += line + "\n";
            }
        }
        catch(IOException e){
            ui.display("\t IOException: \n\t\t error when reading the whole file");
        }
        readFile.freeBufferedReader(); //close the reader
        return text;
    }

    public void rewriteFile(String text, Ui ui){
        WriteFile rwFile = new WriteFile(this.filePath,false,ui);
        try{
            rwFile.write(text);
        }
        catch (IOException e){
            ui.display("\t IOException: \n\t\t error when writing the whole file");
        }
        rwFile.freeBufferedWriter();//free the writer
    }
}
