import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void display(String s){ // Used by Exception Classes
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println(s);
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    private static void displayList(List<Task> tasks){
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ;i< tasks.size() ; i++ ){
            Task t = tasks.get(i);
            System.out.print("\t "+ (i+1) + ". " + t.getTag() +t.getMark() + " " +t.getTask());
            if (t instanceof deadlinesTask){
                System.out.println(((deadlinesTask) t).getDeadlines());
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        displayLogo();
        display("\t Hello I'm Duke\n\t What can I do for you ?");
        String user = sc.nextLine();
        while (!user.equals("bye")){
            try {
                if (user.equals("list")) {
                    if (tasks.size() != 0) {
                        displayList(tasks);
                    } else {
                        display("\t There is any task yet ");
                    }
                }
                else if (user.matches("done \\d+")) {
                    // if it is done and a number of task
                    int index = Integer.parseInt(user.substring(5, user.length())) - 1;
                    if (index > tasks.size() - 1 || index < 0) {
                        throw new inexistentTaskException();
                    }
                    else {
                        tasks.get(index).taskDone();
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
                        display("\t Got it. I've added this task:\n\t   "
                                    + newTask.getTag() + newTask.getMark() + newTask.getTask() +
                                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                else if (user.matches("deadline(.*)")) {
                    String[] taskDescription = user.substring(8).trim().split("/by");
                    if (taskDescription[0].isBlank()) {
                        throw new emptyDeadlineException();
                    }
                    else if (taskDescription.length == 1) { // no /by in input
                        throw new emptyDeadlineDateException();
                    }
                    else {
                        String description = taskDescription[0];
                        String deadline = "(by:" + taskDescription[1] + ")";
                        tasks.add(new deadlinesTask(description, deadline));
                        deadlinesTask newTask = (deadlinesTask) tasks.get(tasks.size() - 1);
                        display("\t Got it. I've added this task:\n\t   "
                                + newTask.getTag() + newTask.getMark() + newTask.getTask() + newTask.getDeadlines() +
                                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                else if (user.matches("event(.*)")) {
                    String[] taskDescription = user.substring(5).trim().split("/at");
                    if (taskDescription[0].isBlank()) {
                        throw new emptyEventException();
                    }
                    else if (taskDescription.length == 1) { // no /by in input
                        throw new emptyEventDateException();
                    }
                    else {
                        String description = taskDescription[0];
                        String period = "(at:" + taskDescription[1] + ")";
                        tasks.add(new eventsTask(description, period));
                        eventsTask newTask = (eventsTask) tasks.get(tasks.size() - 1);
                        display("\t Got it. I've added this task:\n\t   "
                                + newTask.getTag() + newTask.getMark() + newTask.getTask() + newTask.getPeriod() +
                                "\n\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                else {
                    throw new unmeaningException();
                }
            }
            catch (dukeException e ){ // catch one of subclass of dukeException and print the right message
                e.print();
            }
            user=sc.nextLine();
        }
        display("\t Bye. Hope to see you again soon!");
    }
}