package duke.tasktypes;

public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean getDone() { return isDone; }

    public String getDoneStatusSymbol() {
        return (isDone ? "\u2713" : "\u2718");
    }
    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return ("[" + (isDone ? "y" : "n") + "]" + " " + taskName);
    }

}
