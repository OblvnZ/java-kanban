import java.util.UUID;

public class Task {

    protected final String name;
    protected final String description;
    protected final UUID uuid;
    protected TaskStatus status;


    public Task(String name,  String description) {
        this.name = name;
        this.description = description;
        this.uuid = UUID.randomUUID();
        this.status = TaskStatus.NEW;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass() + "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}