package Zephyr.Commands;

import Zephyr.Tasks.AbstractTask;
import Zephyr.Controller.Storage;
import Zephyr.TaskList;
import Zephyr.Controller.Ui;
import Zephyr.Exceptions.ZephyrException;

public class UnmarkCommand extends AbstractCommand {

    UnmarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            isValidCommand();
            int index = Integer.parseInt(this.words[0]);
            AbstractTask task = tasks.getTask(index - 1);

            ui.printTaskUndone(task);

        } catch (IndexOutOfBoundsException e) {
            throw new ZephyrException("Task number out of range.");
        }
    }

    @Override
    public void isValidCommand() throws ZephyrException {
        if (this.arguments.isEmpty()) {
            throw new ZephyrException("Please enter a task number to unmark.");
        }

        if (this.words.length != 1) {
            throw new ZephyrException("There are more arguments than expected.");
        }

        try {
            Integer.parseInt(this.words[0]);
        } catch (NumberFormatException e) {
            throw new ZephyrException("Please enter a valid task number to unmark.");
        }
    }

}
