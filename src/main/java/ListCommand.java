public class ListCommand extends Command {

    public  ListCommand(String user){
        super(user);
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser){
        if (tasks.size() != 0) {
            ui.displayList(tasks);
        }
        else {
            ui.display("\t There is any task yet ");
        }
    }

    public boolean isExit(){
        return false;
    }
}
