public class DeleteCommand extends AbstractCommand {

    DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        int taskIndex = Integer.parseInt(this.arguments) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new ZephyrException("Task number out of range.");
        }
        tasks.deleteTask(taskIndex);
    }

    @Override
    public void isValidCommand() {
        if (this.arguments.isEmpty()) {
            throw new ZephyrException("The description of a delete command cannot be empty.");
        }

        try {
            Integer.parseInt(this.arguments);
        } catch (NumberFormatException e) {
            throw new ZephyrException("Please enter a valid task number to delete.");
        }
    }
}
