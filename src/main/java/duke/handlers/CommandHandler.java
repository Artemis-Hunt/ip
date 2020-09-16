package duke.handlers;

import duke.definitions.CommandPacket;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidParamArgument;
import duke.tasktypes.*;
import duke.printers.Cliui;

import java.util.ArrayList;

public final class CommandHandler {

    public static void handleCommand(CommandPacket command, ArrayList<Task> tasks)
            throws InvalidParamArgument, InvalidIndexException, NumberFormatException {
        Task item;
        switch (command.commandType) {
        //No new items added
        case PRINT_LIST:
            Cliui.printTaskList(tasks, tasks.size());
            return;
        case INVALID:
            Cliui.printInvalid();
            return;
        case MARK_AS_DONE:
            int indexOfTask = extractIndex(command.commandContent, tasks.size());
            item = tasks.get(indexOfTask);
            item.setDone();
            Cliui.printTaskDone(item);
            return;
//        case DELETE_TASK:
//            indexOfTask = extractIndex(command.commandContent, tasks.size());
//            item = tasks.get(indexOfTask);
//            tasks.remove(indexOfTask);
//            Cliui.printTaskDeleted(item, tasks.size());
//            return;

        //New items added
        //Params are processed on a case-by-case basis
        //E.g. deadline looks for param 'by" only, and ignores any other params
        case ADD_TODO:
            item = new ToDo(command.commandContent);
            break;
        case ADD_DEADLINE:
            String by = command.params.get("by");
            if(by == null) {
                throw new InvalidParamArgument("by");
            }
            item = new Deadline(command.commandContent, by);
            break;
        case ADD_EVENT:
            String at = command.params.get("at");
            if(at == null) {
                throw new InvalidParamArgument("at");
            }
            item = new Event(command.commandContent, at);
            break;
        default:
            throw new IllegalStateException("Unexpected commandType: " + command.commandType);
        }
        tasks.add(item);
        Cliui.printTaskAdded(item, tasks.size());
    }

    static int extractIndex(String inputString, int numOfTasks) throws InvalidIndexException {
        try {
            int indexOfTask = Integer.parseInt(inputString) - 1;
            if (indexOfTask < 0 || indexOfTask > numOfTasks - 1) {
                throw new InvalidIndexException(indexOfTask + 1);
            }
            return indexOfTask;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(inputString);
        }
    }
}
