import java.io.*;
import java.util.*;

public class Main {
    static int n, t;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        ArrayDeque<Integer> numbers = new ArrayDeque<>();
        int nMove = t % (2*n);
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                numbers.offer(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < nMove; i++) {
            numbers.offerFirst((numbers.pollLast()));
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(numbers.poll()).append(' ');
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}