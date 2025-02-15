import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Ui {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Ui() {
    }

    /**
     * Print the message to the user
     * Function is refactored to abstract the output stream
     * @param message The String message to be printed
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Overloaded method to print the message to the user
     * @param message The Object message to be printed
     */
    public void print(Object message) {
        // String.valueOf() is used to handle null parameters
        // https://stackoverflow.com/questions/27465731/string-valueof-vs-object-tostring
        String strMessage = String.valueOf(message);
        print(strMessage);
    }

    /**
     * Print the welcome message when the program starts
     */
    public void printWelcome() {
        print("""
                ____________________________________________________________
                 Hello! I'm Zephyr
                 What can I do for thou?
                ____________________________________________________________
                """);
    }

    /**
     * Print the goodbye message when the program ends
     */
    public void printGoodbye() {
        print("""
                ____________________________________________________________
                 Goodbye! May thou have a safe journey ahead.
                ____________________________________________________________
                """);
    }

    /**
     * Print the error message when an error occurs
     */
    public void printLoadingError() {
        print("Error loading file. Creating new file.");
    }


}
