import java.util.Scanner;

public class Duke {
    public static void printSeparator() {
        String separator = "------------------------------------------";
        System.out.println(separator);
    }
    public static void main(String[] args) {
        String greet = " Hello! I'm Duke";
        String prompt = " What can I do for you?";
        String goodbye = " Bye. Hope to see you again soon!";


        String input;
        Scanner in = new Scanner(System.in);

        printSeparator();
        System.out.println(greet);
        System.out.println(prompt);
        printSeparator();

        input = in.nextLine();
        while (!(input.equals("bye"))){
            printSeparator();
            System.out.println(input);
            printSeparator();
            input = in.nextLine();
        }

        printSeparator();
        System.out.println(goodbye);
        printSeparator();
    }
}
