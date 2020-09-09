import definitions.CommandPacket;
import handlers.CommandHandler;
import handlers.InputParser;
import printers.Cliui;
import tasktypes.Task;

import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int[] taskCountWrapper = {0};

        String input;
        Scanner in = new Scanner(System.in);

        Cliui.printGreeting();

        input = in.nextLine().strip();

        while(!(input.equals("bye"))) {
            CommandPacket packet = new InputParser(input).parseInput();
            //System.out.println(packet);
            CommandHandler.handleCommand(packet, tasks, taskCountWrapper);
            input = in.nextLine();
        }

        Cliui.printGoodbye();
    }
}
