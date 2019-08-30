public class EmptyEventException extends DukeException {
    public EmptyEventException(){
    }

    public void print(){
        Duke.display("\t emptyEventException:\n\t\t ☹ OOPS!!! The description of a event task cannot be empty");
    }
}
