import java.io.*;
import java.util.*;

public class Main {
    static int n, answer;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] field, tField;
    static boolean[] exploded;

    public static boolean inRange(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);
    }

    public static void retrieve() {
        for (int i = 0; i < n; i++) {
            field[i] = Arrays.copyOf(tField[i], n);
        }
    }

    public static void explode(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int x = row, y = col;
            for (int j = 0; j < field[row][col]-1; j++) {
                x += dx[i];
                y += dy[i];
                if (!inRange(x, y)) break;
                field[x][y] = 0;
                exploded[y] = true;
            }
        }
        exploded[col] = true;
        field[row][col] = 0;
    }

    public static void fall() {
        for (int i = 0; i < n; i++) {
            if (exploded[i]) {
                int[] temp = new int[n];
                int tempIdx = 0;
                for (int j = n-1; j >= 0; j--) {
                    if (field[j][i] != 0) {
                        temp[tempIdx++] = field[j][i];
                    }
                }

                for (int j = n-1; j>= 0; j--) {
                    field[j][i] = temp[n-1-j];
                }
            }
        }

        Arrays.fill(exploded, false);
    }

    public static int getNumOfPairs() {
        int numOfPairs = 0;
        for (int i = 0; i < n; i++) {
            int numOfSequence = 0, prev = 0;
            for (int j = 0; j < n; j++) {
                if (field[i][j] != 0 && field[i][j] == field[i][prev]) {
                    numOfSequence++;
                } else {
                    if (numOfSequence == 2) numOfPairs++;
                    if (field[i][j] == 0) {
                        numOfSequence = 0;
                    } else {
                        numOfSequence = 1;
                    }
                    prev = j;
                }
            }
            if (numOfSequence == 2) numOfPairs++;
        }

        for (int i = 0; i < n; i++) {
            int numOfSequence = 0, prev = 0;
            for (int j = 0; j < n; j++) {
                if (field[j][i] != 0 && field[j][i] == field[prev][i]) {
                    numOfSequence++;
                } else {
                    if (numOfSequence == 2) numOfPairs++;
                    if (field[j][i] == 0) {
                        numOfSequence = 0;
                    } else {
                        numOfSequence = 1;
                    }
                    prev = j;
                }
            }
            if (numOfSequence == 2) numOfPairs++;
        }

        return numOfPairs;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        field = new int[n][n];
        tField = new int[n][n];
        exploded = new boolean[n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                tField[i][j] = field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                explode(i, j);
                fall();
                answer = Math.max(answer, getNumOfPairs());
                retrieve();
            }
        }

        System.out.println(answer);
    }
}