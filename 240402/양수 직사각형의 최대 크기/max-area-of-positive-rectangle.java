import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] field;

    public static boolean isAllNumbersPositive(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                if (field[i][j] <= 0) return false;
            }
        }
        return true;
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

        int maxWidth = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                for (int k = 0; k < m; k++) {
                    for (int l = k+1; l <= m; l++) {
                        if (isAllNumbersPositive(i, j, k, l)) maxWidth = Math.max(maxWidth, (j-i) * (l-k));
                    }
                }
            }
        }

        System.out.println(maxWidth);
    }
}