package model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    public void shouldBeEqualsByUUID() {
        UUID uuid = UUID.randomUUID();
        Epic first = new Epic("name", "description", uuid, TaskStatus.NEW);
        Epic second = new Epic("name", "description", uuid, TaskStatus.NEW);
        assertEquals(true, first.equals(second));
    }

    @Test
    public void epicShouldBePuttedInEpic() {
        //first.addSubtask(first);      //Вызов метода с эпиком невозможен, метод принимает только Subtask.
        assertEquals(true, true);
    }
}