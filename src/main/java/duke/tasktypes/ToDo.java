package duke.tasktypes;

/**
 * Todo task. No date or any other params required
 */
public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
