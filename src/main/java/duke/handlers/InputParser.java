package duke.handlers;

import duke.definitions.*;
import duke.exceptions.EmptyContentException;
import duke.exceptions.InvalidParamArgument;

import java.util.HashMap;

public final class InputParser {
    private String input;

    public InputParser(String input) {
        this.input = input;
    }

    public CommandPacket parseInput() throws InvalidParamArgument, EmptyContentException {
        String commandContent = "";
        String restOfCommand;
        CommandPacket packet;
        HashMap<String, String> params = new HashMap<>();

        boolean paramsExist = false;
        boolean commandContentExist = false;

        String[] buffer = input.split(" ", 2);
        String commandString = buffer[0];
        commandContentExist = buffer.length > 1 && !buffer[1].startsWith("/");

        /*
        * Start of input parsing:
        * parse input into command, commandContent, and params, if any
        */

        if(commandContentExist) {
            restOfCommand = buffer[1];
            buffer = restOfCommand.split("/", 2);
            commandContent = buffer[0];
            paramsExist = buffer.length > 1;

        }

        if(paramsExist) {
            String paramSubstring = buffer[1];
            params = new ParamsParser(paramSubstring).parseParams();
        }

        //Returns packet for commands with content e.g. todo
        switch(commandString) {
        case "list":
            Command commandType = commandContentExist ? Command.INVALID : Command.PRINT_LIST;
            packet = new CommandPacket(commandType, commandContent, params);
            return packet;
        case "todo":
            packet = new CommandPacket(Command.ADD_TODO, commandContent, params);
            break;
        case "deadline":
            packet = new CommandPacket(Command.ADD_DEADLINE, commandContent, params);
            break;
        case "event":
            packet = new CommandPacket(Command.ADD_EVENT, commandContent, params);
            break;
        case "done":
            packet = new CommandPacket(Command.MARK_AS_DONE, commandContent, params);
            break;
        default:
            packet = new CommandPacket(Command.INVALID, commandContent, params);
        }

        if(commandContent.equals("")) {
            throw new EmptyContentException(commandString);
        }

        return packet;
    }

    private String extractCommandContent(String restOfCommand, boolean paramsExist, int indexOfParamStart) {
        if(paramsExist) {
            return restOfCommand.substring(0, indexOfParamStart);
        }

        return restOfCommand;
    }

    private Command handleCommandWithoutContent(String commandString) {
        if(!commandString.equals(input)) {
            return Command.INVALID;
        }
        switch(commandString) {
        case "list": return Command.PRINT_LIST;
        default: return Command.INVALID;
        }
    }
}
