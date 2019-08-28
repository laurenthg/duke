abstract class Task {
    private String task;
    private String mark;

    protected Task (String task){
        this.task = task;
        this.mark = "[✗]";
    }
    protected void setMark(String mark){
        this.mark= mark;
    }

    protected void taskDone(){
        this.mark = "[✓]";
    }

    protected String getTask(){
        return this.task;
    }

    protected String getMark(){
        return this.mark;
    }

    public abstract String getTag();
}
