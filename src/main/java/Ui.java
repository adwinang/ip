import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Ui {
    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
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

    /**
     * Gets the next string from the user input
     * @return String input from the user
     */
    String nextString() {
        while (tokenizer == null || !tokenizer.hasMoreElements()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    /**
     * Gets the next line from the user input
     * @return String input from the user
     */
    String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Read until the delimiter is found or until end of line
     * @param delimiter Defines the string to detect to stop reading
     * @return The string read until the delimiter
     */
    String nextUntil(String delimiter) {
        if (tokenizer == null || !tokenizer.hasMoreElements()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean delimiterFound = false;
        while (tokenizer.hasMoreElements()) {
            String token = tokenizer.nextToken();
            if (token.endsWith(delimiter)) {
                // Remove the delimiter from the token and append to sb
                sb.append(token, 0, token.length() - delimiter.length());
                delimiterFound = true;
                break;
            }
            sb.append(token).append(" ");
        }
        return delimiterFound ? sb.toString().trim() : "";
    }

    /**
     * Get the remaining line of the input
     * This is like a cursor if nextString/nextInt/nextLong is used
     * @return The remaining line of the input
     */
    String remainingLine() {
        if (tokenizer == null || !tokenizer.hasMoreElements()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (tokenizer.hasMoreElements()) {
            sb.append(tokenizer.nextToken()).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Get the next integer from the user input
     * @return Integer input from the user
     */
    int nextInt() {
        return Integer.parseInt(nextString());
    }

    /**
     * Get the next long from the user input
     * @return Long input from the user
     */
    long nextLong() {
        return Long.parseLong(nextString());
    }

    /**
     * Get the next double from the user input
     * @param n Array size
     * @return Array of integers
     */
    int[] readArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    /**
     * Close the reader when ending the program gracefully
     */
    void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
