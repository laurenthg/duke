import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke(String[] filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        String file; // file name
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
        while (!isExit){
            try {
                String user = this.ui.readCommand();
                Command c = parser.parse(user);
                c.execute(tasks, ui, storage,parser); // parser is needed beacause stringToDate is in Parser class
                isExit = c.isExit();
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
