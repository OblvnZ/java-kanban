package model;

import java.util.UUID;

public class Subtask extends Task {
    private final Epic epic;

    public Subtask(String name, String description, Epic epic) {
        super(name, description);
        this.epic = epic;
        this.epic.addSubtask(this);
    }

    public Subtask(String name, String description, Epic epic, UUID uuid, TaskStatus taskStatus) {
        super(name, description, uuid, taskStatus);
        this.epic = epic;
        this.epic.addSubtask(this);
    }

    public Epic getEpic() {
        return epic;
    }
}
