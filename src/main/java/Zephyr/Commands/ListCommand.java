package Zephyr.Commands;

import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;

public class ListCommand extends AbstractCommand {

    public ListCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        ui.showAllTasks(tasks);
    }

    @Override
    public void isValidCommand() {}


}
