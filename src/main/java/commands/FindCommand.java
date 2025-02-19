package commands;

import java.util.List;

import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;
import exceptions.ZephyrException;
import tasks.AbstractTask;

/**
 * Represents a command to search for tasks based on a provided query.
 * The command filters the tasks in the TaskList whose descriptions contain
 * the search query and prints the matching tasks.
 */
public class FindCommand extends AbstractCommand {

    /**
     * Constructs a FindCommand with the specified search query.
     *
     * @param arguments the search query used to filter tasks
     */
    public FindCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the find command by searching for tasks that match the query.
     * This method retrieves tasks from the TaskList whose descriptions contain
     * the search query and then prints the found tasks using the Ui object.
     *
     * @param tasks   the TaskList to search within
     * @param ui      the Ui used to display the search results
     * @param storage the Storage object (not used in this command)
     * @throws ZephyrException if an error occurs during the search execution
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException {
        List<AbstractTask> foundTasks = tasks.findTasks(this.getArguments());
        ui.showTaskList(foundTasks);
    }

    /**
     * Validates the find command arguments.
     * This method ensures that a non-empty search query is provided.
     *
     * @throws ZephyrException if the search query is empty
     */
    @Override
    public void isValidCommand() throws ZephyrException {
        if (this.getArguments().isEmpty()) {
            throw new ZephyrException("The description of a find command cannot be empty.");
        }
    }
}
