public class Deadline extends Task{
    public String by;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        by = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
