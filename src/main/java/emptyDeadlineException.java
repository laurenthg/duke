public class emptyDeadlineException extends dukeException{
    public emptyDeadlineException(){
    }

    public void print(){
        Duke.display("\t emptyDeadlineException:\n\t\t ☹ OOPS!!! The description of a deadline task cannot be empty");
    }
}
