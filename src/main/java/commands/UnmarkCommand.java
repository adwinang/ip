package zephyr.Commands;

import zephyr.Tasks.AbstractTask;
import zephyr.Controllers.Storage;
import zephyr.DataStructures.TaskList;
import zephyr.Controllers.Ui;
import zephyr.Exceptions.ZephyrException;

/**
 * Represents a command to mark a task as not done in the task list.
 * This command expects a single argument representing the task number.
 * It marks the corresponding task as undone and prints a confirmation message.
 */
public class UnmarkCommand extends AbstractCommand {

    /**
     * Constructs an UnmarkCommand with the specified arguments.
     *
     * @param arguments the task number to unmark
     */
    public UnmarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the unmark command by marking the specified task as undone.
     * This method validates the command arguments, parses the task number,
     * marks the task as undone, and prints a confirmation message.
     * If the task number is out of range, a ZephyrException is thrown.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the UI used for user interaction
     * @param storage the Storage (not used in this command)
     * @throws ZephyrException if the task number is invalid or out of range
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            isValidCommand();
            int index = Integer.parseInt(this.words[0]);
            AbstractTask task = tasks.getTask(index - 1);
            task.markAsUndone();
            ui.printTaskUndone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new ZephyrException("Task number out of range.");
        }
    }

    /**
     * Validates the UnmarkCommand arguments.
     * This method checks that the arguments are not empty,
     * that exactly one argument is provided, and that the argument is a valid integer.
     *
     * @throws ZephyrException if the arguments are empty, contain more than one element,
     * or if the task number is not a valid integer
     */
    @Override
    public void isValidCommand() throws ZephyrException {
        if (this.arguments.isEmpty()) {
            throw new ZephyrException("Please enter a task number to unmark.");
        }
        if (this.words.length != 1) {
            throw new ZephyrException("There are more arguments than expected.");
        }
        try {
            Integer.parseInt(this.words[0]);
        } catch (NumberFormatException e) {
            throw new ZephyrException("Please enter a valid task number to unmark.");
        }
    }
}