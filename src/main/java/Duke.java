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
            Task t = tasks.get(i);
            System.out.print("\t "+ (i+1) + ". " + t.getTag() +t.getMark() + " " +t.getTask());
            if (t instanceof deadlinesTask){
                System.out.println( " by:" + ((deadlinesTask) t).getDeadlines());
            }
            else if ( t instanceof eventsTask){
                System.out.println(((eventsTask) t).getPeriod());
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("\t---------------------------------------------------------------------------------");
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
                        tasks.add(new todoTask(line[3],mark));
                        break;
                    case "[D]":
                        tasks.add(new deadlinesTask(line[3],mark,stringToDate(line[4].substring(4).trim())));
                        break;
                    case "[E]":
                        tasks.add(new eventsTask(line[3],mark,line[4]));
                }
                readLine = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            display("\t IOException: \n\t\t error when readFile for initialization of tasks list");
        }
        catch (inexistentDateException e){
            e.print();
        }
    }

    private static date stringToDate(String deadlineString) throws inexistentDateException {
        String[] dateString = deadlineString.split(" ");
        String[] daysString = dateString[0].split("/");
        String[] hoursString = dateString[1].split(":");
        int day = Integer.parseInt(daysString[0]);
        int month = Integer.parseInt(daysString[1]) -1 ;// Convention of Gregorian Calendar Jan= 0; Feb =1; Dec =11;
        int year = Integer.parseInt(daysString[2]);
        int hrs= Integer.parseInt(hoursString[0]) ;
        int min = Integer.parseInt(hoursString[1]);
        if (min<0 || min >59){
            throw new inexistentDateException();
        }
        if (hrs <0 || hrs >23){
            throw new inexistentDateException();
        }
        switch( month){
            case 0: case 2: case 4: case 6: case 7: case 9: case 11: // month with 31 days : 11 for december
                if (day > 31 || day <0) {
                    throw new inexistentDateException();
                }
                break;
            case 3 : case 5: case 8: case 10: // month with 31 days
                if (day > 30 || day <0) {
                    throw new inexistentDateException();
                }
                break;
            case 1 : // February
                // second part : no leap year and day==29
                if ((day >29 || day < 0) || ((!((year % 4 ==0 && year % 100 != 0) || year % 400 == 0))&& day==29) ){
                    throw new inexistentDateException();
                }
                break;
            default:
                throw new inexistentDateException();
            }
        GregorianCalendar d = new GregorianCalendar(year,month,day,hrs,min);
        return new date(d);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>(); // list of tasks
        String file = System.getProperty("user.dir")+ "/data/duke.txt"; //file path
        readFile rFile = new readFile(file);// reader for initialization of tasks list
        BufferedReader bufferedReader = rFile.getBufferedReader();
        initialization(tasks,bufferedReader); //initialization of tasks list from the Duke.txt datafile
        rFile.freeBufferedReader();
        writeFile wFile = new writeFile(file,true); // true: writer of file by appending txt
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
                else if (user.matches("done \\d+")) {// if it is done and a number of task
                    int index = Integer.parseInt(user.substring(5)) - 1;
                    if (index > tasks.size() - 1 || index < 0) {
                        throw new inexistentTaskException();
                    }
                    else { // to change the mark, the whole file is rewritten ( probably a better way to do it)
                        tasks.get(index).taskDone();
                        Task task = tasks.get(index);
                        String text="" , line ="", oldLine =(index+1)+"//"+task.getTag()+"//"+"[✗]" ,
                                newLine =(index+1)+"//"+task.getTag()+"//"+task.getMark();
                        readFile readFile = new readFile(file);// reader to read before change the data file
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
                        writeFile rwFile = new writeFile(file,false);
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
                else if (user.matches("todo(.*)")) {
                    if (user.substring(4).isBlank()) {
                        throw new emptyTodoException();
                    }
                    else {
                        tasks.add(new todoTask(user.substring(4).trim()));
                        todoTask newTask = (todoTask) tasks.get(tasks.size() - 1);
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
                        throw new emptyDeadlineException();
                    }
                    else if (taskDescription.length == 1) { // no /by in input
                        throw new emptyDeadlineDateException();
                    }
                    else {
                        String description = taskDescription[0].trim();
                        String deadlineString = taskDescription[1].trim();
                        //date format used: dd/MM/yyyy HH:mm
                        String regex ="[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9]";
                        if (!deadlineString.matches(regex)) {
                            throw new dateFormatException();
                        }
                        else {
                            date deadline = stringToDate(deadlineString);
                            tasks.add(new deadlinesTask(description, deadline));
                            deadlinesTask newTask = (deadlinesTask) tasks.get(tasks.size() - 1);
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
                        throw new emptyEventException();
                    }
                    else if (taskDescription.length == 1) { // no /by in input
                        throw new emptyEventDateException();
                    }
                    else {
                        String description = taskDescription[0].trim();
                        String period = "(at:" + taskDescription[1] + ")";
                        tasks.add(new eventsTask(description, period));
                        eventsTask newTask = (eventsTask) tasks.get(tasks.size() - 1);
                        try {
                            wFile.write(tasks.size() + "//" + newTask.getTag() + "//" +
                                    newTask.getMark() + "//" + newTask.getTask() + "//"+ newTask.getPeriod()+"\n");
                        }
                        catch (IOException e){
                            display("\t IOException:\n\t\t error when writing a event to file");
                        }
                        display("\t Got it. I've added this task:\n\t   "
                                + newTask.getTag() + newTask.getMark() + newTask.getTask() + newTask.getPeriod() +
                                "\n\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                else {
                    throw new unmeaningException();
                }
            }
            catch (dukeException e){ // catch one of subclass of dukeException and print the right message
                e.print();
            }
            user=sc.nextLine();
        }
        display("\t Bye. Hope to see you again soon!");
        wFile.freeBufferedWriter();  // close() bufferedwriter
    }
}