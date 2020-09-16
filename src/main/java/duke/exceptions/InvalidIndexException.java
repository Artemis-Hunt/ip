package duke.exceptions;

public class InvalidIndexException extends Exception {
    public InvalidIndexException(int index) {
        super("Invalid index " + index + " provided!");
    }
    public InvalidIndexException(String input) {
        super("\"" + input + "\" is not a number!");
    }
}
