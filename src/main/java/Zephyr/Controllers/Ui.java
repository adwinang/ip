package Zephyr.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.PrintStream;
import java.util.List;

import Zephyr.DataStructures.TaskList;
import Zephyr.Tasks.AbstractTask;

/**
 * Handles user interaction by printing messages, reading user input,
 * and formatting dates for display.
 */
public class Ui {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private final FastScanner scanner;
    private final PrintStream output;
    private final String LINE_BREAK = "____________________________________________________________";

    /**
     * Constructs a new Ui object using standard input and output.
     */
    public Ui() {
        this.scanner = new FastScanner();
        this.output = System.out;
    }

    /**
     * Prints a message to the user.
     *
     * @param message the message to print
     */
    public void println(String message) {
        this.output.println(message);
    }

    /**
     * Prints an object's string representation to the user.
     *
     * @param message the object whose string representation is printed
     */
    public void println(Object message) {
        // String.valueOf() is used to handle null parameters
        // https://stackoverflow.com/questions/27465731/string-valueof-vs-object-tostring
        String strMessage = String.valueOf(message);
        println(strMessage);
    }

    /**
     * Starts the user interface by printing the welcome message.
     */
    public void start() {
        printWelcome();
    }

    /**
     * Prints the welcome message when the program starts.
     */
    public void printWelcome() {
        printBreak();
        println("Hello! I'm Zephyr\nWhat can I do for thou?");
        printBreak();
    }

    /**
     * Prints the goodbye message when the program ends.
     */
    public void printGoodbye() {
        printBreak();
        println("Goodbye! May thou have a safe journey ahead.");
        printBreak();
    }

    /**
     * Prints a line break.
     */
    public void printBreak() {
        println(LINE_BREAK);
    }

    /**
     * Prints a message indicating that the command is unknown.
     */
    public void printUnknown() {
        printBreak();
        println("""
                I do not understand what thou art saying.
                Please enter a valid command using the follow:
                1. list
                2. find <keyword>
                3. mark <task number>
                4. unmark <task number>
                5. todo <task description>
                6. deadlineTask <task description> /by <deadlineTask>
                7. eventTask <task description> /from <start time> /to <end time>
                8. upcoming <task type> <days>
                9. delete <task number>
                10. bye - To exit the programme""");
        printBreak();
    }

    /**
     * Prints an error message indicating a problem occurred while loading a file.
     */
    public void printLoadingError() {
        println("Error loading file. Creating new file.");
    }

    /**
     * Prints an error message indicating a problem occurred while saving a file.
     */
    public void printSavingError() {
        println("Error saving file.");
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task the task that was added
     */
    public void printTaskAdded(AbstractTask task) {
        printBreak();
        println("Got it. I've added this task:");
        println(task.toString());
        printBreak();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public void printTaskDone(AbstractTask task) {
        printBreak();
        println("Nice! I've marked this task as done:");
        println(task.toString());
        printBreak();
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task the task that was deleted
     * @param size the number of tasks remaining in the list
     */
    public void printTaskDeleted(AbstractTask task, int size) {
        printBreak();
        println("Noted. I've removed this task:");
        println(task.toString());
        println("Now thou have " + size + " tasks in the list.");
        printBreak();
    }

    /**
     * Prints a message indicating that a task has been unmarked.
     *
     * @param task the task that was unmarked
     */
    public void printTaskUndone(AbstractTask task) {
        printBreak();
        println("Pity! I've unmarked this task as done:");
        println(task.toString());
        printBreak();
    }

    /**
     * Parses a date string into a LocalDate using the defined date format.
     *
     * @param date the date string to parse
     * @return the LocalDate corresponding to the date string
     */
    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    /**
     * Reads a command from the user input.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void printTaskList(List<AbstractTask> tasks) {
        printBreak();
        println("Here are thine search results:");
        for (int i = 0; i < tasks.size(); i++) {
            println(i + 1 + ". " + tasks.get(i) + "\n");
        }
        printBreak();
    }

    /**
     * Displays all tasks in the provided TaskList.
     *
     * @param tasks the TaskList containing the tasks to display
     */
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