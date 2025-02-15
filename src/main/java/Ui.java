import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.PrintStream;

public class Ui {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private final FastScanner scanner;
    private final PrintStream output;
    private final String LINE_BREAK = "____________________________________________________________";

    public Ui() {
        this.scanner = new FastScanner();
        this.output = System.out;
    }

    /**
     * Print the message to the user
     * Function is refactored to abstract the output stream
     * @param message The String message to be printed
     */
    public void println(String message) {
        this.output.println(message);
    }

    /**
     * Overloaded method to print the message to the user
     * @param message The Object message to be printed
     */
    public void println(Object message) {
        // String.valueOf() is used to handle null parameters
        // https://stackoverflow.com/questions/27465731/string-valueof-vs-object-tostring
        String strMessage = String.valueOf(message);
        println(strMessage);
    }

    public void start() {
        printWelcome();
    }

    /**
     * Print the welcome message when the program starts
     */
    public void printWelcome() {
        printBreak();
        println("""
                 Hello! I'm Zephyr
                 What can I do for thou?
                """);
        printBreak();
    }

    /**
     * Print the goodbye message when the program ends
     */
    public void printGoodbye() {
        printBreak();
        println("""
                 Goodbye! May thou have a safe journey ahead.
                """);
        printBreak();
    }

    public void printBreak() {
        println(LINE_BREAK);
    }

    public void printUnknown() {
        printBreak();
        println("""
                I do not understand what thou art saying.
                Please enter a valid command using the follow:
                1. list
                2. mark <task number>
                3. unmark <task number>
                4. todo <task description>
                5. deadlineTask <task description> /by <deadlineTask>
                6. eventTask <task description> /from <start time> /to <end time>
                7. delete <task number>
                8. bye - To exit the programme
                """);
        printBreak();
    }

    /**
     * Print the error message when an error occurs
     */
    public void printLoadingError() {
        println("Error loading file. Creating new file.");
    }

    public void printTaskAdded(Task task) {
        printBreak();
        println("Got it. I've added this task:");
        println(task.toString());
        printBreak();
    }

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showAllTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            printBreak();
            println("There are no tasks in thine list.");
            printBreak();
            return;
        }
        println(LINE_BREAK);
        println("Here are the tasks in thine list:");
        println(tasks.toString());
        println(LINE_BREAK);
    }
}
