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

/**
 * Helper class to read from or write to the save file, given a save directory
 * Save file is configured as duke.txt in the save directory
 */
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

    /**
     * Attempts to read a save file, creating one if not already existing.
     * Then parses the save file line by line and generates a task list
     *
     * @return saved task list
     * @throws IOException if there is an error with opening or creating the save file
     */
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

        //Reuses the parsing logic in main() of Duke
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

    /**
     * Iterate through tasks and write to file.
     * Each task is written as the corresponding cli input that would have
     * added the task and set it at its corresponding status (done or not done)
     * Example:
     * Task list: [[D][y] homework (by: 25/07/2019 17:50), [T][n] read book]
     * is written as "deadline homework /by 2019-07-25 17:50"
     * followed by "done 1"
     * followed by "todo read book".
     *
     * This allows reuse of the parsers when reading from the save file, instead
     * of having to separately parse the save format
     *
     * @param tasks overall task list
     * @throws IOException when there is an error with writing the file
     */
    public void writeFile(ArrayList<Task> tasks) throws IOException {
        //Delete existing file, if it exists
        Files.deleteIfExists(fullPath);
        BufferedWriter writer = Files.newBufferedWriter(fullPath);

        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String contentToWrite = "";
            String taskName = task.getTaskName();
            //Writes the corresponding command for the task
            if(task instanceof ToDo) {
                contentToWrite += "todo " + taskName;
            } else if (task instanceof Deadline) {
                contentToWrite += "deadline " + taskName + " /by " + ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                contentToWrite += "event " + taskName + " /at " + ((Event) task).getAt();
            }
            writer.write(contentToWrite);
            writer.newLine();

            //Insert command to mark task as done
            if(task.getDone()) {
                writer.write("done " + (i + 1));
                writer.newLine();
            }
        }
        writer.close();
    }
}
