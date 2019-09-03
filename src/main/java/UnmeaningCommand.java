public class UnmeaningCommand extends Command {
    public  UnmeaningCommand(String user){
        super(user);
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser) throws UnmeaningException{
        throw new UnmeaningException(ui);
    }

    public boolean isExit(){
        return false;
    }
}
