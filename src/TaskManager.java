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
        System.out.println("Все задачи удалены.");
    }

    public Task getTaskById(UUID uuid) {
        return tasks.get(uuid);
    }

    public void addTask(Task task) {
        tasks.put(task.getUuid(), task);
        if (task.getClass() == Subtask.class) System.out.println("Добавлена подзадача: " + task.name);
        if (task.getClass() == Epic.class) System.out.println("Добавлен эпик: " + task.name);
        if (task.getClass() == Task.class) System.out.println("Добавлена задача: " + task.name);
    }

    public void updateTask(Task task) {
        tasks.put(task.getUuid(), task);
        if (task.getClass() == Subtask.class) System.out.println("Подзадача '" + task.name + "' обновлена.");
        if (task.getClass() == Epic.class) System.out.println("Эпик '" + task.name + "' обновлен.");
        if (task.getClass() == Task.class) System.out.println("Задача '" + task.name + "' обновлена.");
    }

    public void removeTaskById(UUID uuid) {
        if (getTaskById(uuid).getClass() == Epic.class) {
            Epic epic = (Epic) getTaskById(uuid);
            System.out.println("Эпик '" + epic.name + "' был удален.");
            for (Subtask subtask : getSubtasksByEpic(epic)) {
                removeTaskById(subtask.getUuid());
                System.out.println("Подзадача эпика '" + epic.name + "' - '" + subtask.name + "' была удалена.");
            }
            tasks.remove(uuid);
        } else if (getTaskById(uuid).getClass() == Subtask.class) {
            tasks.remove(uuid);
        } else if (getTaskById(uuid).getClass() == Task.class) {
            tasks.remove(uuid);
        }
    }

    public ArrayList<Subtask> getSubtasksByEpic(Epic epic) {
        return epic.getSubtasks();
    }

    public void updateTaskStatus(Subtask subtask, TaskStatus status) {
        subtask.setStatus(status);
        System.out.println("Статус подзадачи '" + subtask.name + "' был изменен на: " + status);
        Epic epic = subtask.getEpic();
        ArrayList<Subtask> subtasks = epic.getSubtasks();
        for (int i = 0; i < subtasks.size() - 1; i++) {
            if (subtasks.get(i).getStatus() != subtasks.get(i + 1).getStatus()) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
                System.out.println("Статус эпика '" + epic.name + "' был изменен на: " + epic.getStatus());
                return;
            }
        }
        TaskStatus newStatus = subtasks.getFirst().getStatus();
        epic.setStatus(newStatus);
        System.out.println("Статус эпика '" + epic.name + "' был изменен на: " + epic.getStatus());
    }

    public void updateTaskStatus(Task task, TaskStatus status) {
        task.setStatus(status);
        System.out.println("Статус задачи '" + task.name + "' был изменен на: " + task.getStatus());
    }
}