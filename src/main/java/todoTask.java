public class todoTask extends Task{
    private String tag; // [T]

    public todoTask(String task){
        super(task);
        this.tag ="[T]";
    }

    public todoTask(String task, String mark){
        super(task);
        super.setMark(mark);
        this.tag ="[T]";
    }

    public String getTag(){ return this.tag;}
}
