package tasktypes;

public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public String getDoneStatusSymbol() {
        return (isDone ? "\u2713" : "\u2718");
    }
    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "]" + " " + taskName);
    }

}
