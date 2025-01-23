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
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println(greetingMessage);

        // Detect user input in cli
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String userInput = scanner.nextLine();
        // Case is ignored to allow for case-insensitive input
        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
            userInput = scanner.nextLine();
        }

        System.out.println(goodbyeMessage);
    }
}
