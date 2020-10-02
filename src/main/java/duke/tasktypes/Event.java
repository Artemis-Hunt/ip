package duke.tasktypes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends TaskWithDate{

    public Event(String taskName, LocalDate date, LocalTime time) {
        super(taskName, date, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

}
