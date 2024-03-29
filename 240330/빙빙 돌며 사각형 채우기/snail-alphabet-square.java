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

        char[][] field = new char[n][m];
        field[0][0] = 'A';
        int x = 0, y = 0, dir = 1;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        char c = 'B';
        int step = 1;
        while (step++ < n*m) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (!inRange(nx, ny) || field[nx][ny] != 0) dir = (dir+1) % 4;

            x += dx[dir];
            y += dy[dir];
            field[x][y] = c++;
            if (c > 'Z') c = 'A';
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%c ", field[i][j]);
            }
            System.out.println();
        }

    }
}