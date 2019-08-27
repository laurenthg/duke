public class emptyTodoException extends dukeException {
    public emptyTodoException(){
    }

    public void print(){
        Duke.display("\t emptyTodoException:\n\t\t ☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
