public abstract class Command {
    protected String user;

    public Command(String user ){
        this.user=user;
    }
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage, Parser parser) throws DukeException;
}
