package zephyr.Commands;

import zephyr.Controllers.Storage;
import zephyr.DataStructures.TaskList;
import zephyr.Controllers.Ui;
import zephyr.Exceptions.ZephyrException;

/**
 * Represents an unknown command.
 * This command is used when the user input does not match any known command.
 * Executing this command results in printing an unknown command message.
 */
public class UnknownCommand extends AbstractCommand {

    /**
     * Constructs an UnknownCommand with the specified arguments.
     *
     * @param arguments the command arguments
     */
    public UnknownCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the unknown command.
     * Calls the UI to print an unknown command message.
     *
     * @param tasks   the TaskList (not used in this command)
     * @param ui      the UI used for user interaction
     * @param storage the Storage (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printUnknown();
    }

    /**
     * Validates the unknown command.
     * Always throws a ZephyrException to indicate that the command is unknown.
     *
     * @throws ZephyrException always thrown to indicate the command is unknown
     */
    @Override
    public void isValidCommand() {
        throw new ZephyrException("Unknown command.");
    }
}