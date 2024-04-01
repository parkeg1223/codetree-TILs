import java.io.*;
import java.util.*;

public class Main {
    public static int n, m;
    public static int[][] field;

    public static int isHappySequence(int row, int col) {
        int prevNum, consec = 0;
        if (row == -1) {
            prevNum = field[0][col];
            for (int i = 0; i < n; i++) {
                if (field[i][col] == prevNum) {
                    if (++consec >= m) return 1;
                } else {
                    prevNum = field[i][col];
                    consec = 1;
                }
            }
        } else {
            prevNum = field[row][0];
            for (int j = 0; j < n; j++) {
                if (field[row][j] == prevNum) {
                    if (++consec >= m) return 1;
                } else {
                    prevNum = field[row][j];
                    consec = 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        field = new int[n][n];

        int numberOfHappySequence = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            numberOfHappySequence += (isHappySequence(-1, i) + isHappySequence(i, -1));
        }

        System.out.println(numberOfHappySequence);
    }
}