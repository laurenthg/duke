public class DeadlinesTask extends Task{
    private String tag; // [D]
    private Date deadlines;

    public DeadlinesTask(String task, Date deadlines){
        super(task);
        this.tag ="[D]";
        this.deadlines = deadlines;
    }

    public DeadlinesTask(String task, String mark, Date deadlines){
        super(task);
        super.setMark(mark);
        this.tag ="[D]";
        this.deadlines = deadlines;
    }

    public String getTag(){ return this.tag;}

    public Date getDeadlines(){ return this.deadlines;}
}

