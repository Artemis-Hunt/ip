import definitions.CommandPacket;
import exceptions.InvalidParamArgument;
import handlers.CommandHandler;
import handlers.InputParser;
import printers.Cliui;
import tasktypes.Task;

import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        //Task count is wrapped in an array, so that the same variable is
        //accessed and modified in all called methods;
        int[] taskCountWrapper = {0};

        String input;
        Scanner in = new Scanner(System.in);

        Cliui.printGreeting();

        input = in.nextLine().strip();

        while(!(input.equals("bye"))) {
            try {
                CommandPacket packet = new InputParser(input).parseInput();
                //System.out.println(packet);
                CommandHandler.handleCommand(packet, tasks, taskCountWrapper);
            } catch (IllegalStateException e) {
                Cliui.printError(e);
            } catch (InvalidParamArgument e) {
                Cliui.printError(e);
            }
            input = in.nextLine();
        }

        Cliui.printGoodbye();
    }
}
