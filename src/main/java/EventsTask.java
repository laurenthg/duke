public class EventsTask extends Task {
    private String tag; // [E]
    private Date dateFirst;
    private Date dateSecond;

    public EventsTask(String task, Date dateFirst, Date dateSecond) {
        super(task);
        this.tag = "[E]";
        this.dateFirst = dateFirst;
        this.dateSecond = dateSecond;
    }

    public EventsTask(String task, String mark, Date dateFirst, Date dateSecond) {
        super(task);
        super.setMark(mark);
        this.tag = "[E]";
        this.dateFirst = dateFirst;
        this.dateSecond =dateSecond;
    }

    public String getTag(){ return this.tag;}

    public Date getDateFirst(){ return this.dateFirst;}
    public Date getDateSecond(){ return this.dateSecond; }
}

