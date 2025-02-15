package Zephyr.Commands;

import Zephyr.Controller.Storage;
import Zephyr.TaskList;
import Zephyr.Controller.Ui;

public class ListCommand extends AbstractCommand {

    ListCommand(String arguments) {
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
