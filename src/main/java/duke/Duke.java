package duke;

import duke.definitions.CommandPacket;
import duke.exceptions.EmptyContentException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidParamArgument;
import duke.handlers.CommandHandler;
import duke.handlers.InputParser;
import duke.printers.Cliui;
import duke.tasktypes.Task;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();

        String input;
        Scanner in = new Scanner(System.in);

        Cliui.printGreeting();

        input = in.nextLine().strip();

        while(!(input.equals("bye"))) {
            try {
                CommandPacket packet = new InputParser(input).parseInput();
                CommandHandler.handleCommand(packet, tasks);
            } catch (IllegalStateException | InvalidParamArgument | EmptyContentException |
                        InvalidIndexException | NumberFormatException e) {
                Cliui.printError(e);
            }
            input = in.nextLine().strip();
        }

        Cliui.printGoodbye();
    }
}
