public abstract class AbstractCommand {
    /**
     * Arguments of the command, excludes the command itself
     */
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
     * Checks if remaining arguments are valid
     * It is not context aware, meaning it does not check if the arguments are valid for TaskList, Ui or Storage
     * Throws a ZephyrException if the command is invalid
     */
    public abstract void isValidCommand() throws ZephyrException;

}
