import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] field;

    public static int getNumberOfGolds(int row, int col, int K) {
        int numOfGolds = 0;
        for (int i = row-K; i <= row+K; i++) {
            int temp = K-Math.abs(i-row);
            for (int j = col-temp; j <= col+temp; j++) {
                if (i < 0 || i >= n || j < 0 || j >= n) continue;
                numOfGolds += field[i][j];
            }
        }

        return numOfGolds;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        field = new int[n][n];
        int numOfGold = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == 1) numOfGold++;
            }
        }

        int price = 0, maxK = 0;
        while (maxK * maxK + (maxK+1) * (maxK+1) <= numOfGold * m) maxK++;

        int maxNumOfGolds = 0;
        for (int K = 0; K < maxK; K++) {
            price = K*K + (K+1)*(K+1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int value = getNumberOfGolds(i, j, K);
                    System.out.println(i + " " + j + " " + K + " " + value);
                    if (maxNumOfGolds < value && price <= value * m) maxNumOfGolds = value;
                }
            }
        }

        System.out.println(maxNumOfGolds);
    }
}