import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui(){
        this.sc = new Scanner(System.in);
    }

    public String readCommand(){
        return this.sc.nextLine();
    }

    public void displayLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void showWelcome(){
        this.displayLogo();
        this.display("\t Hello I'm Duke\n\t What can I do for you ?");
    }

    public void showBye(){
        this.display("\t Bye. Hope to see you again soon!");
    }



    public void display(String s){
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println(s);
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    /*
    Display the list of tasks
     */
    public void displayList(TaskList tasks){
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0 ;i< tasks.size() ; i++ ){
            System.out.print(tasks.displayOneElementList(i));
        }
        System.out.println("\t---------------------------------------------------------------------------------");
    }

}
