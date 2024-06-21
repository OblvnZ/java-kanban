public class Subtask extends Task {
    private final Epic epic;
    public Subtask(String name, String description, Epic epic) {
        super(name, description);
        this.epic = epic;
        this.epic.addSubtask(this);
    }

    public Epic getEpic() {
        return epic;
    }
}
