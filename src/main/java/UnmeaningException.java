public class UnmeaningException extends DukeException {
    public UnmeaningException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t unmeaningException:\n\t\t OOPS!!! I'm sorry, but I don't know what that means :-(\"");
    }
}
