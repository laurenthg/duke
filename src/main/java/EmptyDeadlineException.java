public class EmptyDeadlineException extends DukeException {
    public EmptyDeadlineException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t emptyDeadlineException:\n\t\t ☹ OOPS!!! The description of a deadline task cannot be empty");
    }
}
