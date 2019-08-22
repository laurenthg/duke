import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static void display(String s){
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println(s);
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    private static void displayList(List<Task> tasks){
        System.out.println("\t---------------------------------------------------------------------------------");
        for (int i = 0 ;i< tasks.size() ; i++ ){
            System.out.println("\t "+ (i+1) + ". " + tasks.get(i).getMark() + " " +tasks.get(i).getTask());
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
            if (user.equals("list")){
                displayList(tasks);
            }
            else if(user.matches("done \\d+")) {
                    // if it is done and a number of task
                    try {
                        int index = Integer.parseInt(user.substring(5, user.length())) - 1;
                        tasks.get(index).taskDone();
                        display("\t Nice! I've marked this task as done:\n\t " + tasks.get(index).getMark() + " "
                                + tasks.get(index).getTask());
                    }
                    catch (Exception e) { // done a task which doesn't exist is considered to add a task
                        display("\t added: "+ user);
                        tasks.add(new Task(user));
                    }
            }
            else{
                display("\t added: "+ user);
                tasks.add(new Task(user));
            }
            user=sc.nextLine();
        }
        display("\t Bye. Hope to see you again soon!");

    }
}
