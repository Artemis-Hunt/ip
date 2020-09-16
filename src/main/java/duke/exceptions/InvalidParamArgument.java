package duke.exceptions;

public class InvalidParamArgument extends Exception {

    public InvalidParamArgument(String paramType) {
        super("No param argument provided for param /" + paramType);
    }

    public InvalidParamArgument(String paramType, boolean exist) {
        super("Param /" + paramType + " specified more than once!");
    }

    public InvalidParamArgument(String paramType, String paramArgument) {
        super("Invalid param argument " + paramArgument + " for param /"
                + paramType);
    }
}