package Zephyr.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * A fast input reader that uses BufferedReader and StringTokenizer
 * to efficiently read tokens, lines, and primitive data types from the standard input.
 */
public class FastScanner {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    /**
     * Constructs a new FastScanner that reads from standard input.
     */
    public FastScanner() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Returns the next token from the input.
     *
     * @return the next token as a String
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
     * Returns the next line of input.
     *
     * @return the next line as a String, or an empty string if an error occurs
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
     * Reads tokens until a token ending with the specified delimiter is found.
     *
     * @param delimiter the delimiter string that indicates when to stop reading
     * @return the concatenated tokens read until the delimiter, or an empty string if no tokens are available
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
                sb.append(token, 0, token.length() - delimiter.length());
                delimiterFound = true;
                break;
            }
            sb.append(token).append(" ");
        }
        return delimiterFound ? sb.toString().trim() : "";
    }

    /**
     * Returns the remaining tokens on the current line.
     *
     * @return a String containing the remaining tokens, or an empty string if none remain
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
     * Returns the next integer from the input.
     *
     * @return the next integer value
     */
    int nextInt() {
        return Integer.parseInt(nextString());
    }

    /**
     * Returns the next long integer from the input.
     *
     * @return the next long value
     */
    long nextLong() {
        return Long.parseLong(nextString());
    }

    /**
     * Reads an array of integers from the input.
     *
     * @param n the number of integers to read
     * @return an array of n integers
     */
    int[] readArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    /**
     * Closes the input reader.
     */
    void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}