public class InexistentTaskException extends DukeException {
    public InexistentTaskException(){
    }

    public void print(){
        Duke.display("\t inexistentTaskException:\n\t\t ☹ OOPS!!! The task doesn't exist");
    }
}
