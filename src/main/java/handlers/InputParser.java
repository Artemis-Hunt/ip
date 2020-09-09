package handlers;

import definitions.*;

import java.util.HashMap;

public final class InputParser {
    private String input;

    public InputParser(String input) {
        this.input = input;
    }

    public CommandPacket parseInput() {
        String commandString;
        String commandTitle = "";

        //Find indexes of where command ends and where params start, if any
        int indexOfFirstSpace = input.indexOf(" ");
        boolean commandTitleExist = (indexOfFirstSpace != -1);
        int indexOfParamStart = input.indexOf(" /");
        boolean paramsExist = (indexOfParamStart != -1);

        //Parse input into command, commandTitle, and params, if any
        if(commandTitleExist) {
            commandString = input.substring(0, indexOfFirstSpace);
            commandTitle = extractCommandTitle(input, indexOfFirstSpace, paramsExist, indexOfParamStart);
        }
        else {
            commandString = input;
        }

        HashMap<String, String> params = new ParamsParser(input).parseParams();

        CommandPacket packet;

        switch(commandString) {
        case "list":
            packet = new CommandPacket(Command.PRINT_LIST, commandTitle, params);
            break;
        case "todo":
            packet = new CommandPacket(Command.ADD_TODO, commandTitle, params);
            break;
        case "deadline":
            packet = new CommandPacket(Command.ADD_DEADLINE, commandTitle, params);
            break;
        case "event":
            packet = new CommandPacket(Command.ADD_EVENT, commandTitle, params);
            break;
        case "done":
            packet = new CommandPacket(Command.MARK_AS_DONE, commandTitle, params);
            break;
        default:
            packet = new CommandPacket(Command.INVALID, commandTitle, params);
        }
        return packet;
    }

    private String extractCommandTitle(String input, int indexOfFirstSpace, boolean paramsExist, int indexOfParamStart) {
        if(paramsExist) {
            return input.substring(indexOfFirstSpace + Constants.LENGTH_OF_SPACE, indexOfParamStart);
        }
        else {
            return input.substring(indexOfFirstSpace + Constants.LENGTH_OF_SPACE);
        }
    }
}
