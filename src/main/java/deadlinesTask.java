public class deadlinesTask extends Task{
    private String tag; // [D]
    private String deadlines;

    public deadlinesTask(String task, String deadlines){
        super(task);
        this.tag ="[D]";
        this.deadlines = deadlines;
    }

    public deadlinesTask(String task, String mark, String deadlines){
        super(task);
        super.setMark(mark);
        this.tag ="[D]";
        this.deadlines = deadlines;
    }

    public String getTag(){ return this.tag;}

    public String getDeadlines(){ return this.deadlines;}
}

