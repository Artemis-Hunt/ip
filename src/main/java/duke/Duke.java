package duke;

import duke.definitions.CommandPacket;
import duke.exceptions.EmptyContentException;
import duke.exceptions.InvalidParamArgument;
import duke.handlers.CommandHandler;
import duke.handlers.InputParser;
import duke.printers.Cliui;
import duke.tasktypes.Task;

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
                CommandHandler.handleCommand(packet, tasks, taskCountWrapper);
            } catch (IllegalStateException | InvalidParamArgument | EmptyContentException e) {
                Cliui.printError(e);
            }
            input = in.nextLine().strip();
        }

        Cliui.printGoodbye();
    }
}
