package Zephyr.DataStructures;

import java.util.ArrayList;
import java.util.List;

import Zephyr.Tasks.AbstractTask;

public class TaskList {
    private final List<AbstractTask> tasks;

    public TaskList(List<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    public void addTask(AbstractTask task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public AbstractTask getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public List<AbstractTask> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return output.toString();
    }
}
