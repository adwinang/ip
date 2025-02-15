package Zephyr.Commands;

import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;
import Zephyr.Exceptions.ZephyrException;

public class UnknownCommand extends AbstractCommand {

    public UnknownCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printUnknown();
    }

    @Override
    public void isValidCommand() {
        throw new ZephyrException("Unknown command.");
    }

}
