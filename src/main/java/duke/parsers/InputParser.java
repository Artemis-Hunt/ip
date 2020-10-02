package duke.parsers;

import duke.definitions.*;
import duke.exceptions.EmptyContentException;
import duke.exceptions.InvalidParamArgument;

import java.util.HashMap;

/**
 * Helper class to parse raw inputs into a commandPacket.
 */
public final class InputParser {
    private String input;

    public InputParser(String input) {
        this.input = input;
    }

    /**
     * Returns a command packet with the parsed input.
     * Example input: deadline do homework /by tomorrow /note skip page 70
     * commandString: "deadline"
     * CommandPacket created:
     * {
     *  commandType: ADD_DEADLINE
     *  commandContent: "do homework"
     *  params: HashMap<String, String>
     *  {
     *   "by": "tomorrow"
     *   "note": "skip page 70"
     *  }
     * }
     *
     * @return commandPacket
     * @throws InvalidParamArgument if any "/" params are invalid e.g. provided with a blank string as argument or provided twice
     * @throws EmptyContentException if the command expects some content (e.g. task name or task index) but none is provided
     */
    public CommandPacket parseInput() throws InvalidParamArgument, EmptyContentException {
        String commandContent = "";
        String restOfCommand;
        CommandPacket packet;
        HashMap<String, String> params = new HashMap<>();

        boolean paramsExist = false;
        boolean commandContentExist = false;

        //Split into [command, rest of input]
        String[] buffer = input.split(" ", 2);
        String commandString = buffer[0];
        //Check for existence of command title
        commandContentExist = buffer.length > 1 && !buffer[1].startsWith("/");

        /*
        * Start of input parsing:
        * parse input into command, commandContent, and params, if any
        */

        if(commandContentExist) {
            restOfCommand = buffer[1];
            buffer = restOfCommand.split(Constants.PARAM_SEPARATOR, 2);
            commandContent = buffer[0];
            paramsExist = buffer.length > 1;
        }

        if(paramsExist) {
            String paramSubstring = buffer[1];
            params = new ParamsParser(paramSubstring).parseParams();
        }

        switch(commandString) {
        //Commands that do not modify specific entries
        //These commands should not have any commandContent
        //Early return ensures that EmptyContentException is not thrown
        case "list":
            Command commandType = commandContentExist ? Command.INVALID : Command.PRINT_LIST;
            packet = new CommandPacket(commandType, commandContent, params);
            return packet;
        case "clear":
            packet = new CommandPacket(Command.CLEAR_TASKS, commandContent, params);
            return packet;

        //Commands that add an entry
        case "todo":
            packet = new CommandPacket(Command.ADD_TODO, commandContent, params);
            break;
        case "deadline":
            packet = new CommandPacket(Command.ADD_DEADLINE, commandContent, params);
            break;
        case "event":
            packet = new CommandPacket(Command.ADD_EVENT, commandContent, params);
            break;

        //Commands that operate on a specific entry
        case "done":
            packet = new CommandPacket(Command.MARK_AS_DONE, commandContent, params);
            break;
        case "delete":
            packet = new CommandPacket(Command.DELETE_TASK, commandContent, params);
            break;
        case "find":
            packet = new CommandPacket(Command.FIND_TASK, commandContent, params);
            break;

        default:
            packet = new CommandPacket(Command.INVALID, commandContent, params);
            return packet;
        }

        if(commandContent.equals("")) {
            throw new EmptyContentException(commandString);
        }

        return packet;
    }
}
