public class TodoTask extends Task{
    private String tag; // [T]

    public TodoTask(String task){
        super(task);
        this.tag ="[T]";
    }

    public TodoTask(String task, String mark){
        super(task);
        super.setMark(mark);
        this.tag ="[T]";
    }

    public String getTag(){ return this.tag;}
}
