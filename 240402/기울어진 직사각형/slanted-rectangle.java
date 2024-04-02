import java.io.*;
import java.util.*;

public class Main {
    static int n, maxSum, sRow, sCol;
    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {1, -1, -1, 1};
    static int[][] field;

    public static boolean inRange(int x, int y){
        return (x >= 0 && x < n && y >= 0 && y < n);
    }

    public static void getSumOfNumbers(int x, int y, int step, int sum) {
        if (step == 4) {
            if (x == sRow && y == sCol) {
                maxSum = Math.max(maxSum, sum);
            }
            return;
        }

        int nx = x + dx[step];
        int ny = y + dy[step];

        if (inRange(nx, ny)) {
            getSumOfNumbers(nx, ny, step, sum+field[x][y]);
            getSumOfNumbers(nx, ny, step+1, sum+field[x][y]);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        field = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sRow = i; sCol = j;
                getSumOfNumbers(i, j, 0, 0);
            }
        }

        System.out.println(maxSum);
    }
}