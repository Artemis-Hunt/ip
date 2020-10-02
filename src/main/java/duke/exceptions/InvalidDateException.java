package duke.exceptions;

public class InvalidDateException extends Exception {

    public InvalidDateException(String dateString) { super("Invalid date " + dateString + " entered!"); }
}
