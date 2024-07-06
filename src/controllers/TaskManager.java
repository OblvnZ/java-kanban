package controllers;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface TaskManager {

    public ArrayList<Task> getTasks();

    public ArrayList<Subtask> getSubtasks();

    public ArrayList<Epic> getEpics();

    public void removeTasks();

    public void removeSubtasks();

    public void removeEpics();

    public Task getTaskById(UUID uuid);

    public void addTask(Task task);

    public void updateTask(Task task);

    public void removeTaskById(UUID uuid);

    public ArrayList<Subtask> getSubtasksByEpic(Epic epic);

    public void updateTaskStatus(Subtask subtask, TaskStatus status);

    public void updateTaskStatus(Epic epic);

    public void updateTaskStatus(Task task, TaskStatus status);
}
