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
