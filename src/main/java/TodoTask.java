public class TodoTask extends Task {
    public TodoTask(String description) {
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

    /**
     * Parse a markdown string into a Todo object
     *
     * @param partialString The markdown string after the '- [ ] T: ' part
     * @return Returns Todo
     */
    static public TodoTask parseString(String partialString) {
        return new TodoTask(partialString);
    }
}
