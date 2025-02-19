package controllers;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import datastructures.TaskList;
import tasks.AbstractTask;

/**
 * Handles user interaction by printing messages, reading user input,
 * and formatting dates for display.
 */
public class Ui {
    // CHECKSTYLE:OFF: AbbreviationAsWordInName
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    // CHECKSTYLE:ON: AbbreviationAsWordInName
    private final FastScanner scanner;
    private final PrintStream output;
    // CHECKSTYLE:OFF: AbbreviationAsWordInName
    private final String LINE_BREAK = "____________________________________________________________";
    // CHECKSTYLE:ON: AbbreviationAsWordInName
    private final Queue<String> queue = new LinkedList<String>();

    /**
     * Constructs a new Ui object using standard input and output.
     */
    public Ui() {
        this.scanner = new FastScanner();
        this.output = System.out;
    }

    /**
     * Check queue size
     * @return size of queue
     */
    public Integer queueSize() {
        return queue.size();
    }

    /**
     * Return first output in queue
     *
     * @return first message
     */
    public String getOutput() {
        return queue.poll();
    }

    /**
     * Prints a message to the user.
     *
     * @param message the message to print
     */
    public void println(String message) {
        queue.add(message);
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
     * Prints the welcome message when the program starts.
     */
    public void printWelcome() {
        String content = addBreak("Hello! I'm app.Zephyr\nWhat can I do for thou?");
        println(content);
    }

    /**
     * Prints the goodbye message when the program ends.
     */
    public void printGoodbye() {
        String content = addBreak("Goodbye! May thou have a safe journey ahead.");
        println(content);
    }

    /**
     * Add break lines to the content
     * @param content the input to add break lines
     * @return Formatted content
     */
    public String addBreak(String content) {
        return this.LINE_BREAK + "\n" + content + "\n" + this.LINE_BREAK;
    }

    /**
     * Format content and add content to queue
     * @param content
     */
    public void printAndAddBreak(String content) {
        String formattedContent = this.addBreak(content);
        queue.add(formattedContent);
    }

    /**
     * Prints a message indicating that the command is unknown.
     */
    public void printUnknown() {
        String content = addBreak("""
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
        queue.add(content);
    }

    /**
     * Prints an error message indicating a problem occurred while loading a file.
     */
    public void printLoadingError() {
        printAndAddBreak("Error loading file. Creating new file.");
    }

    /**
     * Prints an error message indicating a problem occurred while saving a file.
     */
    public void printSavingError() {
        printAndAddBreak("Error saving file.");
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task the task that was added
     */
    public void printTaskAdded(AbstractTask task) {
        printAndAddBreak("Got it. I've added this task:\n" + task.toString());
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public void printTaskDone(AbstractTask task) {
        printAndAddBreak("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task the task that was deleted
     * @param size the number of tasks remaining in the list
     */
    public void printTaskDeleted(AbstractTask task, int size) {
        printAndAddBreak("Noted. I've removed this task:\n"
                + task.toString() + "\nNow thou have " + size + " tasks in the list.");
    }

    /**
     * Prints a message indicating that a task has been unmarked.
     *
     * @param task the task that was unmarked
     */
    public void printTaskUndone(AbstractTask task) {
        printAndAddBreak("Pity! I've unmarked this task as done:\n" + task.toString());
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

    /**
     * Prints a message indicating that the task list is empty.
     *
     * @param tasks the TaskList
     */
    public void printTaskList(List<AbstractTask> tasks) {
        StringBuilder content = new StringBuilder();
        content.append("Here are thine search results:\n");
        for (int i = 0; i < tasks.size(); i++) {
            content.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        printAndAddBreak(content.toString());
    }

    /**
     * Displays all tasks in the provided TaskList.
     *
     * @param tasks the TaskList containing the tasks to display
     */
    public void showAllTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            String content = addBreak("There are no tasks in thine list.");
            queue.add(content);
            return;
        }

        printAndAddBreak("Here are the tasks in thine list:\n" + tasks.toString());
    }
}
