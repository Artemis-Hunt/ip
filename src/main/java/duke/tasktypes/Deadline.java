package duke.tasktypes;

public class Deadline extends Task{
    protected String by;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        by = deadline;
    }

    public String getBy() {
        return by;
    }
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
