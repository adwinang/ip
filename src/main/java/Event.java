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

}
