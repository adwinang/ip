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
            try {
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
                    int markNumber;
                    try {
                        markNumber = scanner.nextInt();
                    } catch (NumberFormatException e) {
                        throw new ZephyrException("Task number must be an integer.");
                    }
                    if (markNumber > taskCount) {
                        throw new ZephyrException("Task number does not exist.");
                    }
                    tasks[markNumber - 1].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[markNumber - 1].toString());
                    System.out.println("____________________________________________________________");
                    break;
                case "unmark":
                    int unmarkNumber;
                    try {
                        unmarkNumber = scanner.nextInt();
                    } catch (NumberFormatException e) {
                        throw new ZephyrException("Task number must be an integer.");
                    }
                    if (unmarkNumber > taskCount) {
                        throw new ZephyrException("Task number does not exist.");
                    }
                    tasks[unmarkNumber - 1].markAsUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as undone:");
                    System.out.println(tasks[unmarkNumber - 1].toString());
                    System.out.println("____________________________________________________________");
                    break;
                case "todo":
                    String description = scanner.remainingLine();
                    if (description.isBlank()) {
                        throw new ZephyrException("Todo description cannot be empty.");
                    }
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
                    if (deadlineDescription.isBlank()) {
                        throw new ZephyrException("Deadline description cannot be empty or /by is missing.");
                    }
                    String by = scanner.remainingLine();
                    if (by.isBlank()) {
                        throw new ZephyrException("Deadline by cannot be empty.");
                    }
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
                    if (eventDescription.isBlank()) {
                        throw new ZephyrException("Event description cannot be empty.");
                    }
                    String from = scanner.nextUntil("/to");
                    if (from.isBlank()) {
                        throw new ZephyrException("Event start time cannot be empty.");
                    }
                    String to = scanner.remainingLine();
                    if (to.isBlank()) {
                        throw new ZephyrException("Event end time cannot be empty.");
                    }
                    Event eventTask = new Event(eventDescription, from, to);
                    tasks[taskCount] = eventTask;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(eventTask);
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    scanner.remainingLine();
                    throw new ZephyrException("""
                            I do not understand what thou art saying.
                            Please enter a valid command using the follow:
                            1. list
                            2. mark <task number>
                            3. unmark <task number>
                            4. todo <task description>
                            5. deadline <task description> /by <deadline>
                            6. event <task description> /from <start time> /to <end time>
                            """);
                }
            } catch (ZephyrException e) {
                System.out.println(e.getMessage());
            }
            userInput = scanner.nextString();
        }

        System.out.println(goodbyeMessage);
    }
}
