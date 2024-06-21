import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TaskManager {

    private final HashMap<UUID, Task> tasks = new HashMap<>();


    public HashMap<UUID, Task> getTasks() {
        return tasks;
    }

    public Task getTask(Task task) {
        return task;
    }

    public void removeTasks() {
        tasks.clear();
    }

    public Task getTaskById(UUID uuid) {
        return tasks.get(uuid);
    }

    public void addTask(Task task) {
        tasks.put(task.getUuid(), task);
    }

    public void updateTask(Task task) {
        tasks.put(task.getUuid(), task);
    }

    public void removeTaskById(UUID uuid) {
        if (getTaskById(uuid).getClass() == Epic.class) {
            Epic epic = (Epic) getTaskById(uuid);
            for (Subtask subtask : getSubtasksByEpic(epic)) {
                removeTaskById(subtask.getUuid());
            }
        }
        tasks.remove(uuid);
    }

    public ArrayList<Subtask> getSubtasksByEpic(Epic epic) {
        return epic.getSubtasks();
    }

    public void updateTaskStatus(Subtask subtask, TaskStatus status) {
        subtask.setStatus(status);
        Epic epic = subtask.getEpic();
        ArrayList<Subtask> subtasks = epic.getSubtasks();
        if (subtasks.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }
        for (int i = 0; i < subtasks.size() - 1; i++) {
            if (subtasks.get(i).getStatus() != subtasks.get(i + 1).getStatus()) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
                return;
            }
        }
        TaskStatus newStatus = subtasks.getFirst().getStatus();
        epic.setStatus(newStatus);
    }

    public void updateTaskStatus(Task task, TaskStatus status) {
        task.setStatus(status);
    }
}