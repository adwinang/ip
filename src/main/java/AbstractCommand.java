public abstract class AbstractCommand {
    String arguments;
    String[] words;

    AbstractCommand(String arguments) {
        this.arguments = arguments;
        this.words = this.arguments.split(" ");
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException;

    public boolean isExit() {
        return false;
    }

    /**
     * Checks if line is a valid command
     * Line excludes the actual command
     * Throws a ZephyrException if the command is invalid
     *
     */
    public abstract void isValidCommand() throws ZephyrException;

}
