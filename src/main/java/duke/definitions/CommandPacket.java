package duke.definitions;

import java.util.HashMap;

/**
 * Defines a CommandPacket. Must be initialized with all 3 properties, though they can be empty.
 */
public class CommandPacket {
    public Command commandType;
    public String commandContent;
    public HashMap<String, String> params;

    public CommandPacket(Command commandType, String commandContent, HashMap<String, String> params) {
        this.commandType = commandType;
        this.commandContent = commandContent;
        this.params = params;
    }

    @Override
    public String toString() {
        String stringToPrint = String.format("Definitions.Command: %s, command title: %s%nParams:%n", commandType, commandContent);
        for(String key: params.keySet()) {
            stringToPrint = stringToPrint + String.format("%s: %s%n", key, params.get(key));
        }
        return stringToPrint;
    }
}