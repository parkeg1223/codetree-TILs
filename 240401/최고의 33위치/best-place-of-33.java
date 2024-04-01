import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] field;

    public static int getNumberOfCoins(int row, int col) {
        int numberOfCoins = 0;

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                numberOfCoins += field[i][j];
            }
        }

        return numberOfCoins;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        field = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxNumberOfCoins = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i + 2 >= N || j + 2 >= N) continue;
                maxNumberOfCoins = Math.max(maxNumberOfCoins, getNumberOfCoins(i, j));
            }
        }

        System.out.println(maxNumberOfCoins);

    }
}