package Zephyr.Commands;

import Zephyr.Tasks.AbstractTask;
import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Tasks.TodoTask;
import Zephyr.Controllers.Ui;
import Zephyr.Exceptions.ZephyrException;

public class TodoCommand extends AbstractCommand {
    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException {
        isValidCommand();
        AbstractTask newTask = new TodoTask(arguments);
        tasks.addTask(newTask);
        ui.printTaskAdded(newTask);
    }

    @Override
    public void isValidCommand() {
        if (arguments.isEmpty()) {
            throw new ZephyrException("The description of a todo cannot be empty.");
        }
    }

}
