package Zephyr.Controllers;

import Zephyr.CommandTypes;
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
 * Zephyr.Controller.Parser class is responsible for parsing the user input into commands
 */
public class Parser {

    private final Ui ui;
    private final Storage storage;

    public Parser(Storage storage, Ui ui) {
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the user input into a command
     * @param input User input
     * @return Command object
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
