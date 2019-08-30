public class EmptyDeadlineException extends DukeException {
    public EmptyDeadlineException(){
    }

    public void print(){
        Duke.display("\t emptyDeadlineException:\n\t\t ☹ OOPS!!! The description of a deadline task cannot be empty");
    }
}
