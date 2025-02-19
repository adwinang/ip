package commands;

import java.util.Arrays;

import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;
import exceptions.ZephyrException;
import tasks.AbstractTask;

/**
 * Tag Command adds tag to the task itself for better tracking
 */
public class TagCommand extends AbstractCommand {

    public TagCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command
     *
     * @param tasks   The TaskList object
     * @param ui      The Ui object
     * @param storage Storage object
     * @throws ZephyrException if the command is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException {
        isValidCommand();
        int taskNumber = Integer.parseInt(this.getWords()[0]) - 1;
        if (taskNumber > tasks.getSize()) {
            throw new ZephyrException("Task Number is out of range");
        }
        AbstractTask task = tasks.getTask(taskNumber);
        String[] tags = Arrays.copyOfRange(this.getWords(), 1, this.getWords().length);
        // Exclude the first element as it is the task number
        task.addTags(tags);

        ui.showAndAddBreak("Thou has tagged task " + (taskNumber + 1)
                + " with " + String.join(" ", tags));
    }

    /**
     * Checks if remaining arguments are valid
     * It is not context aware, meaning it does not check if
     * the arguments are valid for TaskList, Ui or Storage
     *
     * @throws ZephyrException if the command is invalid
     */
    @Override
    public void isValidCommand() throws ZephyrException {
        if (this.getWords().length < 2) {
            throw new ZephyrException("Invalid tag command, make sure to input as such 'tag <task number> <...tags>'");
        }

        try {
            int taskNumber = Integer.parseInt(this.getWords()[0]);
            if (taskNumber - 1 < 0) {
                throw new ZephyrException("Task Number need to be 1 and above");
            }

        } catch (NumberFormatException e) {
            throw new ZephyrException("Please enter a valid task number for "
                    + "first argument ('tag <task number> <...tags>')");
        }
    }
}
