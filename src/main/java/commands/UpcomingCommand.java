package commands;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;
import exceptions.ZephyrException;
import tasks.AbstractTask;
import tasks.DeadlineTask;
import tasks.EventTask;

/**
 * Represents a command to display upcoming tasks within a specified number of days.
 * This command expects two arguments: the task type (either "deadline" or "event")
 * and the number of days. It filters tasks of the specified type that have dates
 * within the next given number of days from the current date and displays them.
 */
public class UpcomingCommand extends AbstractCommand {

    /**
     * Constructs an UpcomingCommand with the specified arguments.
     *
     * @param arguments the arguments in the format "taskType days"
     */
    public UpcomingCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the upcoming command by filtering and displaying tasks.
     * This method validates the command arguments, parses the task type and the number of days,
     * and then iterates over all tasks in the TaskList. For tasks matching the specified type,
     * if their date is after the current date and before the current date plus the specified days,
     * they are considered upcoming. The command then prints the list of upcoming tasks along with
     * the number of days remaining until each task's deadline or start date.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the UI used for user interaction
     * @param storage the Storage (not used in this command)
     * @throws ZephyrException if the command arguments are invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();
        String taskType = this.getWords()[0];
        int days = Integer.parseInt(this.getWords()[1]);
        LocalDate currentDate = LocalDate.now();

        AbstractTask[] upcomingTasks = new AbstractTask[tasks.getSize()];
        int i = 0;
        for (AbstractTask task : tasks.getTasks()) {
            if (taskType.equalsIgnoreCase(task.getTaskType()) && task instanceof DeadlineTask deadlineTask) {
                LocalDate deadlineDate = deadlineTask.getBy();
                if (deadlineDate.isAfter(currentDate) && deadlineDate.isBefore(currentDate.plusDays(days))) {
                    upcomingTasks[i] = task;
                    i++;
                }
            } else if (taskType.equalsIgnoreCase(task.getTaskType()) && task instanceof EventTask eventTask) {
                LocalDate eventDate = eventTask.getFrom();
                if (eventDate.isAfter(currentDate) && eventDate.isBefore(currentDate.plusDays(days))) {
                    upcomingTasks[i] = task;
                    i++;
                }
            }
        }

        if (i == 0) {
            ui.printAndAddBreak("Thou have no upcoming " + taskType + " tasks within the next " + days + " days.");
            return;
        }

        StringBuilder content = new StringBuilder();
        content.append("Thou have ").append(i).append(" upcoming ").append(taskType)
                .append(" tasks within the next ").append(days).append(" days.");
        for (int j = 0; j < i; j++) {
            AbstractTask task = upcomingTasks[j];
            if (taskType.equalsIgnoreCase(task.getTaskType()) && task instanceof DeadlineTask deadlineTask) {
                long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadlineTask.getBy());
                content.append((j + 1)).append(". ").append(deadlineTask);
                content.append("    ").append(daysUntilDeadline).append(" days until deadline.");
            } else if (taskType.equalsIgnoreCase(task.getTaskType()) && task instanceof EventTask eventTask) {
                long daysUntilEvent = ChronoUnit.DAYS.between(currentDate, eventTask.getFrom());
                content.append((j + 1)).append(". ").append(eventTask);
                content.append("    ").append(daysUntilEvent).append(" days until event.");
            }
        }
        ui.printAndAddBreak(content.toString());
    }

    /**
     * Validates the UpcomingCommand arguments.
     * This method checks that exactly two arguments are provided, that the first argument is
     * a valid task type ("deadline" or "event"), and that the second argument is a valid integer
     * representing the number of days.
     *
     * @throws ZephyrException if there more or less than two words and if the first word is not a task type
     */
    @Override
    public void isValidCommand() throws ZephyrException {
        if (this.getWords().length != 2) {
            throw new ZephyrException("There should be only 2 arguments for the upcoming command.");
        }
        String taskType = this.getWords()[0];
        if (!taskType.equals("deadline") && !taskType.equals("event")) {
            throw new ZephyrException("Please enter a valid task type (deadline or event).");
        }
        try {
            Integer.parseInt(this.getWords()[1]);
        } catch (NumberFormatException e) {
            throw new ZephyrException("Thou must input a valid number of days.");
        }
    }
}
