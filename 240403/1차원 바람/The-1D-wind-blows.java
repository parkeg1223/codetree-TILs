import java.io.*;
import java.util.*;

public class Main {
    static int N, M, Q;
    static List<List<Integer>> field = new ArrayList<>();

    public static boolean hasSameNum(int row) {
        for (int i = 0; i < M; i++) {
            if (field.get(row).get(i) == field.get(row-1).get(i)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            field.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                field.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            char d = st.nextToken().charAt(0);

            int dir = (d == 'L') ? 1 : -1;
            Collections.rotate(field.get(r), dir);
            dir *= -1;
            for (int j = r; j > 0; j--) {
                if (hasSameNum(j)) {
                    Collections.rotate(field.get(j-1), dir);
                    dir *= -1;
                } else break;
            }

            dir = (d == 'L') ? -1 : 1;
            for (int j = r+1; j < N; j++) {
                if (hasSameNum(j)) {
                    Collections.rotate(field.get(j), dir);
                    dir *= -1;
                } else break;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(field.get(i).get(j)).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}