package commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;
import exceptions.ZephyrException;
import helpers.StandardDateTime;
import tasks.EventTask;

/**
 * Represents a command to add an EventTask to the task list.
 * The {@code EventCommand} parses the command arguments to extract the event's description,
 * start date (indicated by the "/from" keyword), and end date (indicated by the "/to" keyword).
 * The expected format for the command arguments is:
 * "[description] /from [start date] /to [end date]"
 */
public class EventCommand extends AbstractCommand {

    /**
     * Constructs an EventCommand instance with the specified arguments.
     * [description] /from [start date] /to [end date]
     *
     * @param arguments the raw command arguments in the format
     */
    public EventCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the event command by creating an EventTask and adding it to the task list.
     * The method first validates the command arguments. It then splits the input into the event
     * description and the date components using the "/from" and "/to" keywords. The dates are parsed
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
        String[] tokens = getArguments().split(" /from ", 2);
        assert tokens.length > 1;
        String description = tokens[0];
        String[] fromAndAt = tokens[1].split(" /to ", 2);
        assert fromAndAt.length > 1;
        try {
            LocalDate fromDate = StandardDateTime.parseDateString(fromAndAt[0]);
            LocalDate atDate = StandardDateTime.parseDateString(fromAndAt[1]);
            if (atDate.isBefore(fromDate)) {
                throw new ZephyrException("To date cannot be earlier than from date");
            }
            EventTask event = new EventTask(description, fromDate, atDate);
            tasks.addTask(event);
            ui.showTaskAdded(event);
        } catch (DateTimeParseException ex) {
            throw new ZephyrException("Please enter a valid date in the format 'dd MMM YYYY' / '21 Feb 2025'.");
        }
    }

    /**
     * Validates the EventCommand arguments.
     * This method checks that the command arguments are not blank and that they contain the required
     * "/from" and "/to" keywords to separate the event description from the date values.
     * It also ensures that after splitting the arguments, both the start date and end date are provided.
     *
     * @throws ZephyrException if the command arguments are blank, or if they do not contain the required
     *                         keywords, or if the date components are missing.
     */
    @Override
    public void isValidCommand() {
        if (getArguments().isBlank()) {
            throw new ZephyrException("The description of an event cannot be empty.");
        }
        if (!getArguments().contains("/from")) {
            throw new ZephyrException("The event command must contain '/from'.");
        }
        if (!getArguments().contains("/to")) {
            throw new ZephyrException("The event command must contain '/to'.");
        }

        String[] tokens = getArguments().split(" /from ", 2);
        if (tokens.length < 2) {
            throw new ZephyrException("The event command must contain a date.");
        }

        String[] fromAndAt = tokens[1].split(" /to ", 2);
        if (fromAndAt.length < 2) {
            throw new ZephyrException("The event command must contain an to date.");
        }

    }
}
