import java.util.*;
import java.io.*;

public class Main {

    static int N;
    private static boolean inRange(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        int[][] field = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int n1 = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (inRange(nx, ny) && field[nx][ny] == 1) n1++;
                }
                if (n1 >= 3) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}