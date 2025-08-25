import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) { this.repo = repo; }

    public Task addStudyTask(String title, String subject, LocalDate due)
            throws ValidationException {
        if (title == null || title.isBlank()) throw new ValidationException("Title cannot be empty");
        if (subject == null || subject.isBlank()) throw new ValidationException("Subject cannot be empty");
        if (due == null) throw new ValidationException("Due date is required");
        Task t = new StudyTask(title, subject, due);
        repo.add(t);
        return t;
    }

    public List<Task> list() { return repo.findAll(); }

    public void complete(int id) throws ValidationException {
        Task t = repo.findById(id);
        if (t == null) throw new ValidationException("Task id not found: " + id);
        t.markComplete();
    }
}
