import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] field;

    public static boolean inRange(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);
    }
 
    public static void explode(int col) {
        // 폭발
        int highestIdx = 0;
        boolean[] isExploded = new boolean[n];
        isExploded[col] = true;
        while (highestIdx < n && field[highestIdx][col] == 0) highestIdx++;
        if (highestIdx == n) return;
        for (int i = 0; i < 4; i++) {
            int x = highestIdx, y = col;
            for (int j = 0; j < field[highestIdx][col]-1; j++) {
                x += dx[i];
                y += dy[i];
                if (inRange(x, y)) {
                    field[x][y] = 0;
                    isExploded[y] = true;
                } else break;
            }
        }

        field[highestIdx][col] = 0;

        // 떨어지기
        for (int i = 0; i < n; i++) {
            if (isExploded[i]) {
                int[] temp = new int[n];
                int tempIdx = 0;
                for (int j = n-1; j >= 0; j--) {
                    if (field[j][i] != 0) {
                        temp[tempIdx++] = field[j][i];
                    }
                }
                for (int j = 0; j < n; j++) {
                    field[n-j-1][i] = temp[j];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        field = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            int col = Integer.parseInt(in.readLine().trim());
            explode(col-1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(field[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}