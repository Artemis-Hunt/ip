package duke.exceptions;

public class EmptyContentException extends Exception {

    public EmptyContentException(String command) {
        super("The title of a " + command + " cannot be empty");
    }
}