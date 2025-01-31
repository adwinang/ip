public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " at: " + this.to + ")";
    }

    @Override
    protected String toMarkdownStringInternal(String details) {
        return super.toMarkdownStringInternal("E: " + details);
    }

    @Override
    public String toMarkdownString() {
        return this.toMarkdownStringInternal(this.description + " (from: " + this.from + " at: " + this.to + ")");
    }

    /**
     * Parse a markdown string into an Event object
     * Returns null if the string is not a valid Event
     *
     * @param partialString The markdown string to parse after the '- [ ] E: ' part
     * @return Returns Event or null if the string is not a valid Event
     */
    public static Event parseString(String partialString) {
        String[] details = partialString.split(" \\(from: ", 2);
        if (details.length < 2) {
            return null;
        }
        String description = details[0];
        String[] fromTo = details[1].split(" at: ", 2);
        if (fromTo.length < 2) {
            return null;
        }
        String from = fromTo[0];
        if (!fromTo[1].endsWith(")")) {
            return null;
        }
        String to = fromTo[1].substring(0, fromTo[1].length() - 1);
        return new Event(description, from, to);
    }
}
