import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends AbstractTask {
    protected LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }

    public String getBy() {
        return DeadlineTask.parseDate(this.by);
    }

    @Override
    protected String toMarkdownStringInternal(String details) {
        return super.toMarkdownStringInternal("D: " + details);
    }

    public String toMarkdownString() {
        return this.toMarkdownStringInternal(this.description + " (by: " + this.getBy() + ")");
    }

    /**
     * Parse a markdown string into a Deadline object
     *
     * @param partialString The markdown string after the '- [ ] D: ' part
     * @return Returns Deadline or null if the string is not a valid Deadline
     */
    public static DeadlineTask parseString(String partialString){
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
