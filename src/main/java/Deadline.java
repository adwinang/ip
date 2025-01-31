public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    protected String toMarkdownStringInternal(String details) {
        return super.toMarkdownStringInternal("D: " + details);
    }

    public String toMarkdownString() {
        return this.toMarkdownStringInternal(this.description + " (by: " + this.by + ")");
    }

    /**
     * Parse a markdown string into a Deadline object
     *
     * @param partialString The markdown string after the '- [ ] D: ' part
     * @return Returns Deadline or null if the string is not a valid Deadline
     */
    public static Deadline parseString(String partialString) {
        String[] details = partialString.split(" \\(by: ", 2);
        if (details.length < 2) {
            return null;
        }
        String description = details[0];
        String by = details[1].substring(0, details[1].length() - 1);
        return new Deadline(description, by);
    }

}
