package definitions;

import java.util.HashMap;

public class CommandPacket {
    public Command commandType;
    public String commandTitle;
    public HashMap<String, String> params;

    public CommandPacket(Command commandType, String commandTitle, HashMap<String, String> params) {
        this.commandType = commandType;
        this.commandTitle = commandTitle;
        this.params = params;
    }

    @Override
    public String toString() {
        String stringToPrint = String.format("Definitions.Command: %s, command title: %s%nParams:%n", commandType, commandTitle);
        for(String key: params.keySet()) {
            stringToPrint = stringToPrint + String.format("%s: %s%n", key, params.get(key));
        }
        return stringToPrint;
    }
}