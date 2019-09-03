public class InexistentTaskException extends DukeException {
    public InexistentTaskException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t inexistentTaskException:\n\t\t ☹ OOPS!!! The task doesn't exist");
    }
}
