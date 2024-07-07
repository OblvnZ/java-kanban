package controllers;

import java.util.*;

import model.*;

public class InMemoryTaskManager implements TaskManager {

    private final HashMap<UUID, Task> tasks = new HashMap<>();
    private final HashMap<UUID, Epic> epics = new HashMap<>();
    private final HashMap<UUID, Subtask> subtasks = new HashMap<>();
    private InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void removeTasks() {
        tasks.clear();
    }

    @Override
    public void removeSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            updateTaskStatus(epic);
        }
        subtasks.clear();
    }

    @Override
    public void removeEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public Task getTaskById(UUID uuid) {
        if (tasks.containsKey(uuid)) {
            historyManager.add(tasks.get(uuid));
            return tasks.get(uuid);
        } else if (epics.containsKey(uuid)) {
            historyManager.add(epics.get(uuid));
            return epics.get(uuid);
        } else if (subtasks.containsKey(uuid)) {
            historyManager.add(subtasks.get(uuid));
            return subtasks.get(uuid);
        }
        return null;
    }

    @Override
    public void addTask(Task task) {
        if (task.getClass() == Task.class) tasks.put(task.getUuid(), task);
        if (task.getClass() == Subtask.class) {
            updateTaskStatus(((Subtask) task).getEpic());
            subtasks.put(task.getUuid(), (Subtask) task);
        }
        if (task.getClass() == Epic.class) epics.put(task.getUuid(), (Epic) task);
    }

    @Override
    public void updateTask(Task task) {
        if (task.getClass() == Task.class) tasks.put(task.getUuid(), task);
        if (task.getClass() == Epic.class) epics.put(task.getUuid(), (Epic) task);
        if (task.getClass() == Subtask.class) {
            subtasks.put(task.getUuid(), (Subtask) task);
            updateTaskStatus(((Subtask) task).getEpic());
        }
    }

    @Override
    public void removeTaskById(UUID uuid) {
        if (getTaskById(uuid).getClass() == Epic.class) {
            Epic epic = (Epic) getTaskById(uuid);
            for (Task subtask : getSubtasksByEpic(epic)) {
                if (subtasks.containsValue(subtask)) subtasks.remove(subtask.getUuid());
            }
            epics.remove(uuid);
        } else if (getTaskById(uuid).getClass() == Subtask.class) {
            Subtask subtaskToRemove = (Subtask) getTaskById(uuid);
            subtasks.remove(uuid);
            updateTaskStatus(subtaskToRemove.getEpic());
        } else if (getTaskById(uuid).getClass() == Task.class) {
            tasks.remove(uuid);
        }
    }

    @Override
    public ArrayList<Subtask> getSubtasksByEpic(Epic epic) {
        return epic.getSubtasks();
    }

    @Override
    public void updateTaskStatus(Subtask subtask, TaskStatus status) {
        subtask.setStatus(status);
        Epic epic = subtask.getEpic();
        updateTaskStatus(epic);
    }

    @Override
    public void updateTaskStatus(Epic epic) {
        ArrayList<Subtask> subtasks = epic.getSubtasks();
        for (int i = 0; i < subtasks.size() - 1; i++) {
            if (subtasks.get(i).getStatus() != subtasks.get(i + 1).getStatus()) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
                return;
            }
        }
        TaskStatus newStatus = subtasks.getFirst().getStatus();
        epic.setStatus(newStatus);
    }

    @Override
    public void updateTaskStatus(Task task, TaskStatus status) {
        task.setStatus(status);
    }

    @Override
    public List<Task> getTasksHistory() {
        return tasksHistory;
    }
}