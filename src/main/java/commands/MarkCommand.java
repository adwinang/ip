package zephyr.Commands;

import zephyr.Tasks.AbstractTask;
import zephyr.Controllers.Storage;
import zephyr.DataStructures.TaskList;
import zephyr.Controllers.Ui;
import zephyr.Exceptions.ZephyrException;

/**
 * Represents a command to mark a task as done in the task list.
 * This command expects a single argument representing the task number.
 * It marks the corresponding task as completed and notifies the user.
 */
public class MarkCommand extends AbstractCommand {

    /**
     * Constructs a MarkCommand instance with the specified arguments.
     *
     * @param arguments the arguments passed to the command (should be a single task number)
     */
    public MarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the mark command by marking the specified task as done.
     * This method validates the command arguments, parses the task number, marks the task as done,
     * and uses the UI to print a confirmation message.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui object used to interact with the user
     * @param storage the Storage object (not used in this command)
     * @throws ZephyrException if the command is invalid or the task number is not valid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException {
        isValidCommand();
        int index = Integer.parseInt(this.words[0]);
        AbstractTask task = tasks.getTask(index - 1);
        task.markAsDone();
        ui.printTaskDone(task);
    }

    /**
     * Validates the MarkCommand arguments.
     * This method checks that the arguments are not empty, that exactly one argument is provided,
     * and that the provided argument can be parsed as an integer.
     *
     * @throws ZephyrException if the arguments are empty, contain more than one element,
     * or if the task number is not a valid integer
     */
    @Override
    public void isValidCommand() throws ZephyrException {
        if (this.arguments.isEmpty()) {
            throw new ZephyrException("Please enter a task number to mark as done.");
        }
        if (this.words.length != 1) {
            throw new ZephyrException("There are more arguments than expected.");
        }
        try {
            Integer.parseInt(this.words[0]);
        } catch (NumberFormatException e) {
            throw new ZephyrException("Please enter a valid task number to mark as done.");
        }
    }
}