import java.io.*;
import java.util.*;

public class Main {
    static int N, M, Q;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] field;

    public static boolean inRange(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    public static void wind2D(int r1, int c1, int r2, int c2) {
        int temp = field[r1][c1];

        for (int i = r1+1; i <= r2; i++) {
            field[i-1][c1] = field[i][c1];
        }

        for (int i = c1+1; i <= c2; i++) {
            field[r2][i-1] = field[r2][i];
        }

        for (int i = r2; i > r1; i--) {
            field[i][c2] = field[i-1][c2];
        }

        for (int i = c2; i > c1; i--) {
            field[r1][i] = field[r1][i-1];
        }

        field[r1][c1+1] = temp;

        int[][] newArr = new int[r2-r1+1][c2-c1+1];
        for (int i = 0; i <= r2-r1; i++) {
            for (int j = 0; j <= c2-c1; j++) {
                int sum = field[i+r1][j+c1], div = 1;
                for (int k = 0; k < 4; k++) {
                    int nx = i + r1 + dx[k];
                    int ny = j + c1 + dy[k];
                    if (inRange(nx, ny)) {
                        sum += field[nx][ny];
                        div++;
                    }
                }
                newArr[i][j] = sum / div;
            }
        }

        for (int i = 0; i <= r2-r1; i++) {
            for (int j = 0; j <= c2-c1; j++) {
                field[i+r1][j+c1] = newArr[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        field = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(in.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;
            wind2D(r1, c1, r2, c2);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(field[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}