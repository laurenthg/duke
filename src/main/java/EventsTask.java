/**
 * Represents a Event Task.
 */
public class EventsTask extends Task {
    private String tag; // [E]
    private Date dateFirst;
    private Date dateSecond;

    /**
     * Constructor of EventsTask. The task is not done by default.
     * @param task tasks list.
     * @param dateFirst the start date of the period of the event.
     * @param dateSecond the end date of the period of the event.
     */
    public EventsTask(String task, Date dateFirst, Date dateSecond) {
        super(task);
        this.tag = "[E]";
        this.dateFirst = dateFirst;
        this.dateSecond = dateSecond;
    }

    /**
     * Constructor of EventsTask. The task could be done or not depending on the parameter given.
     * @param task tasks list.
     * @param mark represent if the task is done or not.
     * @param dateFirst the start date of the period of the event.
     * @param dateSecond the end date of the period of the event.
     */
    public EventsTask(String task, String mark, Date dateFirst, Date dateSecond) {
        super(task);
        super.setMark(mark);
        this.tag = "[E]";
        this.dateFirst = dateFirst;
        this.dateSecond =dateSecond;
    }

    /**
     * Getter of Tag ( [T] ).
     * @return String : [T]
     */
    public String getTag(){ return this.tag;}

    /**
     * Getter of start date.
     * @return the start date of the task.
     */
    public Date getDateFirst(){ return this.dateFirst;}

    /**
     * Getter of end date.
     * @return the end date of the task.
     */
    public Date getDateSecond(){ return this.dateSecond; }
}

