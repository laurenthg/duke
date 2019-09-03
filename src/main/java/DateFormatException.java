public class DateFormatException extends DukeException {
    public DateFormatException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t dateFormatException:\n\t\t ☹ OOPS!!! Please respect the date format\n\t\t\t " +
                "dd/MM/yyyy HH:mm");
    }
}
