package model;

import java.util.ArrayList;
import java.util.UUID;

public class Epic extends Task {
    private final ArrayList<Subtask> subtasks;
    public Epic(String name, String description) {
        super(name, description);
        subtasks = new ArrayList<>();
    }

    public Epic(String name, String description, UUID uuid, TaskStatus taskStatus) {
        super(name, description, uuid, taskStatus);
        subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
}