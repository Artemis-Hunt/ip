package duke.exceptions;

public class EmptyContentException extends Exception {

    public EmptyContentException(String command) {
        super(command + " cannot be called by itself!");
    }
}