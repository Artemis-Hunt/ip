package handlers;

import definitions.Constants;

import java.util.HashMap;

public class ParamsParser {
    protected String paramString;

    public ParamsParser(String paramString) {
        this.paramString = paramString;
    }

    public HashMap<String, String> parseParams() {
        HashMap<String, String> params = new HashMap<String, String>();

        int paramTypeStartIndex = paramString.indexOf(Constants.PARAM_SEPARATOR);
        String paramSubstring = paramString.substring(paramTypeStartIndex + Constants.LENGTH_OF_PARAM_SEPARATOR);
        boolean shouldContinueParsing = paramTypeStartIndex > -1;

        while(shouldContinueParsing) {
            int paramTypeEndIndex = paramSubstring.indexOf(" ");
            int paramArgumentStartIndex = paramTypeEndIndex + Constants.LENGTH_OF_SPACE;
            int nextParamStartIndex = paramSubstring.indexOf(Constants.PARAM_SEPARATOR);
            boolean nextParamExist = nextParamStartIndex != -1;

            String paramType = paramSubstring.substring(0, paramTypeEndIndex);
            String paramArgument;

            if(nextParamExist) {
                paramArgument = paramSubstring.substring(paramArgumentStartIndex, nextParamStartIndex);
                paramSubstring = paramSubstring.substring(nextParamStartIndex + Constants.LENGTH_OF_PARAM_SEPARATOR);
            }
            else {
                paramArgument = paramSubstring.substring(paramArgumentStartIndex);
                shouldContinueParsing = false;
            }

            //todo: Error handling - what if no argument provided for param
            params.put(paramType, paramArgument);
        }
        return params;
    }
}
