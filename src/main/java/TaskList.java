import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    public Task get(int i ){
        return this.tasks.get(i);
    }

    public void add(Task t ){
        this.tasks.add(t);
    }

    public int size(){
        return this.tasks.size();
    }

    public Task remove( int i){
        return this.tasks.remove(i);
    }

    /*
    return the String of display of one element of list of tasks
     */
    public String displayOneElementList(int index){
        Task t = this.tasks.get(index);
        String result = "\t "+ (index+1) + ". " + t.getTag() +t.getMark() + " " +t.getTask();
        if (t instanceof DeadlinesTask){
            result +=  " by:" + ((DeadlinesTask) t).getDeadlines() + "\n";
        }
        else if ( t instanceof EventsTask){
            result +=  " at:" +
                    ((EventsTask) t).getDateFirst() + " - " + ((EventsTask) t).getDateSecond() + "\n";
        }
        else {
            result += "\n";
        }
        return result;
    }
}
