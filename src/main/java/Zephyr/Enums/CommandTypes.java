package Zephyr;

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

    // Constructor to associate string values
    CommandTypes(String value) {
        this.value = value;
    }

    // Method to get enum from string
    public static CommandTypes fromValue(String value) {
        for (CommandTypes command : CommandTypes.values()) {
            if (command.value.equalsIgnoreCase(value)) {
                return command;
            }
        }
        return UNKNOWN;
    }

}
