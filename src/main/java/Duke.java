import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void display(String s){ // Used also by Exception Classes
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println(s);
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    /*
    Display the list of tasks
     */
    private static void displayList(List<Task> tasks){
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ;i< tasks.size() ; i++ ){
            System.out.print(displayOneElementList(tasks,i));
        }
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    /*
    return the String of display of one element of list of tasks
     */
    private static String displayOneElementList(List<Task> tasks, int index){
        Task t = tasks.get(index);
        String result = "\t "+ (index+1) + ". " + t.getTag() +t.getMark() + " " +t.getTask();
        if (t instanceof DeadlinesTask){
            result +=  " by:" + ((DeadlinesTask) t).getDeadlines() + "\n";
        }
        else if ( t instanceof EventsTask){
            result +=  " at:" +
                    ((EventsTask) t).getDateFirst() + " - " + ((EventsTask) t).getDateSecond() + "\n";
        }
        else {
            result += "\n";
        }
        return result;
    }

    private static void displayLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /*
    Initialization of the tasks list by reading the data file
     */
    private static void initialization(List<Task> tasks, BufferedReader bufferedReader){
        try{
            String readLine = bufferedReader.readLine();
            String[] line;
            String mark = "";
            while (readLine != null && !readLine.isBlank()){
                line = readLine.split("//");
                mark = line[2];
                switch ( line[1] ){
                    case "[T]":
                        tasks.add(new TodoTask(line[3],mark));
                        break;
                    case "[D]":
                        tasks.add(new DeadlinesTask(line[3],mark,stringToDate(line[4].substring(4).trim())));
                        break;
                    case "[E]":
                        tasks.add(
                                new EventsTask(line[3],mark,stringToDate(line[4].substring(4)), stringToDate(line[5])));
                }
                readLine = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            display("\t IOException: \n\t\t error when readFile for initialization of tasks list");
        }
        catch (InexistentDateException e){
            e.print();
        }
    }

    private static Date stringToDate(String deadlineString) throws InexistentDateException {
        String[] dateString = deadlineString.split(" ");
        String[] daysString = dateString[0].split("/");
        String[] hoursString = dateString[1].split(":");
        int day = Integer.parseInt(daysString[0]);
        int month = Integer.parseInt(daysString[1]) -1 ;// Convention of Gregorian Calendar Jan= 0; Feb =1; Dec =11;
        int year = Integer.parseInt(daysString[2]);
        int hrs= Integer.parseInt(hoursString[0]) ;
        int min = Integer.parseInt(hoursString[1]);
        if (min<0 || min >59){
            throw new InexistentDateException();
        }
        if (hrs <0 || hrs >23){
            throw new InexistentDateException();
        }
        switch( month){
            case 0: case 2: case 4: case 6: case 7: case 9: case 11: // month with 31 days : 11 for december
                if (day > 31 || day <0) {
                    throw new InexistentDateException();
                }
                break;
            case 3 : case 5: case 8: case 10: // month with 31 days
                if (day > 30 || day <0) {
                    throw new InexistentDateException();
                }
                break;
            case 1 : // February
                // second part : no leap year and day==29
                if ((day >29 || day < 0) || ((!((year % 4 ==0 && year % 100 != 0) || year % 400 == 0))&& day==29) ){
                    throw new InexistentDateException();
                }
                break;
            default:
                throw new InexistentDateException();
            }
        GregorianCalendar d = new GregorianCalendar(year,month,day,hrs,min);
        return new Date(d);
    }

    private Scanner sc;
    private List<Task> tasks;
    private String file;

    public Duke(String[] filePath) {
        this.sc = new Scanner(System.in);
        this.tasks = new ArrayList<Task>(); // Use of ArrayList (A-Collections) to store tasks
        this.file =""; // file name
        if (filePath.length != 0 ){ // test file in case of test
            file = filePath[0];
        }
        else{ // no test file
            file = System.getProperty("user.dir")+ "/data/duke.txt";
        }
        ReadFile rFile = new ReadFile(file);// reader for initialization of tasks list
        BufferedReader bufferedReader = rFile.getBufferedReader();
        initialization(tasks,bufferedReader); //initialization of tasks list from the Duke.txt datafile
        rFile.freeBufferedReader();
    }

    public void run() {
        WriteFile wFile = new WriteFile(file,true); // true: writer of file by appending txt
        displayLogo();
        display("\t Hello I'm Duke\n\t What can I do for you ?");
        String user = sc.nextLine();
        while (!user.equals("bye")){
            try {
                if (user.equals("list")) {
                    if (tasks.size() != 0) {
                        displayList(tasks);
                    }
                    else {
                        display("\t There is any task yet ");
                    }
                }
                else if (user.matches("find (.*)")) {
                    String find = user.substring(5);
                    String result = "";
                    for ( int i = 0 ; i< tasks.size() ; i++){
                        if ( tasks.get(i).getTask().contains(find)){
                            result += displayOneElementList(tasks,i);
                        }
                    }
                    if ( result.isEmpty()){
                        display("\t There is no matching tasks in your list");
                    }
                    else {
                        display("\t Here are the matching tasks in your list: \n" + result );
                    }
                }
                else if (user.matches("done \\d+")) {// if it is done and a number of task
                    int index = Integer.parseInt(user.substring(5)) - 1;
                    if (index > tasks.size() - 1 || index < 0) {
                        throw new InexistentTaskException();
                    }
                    else { // to change the mark, the whole file is rewritten ( probably a better way to do it)
                        tasks.get(index).taskDone();
                        Task task = tasks.get(index);
                        String text="" , line ="", oldLine =(index+1)+"//"+task.getTag()+"//"+"[✗]" ,
                                newLine =(index+1)+"//"+task.getTag()+"//"+task.getMark();
                        ReadFile readFile = new ReadFile(file);// reader to read before change the data file
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
                            display("\t IOException: \n\t\t error when reading the whole file");
                        }
                        readFile.freeBufferedReader(); //close the reader
                        // false :// rewriter of file by replacing the whole file
                        WriteFile rwFile = new WriteFile(file,false);
                        try{
                            rwFile.write(text);
                        }
                        catch (IOException e){
                            display("\t IOException: \n\t\t error when writing the whole file");
                        }
                        rwFile.freeBufferedWriter();//free the writer
                        display("\t Nice! I've marked this task as done:\n\t " + tasks.get(index).getTag() +
                                    tasks.get(index).getMark() + " " + tasks.get(index).getTask());
                    }
                }
                else if (user.matches("delete \\d+")) {// if it is done and a number of task
                    int index = Integer.parseInt(user.substring(7)) - 1;
                    if (index > tasks.size() - 1 || index < 0) {
                        throw new InexistentTaskException();
                    }
                    else { // the tasks exist
                        Task removedTask = tasks.remove(index);
                        String text="" , line ="", oldLine =(index+1)+"//"+removedTask.getTag() ,
                                newLine ="";
                        ReadFile readFile = new ReadFile(file);// reader to read before change the data file
                        BufferedReader bufferedR = readFile.getBufferedReader();
                        try{
                            for (int i = 0 ; i< tasks.size()+1 ; i++){ // one task have been just removed
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
                            display("\t IOException: \n\t\t error when reading the whole file");
                        }
                        readFile.freeBufferedReader(); //close the reader
                        // false :// rewriter of file by replacing the whole file
                        WriteFile rwFile = new WriteFile(file,false);
                        try{
                            rwFile.write(text);
                        }
                        catch (IOException e){
                            display("\t IOException: \n\t\t error when writing the whole file");
                        }
                        rwFile.freeBufferedWriter();//free the writer
                        display("\t Noted. I've removed this task: \n" +
                                "\t\t "+removedTask.getTag() + removedTask.getMark() + " " + removedTask.getTask()+
                                "\n\t Now you have "+ tasks.size() +" tasks in the list");
                    }
                }
                else if (user.matches("todo(.*)")) {
                    if (user.substring(4).isBlank()) {
                        throw new EmptyTodoException();
                    }
                    else {
                        tasks.add(new TodoTask(user.substring(4).trim()));
                        TodoTask newTask = (TodoTask) tasks.get(tasks.size() - 1);
                        try {
                            wFile.write(tasks.size() + "//" + newTask.getTag() + "//" +
                                    newTask.getMark() + "//" + newTask.getTask()+"\n");
                        }
                        catch (IOException e){
                            display("\t IOException:\n\t\t error when writing a todoTask to file");
                        }
                        display("\t Got it. I've added this task:\n\t   "
                                    + newTask.getTag() + newTask.getMark() + newTask.getTask() +
                                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                else if (user.matches("deadline (.*)")) {
                    String[] taskDescription = user.substring(8).split("/by");
                    if (taskDescription[0].isBlank()) {
                        throw new EmptyDeadlineException();
                    }
                    else if (taskDescription.length == 1) { // no /by in input
                        throw new EmptyDeadlineDateException();
                    }
                    else {
                        String description = taskDescription[0].trim();
                        String deadlineString = taskDescription[1].trim();
                        //date format used: dd/MM/yyyy HH:mm
                        String regex ="[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9]";
                        if (!deadlineString.matches(regex)) {
                            throw new DateFormatException();
                        }
                        else {
                            Date deadline = stringToDate(deadlineString);
                            tasks.add(new DeadlinesTask(description, deadline));
                            DeadlinesTask newTask = (DeadlinesTask) tasks.get(tasks.size() - 1);
                            try {
                                wFile.write(tasks.size() + "//" + newTask.getTag() + "//" +
                                        newTask.getMark() + "//" + newTask.getTask() + "//" + " by:"
                                        +newTask.getDeadlines() + "\n");
                            } catch (IOException e) {
                                display("\t IOException:\n\t\t error when writing a deadline to file");
                            }
                            display("\t Got it. I've added this task:\n\t   "
                                    + newTask.getTag() + newTask.getMark() + newTask.getTask() + " by:"
                                    + newTask.getDeadlines() +
                                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                }
                else if (user.matches("event (.*)")) {
                    String[] taskDescription = user.substring(5).split("/at");
                    if (taskDescription[0].isBlank()) {
                        throw new EmptyEventException();
                    }
                    else if (taskDescription.length == 1) { // no /by in input
                        throw new EmptyEventDateException();
                    }
                    else {
                        String description = taskDescription[0].trim();
                        String periodString = taskDescription[1].trim();
                        //date format used: dd/MM/yyyy HH:mm - dd/MM/yyyy HH:mm
                        String regex ="[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9] " +
                                "- [0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9]";
                        if (!periodString.matches(regex)) {
                            throw new DateEventFormatException();
                        }
                        else {
                            String[] dateString = periodString.split(" - ");
                            Date dateFirst = stringToDate(dateString[0]);
                            Date dateSecond = stringToDate(dateString[1]);
                            tasks.add(new EventsTask(description, dateFirst,dateSecond));
                            EventsTask newTask = (EventsTask) tasks.get(tasks.size() - 1);
                            try {
                                wFile.write(tasks.size() + "//" + newTask.getTag() + "//" +
                                        newTask.getMark() + "//" + newTask.getTask() + "//"+
                                        " at:" + newTask.getDateFirst() + "//" + newTask.getDateSecond()+"\n");
                            }
                            catch (IOException e){
                                display("\t IOException:\n\t\t error when writing a event to file");
                            }
                            display("\t Got it. I've added this task:\n\t   "
                                    + newTask.getTag() + newTask.getMark() + newTask.getTask() + " at:"
                                            + newTask.getDateFirst() + " - " + newTask.getDateSecond() +
                                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                }
                else {
                    throw new UnmeaningException();
                }
            }
            catch (DukeException e){ // catch one of subclass of dukeException and print the right message
                e.print();
            }
            user=sc.nextLine();
        }
        display("\t Bye. Hope to see you again soon!");
        wFile.freeBufferedWriter();  // close() bufferedwriter
    }

    public static void main(String[] args) {
        new Duke(args).run();
    }
}
