import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[] exploded;
    static int[][] field;

    public static boolean inRange(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);
    }

    public static void explode(int r, int c) {
        exploded[c] = true;
        for (int i = 0; i < 4; i++) {
            int x = r, y = c;
            for (int j = 0; j < field[r][c]-1; j++) {
                x += dx[i];
                y += dy[i];
                if (inRange(x, y)) {
                    field[x][y] = 0;
                    exploded[y] = true;
                } else break;
            }
        }
        field[r][c] = 0;
    }

    public static void fall() {
        for (int i = 0; i < n; i++) {
            if (exploded[i]) {
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
        n = Integer.parseInt(in.readLine());
        field = new int[n][n];
        exploded = new boolean[n];

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(in.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;

        explode(r, c);
        fall();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(field[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}