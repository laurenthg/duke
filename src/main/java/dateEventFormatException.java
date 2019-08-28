public class dateEventFormatException extends dukeException{
    public dateEventFormatException(){
    }

    public void print(){
        Duke.display("\t dateEventFormatException:\n\t\t ☹ OOPS!!! Please respect the date format for an event" +
                "\n\t\t\t dd/MM/yyyy HH:mm - dd/MM/yyyy HH:mm");
    }
}
