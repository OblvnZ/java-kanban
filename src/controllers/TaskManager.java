package controllers;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface TaskManager {

    ArrayList<Task> getTasks();
    ArrayList<Subtask> getSubtasks();
    ArrayList<Epic> getEpics();
    final List<Task> tasksHistory = new ArrayList<>();

    void removeTasks();

    void removeSubtasks();

    void removeEpics();

    Task getTaskById(UUID uuid);

    void addTask(Task task);

    void updateTask(Task task);

    void removeTaskById(UUID uuid);

    ArrayList<Subtask> getSubtasksByEpic(Epic epic);

    void updateTaskStatus(Subtask subtask, TaskStatus status);

    void updateTaskStatus(Epic epic);

    void updateTaskStatus(Task task, TaskStatus status);

    List<Task> getTasksHistory();
}
