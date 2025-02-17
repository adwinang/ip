package zephyr.Controllers;

import zephyr.Enums.CommandTypes;
import zephyr.Commands.AbstractCommand;
import zephyr.Commands.ByeCommand;
import zephyr.Commands.DeadlineCommand;
import zephyr.Commands.DeleteCommand;
import zephyr.Commands.EventCommand;
import zephyr.Commands.ListCommand;
import zephyr.Commands.MarkCommand;
import zephyr.Commands.TodoCommand;
import zephyr.Commands.UnknownCommand;
import zephyr.Commands.UnmarkCommand;
import zephyr.Commands.UpcomingCommand;
import zephyr.Commands.FindCommand;

/**
 * A parser that converts user input into corresponding command objects.
 */
public class Parser {

    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a new Parser with the given storage and user interface.
     *
     * @param storage the Storage object used for file operations
     * @param ui      the Ui object used for user interaction
     */
    public Parser(Storage storage, Ui ui) {
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the given user input string into an appropriate command.
     *
     * @param input the user input
     * @return an AbstractCommand corresponding to the input
     */
    public AbstractCommand parse(String input) {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0];
        String arguments = tokens.length > 1 ? tokens[1] : "";
        return switch (CommandTypes.fromValue(command)) {
            case LIST -> new ListCommand(arguments);
            case MARK -> new MarkCommand(arguments);
            case UNMARK -> new UnmarkCommand(arguments);
            case TODO -> new TodoCommand(arguments);
            case DEADLINE -> new DeadlineCommand(arguments);
            case EVENT -> new EventCommand(arguments);
            case DELETE -> new DeleteCommand(arguments);
            case UPCOMING -> new UpcomingCommand(arguments);
            case FIND -> new FindCommand(arguments);
            case BYE -> new ByeCommand(arguments);
            default -> new UnknownCommand(arguments);
        };
    }
}