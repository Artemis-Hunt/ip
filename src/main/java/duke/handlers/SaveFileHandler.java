package duke.handlers;

import duke.definitions.CommandPacket;
import duke.exceptions.EmptyContentException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidParamArgument;
import duke.parsers.InputParser;
import duke.printers.Cliui;
import duke.tasktypes.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveFileHandler {
    private String pathString;
    private String fullPathString;
    private Path path;
    private Path fullPath;

    public SaveFileHandler(String pathString) {
        this.pathString = pathString;
        fullPathString = pathString + "/duke.txt";
        path = Paths.get(pathString);
        fullPath = Paths.get(fullPathString);
    }

    public ArrayList<Task> openFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        boolean pathExists = Files.exists(path);
        boolean fileExists = Files.exists(fullPath);

        if(!pathExists) {
            Files.createDirectories(path);
        }

        if(!fileExists) {
            Files.createFile(fullPath);
        }

        Scanner in = new Scanner(fullPath);

        while(in.hasNextLine()) {
            try {
                CommandPacket packet = new InputParser(in.nextLine()).parseInput();
                CommandHandler.handleCommand(packet, tasks, true);
            } catch (IllegalStateException | InvalidParamArgument | EmptyContentException |
                    InvalidIndexException | NumberFormatException e) {
                Cliui.printFileError(e);
            }
        }

        return tasks;
    }

    public void writeFile(ArrayList<Task> tasks) throws IOException {
        //Delete existing file, if it exists
        Files.deleteIfExists(fullPath);
        BufferedWriter writer = Files.newBufferedWriter(fullPath);

         /* Iterate through tasks and write to file
         * Each task is written as the corresponding cli input that would have
         * added the task and set it at its corresponding status (done or not done)
         * Example:
         * Tasks: [[D][✓] homework (by: tmr), [T][✘] read book]
         * is written as "deadline homework /by tmr"
         * followed by "done 1"
         * followed by "todo read book"
         */
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String contentToWrite = "";
            String taskName = task.getTaskName();
            if(task instanceof ToDo) {
                contentToWrite += "todo " + taskName;
            } else if (task instanceof Deadline) {
                contentToWrite += "deadline " + taskName + " /by " + ((Deadline) task).getDateAndTime();
            } else if (task instanceof Event) {
                contentToWrite += "event " + taskName + " /at " + ((Event) task).getDateAndTime();
            }
            writer.write(contentToWrite);
            writer.newLine();

            //If task is done, insert command to mark task as done
            if(task.getDone()) {
                writer.write("done " + (i + 1));
                writer.newLine();
            }
        }
        writer.close();
    }
}
