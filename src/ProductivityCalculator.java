import java.util.List;

public class ProductivityCalculator {
    public int score(List<Task> tasks, int hoursStudied) {
        int points = 0;
        for (Task t : tasks) {
            if (t.getStatus() == Status.DONE) points += t.weight();
        }
        return points * 10 + Math.max(0, hoursStudied) * 5;
    }
}
