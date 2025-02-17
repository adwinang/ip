package commands;

import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;

/**
 * Represents the command to terminate the application.
 * The {@code ByeCommand} class handles the termination process by
 * displaying a goodbye message through the UI. When executed, it ensures
 * that the command is valid and then signals the program to exit.
 */
public class ByeCommand extends AbstractCommand {

    /**
     * Constructs a ByeCommand instance.
     * This command does not require any arguments but follows the standard command format.
     *
     * @param arguments The arguments passed to the command (expected to be empty).
     */
    public ByeCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the Bye command.
     * This method verifies that the command is valid and then
     * calls the UI to display the goodbye message.
     *
     * @param tasks   The TaskList object (not used in this command).
     * @param ui      The Ui object to handle user interaction.
     * @param storage The Storage object (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        ui.printGoodbye();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, since this command terminates the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Validates the Bye command.
     * Since this command does not require any arguments, no additional validation is necessary.
     */
    @Override
    public void isValidCommand() {
        // No validation needed for the Bye command.
    }
}
