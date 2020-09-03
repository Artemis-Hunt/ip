import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printSeparator() {
        String separator = "------------------------------------------";
        System.out.println(separator);
    }

    public static void printTaskList(Task[] array, int length) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= length; i++) {
            Task item = array[i - 1];
            System.out.println(i + "." + item);
        }
    }


    public static void main(String[] args) {
        String greet = " Hello! I'm Duke";
        String prompt = " What can I do for you?";
        String goodbye = " Bye. Hope to see you again soon!";

        Task[] tasks = new Task[100];
        int taskCount = 0;

        String input;
        Scanner in = new Scanner(System.in);

        printSeparator();
        System.out.println(greet);
        System.out.println(prompt);
        printSeparator();

        input = in.nextLine();
        String command;
        String commandParams;
        int indexOfFirstBlank;

        while (!(input.equals("bye"))){
            System.out.println("Command is: " + input);
            indexOfFirstBlank = input.indexOf(" ");
            command = input;

            printSeparator();

            if(input.equals("list")) {
                printTaskList(tasks, taskCount);
            }
            else if(indexOfFirstBlank != -1) {
                command = input.substring(0, indexOfFirstBlank);
                commandParams = input.substring(indexOfFirstBlank + 1);
                Task item;
                if (command.equals("done")) {
                    item = tasks[Integer.parseInt(commandParams) - 1];
                    item.setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + item);
                } else if (command.equals("todo")) {
                    item = new ToDo(commandParams);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + item);
                    System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
                    tasks[taskCount] = item;
                    taskCount++;
                } else if (command.equals("deadline")) {
                    int indexOfBy = commandParams.indexOf("/by");
                    String taskName = commandParams.substring(0, indexOfBy);
                    String deadline = commandParams.substring(indexOfBy + 4);
                    item = new Deadline(taskName, deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + item);
                    System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
                    tasks[taskCount] = item;
                    taskCount++;
                } else if (command.equals("event")) {
                    int indexOfAt = commandParams.indexOf("/at");
                    String taskName = commandParams.substring(0, indexOfAt);
                    String deadline = commandParams.substring(indexOfAt + 4);
                    item = new Event(taskName, deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + item);
                    System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
                    tasks[taskCount] = item;
                    taskCount++;
                }
                else {
                    System.out.println("added: " + input);
                    item = new Task(input);
                    tasks[taskCount] = item;
                    taskCount++;
                }
            }


            printSeparator();
            input = in.nextLine();
        }

        printSeparator();
        System.out.println(goodbye);
        printSeparator();
    }
}
