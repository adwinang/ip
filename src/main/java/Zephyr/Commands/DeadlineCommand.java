package Zephyr.Commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import Zephyr.Tasks.DeadlineTask;
import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;
import Zephyr.Exceptions.ZephyrException;

public class DeadlineCommand extends AbstractCommand {
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        String[] tokens = this.arguments.split(" /by ", 2);
        String description = tokens[0];
        String by = tokens[1];

        try {
            LocalDate byDate = ui.parseDate(by);
            DeadlineTask task = new DeadlineTask(description, byDate);
            tasks.addTask(task);
            ui.printTaskAdded(task);
        } catch (DateTimeParseException ex) {
            throw new ZephyrException("Please enter a valid date in the format 'dd MMM YYYY'.");
        }
    }

    @Override
    public void isValidCommand() {
        if (this.arguments.isBlank()) {
            throw new ZephyrException("The description of a deadline cannot be empty.");
        }
        if (!this.arguments.contains("/by")) {
            throw new ZephyrException("The deadline command must contain a '/by' keyword.");
        }

        String[] tokens = this.arguments.split(" /by ", 2);
        if (tokens.length < 2) {
            throw new ZephyrException("The deadline command must contain a deadline.");
        }
    }

}
