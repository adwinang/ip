package Zephyr.Commands;

import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * The {@code ListCommand} displays all tasks to the user. It does not require any
 * additional arguments and simply delegates to the UI to show the tasks.
 */
public class ListCommand extends AbstractCommand {

    /**
     * Constructs a {@code ListCommand} instance.
     * This command does not require any additional arguments.
     *
     * @param arguments the arguments passed with the command (expected to be empty).
     */
    public ListCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the list command by displaying all tasks in the task list.
     * This method validates the command (although no validation is needed for listing)
     * and then calls the {@link Ui#showAllTasks(TaskList)} method to display the tasks.
     *
     * @param tasks   the {@link TaskList} containing the tasks to be listed.
     * @param ui      the {@link Ui} object responsible for user interaction.
     * @param storage the {@link Storage} object (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        ui.showAllTasks(tasks);
    }

    /**
     * Validates the {@code ListCommand} arguments.
     * Since the list command does not require any arguments, no validation is performed.
     */
    @Override
    public void isValidCommand() {
        // No validation needed for ListCommand as it does not require any arguments.
    }
}