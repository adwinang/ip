package Zephyr.Commands;

import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;

public class ByeCommand extends AbstractCommand {

    public ByeCommand(String arguments) {
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
