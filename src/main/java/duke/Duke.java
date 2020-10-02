package duke;

import duke.definitions.CommandPacket;
import duke.exceptions.EmptyContentException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidParamArgument;
import duke.handlers.CommandHandler;
import duke.parsers.InputParser;
import duke.printers.Cliui;
import duke.tasktypes.Task;
import duke.handlers.SaveFileHandler;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {

        ArrayList<Task> tasks;
        SaveFileHandler saveFileHandler = new SaveFileHandler("./data");
        try {
            tasks = saveFileHandler.openFile();
        } catch(IOException e) {
            Cliui.printError(e);
            return;
        }
        String input;
        Scanner in = new Scanner(System.in);

        Cliui.printGreeting();

        input = in.nextLine().strip();

        while(!(input.equals("bye"))) {
            boolean isTasksModified = false;
            try {
                CommandPacket packet = new InputParser(input).parseInput();
                isTasksModified = CommandHandler.handleCommand(packet, tasks, false);

                if(isTasksModified) {
                    saveFileHandler.writeFile(tasks);
                }
            } catch (IllegalStateException | InvalidParamArgument | EmptyContentException |
                        InvalidIndexException | NumberFormatException | IOException | DateTimeParseException e) {
                Cliui.printError(e);
            }
            input = in.nextLine().strip();
        }

        Cliui.printGoodbye();
    }
}
