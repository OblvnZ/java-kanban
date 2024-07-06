import controllers.InMemoryTaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    InMemoryTaskManager manager = new InMemoryTaskManager();

    @Test
    public void addTaskAndFindByUUID() {
        Task task1 = new Task("Собрать шкаф", "Собрать шкаф опираясь на инструкцию");
        Epic epic1 = new Epic("Переезд", "Переехать в другой город");
        Subtask subtask1 = new Subtask("Собрать вещи", "Упаковать вещи по коробкам", epic1);
        manager.addTask(task1);
        manager.addTask(epic1);
        manager.addTask(subtask1);
        assertEquals(task1, manager.getTaskById(task1.getUuid()));
        assertEquals(epic1, manager.getTaskById(epic1.getUuid()));
        assertEquals(subtask1, manager.getTaskById(subtask1.getUuid()));
    }

    @Test
    public void ifTaskConflicts() {
        UUID uuid = UUID.randomUUID();
        Task task1 = new Task("ABC", "QWE");
        manager.addTask(task1);
        assertNotEquals(task1.getUuid(), uuid);
    }

    @Test
    public void tasksFieldChanges() {
        String name = "name";
        String description = "description";
        Task task = new Task(name, description);
        manager.addTask(task);
        assertEquals(name, task.getName());
        assertEquals(description, task.getDescription());
    }
}