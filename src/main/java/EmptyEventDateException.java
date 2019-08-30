public class EmptyEventDateException extends DukeException {
    public EmptyEventDateException(){
    }

    public void print(){
        Duke.display("\t emptyEventDateException:\n\t\t ☹ OOPS!!! Please enter a period for the event task");
    }
}
