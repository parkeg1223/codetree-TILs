import java.util.*;
import java.io.*;

public class Main {

    static int N, M;

    private static boolean inRange(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] field = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            field[r][c] = 1;
            int cnt = 0;
            for (int k = 0; k < 4; k++) {
                int nx = r + dx[k];
                int ny = c + dy[k];
                if (inRange(nx, ny) && field[nx][ny] == 1) cnt++;
            }
            if (cnt == 3) System.out.println(1);
            else System.out.println(0);
        }
    }
}