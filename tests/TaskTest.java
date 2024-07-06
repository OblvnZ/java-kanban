import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    public void shouldBeEqualsByUUID() {
        UUID uuid = UUID.randomUUID();
        Task first = new Task("name", "description", uuid, TaskStatus.NEW);
        Task second = new Task("name", "description", uuid, TaskStatus.NEW);
        assertEquals(first, second);
    }
}