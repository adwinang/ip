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

}
