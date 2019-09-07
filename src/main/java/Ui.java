import java.util.Scanner;

/**
 *  Represents Ui which deals with the interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor of the Ui
     */
    public Ui(){
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the String representing the next line of command of the user.
     * @return the String representing the next line of command of the user.
     */
    public String readCommand(){
        return this.sc.nextLine();
    }

    /**
     * Display the duke logo.
     */
    public void displayLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Show welcome to the user.
     */
    public void showWelcome(){
        this.displayLogo();
        this.display("\t Hello I'm Duke\n\t What can I do for you ?");
    }

    /**
     * Bye message to the user.
     */
    public void showBye(){
        this.display("\t Bye. Hope to see you again soon!");
    }


    /**
     * Display the String in the parameter between two lines.
     * @param s String which will be printed.
     */
    public void display(String s){
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println(s);
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    /**
     * Display the list of tasks.
     * @param tasks tasks list.
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
