package duke.tasktypes;

public class Event extends Task{

    protected String startAndEnd;

    public Event(String taskName, String startAndEnd) {
        super(taskName);
        this.startAndEnd = startAndEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startAndEnd + ")";
    }

}
