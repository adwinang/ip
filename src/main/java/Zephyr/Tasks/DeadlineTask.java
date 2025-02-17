package Zephyr.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * The deadline is specified by a date.
 */
public class DeadlineTask extends AbstractTask {
    protected LocalDate by;

    /**
     * Constructs a DeadlineTask with the given description and deadline.
     *
     * @param description the description of the task
     * @param by          the deadline date for the task
     */
    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of the deadline task.
     * The format includes the task type indicator, status, description, and deadline.
     *
     * @return the String representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }

    /**
     * Returns the deadline date of the task.
     *
     * @return the deadline as a LocalDate
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns the type of the task.
     *
     * @return the String "deadline"
     */
    @Override
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Converts the task to a markdown-formatted string with deadline details.
     *
     * @param details the details to include in the markdown string
     * @return the markdown string representation of the deadline task
     */
    @Override
    protected String toMarkdownStringInternal(String details) {
        return super.toMarkdownStringInternal("D: " + details);
    }

    /**
     * Converts the task to a markdown-formatted string.
     *
     * @return the markdown string representation of the deadline task
     */
    public String toMarkdownString() {
        return this.toMarkdownStringInternal(this.description + " (by: " + this.getBy() + ")");
    }

    /**
     * Parses a markdown string into a DeadlineTask object.
     *
     * @param partialString the markdown string after the "- [ ] D: " part
     * @return a DeadlineTask object if parsing is successful, or null if the string is not valid
     */
    public static DeadlineTask parseString(String partialString) {
        String[] details = partialString.split(" \\(by: ", 2);
        if (details.length < 2) {
            return null;
        }
        String description = details[0];
        String by = details[1].substring(0, details[1].length() - 1);
        try {
            LocalDate byDate = LocalDate.parse(by, AbstractTask.getFormatter());
            return new DeadlineTask(description, byDate);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}