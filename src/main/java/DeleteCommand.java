public class DeleteCommand extends Command {
    public  DeleteCommand(String user){
        super(user);
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser)throws InexistentTaskException{
        int index = Integer.parseInt(user.substring(7)) - 1;
        if (index > tasks.size() - 1 || index < 0) {
            throw new InexistentTaskException(ui);
        }
        else { // the tasks exist
            Task removedTask = tasks.remove(index);
            String text = storage.getDeleteTaskString(removedTask,index,ui,tasks.size());
            //rewriter of file by replacing the whole file
            storage.rewriteFile(text,ui);
            ui.display("\t Noted. I've removed this task: \n" +
                    "\t\t "+removedTask.getTag() + removedTask.getMark() + " " + removedTask.getTask()+
                    "\n\t Now you have "+ tasks.size() +" tasks in the list");
        }
    }

    public boolean isExit(){
        return false;
    }
}
