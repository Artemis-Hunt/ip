package duke.handlers;

import duke.definitions.CommandPacket;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidParamArgument;
import duke.tasktypes.*;
import duke.printers.Cliui;

import java.util.ArrayList;
import java.util.Scanner;

public final class CommandHandler {

    public static boolean handleCommand(CommandPacket command, ArrayList<Task> tasks, boolean readFromFile)
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
        case FIND_TASK:
            ArrayList<Task> filteredArray = filterTasks(tasks, command.commandContent);
            Cliui.printFindTask(filteredArray, filteredArray.size());
            return false;

        //Changes to items, return true
        case MARK_AS_DONE:
            int indexOfTask = extractIndex(command.commandContent, tasks.size());
            item = tasks.get(indexOfTask);
            item.setDone();
            if(!readFromFile) {
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
        if(!readFromFile) {
            Cliui.printTaskAdded(item, tasks.size());
        }

        return true;
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

    /**
     * Returns filtered tasks based on search string
     *
     * @param tasks overall task list
     * @param searchString raw search string
     * @return ArrayList of matching tasks
     */
    static ArrayList<Task> filterTasks(ArrayList<Task> tasks, String searchString) {
        ArrayList<Task> filteredArray = new ArrayList<>();
        for(Task task: tasks) {
            String taskName = task.getTaskName();
            boolean containsSearchString = taskName.contains(searchString);
            if(containsSearchString) {
                filteredArray.add(task);
            }
        }
        return filteredArray;
    }
}
