package Zephyr.DataStructures;

import java.util.ArrayList;
import java.util.List;
import Zephyr.Tasks.AbstractTask;

/**
 * Represents a list of tasks.
 * This class manages a collection of AbstractTask objects.
 */
public class TaskList {
    private final List<AbstractTask> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks a List of AbstractTask objects to initialize the TaskList
     */
    public TaskList(List<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given ArrayList of tasks.
     *
     * @param tasks an ArrayList of AbstractTask objects to initialize the TaskList
     */
    public TaskList(ArrayList<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task the AbstractTask to add
     */
    public void addTask(AbstractTask task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index the index of the task to remove
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the AbstractTask at the given index
     */
    public AbstractTask getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the size of the TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return a List of AbstractTask objects
     */
    public List<AbstractTask> getTasks() {
        return tasks;
    }

    /**
     * Returns a string representation of the TaskList.
     * Each task is prefixed with its number in the list.
     *
     * @return a formatted String listing all tasks
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return output.toString();
    }
}