package duke.tasktypes;

/**
 * Event task. Requires a date and time to be provided
 */
public class Event extends Task{

    protected String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
