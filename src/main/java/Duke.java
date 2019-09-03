import jdk.jfr.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke(String[] filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        String file  =""; // file name
        if (filePath.length != 0 ){ // test file in case of test
            file = filePath[0];
        }
        else{ // no test file
            file = System.getProperty("user.dir")+ "/data/duke.txt";
        }
        this.storage = new Storage(file);
        try{
            this.tasks = new TaskList(storage.load(parser, ui)); // Use of ArrayList (A-Collections) to store tasks
        }
        catch (DukeException e){
            e.print();
        }
        catch (IOException e){
            this.ui.display("\t IOException: \n\t\t error when readFile for initialization of tasks list");
        }
    }


    public void run() {
        this.ui.showWelcome();
        this.storage.getNewAppendWrite(this.storage.getFilePath(),ui);
        boolean isExit = false;
        //while (!user.equals("bye")){
        while (!isExit){
            try {
                String user = this.ui.readCommand();
                if (user.equals("list")) {
                    ListCommand l = new ListCommand(user);
                    l.execute(tasks,ui,storage,parser);
                }
                else if (user.matches("find (.*)")) {
                    FindCommand f = new FindCommand(user);
                    f.execute(tasks,ui,storage,parser);
                }
                else if (user.matches("done \\d+")) {// if it is done and a number of task
                    DoneCommand d = new DoneCommand(user);
                    d.execute(tasks,ui,storage,parser);
                }
                else if (user.matches("delete \\d+")) {// if it is done and a number of task
                    DeleteCommand de = new DeleteCommand(user);
                    de.execute(tasks,ui,storage,parser);
                }
                else if (user.matches("todo(.*)")) {
                    TodoCommand todo = new TodoCommand(user);
                    todo.execute(tasks,ui,storage,parser);
                }
                else if (user.matches("deadline (.*)")) {
                    DeadlineCommand dead = new DeadlineCommand(user);
                    dead.execute(tasks,ui,storage,parser);
                }
                else if (user.matches("event (.*)")) {
                    EventCommand event = new EventCommand(user);
                    event.execute(tasks,ui,storage,parser);
                }
                else if (user.matches(("bye"))){
                    ByeCommand bye = new ByeCommand(user);
                    bye.execute(tasks,ui,storage,parser);
                    isExit = true;
                }
                else {
                    UnmeaningCommand unmeaningCommand = new UnmeaningCommand(user);
                    unmeaningCommand.execute(tasks,ui,storage,parser);
                }
            }
            catch (DukeException e){ // catch one of subclass of dukeException and print the right message
                e.print();
            }
        }
        this.storage.getAppendWrite().freeBufferedWriter();  // close() bufferedwriter
    }

    public static void main(String[] args) {
        new Duke(args).run();
    }
}
