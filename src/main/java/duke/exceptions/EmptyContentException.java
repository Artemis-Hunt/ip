package duke.exceptions;

public class EmptyContentException extends Exception {

    public EmptyContentException(String command) {
        super(command + " cannot have an empty name/index!");
    }
}