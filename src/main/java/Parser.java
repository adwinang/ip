/**
 * Parser class is responsible for parsing the user input into commands
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
            case CommandTypes.LIST -> new ListCommand(arguments);
            case CommandTypes.MARK -> new MarkCommand(arguments);
            case CommandTypes.UNMARK -> new UnmarkCommand(arguments);
            case CommandTypes.TODO -> new TodoCommand(arguments);
            case CommandTypes.DEADLINE -> new DeadlineCommand(arguments);
            case CommandTypes.EVENT -> new EventCommand(arguments);
            case CommandTypes.DELETE -> new DeleteCommand(arguments);
            case CommandTypes.UPCOMING -> new UpcomingCommand(arguments);
            case CommandTypes.BYE -> new ByeCommand(arguments);
            default -> new UnknownCommand(arguments);
        };
    }

}
