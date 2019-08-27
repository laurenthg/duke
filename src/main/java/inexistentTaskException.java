public class inexistentTaskException extends dukeException {
    public inexistentTaskException(){
    }

    public void print(){
        Duke.display("\t inexistentTaskException:\n\t\t ☹ OOPS!!! The task doesn't exist");
    }
}
