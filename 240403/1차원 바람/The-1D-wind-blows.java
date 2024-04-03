import java.io.*;
import java.util.*;

public class Main {
    static int N, M, Q;
    static int[][] field;

    public static boolean hasSameNum(int row) {
        for (int i = 0; i < M; i++) {
            if (field[row][i] == field[row-1][i]) return true;
        }
        return false;
    }

    public static void wind(int row, int dir) {
        if (dir == 1) {
            int temp = field[row][0];
            for (int i = 1; i < M; i++) {
                field[row][i-1] = field[row][i];
            }
            field[row][M-1] = temp;
        } else if (dir == -1) {
            int temp = field[row][M-1];
            for (int i = M-1; i > 0; i--) {
                field[row][i] = field[row][i-1];
            }
            field[row][0] = temp;
        }
    } 

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

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
            int r = Integer.parseInt(st.nextToken()) - 1;
            char d = st.nextToken().charAt(0);

            int dir = (d == 'L') ? -1 : 1;
            wind(r, dir);
            dir *= -1;
            for (int j = r; j > 0; j--) {
                if (hasSameNum(j)) {
                    wind(j-1, dir);
                    dir *= -1;
                } else {
                    break;
                }
            }

            dir = (d == 'L') ? 1 : -1;
            for (int j = r+1; j < N; j++) {
                if (hasSameNum(j)) {
                    wind (j, dir);
                    dir *= -1;
                } else {
                    break;
                }
            }
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