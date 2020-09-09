package handlers;

import definitions.CommandPacket;
import tasktypes.*;
import printers.Cliui;

public final class CommandHandler {

    public static void handleCommand(CommandPacket command, Task[] tasks, int[] taskCountWrapper) {
        Task item;
        switch(command.commandType) {
        //No new items added
        case PRINT_LIST:
            Cliui.printTaskList(tasks, taskCountWrapper[0]);
            return;
        case INVALID:
            Cliui.printInvalid();;
            return;
        case MARK_AS_DONE:
            int indexOfTask = Integer.parseInt(command.commandTitle) - 1;
            item = tasks[indexOfTask];
            item.setDone();
            Cliui.printTaskDone(item);
            return;

        //New items added
        case ADD_TODO:
            item = new ToDo(command.commandTitle);
            break;
        case ADD_DEADLINE:
            String by = command.params.get("by");
            item = new Deadline(command.commandTitle, by);
            break;
        case ADD_EVENT:
            String at = command.params.get("at");
            item = new Event(command.commandTitle, at);
            break;
        default:
            throw new IllegalStateException("Unexpected commandType: " + command.commandType);
        }
        tasks[taskCountWrapper[0]++] = item;
        Cliui.printTaskAdded(item, taskCountWrapper[0]);
    }
}
