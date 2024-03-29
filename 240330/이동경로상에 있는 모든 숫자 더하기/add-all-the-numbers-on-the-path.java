import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static boolean inRange(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        String s = in.readLine();
        int[][] field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = N/2, y = N/2, dir = 0, answer = field[N/2][N/2];
        for (int i = 0; i < T; i++) {
            char c = s.charAt(i);
            if (c == 'L') {
                dir = (dir + 3) % 4;
            } else if (c == 'R') {
                dir = (dir + 1) % 4;
            } else {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (inRange(nx, ny)) {
                    x += dx[dir];
                    y += dy[dir];
                    answer += field[x][y];
                }
            }
        }

        System.out.println(answer);
    }
}