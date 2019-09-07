/**
 * Represent a exception when the task to mark done or to delete does not exist.
 */
public class InexistentTaskException extends DukeException {
    /**
     * Constructor of InexistentTaskException.
     * @param ui Ui which deals with the interactions with the user.
     */
    public InexistentTaskException(Ui ui){
        super(ui);
    }

    /**
     * Tell the user that the tasks given does not exist.
     */
    public void print(){
        super.ui.display("\t inexistentTaskException:\n\t\t ☹ OOPS!!! The task doesn't exist");
    }
}
