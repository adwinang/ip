import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String nextString() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    String nextLine() {
        try {
            return br.readLine();
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
        if (st == null || !st.hasMoreElements()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean delimiterFound = false;
        while (st.hasMoreElements()) {
            String token = st.nextToken();
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

    String remainingLine() {
        if (st == null || !st.hasMoreElements()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreElements()) {
            sb.append(st.nextToken()).append(" ");
        }
        return sb.toString().trim();
    }

    int nextInt() {
        return Integer.parseInt(nextString());
    }

    long nextLong() {
        return Long.parseLong(nextString());
    }

    int[] readArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    void close() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
