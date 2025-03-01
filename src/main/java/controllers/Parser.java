package controllers;

import commands.AbstractCommand;
import commands.ByeCommand;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TagCommand;
import commands.TodoCommand;
import commands.UnknownCommand;
import commands.UnmarkCommand;
import commands.UpcomingCommand;
import enums.CommandTypes;


/**
 * A parser that converts user input into corresponding command objects.
 */
public class Parser {

    /**
     * Constructs a new Parser with the given storage and user interface.
     *
     */
    public Parser() {}

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
        case TAG -> new TagCommand(arguments);
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
