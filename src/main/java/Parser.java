import java.util.GregorianCalendar;

public class Parser {
    public Parser(){
    }

    public Command parse(String user){
        Command c ;
        if (user.equals("list")) {
            c = new ListCommand(user);
        }
        else if (user.matches("find (.*)")) {
            c = new FindCommand(user);
        }
        else if (user.matches("done \\d+")) {// if it is done and a number of task
            c = new DoneCommand(user);
        }
        else if (user.matches("delete \\d+")) {// if it is done and a number of task
            c = new DeleteCommand(user);
        }
        else if (user.matches("todo(.*)")) {
            c = new TodoCommand(user);
        }
        else if (user.matches("deadline (.*)")) {
            c = new DeadlineCommand(user);
        }
        else if (user.matches("event (.*)")) {
            c = new EventCommand(user);
        }
        else if (user.matches(("bye"))){
            c = new ByeCommand(user);
        }
        else {
            c = new UnmeaningCommand(user);
        }
        return c ;

    }


    public Date stringToDate(String deadlineString, Ui ui) throws InexistentDateException {
        String[] dateString = deadlineString.split(" ");
        String[] daysString = dateString[0].split("/");
        String[] hoursString = dateString[1].split(":");
        int day = Integer.parseInt(daysString[0]);
        int month = Integer.parseInt(daysString[1]) -1 ;// Convention of Gregorian Calendar Jan= 0; Feb =1; Dec =11;
        int year = Integer.parseInt(daysString[2]);
        int hrs= Integer.parseInt(hoursString[0]) ;
        int min = Integer.parseInt(hoursString[1]);
        if (min<0 || min >59){
            throw new InexistentDateException(ui);
        }
        if (hrs <0 || hrs >23){
            throw new InexistentDateException(ui);
        }
        switch( month){
            case 0: case 2: case 4: case 6: case 7: case 9: case 11: // month with 31 days : 11 for december
                if (day > 31 || day <0) {
                    throw new InexistentDateException(ui);
                }
                break;
            case 3 : case 5: case 8: case 10: // month with 31 days
                if (day > 30 || day <0) {
                    throw new InexistentDateException(ui);
                }
                break;
            case 1 : // February
                // second part : no leap year and day==29
                if ((day >29 || day < 0) || ((!((year % 4 ==0 && year % 100 != 0) || year % 400 == 0))&& day==29) ){
                    throw new InexistentDateException(ui);
                }
                break;
            default:
                throw new InexistentDateException(ui);
        }
        GregorianCalendar d = new GregorianCalendar(year,month,day,hrs,min);
        return new Date(d);
    }
}
