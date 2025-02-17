package Zephyr.Commands;

import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;
import Zephyr.Exceptions.ZephyrException;

/**
 * Abstract class for all commands
 */
public abstract class AbstractCommand {
    /**
     * Arguments of the command, excludes the command itself
     */
    String arguments;
    String[] words;

    /**
     * Constructor for AbstractCommand
     * Words array is populated with the arguments split by spaces
     *
     * @param arguments Arguments of the command
     */
    AbstractCommand(String arguments) {
        this.arguments = arguments;
        this.words = this.arguments.split(" ");
    }

    /**
     * Executes the command
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @throws ZephyrException if the command is invalid
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ZephyrException;


    /**
     * Checks if the command is an exit command
     * @return true if the command is an exit type command, false otherwise
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if remaining arguments are valid
     * It is not context aware, meaning it does not check if the arguments are valid for Zephyr.TaskList, Zephyr.Controller.Ui or Zephyr.Controller.Storage
     * @throws ZephyrException if the command is invalid
     */
    public abstract void isValidCommand() throws ZephyrException;

}
