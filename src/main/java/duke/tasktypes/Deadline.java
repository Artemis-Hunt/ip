package duke.tasktypes;

import java.time.LocalTime;
import java.time.LocalDate;

public class Deadline extends TaskWithDate{

    public Deadline(String taskName, LocalDate date, LocalTime time) {
        super(taskName, date, time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
