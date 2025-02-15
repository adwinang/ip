package Zephyr.Commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import Zephyr.Tasks.EventTask;
import Zephyr.Controller.Storage;
import Zephyr.TaskList;
import Zephyr.Controller.Ui;
import Zephyr.Exceptions.ZephyrException;

public class EventCommand extends AbstractCommand {
    EventCommand(String arguments) {
        super(arguments);
    }

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
