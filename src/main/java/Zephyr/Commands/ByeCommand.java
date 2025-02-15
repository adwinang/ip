package Zephyr.Commands;

import Zephyr.Controller.Storage;
import Zephyr.TaskList;
import Zephyr.Controller.Ui;

public class ByeCommand extends AbstractCommand {

    ByeCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void isValidCommand() {}

}
