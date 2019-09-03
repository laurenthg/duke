public class EmptyEventDateException extends DukeException {
    public EmptyEventDateException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t emptyEventDateException:\n\t\t ☹ OOPS!!! Please enter a period for the event task");
    }
}
