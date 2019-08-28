public class eventsTask extends Task {
    private String tag; // [E]
    private date dateFirst;
    private date dateSecond;

    public eventsTask(String task, date dateFirst,date dateSecond) {
        super(task);
        this.tag = "[E]";
        this.dateFirst = dateFirst;
        this.dateSecond = dateSecond;
    }

    public eventsTask(String task, String mark, date dateFirst, date dateSecond) {
        super(task);
        super.setMark(mark);
        this.tag = "[E]";
        this.dateFirst = dateFirst;
        this.dateSecond =dateSecond;
    }

    public String getTag(){ return this.tag;}

    public date getDateFirst(){ return this.dateFirst;}
    public date getDateSecond(){ return this.dateSecond; }
}

