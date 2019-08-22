import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    private static void display(String s){
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println(s);
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    private static void displayList(List<String> tasks){
        System.out.println("\t---------------------------------------------------------------------------------");
        for (String s :tasks){
            System.out.println(s);
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
        List<String> tasks = new ArrayList<String>();
        displayLogo();
        display("\t Hello I'm Duke\n\t What can I do for you ?");
        String user = sc.nextLine();
        while (!user.equals("bye")){
            if (!user.equals("list")){
                display("\t added: "+ user);
                tasks.add("\t "+tasks.size()+". "+user);
            }
            else{
                displayList(tasks);
            }
            user=sc.nextLine();
        }
        display("\t Bye. Hope to see you again soon!");

    }
}
