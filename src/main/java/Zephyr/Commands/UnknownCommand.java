package Zephyr.Commands;

import Zephyr.Controller.Storage;
import Zephyr.TaskList;
import Zephyr.Controller.Ui;
import Zephyr.Exceptions.ZephyrException;

public class UnknownCommand extends AbstractCommand {

    UnknownCommand(String arguments) {
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
