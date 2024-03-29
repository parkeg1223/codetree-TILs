import java.util.*;
import java.io.*;

public class Main {
    static int n;

    private static boolean inRange(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        StringBuilder sb = new StringBuilder();
        int[][] field = new int[n][n];
        field[n/2][n/2] = 1;
        int x = n/2, y = n/2, dir = 1;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int num = 2, step = 1;
        if (n > 1) {
            for (int i = 0; i < 2*n-1; i++) {
                for (int j = 0; j < step; j++) {
                    x += dx[dir];
                    y += dy[dir];
                    field[x][y] = num++;
                }
                dir = (dir + 3) % 4;
                if (i % 2 == 1 && i != 2*n-3) step++;
            }
        }
        

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(field[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}