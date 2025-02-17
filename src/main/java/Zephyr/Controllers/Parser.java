package Zephyr.Controllers;

import Zephyr.Enums.CommandTypes;
import Zephyr.Commands.AbstractCommand;
import Zephyr.Commands.ByeCommand;
import Zephyr.Commands.DeadlineCommand;
import Zephyr.Commands.DeleteCommand;
import Zephyr.Commands.EventCommand;
import Zephyr.Commands.ListCommand;
import Zephyr.Commands.MarkCommand;
import Zephyr.Commands.TodoCommand;
import Zephyr.Commands.UnknownCommand;
import Zephyr.Commands.UnmarkCommand;
import Zephyr.Commands.UpcomingCommand;

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
            case BYE -> new ByeCommand(arguments);
            default -> new UnknownCommand(arguments);
        };
    }
}