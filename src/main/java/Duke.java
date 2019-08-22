import java.util.Scanner;

public class Duke {

    private static void display(String s){
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println(s);
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
        displayLogo();
        display("\t Hello I'm Duke\n\t What can I do for you ?");
        Scanner sc = new Scanner(System.in);
        String user = sc.nextLine();
        while (!user.equals("bye")){
            display("\t "+ user);
            user=sc.nextLine();
        }
        display("\t Bye. Hope to see you again soon!");

    }
}
