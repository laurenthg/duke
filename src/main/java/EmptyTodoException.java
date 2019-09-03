public class EmptyTodoException extends DukeException {
    public EmptyTodoException(Ui ui){
        super(ui);
    }

    public void print(){
        super.ui.display("\t emptyTodoException:\n\t\t ☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
