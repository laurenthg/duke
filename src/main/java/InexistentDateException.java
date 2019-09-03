public class InexistentDateException extends DukeException {
    public InexistentDateException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t inexistentDateException:\n\t\t ☹ OOPS!!! \n\t\t\t The date doesn't exist" );
    }
}