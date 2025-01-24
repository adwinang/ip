public class Zephyr {
    public static void main(String[] args) {
        String greetingMessage= """
                ____________________________________________________________
                 Hello! I'm Zephyr
                 What can I do for thou?
                ____________________________________________________________
                """;
        String goodbyeMessage= """
                ____________________________________________________________
                 Bye. Hope to see thou again soon!
                ____________________________________________________________
                """;

        // Assumption of max 100 tasks to store
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(greetingMessage);

        // Detect user input in cli
        FastScanner scanner = new FastScanner();
        String userInput = scanner.nextString();
        // Case is ignored to allow for case-insensitive input
        while (!userInput.equalsIgnoreCase("bye")) {
            switch (userInput.toLowerCase()) {
            case "list":
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + ". " + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");
                break;
            case "mark":
                int markNumber = scanner.nextInt();
                tasks[markNumber - 1].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[markNumber - 1].toString());
                System.out.println("____________________________________________________________");
                break;
            case "unmark":
                int unmarkNumber = scanner.nextInt();
                tasks[unmarkNumber - 1].markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as undone:");
                System.out.println(tasks[unmarkNumber - 1].toString());
                System.out.println("____________________________________________________________");
                break;
            case "todo":
                String description = scanner.remainingLine();
                Todo task = new Todo(description);
                tasks[taskCount] = task;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("____________________________________________________________");
                break;
            case "deadline":
                String deadlineDescription = scanner.nextUntil("/by");
                String by = scanner.remainingLine();
                Deadline deadlineTask = new Deadline(deadlineDescription, by);
                tasks[taskCount] = deadlineTask;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(deadlineTask);
                System.out.println("____________________________________________________________");
                break;
            case "event":
                String eventDescription = scanner.nextUntil("/from");
                String from = scanner.nextUntil("/to");
                String to = scanner.remainingLine();
                Event eventTask = new Event(eventDescription, from, to);
                tasks[taskCount] = eventTask;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(eventTask);
                System.out.println("____________________________________________________________");
                break;
            default:
                String defaultDescription = userInput + " " +
                        scanner.remainingLine();
                tasks[taskCount] = new Task(defaultDescription);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Added: " + defaultDescription);
                System.out.println("____________________________________________________________");
                break;
            }
            userInput = scanner.nextString();
        }

        System.out.println(goodbyeMessage);
    }
}
