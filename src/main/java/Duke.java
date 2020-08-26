import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printSeparator() {
        String separator = "------------------------------------------";
        System.out.println(separator);
    }

    public static void printArray(String[] array) {
        for(int i = 1; i <= array.length; i++) {
            System.out.println(i + ". " + array[i-1]);
        }
    }
    public static void main(String[] args) {
        String greet = " Hello! I'm Duke";
        String prompt = " What can I do for you?";
        String goodbye = " Bye. Hope to see you again soon!";

        String[] inputsArray = new String[100];
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
                printArray(Arrays.copyOf(inputsArray, inputCount));
            }
            else {
                System.out.println("added: " + input);
                inputsArray[inputCount++] = input;
            }
            printSeparator();
            input = in.nextLine();
        }

        printSeparator();
        System.out.println(goodbye);
        printSeparator();
    }
}
