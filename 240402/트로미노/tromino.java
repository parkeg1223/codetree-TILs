import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] field;

    public static int _1stblock(int row, int col) {
        int maxValue = Integer.MIN_VALUE;
        int sum = field[row][col] + field[row+1][col] + field[row][col+1] + field[row+1][col+1];

        for (int i = row; i < row + 2; i++) {
            for (int j = col; j < col + 2; j++) {
                maxValue = Math.max(maxValue, sum - field[i][j]);
            }
        }

        return maxValue;
    }

    public static int _2ndblockH(int row, int col) {
        int sum = 0;
        for (int j = col; j < col + 3; j++) {
            sum += field[row][j];
        }
        return sum;
    }

    public static int _2ndblockV(int row, int col) {
        int sum = 0;
        for (int i = row; i < row + 3; i++) {
            sum += field[i][col];
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        field = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + 2 <= n && j + 2 <= m) maxSum = Math.max(maxSum, _1stblock(i, j));
                if (i + 3 <= n) maxSum = Math.max(maxSum, _2ndblockV(i, j));
                if (j + 3 <= m) maxSum = Math.max(maxSum, _2ndblockH(i, j));
            }
        }

        System.out.println(maxSum);
    }
}