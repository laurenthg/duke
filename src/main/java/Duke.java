import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke(String[] filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        String file  =""; // file name
        if (filePath.length != 0 ){ // test file in case of test
            file = filePath[0];
        }
        else{ // no test file
            file = System.getProperty("user.dir")+ "/data/duke.txt";
        }
        this.storage = new Storage(file);
        try{
            this.tasks = new TaskList(storage.load(parser, ui)); // Use of ArrayList (A-Collections) to store tasks
        }
        catch (DukeException e){
            e.print();
        }
        catch (IOException e){
            this.ui.display("\t IOException: \n\t\t error when readFile for initialization of tasks list");
        }
    }


    public void run() {
        this.ui.showWelcome();
        this.storage.getNewAppendWrite(this.storage.getFilePath(),ui);
        String user = this.ui.readCommand();
        while (!user.equals("bye")){
            try {
                if (user.equals("list")) {
                    if (tasks.size() != 0) {
                        this.ui.displayList(tasks);
                    }
                    else {
                        ui.display("\t There is any task yet ");
                    }
                }
                else if (user.matches("find (.*)")) {
                    String find = user.substring(5);
                    String result = "";
                    for ( int i = 0 ; i< tasks.size() ; i++){
                        if ( tasks.get(i).getTask().contains(find)){
                            result += this.tasks.displayOneElementList(i);
                        }
                    }
                    if ( result.isEmpty()){
                        ui.display("\t There is no matching tasks in your list");
                    }
                    else {
                        ui.display("\t Here are the matching tasks in your list: \n" + result );
                    }
                }
                else if (user.matches("done \\d+")) {// if it is done and a number of task
                    int index = Integer.parseInt(user.substring(5)) - 1;
                    if (index > tasks.size() - 1 || index < 0) {
                        throw new InexistentTaskException(ui);
                    }
                    else { // to change the mark, the whole file is rewritten ( probably a better way to do it)
                        tasks.get(index).taskDone();
                        //get the String with the index task marked done
                        String text = this.storage.getDoneString(tasks,index,this.ui);
                        //  rewriter of file by replacing the whole file
                        this.storage.rewriteFile(text,ui);
                        ui.display("\t Nice! I've marked this task as done:\n\t " + tasks.get(index).getTag() +
                                    tasks.get(index).getMark() + " " + tasks.get(index).getTask());
                    }
                }
                else if (user.matches("delete \\d+")) {// if it is done and a number of task
                    int index = Integer.parseInt(user.substring(7)) - 1;
                    if (index > tasks.size() - 1 || index < 0) {
                        throw new InexistentTaskException(ui);
                    }
                    else { // the tasks exist
                        Task removedTask = tasks.remove(index);
                        String text = this.storage.getDeleteTaskString(removedTask,index,ui,tasks.size());
                        //rewriter of file by replacing the whole file
                        this.storage.rewriteFile(text,ui);
                        ui.display("\t Noted. I've removed this task: \n" +
                                "\t\t "+removedTask.getTag() + removedTask.getMark() + " " + removedTask.getTask()+
                                "\n\t Now you have "+ tasks.size() +" tasks in the list");
                    }
                }
                else if (user.matches("todo(.*)")) {
                    if (user.substring(4).isBlank()) {
                        throw new EmptyTodoException(ui);
                    }
                    else {
                        tasks.add(new TodoTask(user.substring(4).trim()));
                        TodoTask newTask = (TodoTask) tasks.get(tasks.size() - 1);
                        try {
                            this.storage.getAppendWrite().write(tasks.size() + "//" + newTask.getTag() + "//" +
                                    newTask.getMark() + "//" + newTask.getTask()+"\n");
                        }
                        catch (IOException e){
                            ui.display("\t IOException:\n\t\t error when writing a todoTask to file");
                        }
                        ui.display("\t Got it. I've added this task:\n\t   "
                                    + newTask.getTag() + newTask.getMark() + newTask.getTask() +
                                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                else if (user.matches("deadline (.*)")) {
                    String[] taskDescription = user.substring(8).split("/by");
                    if (taskDescription[0].isBlank()) {
                        throw new EmptyDeadlineException(ui);
                    }
                    else if (taskDescription.length == 1) { // no /by in input
                        throw new EmptyDeadlineDateException(ui);
                    }
                    else {
                        String description = taskDescription[0].trim();
                        String deadlineString = taskDescription[1].trim();
                        //date format used: dd/MM/yyyy HH:mm
                        String regex ="[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9]";
                        if (!deadlineString.matches(regex)) {
                            throw new DateFormatException(ui);
                        }
                        else {
                            Date deadline = this.parser.stringToDate(deadlineString,this.ui);
                            tasks.add(new DeadlinesTask(description, deadline));
                            DeadlinesTask newTask = (DeadlinesTask) tasks.get(tasks.size() - 1);
                            try {
                                this.storage.getAppendWrite().write(tasks.size() + "//" + newTask.getTag() + "//" +
                                        newTask.getMark() + "//" + newTask.getTask() + "//" + " by:"
                                        +newTask.getDeadlines() + "\n");
                            } catch (IOException e) {
                                ui.display("\t IOException:\n\t\t error when writing a deadline to file");
                            }
                            ui.display("\t Got it. I've added this task:\n\t   "
                                    + newTask.getTag() + newTask.getMark() + newTask.getTask() + " by:"
                                    + newTask.getDeadlines() +
                                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                }
                else if (user.matches("event (.*)")) {
                    String[] taskDescription = user.substring(5).split("/at");
                    if (taskDescription[0].isBlank()) {
                        throw new EmptyEventException(ui);
                    }
                    else if (taskDescription.length == 1) { // no /by in input
                        throw new EmptyEventDateException(ui);
                    }
                    else {
                        String description = taskDescription[0].trim();
                        String periodString = taskDescription[1].trim();
                        //date format used: dd/MM/yyyy HH:mm - dd/MM/yyyy HH:mm
                        String regex ="[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9] " +
                                "- [0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9]";
                        if (!periodString.matches(regex)) {
                            throw new DateEventFormatException(ui);
                        }
                        else {
                            String[] dateString = periodString.split(" - ");
                            Date dateFirst = this.parser.stringToDate(dateString[0],ui);
                            Date dateSecond = this.parser.stringToDate(dateString[1],ui);
                            tasks.add(new EventsTask(description, dateFirst,dateSecond));
                            EventsTask newTask = (EventsTask) tasks.get(tasks.size() - 1);
                            try {
                                this.storage.getAppendWrite().write(tasks.size() + "//" + newTask.getTag() + "//" +
                                        newTask.getMark() + "//" + newTask.getTask() + "//"+
                                        " at:" + newTask.getDateFirst() + "//" + newTask.getDateSecond()+"\n");
                            }
                            catch (IOException e){
                                ui.display("\t IOException:\n\t\t error when writing a event to file");
                            }
                            ui.display("\t Got it. I've added this task:\n\t   "
                                    + newTask.getTag() + newTask.getMark() + newTask.getTask() + " at:"
                                            + newTask.getDateFirst() + " - " + newTask.getDateSecond() +
                                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                }
                else {
                    throw new UnmeaningException(ui);
                }
            }
            catch (DukeException e){ // catch one of subclass of dukeException and print the right message
                e.print();
            }
            user=this.ui.readCommand();
        }
        this.ui.showBye();
        this.storage.getAppendWrite().freeBufferedWriter();  // close() bufferedwriter
    }

    public static void main(String[] args) {
        new Duke(args).run();
    }
}
