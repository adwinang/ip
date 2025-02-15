import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    private void createIfDirectoryNotFound() {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

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
        return lines;
    }

    public void saveFile(List<AbstractTask> tasks) throws IOException {
        createIfDirectoryNotFound();
        FileWriter fileWriter = new FileWriter(file);
        for (AbstractTask task : tasks) {
            fileWriter.write(task.toMarkdownString() + "\n");
        }
        fileWriter.close();
    }

    public void overwriteFile(String content) throws IOException {
        createIfDirectoryNotFound();
        java.io.FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(content);
        fileWriter.close();
    }

    public void appendToFile(String content) throws IOException {
        createIfDirectoryNotFound();
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(content);
        fileWriter.close();
    }

    /**
     * Parse a line of text into a Task object
     * @param line The line of text to parse
     * @return Task object or null if parsing fails
     */
    AbstractTask parseLine(String line) {
        // 1. Basic length check to avoid StringIndexOutOfBounds
        //    Minimum valid example is: "- [ ] X: " (8 chars before content)
        if (line == null || line.length() < 8) {
            return null;
        }

        // 2. Must start with "- ["
        if (!line.startsWith("- [")) {
            return null;
        }

        // 3. Check the 'X' or ' ' for the check mark (index 3)
        char checkMark = line.charAt(3);
        if (checkMark != 'X' && checkMark != ' ') {
            return null;
        }

        // 4. The next character must be ']' at index 4
        if (line.charAt(4) != ']') {
            return null;
        }

        // 5. Next must be space at index 5
        if (line.charAt(5) != ' ') {
            return null;
        }

        // 6. Next, a single-letter code at index 6 (e.g., D, E, T, etc.)
        char letter = line.charAt(6);
        if (!Character.isLetter(letter)) {
            return null;
        }

        // 7. Next must be ':' at index 7
        if (line.charAt(7) != ':') {
            return null;
        }

        // 8. Next must be a space at index 8
        if (line.charAt(8) != ' ') {
            return null;
        }

        // 9. Everything after index 8 + 1 = 9 is the content
        String content = line.substring(9).trim();

        // 10. Create your Task-like object
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
