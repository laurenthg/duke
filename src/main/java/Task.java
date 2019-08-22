public class Task {
    private String task;
    private String mark;

    public Task (String task){
        this.task = task;
        this.mark = "[✗]";
    }

    public void taskDone(){
        this.mark = "[✓]";
    }

    public String getTask(){
        return this.task;
    }

    public String getMark(){
        return this.mark;
    }

}
