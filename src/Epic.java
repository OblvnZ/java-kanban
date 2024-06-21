import java.util.ArrayList;

public class Epic extends  Task {
    private ArrayList<Subtask> subtasks;
    public Epic(String name, String description) {
        super(name, description);
        subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
}
