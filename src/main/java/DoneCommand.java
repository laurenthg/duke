public class DoneCommand extends Command {
    public  DoneCommand(String user){
        super(user);
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser) throws InexistentTaskException{
        int index = Integer.parseInt(user.substring(5)) - 1;
        if (index > tasks.size() - 1 || index < 0) {
            throw new InexistentTaskException(ui);
        }
        else { // to change the mark, the whole file is rewritten ( probably a better way to do it)
            tasks.get(index).taskDone();
            //get the String with the index task marked done
            String text = storage.getDoneString(tasks,index,ui);
            //  rewriter of file by replacing the whole file
            storage.rewriteFile(text,ui);
            ui.display("\t Nice! I've marked this task as done:\n\t " + tasks.get(index).getTag() +
                    tasks.get(index).getMark() + " " + tasks.get(index).getTask());
        }
    }

    public boolean isExit(){
        return false;
    }
}
