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
        System.out.println(greetingMessage);

        // Detect user input in cli
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String userInput = scanner.nextLine();
        // Case is ignored to allow for case-insensitive input
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println("You said: " + userInput);
            userInput = scanner.nextLine();
        }
        System.out.println(goodbyeMessage);

    }
}
