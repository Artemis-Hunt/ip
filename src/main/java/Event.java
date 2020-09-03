public class Event extends Task{

    public String startAndEnd;

    public Event(String taskName, String startAndEnd) {
        super(taskName);
        this.startAndEnd = startAndEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startAndEnd + ")";
    }

}
