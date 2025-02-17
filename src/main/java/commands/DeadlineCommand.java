package commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;
import exceptions.ZephyrException;
import tasks.DeadlineTask;

/**
 * Represents a command to add a DeadlineTask to the task list.
 * The DeadlineCommand processes a command that includes a task description
 * and a deadline date, which is expected to be provided in the format <code>dd MMM yyyy</code>.
 * If the input is invalid, an exception is thrown.
 */
public class DeadlineCommand extends AbstractCommand {

    /**
     * Constructs a DeadlineCommand instance.
     * The command expects arguments in the format
     * <task description> /by <deadline date>
     *
     * @param arguments The raw arguments passed with the command.
     */
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the deadline command by creating a DeadlineTask
     * and adding it to the task list.
     * The method parses the user input to extract the task description
     * and deadline date. If the date is invalid, an exception is thrown.
     *
     * @param tasks   The TaskList where the new task will be added.
     * @param ui      The Ui object responsible for user interaction.
     * @param storage The Storage object (not used in this command).
     * @throws ZephyrException if the command is invalid or the date format is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        String[] tokens = this.getArguments().split(" /by ", 2);
        String description = tokens[0];
        String by = tokens[1];

        try {
            LocalDate byDate = ui.parseDate(by);
            DeadlineTask task = new DeadlineTask(description, byDate);
            tasks.addTask(task);
            ui.printTaskAdded(task);
        } catch (DateTimeParseException ex) {
            throw new ZephyrException("Please enter a valid date in the format 'dd MMM yyyy'.");
        }
    }

    /**
     * Validates the DeadlineCommand arguments.
     * This method ensures that the command contains a valid task description
     * and the "/by" keyword separating the task description from the deadline.
     *
     * @throws ZephyrException if the description is empty, the "/by" keyword is missing,
     *                         or the deadline date is not provided.
     */
    @Override
    public void isValidCommand() {
        if (this.getArguments().isBlank()) {
            throw new ZephyrException("The description of a deadline cannot be empty.");
        }
        if (!this.getArguments().contains("/by")) {
            throw new ZephyrException("The deadline command must contain a '/by' keyword.");
        }

        String[] tokens = this.getArguments().split(" /by ", 2);
        if (tokens.length < 2) {
            throw new ZephyrException("The deadline command must contain a deadline.");
        }
    }
}
