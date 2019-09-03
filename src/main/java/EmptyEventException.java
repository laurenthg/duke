public class EmptyEventException extends DukeException {
    public EmptyEventException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t emptyEventException:\n\t\t ☹ OOPS!!! The description of a event task cannot be empty");
    }
}
