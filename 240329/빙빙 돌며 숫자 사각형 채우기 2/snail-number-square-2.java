import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    private static boolean inRange(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < m);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] field = new int[n][m];
        int x = 0, y = 0, dir = 2;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        field[0][0] = 1;
        for (int i = 2; i <= n*m; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (!inRange(nx, ny) || field[nx][ny] != 0) {
                dir = (dir + 3) % 4;
            }

            x += dx[dir];
            y += dy[dir];
            field[x][y] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }

    }
}