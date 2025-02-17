package Zephyr.Enums;

/**
 * Represents the available command types for the application.
 * Each command type is associated with a string value.
 */
public enum CommandTypes {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    UPCOMING("upcoming"),
    BYE("bye"),
    UNKNOWN("unknown");

    private final String value;

    /**
     * Constructs a CommandTypes enum with the specified string value.
     *
     * @param value the string representation of the command type
     */
    CommandTypes(String value) {
        this.value = value;
    }

    /**
     * Returns the CommandTypes enum that matches the given string value.
     * The comparison is case-insensitive.
     *
     * @param value the string representation of the command type
     * @return the matching CommandTypes enum, or UNKNOWN if no match is found
     */
    public static CommandTypes fromValue(String value) {
        for (CommandTypes command : CommandTypes.values()) {
            if (command.value.equalsIgnoreCase(value)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}