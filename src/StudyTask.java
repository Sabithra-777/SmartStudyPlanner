import java.time.LocalDate;

public class StudyTask extends Task {
    private String subject;

    public StudyTask(String title, String subject, LocalDate dueDate) {
        super(title, dueDate);
        this.subject = subject;
    }

    public String getSubject() { return subject; }

    @Override public int weight() { return 1; }

    @Override public String toString() {
        return super.toString() + " | subject: " + subject;
    }
}
