package exceptions;

public class InvalidParamArgument extends Exception {

    public InvalidParamArgument() {
        super("No param argument provided!");
    }
    public InvalidParamArgument(String paramType, String paramArgument) {
        super("Invalid param argument " + paramArgument + " for param type "
                + paramType);
    }
}