package duke.tasktypes;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 * General task type with date and time - not allowed to initialize
 */
public abstract class TaskWithDate extends Task{
    protected String dateString;
    protected LocalDate date;
    protected LocalTime time;

    public TaskWithDate(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.dateString = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
        this.date = date;
        this.time = time;
    }

    public String getDateAndTime() {
        return date + " " + time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dateString + " " + time + ")";
    }
}
