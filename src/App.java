import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TaskRepository repo = new InMemoryTaskRepository();
        TaskService service = new TaskService(repo);
        ProductivityCalculator calc = new ProductivityCalculator();

        Thread reminder = new Thread(new ReminderThread(repo), "reminder");
        reminder.setDaemon(true);
        reminder.start();

        Scanner sc = new Scanner(System.in);
        System.out.println("Smart Study Planner (Console)");
        while (true) {
            System.out.println("\nMenu: 1) Add task  2) List  3) Complete  4) Productivity score  5) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": {
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Subject: ");
                        String subject = sc.nextLine();
                        System.out.print("Due date (YYYY-MM-DD): ");
                        LocalDate due = LocalDate.parse(sc.nextLine().trim());
                        service.addStudyTask(title, subject, due);
                        System.out.println("Added.");
                        break;
                    }
                    case "2": {
                        List<Task> tasks = service.list();
                        if (tasks.isEmpty()) System.out.println("(no tasks)");
                        else tasks.forEach(System.out::println);
                        break;
                    }
                    case "3": {
                        System.out.print("Task id to complete: ");
                        int id = Integer.parseInt(sc.nextLine().trim());
                        service.complete(id);
                        System.out.println("Marked complete.");
                        break;
                    }
                    case "4": {
                        System.out.print("Hours studied today: ");
                        int hours = Integer.parseInt(sc.nextLine().trim());
                        int score = calc.score(service.list(), hours);
                        System.out.println("Today's productivity score: " + score);
                        break;
                    }
                    case "5":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (ValidationException | DateTimeParseException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
