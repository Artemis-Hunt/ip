package duke.handlers;

import duke.definitions.Constants;
import duke.exceptions.InvalidParamArgument;

import java.util.HashMap;

public class ParamsParser {
    protected String paramSubstring;

    public ParamsParser(String paramSubstring) {
        this.paramSubstring = paramSubstring;
    }

    public HashMap<String, String> parseParams() throws InvalidParamArgument {
        HashMap<String, String> params = new HashMap<>();
        String[] buffer;
        boolean shouldContinueParsing = true;

        do {
            buffer = paramSubstring.split(" ", 2);
            String paramType = buffer[0];
            boolean paramArgumentExist = buffer.length > 1;
            if(!paramArgumentExist) {
                throw new InvalidParamArgument();
            }

            paramSubstring = buffer[1];

            buffer = paramSubstring.split(Constants.PARAM_SEPARATOR, 2);
            String paramArgument = buffer[0];
            boolean nextParamExist = buffer.length > 1;

            if(nextParamExist) {
                paramSubstring = buffer[1];
            }
            else {
                shouldContinueParsing = false;
            }

            //todo: Error handling - what if no argument provided for param
            params.put(paramType, paramArgument);
        } while(shouldContinueParsing);
        return params;
    }
}
