package Zephyr.Commands;

import Zephyr.Tasks.AbstractTask;
import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;
import Zephyr.Exceptions.ZephyrException;

public class MarkCommand extends AbstractCommand {
    public MarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException {
        isValidCommand();
        int index = Integer.parseInt(this.words[0]);
        AbstractTask task = tasks.getTask(index - 1);
        task.markAsDone();
        ui.printTaskDone(task);
    }

    @Override
    public void isValidCommand() throws ZephyrException {
        // Split line into array of String
        if (this.arguments.isEmpty()) {
            throw new ZephyrException("Please enter a task number to mark as done.");
        }

        if (this.words.length != 1) {
            throw new ZephyrException("There are more arguments than expected.");
        }

        // Convert first word to integer
        try {
            Integer.parseInt(this.words[0]);
        } catch (NumberFormatException e) {
            throw new ZephyrException("Please enter a valid task number to mark as done.");
        }

    }

}
