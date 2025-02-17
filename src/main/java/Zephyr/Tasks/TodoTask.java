package Zephyr.Tasks;

/**
 * Represents a todo task.
 */
public class TodoTask extends AbstractTask {
    /**
     * Constructs a TodoTask with the given description.
     *
     * @param description the description of the todo task
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the todo task.
     * The format includes the task type indicator and the task details.
     *
     * @return the String representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the type of the task.
     *
     * @return the String "todo"
     */
    @Override
    public String getTaskType() {
        return "todo";
    }

    /**
     * Converts the task to a markdown-formatted string with todo details.
     *
     * @param details the details to include in the markdown string
     * @return the markdown string representation of the todo task
     */
    @Override
    protected String toMarkdownStringInternal(String details) {
        return super.toMarkdownStringInternal("T: " + details);
    }

    /**
     * Converts the task to a markdown-formatted string.
     *
     * @return the markdown string representation of the todo task
     */
    @Override
    public String toMarkdownString() {
        return this.toMarkdownStringInternal(this.description);
    }

    /**
     * Parses a markdown string into a TodoTask object.
     *
     * @param partialString the markdown string after the "- [ ] T: " part
     * @return a TodoTask object created from the given markdown string
     */
    static public TodoTask parseString(String partialString) {
        return new TodoTask(partialString);
    }
}