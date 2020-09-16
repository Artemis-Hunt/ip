package duke.handlers;

import duke.definitions.Constants;
import duke.exceptions.InvalidParamArgument;

import java.util.HashMap;

public class ParamsParser {
    protected String paramSubstring;

    public ParamsParser(String paramSubstring) {
        this.paramSubstring = paramSubstring;
    }

    /**
     * Example input: deadline do homework /by tomorrow /note skip page 70
     * --First iteration--
     * paramSubstring: "by tomorrow /note skip page 70"
     * paramType: "by"
     * paramArgument: "tomorrow"
     * --Next iteration--
     * paramSubstring: "note skip page 70"
     * paramType: "note"
     * paramArgument: "skip page 70"
     * etc.
     */
    public HashMap<String, String> parseParams() throws InvalidParamArgument {
        HashMap<String, String> params = new HashMap<>();
        String[] buffer;
        boolean shouldContinueParsing = true;

        do {
            //Separate into [paramType, rest of string]
            buffer = paramSubstring.split(" ", 2);
            String paramType = buffer[0];
            boolean paramArgumentExist = buffer.length > 1;
            if(!paramArgumentExist) {
                throw new InvalidParamArgument(paramType);
            }

            paramSubstring = buffer[1];

            //Separate into [paramArgument, rest of string]
            buffer = paramSubstring.split(Constants.PARAM_SEPARATOR, 2);
            String paramArgument = buffer[0];
            boolean nextParamExist = buffer.length > 1;

            if(nextParamExist) {
                paramSubstring = buffer[1];
            }
            else {
                shouldContinueParsing = false;
            }

            //If param already exists
            if(params.containsKey(paramType)) {
                throw new InvalidParamArgument(paramType, true);
            }

            params.put(paramType, paramArgument);
        } while(shouldContinueParsing);
        return params;
    }
}
