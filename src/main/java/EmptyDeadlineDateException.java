public class EmptyDeadlineDateException extends DukeException {
    public EmptyDeadlineDateException(){
    }

    public void print(){
        Duke.display("\t emptyDeadlineDateException:\n\t\t ☹ OOPS!!! Please enter a deadline for the task");
    }
}
