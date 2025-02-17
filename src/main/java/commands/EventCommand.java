package zephyr.Commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import zephyr.Tasks.EventTask;
import zephyr.Controllers.Storage;
import zephyr.DataStructures.TaskList;
import zephyr.Controllers.Ui;
import zephyr.Exceptions.ZephyrException;

/**
 * Represents a command to add an EventTask to the task list.
 * The {@code EventCommand} parses the command arguments to extract the event's description,
 * start date (indicated by the "/from" keyword), and end date (indicated by the "/at" keyword).
 * The expected format for the command arguments is:
 * <description> /from <start date> /at <end date>.
 */
public class EventCommand extends AbstractCommand {

    /**
     * Constructs an EventCommand instance with the specified arguments.
     *
     * @param arguments the raw command arguments in the format
     *                  <description> /from <start date> /at <end date>.
     */
    public EventCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the event command by creating an EventTask and adding it to the task list.
     * The method first validates the command arguments. It then splits the input into the event
     * description and the date components using the "/from" and "/at" keywords. The dates are parsed
     * using the Ui.parseDate method. If parsing is successful, an EventTask is created,
     * added to the task list, and a confirmation is displayed via the Ui. If a date cannot be parsed,
     * a ZephyrException is thrown.
     *
     * @param tasks   the TaskList to which the new event task is added.
     * @param ui      the Ui used for interacting with the user and parsing dates.
     * @param storage the Storage (not used in this command).
     * @throws ZephyrException if the command is invalid or the date format is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        String[] tokens = this.arguments.split(" /from ", 2);
        String description = tokens[0];
        String[] fromAndAt = tokens[1].split(" /at ", 2);
        try {
            LocalDate fromDate = ui.parseDate(fromAndAt[0]);
            LocalDate atDate = ui.parseDate(fromAndAt[1]);
            EventTask event = new EventTask(description, fromDate, atDate);
            tasks.addTask(event);
            ui.printTaskAdded(event);
        } catch (DateTimeParseException ex) {
            throw new ZephyrException("Please enter a valid date in the format 'dd MMM YYYY'.");
        }
    }

    /**
     * Validates the EventCommand arguments.
     * This method checks that the command arguments are not blank and that they contain the required
     * "/from" and "/at" keywords to separate the event description from the date values.
     * It also ensures that after splitting the arguments, both the start date and end date are provided.
     *
     * @throws ZephyrException if the command arguments are blank, or if they do not contain the required
     *                         keywords, or if the date components are missing.
     */
    @Override
    public void isValidCommand() {
        if (this.arguments.isBlank()) {
            throw new ZephyrException("The description of an event cannot be empty.");
        }
        if (!this.arguments.contains("/from")) {
            throw new ZephyrException("The event command must contain '/from'.");
        }
        if (!this.arguments.contains("/at")) {
            throw new ZephyrException("The event command must contain '/at'.");
        }

        String[] tokens = this.arguments.split(" /from ", 2);
        if (tokens.length < 2) {
            throw new ZephyrException("The event command must contain a date.");
        }

        String[] fromAndAt = tokens[1].split(" /at ", 2);
        if (fromAndAt.length < 2) {
            throw new ZephyrException("The event command must contain an at date.");
        }
    }
}