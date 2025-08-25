import java.time.LocalDate;
import java.util.List;

public class ReminderThread implements Runnable {
    private final TaskRepository repo;
    private volatile boolean running = true;

    public ReminderThread(TaskRepository repo) { this.repo = repo; }
    public void stop() { running = false; }

    @Override
    public void run() {
        while (running) {
            try {
                List<Task> tasks = repo.findAll();
                LocalDate today = LocalDate.now();
                for (Task t : tasks) {
                    if (t.getStatus() == Status.PENDING &&
                        (t.getDueDate().isEqual(today) || t.getDueDate().isBefore(today))) {
                        System.out.println("⚠ Reminder: Task due today or overdue -> " + t);
                    }
                }
                Thread.sleep(10000); // 10s for demo
            } catch (InterruptedException ignored) { }
        }
    }
}
