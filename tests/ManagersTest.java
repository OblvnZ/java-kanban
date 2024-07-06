import static org.junit.jupiter.api.Assertions.*;

import controllers.InMemoryTaskManager;
import org.junit.jupiter.api.Test;

class ManagersTest {
    @Test
    public void validManager() {
        Managers managers = new Managers();
        InMemoryTaskManager inMemMngr = (InMemoryTaskManager) managers.getDefault();
        assertEquals(0, inMemMngr.getTasks().size());
        assertEquals(0, inMemMngr.getEpics().size());
        assertEquals(0, inMemMngr.getSubtasks().size());
    }
}