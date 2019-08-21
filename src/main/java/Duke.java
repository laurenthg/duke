import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println("\t Hello I'm Duke\n\t What can I do for you ?");
        System.out.println("\t---------------------------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        String user = sc.nextLine();
        while (!user.equals("bye")){
            System.out.println("");
            System.out.println("\t---------------------------------------------------------------------------------");
            System.out.println("\t "+ user);
            System.out.println("\t---------------------------------------------------------------------------------");
            user=sc.nextLine();
        }
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t---------------------------------------------------------------------------------");

    }
}
