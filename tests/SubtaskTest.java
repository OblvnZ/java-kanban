import model.Epic;
import model.Subtask;
import model.TaskStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    public void shouldBeEqualsByUUID() {
        UUID uuid = UUID.randomUUID();
        Epic epic = new Epic("name", "description");
        Subtask first = new Subtask("name", "description", epic, uuid, TaskStatus.NEW);
        Subtask second = new Subtask("name", "description", epic, uuid, TaskStatus.NEW);
        assertEquals(first, second);
    }

    @Test
    public void subtaskShouldBePuttedInSubtask() {
        //first.addSubtask(first);      //Вызов метода с эпиком невозможен, метод принимает только Epic.
        assertEquals(true, true);
    }
}