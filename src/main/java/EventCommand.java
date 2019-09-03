import java.io.IOException;

public class EventCommand extends Command {
    public  EventCommand(String user){
        super(user);
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser)
            throws EmptyEventDateException , EmptyEventException , DateEventFormatException, InexistentDateException{
        String[] taskDescription = user.substring(5).split("/at");
        if (taskDescription[0].isBlank()) {
            throw new EmptyEventException(ui);
        }
        else if (taskDescription.length == 1) { // no /by in input
            throw new EmptyEventDateException(ui);
        }
        else {
            String description = taskDescription[0].trim();
            String periodString = taskDescription[1].trim();
            //date format used: dd/MM/yyyy HH:mm - dd/MM/yyyy HH:mm
            String regex ="[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9] " +
                    "- [0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9]";
            if (!periodString.matches(regex)) {
                throw new DateEventFormatException(ui);
            }
            else {
                String[] dateString = periodString.split(" - ");
                Date dateFirst = parser.stringToDate(dateString[0],ui);
                Date dateSecond = parser.stringToDate(dateString[1],ui);
                tasks.add(new EventsTask(description, dateFirst,dateSecond));
                EventsTask newTask = (EventsTask) tasks.get(tasks.size() - 1);
                try {
                    storage.getAppendWrite().write(tasks.size() + "//" + newTask.getTag() + "//" +
                            newTask.getMark() + "//" + newTask.getTask() + "//"+
                            " at:" + newTask.getDateFirst() + "//" + newTask.getDateSecond()+"\n");
                }
                catch (IOException e){
                    ui.display("\t IOException:\n\t\t error when writing a event to file");
                }
                ui.display("\t Got it. I've added this task:\n\t   "
                        + newTask.getTag() + newTask.getMark() + newTask.getTask() + " at:"
                        + newTask.getDateFirst() + " - " + newTask.getDateSecond() +
                        "\n\t Now you have " + tasks.size() + " tasks in the list.");
            }
        }
    }

    public boolean isExit(){
        return false;
    }
}
