public class InexistentDateException extends DukeException {
    public InexistentDateException(){
    }

    public void print(){
        Duke.display("\t inexistentDateException:\n\t\t ☹ OOPS!!! \n\t\t\t The date doesn't exist" );
    }
}