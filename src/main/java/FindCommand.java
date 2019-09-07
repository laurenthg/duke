/**
 * Represents a Find Command.
 * Allow to find a specific task from the task list.
 */
public class FindCommand extends Command{
    /**
     * Constructor of FindCommand.
     * @param user String which represent the input string of the user.
     */
    public FindCommand(String user){
        super(user);
    }

    /**
     * Returns a boolean false as it is a find command.
     * @return a boolean false.
     */
    public boolean isExit(){
        return false;
    }

    /**
     * Allow to find tasks from the task list.
     * @param tasks TaskList which is the list of task.
     * @param ui Ui which deals with the interactions with the user.
     * @param storage Storage which deals with loading tasks from the file and saving tasks in the file.
     * @param parser Parser which deals with making sense of the user command.
     */
    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser){
        String find = user.substring(5);
        String result = "";
        for ( int i = 0 ; i< tasks.size() ; i++){
            if ( tasks.get(i).getTask().contains(find)){
                result += tasks.displayOneElementList(i);
            }
        }
        if ( result.isEmpty()){
            ui.display("\t There is no matching tasks in your list");
        }
        else {
            ui.display("\t Here are the matching tasks in your list: \n" + result );
        }
    }

}
