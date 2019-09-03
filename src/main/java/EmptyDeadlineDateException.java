public class EmptyDeadlineDateException extends DukeException {
    public EmptyDeadlineDateException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t emptyDeadlineDateException:\n\t\t ☹ OOPS!!! Please enter a deadline for the task");
    }
}
