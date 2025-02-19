package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end date.
 */
public class EventTask extends AbstractTask {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an EventTask with the given description, start date, and end date.
     *
     * @param description the description of the event
     * @param from        the start date of the event
     * @param to          the end date of the event
     */
    public EventTask(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String representation of the event task.
     * The format includes the task type indicator, status, description, start, and end dates.
     *
     * @return the String representation of the event task
     */
    @Override
    public String toString() {
        return this.toStringInternal("[E]",
                "(from: " + this.getFrom() + " at: " + this.getTo() + ")");
    }

    /**
     * Returns the type of the task.
     *
     * @return the String "event"
     */
    @Override
    public String getTaskType() {
        return "event";
    }

    /**
     * Returns the start date of the event.
     *
     * @return the start date as a LocalDate
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return the end date as a LocalDate
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Converts the task to a markdown-formatted string with event details.
     *
     * @param details the details to include in the markdown string
     * @return the markdown string representation of the event task
     */
    @Override
    protected String toMarkdownStringInternal(String details) {
        return super.toMarkdownStringInternal("E: " + details);
    }

    /**
     * Converts the task to a markdown-formatted string.
     *
     * @return the markdown string representation of the event task
     */
    @Override
    public String toMarkdownString() {
        return this.toMarkdownStringInternal(this.description
                + " (from: " + this.getFrom() + " at: " + this.getTo() + ")");
    }

    /**
     * Parses a markdown string into an EventTask object.
     * Returns null if the string is not a valid event.
     *
     * @param partialString the markdown string after the "- [ ] E: " part
     * @return an EventTask object if parsing is successful, or null if parsing fails
     */
    public static EventTask parseString(String partialString) {
        String[] details = partialString.split(" \\(from: ", 2);
        if (details.length < 2) {
            return null;
        }
        String description = details[0];
        String[] fromTo = details[1].split(" at: ", 2);
        if (fromTo.length < 2) {
            return null;
        }
        try {
            String from = fromTo[0];
            if (!fromTo[1].endsWith(")")) {
                return null;
            }
            LocalDate fromDate = LocalDate.parse(from, AbstractTask.getFormatter());
            String to = fromTo[1].substring(0, fromTo[1].length() - 1);
            LocalDate toDate = LocalDate.parse(to, AbstractTask.getFormatter());
            return new EventTask(description, fromDate, toDate);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
