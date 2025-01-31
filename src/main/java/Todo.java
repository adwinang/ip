public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    protected String toMarkdownStringInternal(String details) {
        return super.toMarkdownStringInternal("T: " + details);
    }

    @Override
    public String toMarkdownString() {
        return this.toMarkdownStringInternal(this.description);
    }

}
