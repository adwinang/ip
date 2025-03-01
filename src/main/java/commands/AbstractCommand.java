package commands;

import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;
import exceptions.ZephyrException;

/**
 * Abstract class for all commands
 */
public abstract class AbstractCommand {
    /**
     * Arguments of the command, excludes the command itself
     */
    private String arguments;
    private String[] words;

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
     * @param tasks The TaskList object
     * @param ui The Ui object
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
     * It is not context aware, meaning it does not check if
     * the arguments are valid for TaskList, Ui or Storage
     * @throws ZephyrException if the command is invalid
     */
    public abstract void isValidCommand() throws ZephyrException;

    public String getArguments() {
        return arguments;
    }

    public String[] getWords() {
        return words;
    }

}
