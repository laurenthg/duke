import java.io.IOException;

public class TodoCommand extends Command {
    public  TodoCommand(String user){
        super(user);
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser) throws EmptyTodoException{
        if (user.substring(4).isBlank()) {
            throw new EmptyTodoException(ui);
        }
        else {
            tasks.add(new TodoTask(user.substring(4).trim()));
            TodoTask newTask = (TodoTask) tasks.get(tasks.size() - 1);
            try {
                storage.getAppendWrite().write(tasks.size() + "//" + newTask.getTag() + "//" +
                        newTask.getMark() + "//" + newTask.getTask()+"\n");
            }
            catch (IOException e){
                ui.display("\t IOException:\n\t\t error when writing a todoTask to file");
            }
            ui.display("\t Got it. I've added this task:\n\t   "
                    + newTask.getTag() + newTask.getMark() + newTask.getTask() +
                    "\n\t Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public boolean isExit(){
        return false;
    }
}
