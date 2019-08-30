public class EmptyTodoException extends DukeException {
    public EmptyTodoException(){
    }

    public void print(){
        Duke.display("\t emptyTodoException:\n\t\t ☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
