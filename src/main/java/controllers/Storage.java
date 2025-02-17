package zephyr.Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zephyr.Tasks.AbstractTask;
import zephyr.Tasks.DeadlineTask;
import zephyr.Tasks.EventTask;
import zephyr.Tasks.TodoTask;

/**
 * Handles storage operations such as loading and saving tasks to a file.
 */
public class Storage {
    private File file;

    /**
     * Constructs a new Storage object with the given file path.
     *
     * @param filePath the path to the file used for storage
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Creates the parent directory if it does not exist.
     */
    private void createIfDirectoryNotFound() {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return a list of AbstractTask objects loaded from the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public List<AbstractTask> loadFile() throws IOException {
        List<AbstractTask> lines = new ArrayList<>();
        if (!file.exists()) {
            return lines;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(parseLine(line));
        }
        reader.close();
        return lines;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks the list of tasks to save
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveFile(List<AbstractTask> tasks) throws IOException {
        createIfDirectoryNotFound();
        FileWriter fileWriter = new FileWriter(file);
        for (AbstractTask task : tasks) {
            fileWriter.write(task.toMarkdownString() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Overwrites the storage file with the given content.
     *
     * @param content the new content to write to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void overwriteFile(String content) throws IOException {
        createIfDirectoryNotFound();
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(content);
        fileWriter.close();
    }

    /**
     * Appends the given content to the storage file.
     *
     * @param content the content to append
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void appendToFile(String content) throws IOException {
        createIfDirectoryNotFound();
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(content);
        fileWriter.close();
    }

    /**
     * Parses a line of text from the storage file into an AbstractTask.
     *
     * @param line the line of text to parse
     * @return an AbstractTask corresponding to the line, or null if parsing fails
     */
    AbstractTask parseLine(String line) {
        // Basic length check to avoid StringIndexOutOfBounds; minimum valid format is 9 characters.
        if (line == null || line.length() < 8) {
            return null;
        }
        // The line must start with "- ["
        if (!line.startsWith("- [")) {
            return null;
        }
        // Check the check mark at index 3; must be 'X' or a space.
        char checkMark = line.charAt(3);
        if (checkMark != 'X' && checkMark != ' ') {
            return null;
        }
        // Verify the closing bracket and following space.
        if (line.charAt(4) != ' ' || line.charAt(5) != ' ') {
            return null;
        }
        // The next character should be a single-letter code representing the task type.
        char letter = line.charAt(6);
        if (!Character.isLetter(letter)) {
            return null;
        }
        // Check for a colon and a space after the letter.
        if (line.charAt(7) != ':' || line.charAt(8) != ' ') {
            return null;
        }
        // The remainder of the line is the task content.
        String content = line.substring(9).trim();
        boolean isDone = (checkMark == 'X');
        AbstractTask task;
        task = switch (letter) {
            case 'T' -> TodoTask.parseString(content);
            case 'D' -> DeadlineTask.parseString(content);
            case 'E' -> EventTask.parseString(content);
            default -> null;
        };
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }
}