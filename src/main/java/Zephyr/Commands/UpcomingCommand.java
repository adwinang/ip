package Zephyr.Commands;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import Zephyr.Tasks.AbstractTask;
import Zephyr.Tasks.DeadlineTask;
import Zephyr.Tasks.EventTask;
import Zephyr.Controllers.Storage;
import Zephyr.DataStructures.TaskList;
import Zephyr.Controllers.Ui;
import Zephyr.Exceptions.ZephyrException;

public class UpcomingCommand extends AbstractCommand {

    public UpcomingCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isValidCommand();

        String taskType = this.words[0];
        int days = Integer.parseInt(this.words[1]);
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
            ui.printBreak();
            ui.println("Thou have no upcoming " + taskType + " tasks within the next " + days + " days.");
            ui.printBreak();
            return;
        }

        ui.printBreak();
        ui.println("Thou have " + i + " upcoming " + taskType + " tasks within the next " + days + " days.");

        for (int j = 0; j < i; j++) {
            AbstractTask task = upcomingTasks[j];
            if (taskType.equalsIgnoreCase(task.getTaskType()) && task instanceof DeadlineTask deadlineTask) {
                long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadlineTask.getBy());

                ui.println((j + 1) + ". " + deadlineTask);
                ui.println("    " + daysUntilDeadline + " days until deadline.");
            } else if (taskType.equalsIgnoreCase(task.getTaskType()) && task instanceof EventTask eventTask) {
                long daysUntilEvent = ChronoUnit.DAYS.between(currentDate, eventTask.getFrom());

                ui.println((j + 1) + ". " + eventTask);
                ui.println("    " + daysUntilEvent + " days until event.");
            }
        }

        ui.printBreak();
    }


    @Override
    public void isValidCommand() throws ZephyrException {
        if (this.words.length != 2) {
            throw new ZephyrException("There should be only 2 arguments for the upcoming command.");
        }
        String taskType = this.words[0];
        if (!taskType.equals("deadline") && !taskType.equals("event")) {
            throw new ZephyrException("Please enter a valid task type (deadline or event).");
        }
        try {
            Integer.parseInt(this.words[1]);
        } catch (NumberFormatException e) {
            throw new ZephyrException("Thou must input a valid number of days.");
        }
    }

}
