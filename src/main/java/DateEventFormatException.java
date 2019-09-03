public class DateEventFormatException extends DukeException {
    public DateEventFormatException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t dateEventFormatException:\n\t\t ☹ OOPS!!! Please respect the date format for an event" +
                "\n\t\t\t dd/MM/yyyy HH:mm - dd/MM/yyyy HH:mm");
    }
}
