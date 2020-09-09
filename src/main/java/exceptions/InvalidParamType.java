package exceptions;

public class InvalidParamType extends Exception {

    public InvalidParamType(String paramType) {
        super("Invalid param type " + paramType);
    }
}
