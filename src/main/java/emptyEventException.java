public class emptyEventException extends dukeException{
    public emptyEventException(){
    }

    public void print(){
        Duke.display("\t emptyEventException:\n\t\t ☹ OOPS!!! The description of a event task cannot be empty");
    }
}
