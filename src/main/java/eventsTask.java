public class eventsTask extends Task {
    private String tag; // [E]
    private String period;

    public eventsTask(String task, String period) {
        super(task);
        this.tag = "[E]";
        this.period = period;
    }

    public String getTag(){ return this.tag;}

    public String getPeriod(){ return this.period;}
}

