public class Task {
    private String taskName;
    private boolean isDone;

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

    @Override
    public String toString() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "]" + " " + taskName);
    }

    public String getTaskName() {
        return taskName;
    }
}
