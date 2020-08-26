import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printSeparator() {
        String separator = "------------------------------------------";
        System.out.println(separator);
    }

    public static void printTaskList(Task[] array) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= array.length; i++) {
            Task item = array[i - 1];
            System.out.println(i + "." + item.getStatusAndName());
        }
    }


    public static void main(String[] args) {
        String greet = " Hello! I'm Duke";
        String prompt = " What can I do for you?";
        String goodbye = " Bye. Hope to see you again soon!";

        Task[] ListOfInputs = new Task[100];
        int inputCount = 0;

        String input;
        Scanner in = new Scanner(System.in);

        printSeparator();
        System.out.println(greet);
        System.out.println(prompt);
        printSeparator();

        input = in.nextLine();
        while (!(input.equals("bye"))){
            printSeparator();
            if(input.equals("list")) {
                printTaskList(Arrays.copyOf(ListOfInputs, inputCount));
            }
            else if(input.contains("done ")) {
                String[] inputWords = input.split(" ");
                Task item = ListOfInputs[Integer.parseInt(inputWords[1]) - 1];
                item.setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + item.getStatusAndName());
            }
            else {
                System.out.println("added: " + input);
                Task item = new Task(input);
                ListOfInputs[inputCount++] = item;
            }
            printSeparator();
            input = in.nextLine();
        }

        printSeparator();
        System.out.println(goodbye);
        printSeparator();
    }
}
