import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class Zephyr {

    public enum Command {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        BYE("bye"),
        UNKNOWN("unknown");


        private final String value;

        // Constructor to associate string values
        Command(String value) {
            this.value = value;
        }

        // Method to get enum from string
        public static Command fromValue(String value) {
            for (Command command : Command.values()) {
                if (command.value.equalsIgnoreCase(value)) {
                    return command;
                }
            }
            return UNKNOWN;
        }
    }

    public static void saveToFile(String relativeFolder, String fileName, String content) {
        try {
            // Construct the relative path
            Path directoryPath = Path.of(relativeFolder);
            Path filePath = directoryPath.resolve(fileName); // Combines folder + filename

            // Ensure the directory exists (creates if missing)
            Files.createDirectories(directoryPath);

            // Write content to the file (creating or replacing it)
            Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("File saved at: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse a line of text into a Task object
     * @param line The line of text to parse
     * @return Task object or null if parsing fails
     */
    public static Task parseLine(String line) {
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
        Task task;
        task = switch (letter) {
            case 'T' -> Todo.parseString(content);
            case 'D' -> Deadline.parseString(content);
            case 'E' -> Event.parseString(content);
            default -> null;
        };
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    public static void main(String[] args) {
        String GREETING_MESSAGE= """
                ____________________________________________________________
                 Hello! I'm Zephyr
                 What can I do for thou?
                ____________________________________________________________
                """;
        String GOODBYE_MESSAGE= """
                ____________________________________________________________
                 Bye. Hope to see thou again soon!
                ____________________________________________________________
                """;

        // Assumption of max 100 tasks to store
        ArrayList<Task> taskList = new ArrayList<>();

        Path folderPath = Path.of("data");
        Path filePath = folderPath.resolve("tasks.md");

        if (Files.exists(folderPath) && Files.exists(filePath)) {
            try (Stream<String> lines = Files.lines(filePath)) {
                lines.forEach(line -> {
                    Task task = parseLine(line);
                    if (task != null) {
                        taskList.add(task);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println(GREETING_MESSAGE);

        // Detect user input in cli
        FastScanner scanner = new FastScanner();
        String userInput = scanner.nextString();
        // Case is ignored to allow for case-insensitive input
        while (!userInput.equalsIgnoreCase("bye")) {
            try {
                switch (Command.fromValue(userInput)) {
                case LIST:
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + ". " + taskList.get(i).toString());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case MARK:
                    int markNumber;
                    try {
                        markNumber = scanner.nextInt();
                    } catch (NumberFormatException e) {
                        throw new ZephyrException("Task number must be an integer.");
                    }
                    if (markNumber > taskList.size()) {
                        throw new ZephyrException("Task number does not exist.");
                    }
                    if (markNumber < 1) {
                        throw new ZephyrException("Task number must be greater than 0.");
                    }
                    markNumber--;
                    taskList.get(markNumber).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.get(markNumber).toString());
                    System.out.println("____________________________________________________________");
                    break;
                case UNMARK:
                    int unmarkNumber;
                    try {
                        unmarkNumber = scanner.nextInt();
                    } catch (NumberFormatException e) {
                        throw new ZephyrException("Task number must be an integer.");
                    }
                    if (unmarkNumber > taskList.size()) {
                        throw new ZephyrException("Task number does not exist.");
                    }
                    if (unmarkNumber < 1) {
                        throw new ZephyrException("Task number must be greater than 0.");
                    }
                    unmarkNumber--;
                    taskList.get(unmarkNumber).markAsUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as undone:");
                    System.out.println(taskList.get(unmarkNumber).toString());
                    System.out.println("____________________________________________________________");
                    break;
                case TODO:
                    String description = scanner.remainingLine();
                    if (description.isBlank()) {
                        throw new ZephyrException("Todo description cannot be empty.");
                    }
                    Todo task = new Todo(description);
                    taskList.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println("____________________________________________________________");
                    break;
                case DEADLINE:
                    String deadlineDescription = scanner.nextUntil("/by");
                    if (deadlineDescription.isBlank()) {
                        throw new ZephyrException("Deadline description cannot be empty or /by is missing.");
                    }
                    String by = scanner.remainingLine();
                    if (by.isBlank()) {
                        throw new ZephyrException("Deadline by cannot be empty.");
                    }
                    Deadline deadlineTask = new Deadline(deadlineDescription, by);
                    taskList.add(deadlineTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadlineTask);
                    System.out.println("____________________________________________________________");
                    break;
                case EVENT:
                    String eventDescription = scanner.nextUntil("/from");
                    if (eventDescription.isBlank()) {
                        throw new ZephyrException("Event description cannot be empty.");
                    }
                    String from = scanner.nextUntil("/to");
                    if (from.isBlank()) {
                        throw new ZephyrException("Event start time cannot be empty.");
                    }
                    String to = scanner.remainingLine();
                    if (to.isBlank()) {
                        throw new ZephyrException("Event end time cannot be empty.");
                    }
                    Event eventTask = new Event(eventDescription, from, to);
                    taskList.add(eventTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(eventTask);
                    System.out.println("____________________________________________________________");
                    break;
                case DELETE:
                    int deleteNumber;
                    try {
                        deleteNumber = scanner.nextInt();
                    } catch (NumberFormatException e) {
                        throw new ZephyrException("Task number must be an integer.");
                    }
                    if (deleteNumber > taskList.size()) {
                        throw new ZephyrException("Task number does not exist.");
                    }
                    if (deleteNumber < 1) {
                        throw new ZephyrException("Task number must be greater than 0.");
                    }

                    deleteNumber--;
                    Task deletedTask = taskList.remove(deleteNumber);
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletedTask);
                    System.out.println("Now thou have " + taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    scanner.remainingLine();
                    throw new ZephyrException("""
                            I do not understand what thou art saying.
                            Please enter a valid command using the follow:
                            1. list
                            2. mark <task number>
                            3. unmark <task number>
                            4. todo <task description>
                            5. deadline <task description> /by <deadline>
                            6. event <task description> /from <start time> /to <end time>
                            7. delete <task number>
                            8. bye - To exit the programme
                            """);
                }
            } catch (ZephyrException e) {
                System.out.println(e.getMessage());
            }
            userInput = scanner.nextString();
        }

        StringBuilder output = new StringBuilder();
        for (Task task : taskList) {
            output.append(task.toMarkdownString()).append("\n");
        }
        saveToFile("data", "tasks.md", output.toString());
        System.out.println(GOODBYE_MESSAGE);
    }
}
