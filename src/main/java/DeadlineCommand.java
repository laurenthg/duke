import java.io.IOException;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String user){
        super(user);
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser)
            throws EmptyDeadlineDateException, EmptyDeadlineException, DateFormatException,InexistentDateException{
        String[] taskDescription = user.substring(8).split("/by");
        if (taskDescription[0].isBlank()) {
            throw new EmptyDeadlineException(ui);
        }
        else if (taskDescription.length == 1) { // no /by in input
            throw new EmptyDeadlineDateException(ui);
        }
        else {
            String description = taskDescription[0].trim();
            String deadlineString = taskDescription[1].trim();
            //date format used: dd/MM/yyyy HH:mm
            String regex ="[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9]";
            if (!deadlineString.matches(regex)) {
                throw new DateFormatException(ui);
            }
            else {
                Date deadline = parser.stringToDate(deadlineString,ui);
                tasks.add(new DeadlinesTask(description, deadline));
                DeadlinesTask newTask = (DeadlinesTask) tasks.get(tasks.size() - 1);
                try {
                    storage.getAppendWrite().write(tasks.size() + "//" + newTask.getTag() + "//" +
                            newTask.getMark() + "//" + newTask.getTask() + "//" + " by:"
                            +newTask.getDeadlines() + "\n");
                } catch (IOException e) {
                    ui.display("\t IOException:\n\t\t error when writing a deadline to file");
                }
                ui.display("\t Got it. I've added this task:\n\t   "
                        + newTask.getTag() + newTask.getMark() + newTask.getTask() + " by:"
                        + newTask.getDeadlines() +
                        "\n\t Now you have " + tasks.size() + " tasks in the list.");
            }
        }
    }

    public boolean isExit(){
        return false;
    }
}
