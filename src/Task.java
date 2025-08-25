import java.time.LocalDate;

public abstract class Task {
    private static int nextId = 1;
    private final int id;
    private String title;
    private LocalDate dueDate;
    private Status status = Status.PENDING;

    public Task(String title, LocalDate dueDate) {
        this.id = nextId++;
        this.title = title;
        this.dueDate = dueDate;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate getDueDate() { return dueDate; }
    public Status getStatus() { return status; }
    public void setTitle(String t) { this.title = t; }
    public void setDueDate(LocalDate d) { this.dueDate = d; }
    public void markComplete() { this.status = Status.DONE; }

    public abstract int weight();

    @Override
    public String toString() {
        return String.format("[%d] %s | due %s | %s", id, title, dueDate, status);
    }
}
