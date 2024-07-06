package controllers;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> tasksHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        updateTasksHistory();
        tasksHistory.add(task);
    }

    @Override
    public List<Task> getTasksHistory() {
        return tasksHistory;
    }

    @Override
    public void updateTasksHistory() {
        if (tasksHistory.size() == 10) {
            tasksHistory.remove(0);
        }
    }
}
