import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }
        }

        int nMove = t % (3*n);
        for (int i = 0; i < nMove; i++) {
            queue.offerFirst(queue.pollLast());
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(queue.poll()).append(' ');
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}