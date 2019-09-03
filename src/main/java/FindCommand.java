public class FindCommand extends Command{

    public FindCommand(String user){
        super(user);
    }
    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui , Storage storage, Parser parser){
        String find = user.substring(5);
        String result = "";
        for ( int i = 0 ; i< tasks.size() ; i++){
            if ( tasks.get(i).getTask().contains(find)){
                result += tasks.displayOneElementList(i);
            }
        }
        if ( result.isEmpty()){
            ui.display("\t There is no matching tasks in your list");
        }
        else {
            ui.display("\t Here are the matching tasks in your list: \n" + result );
        }
    }

}
