package Zephyr.Commands;

import Zephyr.Controller.Storage;
import Zephyr.TaskList;
import Zephyr.Controller.Ui;
import Zephyr.Exceptions.ZephyrException;

public abstract class AbstractCommand {
    /**
     * Arguments of the command, excludes the command itself
     */
    String arguments;
    String[] words;

    AbstractCommand(String arguments) {
        this.arguments = arguments;
        this.words = this.arguments.split(" ");
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException;

    public boolean isExit() {
        return false;
    }

    /**
     * Checks if remaining arguments are valid
     * It is not context aware, meaning it does not check if the arguments are valid for Zephyr.TaskList, Zephyr.Controller.Ui or Zephyr.Controller.Storage
     * Throws a Zephyr.Exceptions.ZephyrException if the command is invalid
     */
    public abstract void isValidCommand() throws ZephyrException;

}
