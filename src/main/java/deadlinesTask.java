public class deadlinesTask extends Task{
    private String tag; // [D]
    private date deadlines;

    public deadlinesTask(String task, date deadlines){
        super(task);
        this.tag ="[D]";
        this.deadlines = deadlines;
    }

    public deadlinesTask(String task, String mark, date deadlines){
        super(task);
        super.setMark(mark);
        this.tag ="[D]";
        this.deadlines = deadlines;
    }

    public String getTag(){ return this.tag;}

    public date getDeadlines(){ return this.deadlines;}
}

