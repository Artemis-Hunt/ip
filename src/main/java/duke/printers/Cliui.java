package duke.printers;

import duke.definitions.CommandPacket;
import duke.tasktypes.Task;

import java.util.ArrayList;

public class Cliui {
    static String separator = "------------------------------------------";
    static String indent = " ";
    static String greet = " Hello! I'm duke.";
    static String prompt = " What can I do for you?";
    static String taskAdded = "Got it. I've added this task:";
    static String taskDeleted = "Noted. I've removed this task:";
    static String taskDone = "Nice! I've marked this task as done:";
    static String goodbye = " Bye. Hope to see you again soon!";

    public static void printSeparator() {
        System.out.println(separator);
    }

    public static void printGreeting() {
        printSeparator();
        System.out.println(greet);
        System.out.println(prompt);
        printSeparator();
    }

    public static void printGoodbye() {
        printSeparator();
        System.out.println(goodbye);
        printSeparator();
    }

    public static void printTaskAdded(Task task, int numOfTasks) {
        printSeparator();
        System.out.println(taskAdded);
        System.out.println(indent + task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list");
        printSeparator();
    }

    public static void printTaskDeleted(Task task, int numOfTasks) {
        printSeparator();
        System.out.println(taskDeleted);
        System.out.println(indent + task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list");
        printSeparator();
    }

    public static void printTaskDone(Task task) {
        printSeparator();
        System.out.println(taskDone);
        System.out.println(indent + task);
        printSeparator();
    }

    public static void printTaskList(ArrayList<Task> tasks, int length) {
        printSeparator();
        boolean isListEmpty = length == 0;
        if(isListEmpty) {
            System.out.println("Task list empty!");
        }
        else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= length; i++) {
                Task item = tasks.get(i - 1);
                System.out.println(i + "." + item);
            }
        }
        printSeparator();
    }

    public static void printClearListConfirmation() {
        printSeparator();
        System.out.println("Clearing entire task list. Are you sure? (y/n)");
        printSeparator();
    }

    public static void printListCleared() {
        printSeparator();
        System.out.println("Task list has been cleared");
        printSeparator();
    }

    public static void printListNotCleared() {
        printSeparator();
        System.out.println("Task list has not been modified");
        printSeparator();
    }

    public static void printInvalid() {
        printSeparator();
        System.out.println("Invalid command!");
        printSeparator();
    }

    public static void printError(Exception error) {
        printSeparator();
        System.out.println("Error: " + error.getMessage());
        printSeparator();
    }

    public static void printFileError(Exception error) {
        printSeparator();
        System.out.println("Error: " + error.getMessage());
        System.out.println("File has been corrupted");
        System.out.println("Task list has been initialized as empty.");
        System.out.println("Duke.txt will be overwritten upon adding any new item To prevent this, type \"bye\"");
        printSeparator();
    }

    public static void printPacket(CommandPacket packet) {
        System.out.println("--- Command packet ---");
        System.out.println(packet);
        System.out.println("-----------------------");
    }

    public static void printInput(String input) {
        System.out.println("Raw input is:__" + input + "__");
    }
}
