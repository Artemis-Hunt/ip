package duke.handlers;

import duke.definitions.CommandPacket;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidParamArgument;
import duke.tasktypes.*;
import duke.printers.Cliui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class to handle a commandPacket
 */
public final class CommandHandler {

    /**
     * Handles the given commandPacket - takes action based on the commandType.
     *
     * @param command the provided commandPacket
     * @param tasks over task list
     * @param isReadFromFile
     * @return boolean to indicate whether the task list has been modified
     * @throws InvalidParamArgument if a compulsory command param is not provided e.g. "/by" for deadline
     * @throws InvalidIndexException if an invalid task index is given for op[erations on a specific task
     * @throws NumberFormatException if task index given is not in an integer form or is not-a-number
     */
    public static boolean handleCommand(CommandPacket command, ArrayList<Task> tasks, boolean isReadFromFile)
            throws InvalidParamArgument, InvalidIndexException, NumberFormatException {
        Task item;
        switch (command.commandType) {
        //No changes to items, return false
        case PRINT_LIST:
            Cliui.printTaskList(tasks, tasks.size());
            return false;
        case INVALID:
            Cliui.printInvalid();
            return false;

        //Changes to items, return true
        case MARK_AS_DONE:
            int indexOfTask = extractIndex(command.commandContent, tasks.size());
            item = tasks.get(indexOfTask);
            item.setDone();
            if(!isReadFromFile) {
                Cliui.printTaskDone(item);
            }
            return true;
        case DELETE_TASK:
            indexOfTask = extractIndex(command.commandContent, tasks.size());
            item = tasks.get(indexOfTask);
            tasks.remove(indexOfTask);
            Cliui.printTaskDeleted(item, tasks.size());
            return true;
        case CLEAR_TASKS:
            //Requires user to confirm clear operation by typing "y"
            //Aborts if "n" or other input
            Cliui.printClearListConfirmation();
            Scanner in = new Scanner(System.in);
            String confirmationInput = in.nextLine().toLowerCase();
            if(confirmationInput.equals("y")) {
                tasks.clear();
                return true;
            }
            else if(confirmationInput.equals("n")) {
                return false;
            }
            else {
                Cliui.printInvalid();
                return false;
            }

        /*
         * New items added
         * Params are processed on a case-by-case basis
         * E.g. deadline looks for param 'by" only, and ignores any other params
         * Only return at end of code as the task has to be added to task list
         * and task has to be printed before returning true
        */
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

        //Only print task added message if it's not originating from the save file
        //Prevents mass printing of messages when reading from save file
        if(!isReadFromFile) {
            Cliui.printTaskAdded(item, tasks.size());
        }

        return true;
    }

    /**
     * Extracts the array index of the task to select, from the input argument.
     *
     * @param inputString
     * @param numOfTasks
     * @return array index
     * @throws InvalidIndexException if task index given is not in an integer form or is not-a-number
     */
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
