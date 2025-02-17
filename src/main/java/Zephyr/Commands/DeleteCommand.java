package Zephyr.Commands;

import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;
import Zephyr.Exceptions.ZephyrException;

/**
 * Represents a command to delete a task from the task list.
 * The DeleteCommand removes a task based on its index
 * in the task list. The command expects a valid task number as input.
 * If the input is invalid or out of range, an exception is thrown.
 */
public class DeleteCommand extends AbstractCommand {

    /**
     * Constructs a DeleteCommand instance.
     * The command requires an integer argument representing the task index (1-based).
     *
     * @param arguments The task number to delete.
     */
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the delete command by removing a task from the task list.
     * This method validates the input, parses the task number, and removes
     * the corresponding task. If the task number is invalid or out of range,
     * an exception is thrown.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param ui      The Ui object responsible for user interaction.
     * @param storage The Storage object (not used in this command).
     * @throws ZephyrException if the task number is invalid or out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        int taskIndex = Integer.parseInt(this.arguments) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new ZephyrException("Task number out of range.");
        }
        tasks.deleteTask(taskIndex);
        ui.printTaskDeleted(tasks.getTask(taskIndex), tasks.getSize());
    }

    /**
     * Validates the DeleteCommand arguments.
     * This method ensures that the command contains a valid task number.
     * It checks whether the input is empty or contains non-numeric characters.
     *
     * @throws ZephyrException if the input is empty or not a valid integer.
     */
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