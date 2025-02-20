package controllers;

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
    // CHECKSTYLE:ON: AbbreviationAsWordInName
    private final Queue<String> queue = new LinkedList<>();

    /**
     * Constructs a new Ui object using standard input and output.
     */
    public Ui() {
        this.scanner = new FastScanner();
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
     * Add multiple messages to queue
     * @param messages vararg format
     */
    public void addMultiple(Object... messages) {
        for (Object message : messages) {
            queue.add(String.valueOf(message));
        }
    }

    /**
     * Add to queue
     *
     * @param message the object whose string representation is printed
     */
    public void addQueue(Object message) {
        // String.valueOf() is used to handle null parameters
        // https://stackoverflow.com/questions/27465731/string-valueof-vs-object-tostring
        String strMessage = String.valueOf(message);
        queue.add(strMessage);
    }

    /**
     * Show the welcome message when the program starts.
     */
    public void showWelcome() {
        String content = "Hello! I'm app.Zephyr\nWhat can I do for thou?";
        addQueue(content);
    }

    /**
     * Show the goodbye message when the program ends.
     */
    public void showGoodbye() {
        String content = "Goodbye! May thou have a safe journey ahead.";
        addQueue(content);
    }

    /**
     * Show a message indicating that the command is unknown.
     */
    public void showUnknown() {
        String content = """
                I do not understand what thou art saying.
                Please enter a valid command using the follow:
                1. list
                2. find <keyword>
                3. mark <task number>
                4. unmark <task number>
                5. tag <task number> <...tag>
                6. todo <task description>
                7. deadlineTask <task description> /by <deadlineTask>
                8. eventTask <task description> /from <start time> /to <end time>
                9. upcoming <task type> <days>
                10. delete <task number>
                11. bye - To exit the programme""";
        queue.add(content);
    }

    /**
     * Show an error message indicating a problem occurred while loading a file.
     */
    public void showLoadingError() {
        addQueue("Error loading file. Creating new file.");
    }

    /**
     * Show an error message indicating a problem occurred while saving a file.
     */
    public void showSavingError() {
        addQueue("Error saving file.");
    }

    /**
     * Show a message indicating that a task has been added.
     *
     * @param task the task that was added
     */
    public void showTaskAdded(AbstractTask task) {
        addQueue("Got it. I've added this task:\n" + task.toString());
    }

    /**
     * Show a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public void showTaskDone(AbstractTask task) {
        addQueue("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Show a message indicating that a task has been deleted.
     *
     * @param task the task that was deleted
     * @param size the number of tasks remaining in the list
     */
    public void showTaskDeleted(AbstractTask task, int size) {
        addQueue("Noted. I've removed this task:\n"
                + task.toString() + "\nNow thou have " + size + " tasks in the list.");
    }

    /**
     * Show a message indicating that a task has been unmarked.
     *
     * @param task the task that was unmarked
     */
    public void showTaskUndone(AbstractTask task) {
        addQueue("Pity! I've unmarked this task as done:\n" + task.toString());
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
     * Show a message indicating that the task list is empty.
     *
     * @param tasks the TaskList
     */
    public void showTaskList(List<AbstractTask> tasks) {
        StringBuilder content = new StringBuilder();
        content.append("Here are thine search results:\n");
        for (int i = 0; i < tasks.size(); i++) {
            content.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        addQueue(content.toString());
    }

    /**
     * Displays all tasks in the provided TaskList.
     *
     * @param tasks the TaskList containing the tasks to display
     */
    public void showAllTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            String content = "There are no tasks in thine list.";
            queue.add(content);
            return;
        }

        addQueue("Here are the tasks in thine list:\n" + tasks.toString());
    }

    /**
     * Show that the task has been tagged
     * @param taskNumber User Input for Task Number (starts from 1)
     * @param tags Array of tags that is added
     */
    public void showTaggingOfTask(Integer taskNumber, String[] tags) {
        this.addQueue("Thou has tagged task " + (taskNumber + 1) + " with " + String.join(" ", tags));
    }

    /**
     * Show that there is no upcoming tasks
     * @param taskType user input for request of task type
     * @param days number of days in the range
     */
    public void showNoUpcomingTasks(String taskType, Integer days) {
        queue.add("Thou have no upcoming \" + taskType + \" tasks within the next \" + days + \" days.");
    }
}
