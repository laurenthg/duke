public class ByeCommand extends Command {
    public  ByeCommand(String user){
        super(user);
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser){
        ui.showBye();
    }

    public boolean isExit(){
        return true;
    }
}
