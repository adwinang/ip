package commands;

import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;
import exceptions.ZephyrException;
import tasks.AbstractTask;
import tasks.TodoTask;

/**
 * Represents a command to add a TodoTask to the task list.
 * This command creates a new TodoTask using the provided description,
 * adds it to the task list, and prints a confirmation message to the user.
 */
public class TodoCommand extends AbstractCommand {

    /**
     * Constructs a TodoCommand with the specified arguments.
     *
     * @param arguments the description of the todo task
     */
    public TodoCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the todo command.
     * This method validates the command arguments, creates a new TodoTask,
     * adds it to the task list, and prints a confirmation message.
     *
     * @param tasks   the TaskList where the new task will be added
     * @param ui      the Ui used for user interaction
     * @param storage the Storage (not used in this command)
     * @throws ZephyrException if the command arguments are invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException {
        isValidCommand();
        AbstractTask newTask = new TodoTask(this.getArguments());
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask);
    }

    /**
     * Validates the TodoCommand arguments.
     * Checks that the description provided for the todo task is not empty.
     *
     * @throws ZephyrException if the description is empty
     */
    @Override
    public void isValidCommand() {
        if (this.getArguments().isEmpty()) {
            throw new ZephyrException("The description of a todo cannot be empty.");
        }
    }
}
