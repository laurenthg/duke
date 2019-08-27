public class emptyDeadlineDateException extends dukeException {
    public emptyDeadlineDateException(){
    }

    public void print(){
        Duke.display("\t emptyDeadlineDateException:\n\t\t ☹ OOPS!!! Please enter a deadline for the task");
    }
}
