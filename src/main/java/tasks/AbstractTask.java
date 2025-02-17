package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An abstract representation of a task.
 * This class holds a description and a completion status.
 * Subclasses must implement methods to provide task-specific details.
 */
public abstract class AbstractTask {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs an AbstractTask with the given description.
     *
     * @param description the description of the task
     */
    public AbstractTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return a String representing the task type
     */
    public abstract String getTaskType();

    /**
     * Returns the status icon of the task.
     * "X" indicates that the task is done; otherwise, a blank space is returned.
     *
     * @return the status icon as a String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the DateTimeFormatter used for parsing and formatting dates.
     *
     * @return the DateTimeFormatter with the pattern "MMMM dd yyyy"
     */
    public static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("MMMM dd yyyy");
    }

    /**
     * Formats the given LocalDate using the defined formatter.
     *
     * @param date the LocalDate to format
     * @return a String representing the formatted date
     */
    public static String parseDate(LocalDate date) {
        return date.format(AbstractTask.getFormatter());
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a String representation of the task.
     * The format includes the status icon and the description.
     *
     * @return the String representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task to a markdown-formatted string.
     *
     * @param details the details to include in the markdown string
     * @return the markdown string representation of the task
     */
    protected String toMarkdownStringInternal(String details) {
        return "- [" + this.getStatusIcon() + "] " + details;
    }

    /**
     * Converts the task to a markdown-formatted string.
     *
     * @return the markdown string representation of the task
     */
    public abstract String toMarkdownString();
}
