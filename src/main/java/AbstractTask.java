import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractTask {
    protected String description;
    protected boolean isDone;

    public AbstractTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    static public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("MMMM dd yyyy");
    }

    static public String parseDate(LocalDate date) {
        return date.format(AbstractTask.getFormatter());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * An internal function to convert the task to a markdown string
     * @param details The details to include in the markdown string
     * @return The markdown string
     */
    protected String toMarkdownStringInternal(String details) {
        return "- [" + this.getStatusIcon() + "] " + details;
    }

    public abstract String toMarkdownString();
}
