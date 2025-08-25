import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public void add(Task task) { tasks.add(task); }

    public Task findById(int id) {
        for (Task t : tasks) if (t.getId() == id) return t;
        return null;
    }

    public List<Task> findAll() { return tasks; }
}
