import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] field;
    static int[] numOfBombs;
    static boolean[] exploded;

    public static void explode() {
        // 폭발
        for (int i = 0; i < N; i++) {
            if (numOfBombs[i] > 0) {
                int numOfSequence = 0, sIdx = 0;
                boolean isSequenceExploded = false;
                for (int j = 0; j < N; j++) {
                    if (field[j][i] == field[sIdx][i]) {
                        if (++numOfSequence >= M) {
                            exploded[i] = true;
                            isSequenceExploded = true;
                        }
                    } else {
                        if (isSequenceExploded) {
                            for (int k = sIdx; k < j; k++) {
                                field[k][i] = 0;
                            }
                            isSequenceExploded = false;
                        }
                        sIdx = j;
                        numOfSequence = 1;
                    }
                }
                if (isSequenceExploded) {
                    for (int k = sIdx; k < N; k++) {
                        field[k][i] = 0;
                    }
                }
            }
        }
        
        fall();
        Arrays.fill(exploded, false);
    }

    public static void rotate() {
        // 시계방향 90도 회전
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            int tempIdx = N-1;
            for (int j = N-1; j >= 0; j--) {
                if (field[N-i-1][j] != 0) {
                    temp[tempIdx--][i] = field[N-i-1][j];
                }
            }
            numOfBombs[i] = N-1-tempIdx;
        }

        for (int i = 0; i < N; i++) {
            field[i] = Arrays.copyOf(temp[i], N);
        }
    }

    public static void fall() {
        // 폭발로 떨어지기
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            if (exploded[i]) {
                int tempIdx = N-1;
                for (int j = 0; j < N; j++) {
                    if (field[N-j-1][i] != 0) {
                        temp[tempIdx--][i] = field[N-j-1][i];
                    }
                }
                numOfBombs[i] = N-1-tempIdx;
            }
        }

        for (int i = 0; i < N; i++) {
            if (exploded[i]) {
                for (int j = 0; j < N; j++) {
                    field[j][i] = temp[j][i];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[N][N];
        numOfBombs = new int[N];
        exploded = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
            numOfBombs[i] = N;
        }

        for (int i = 0; i < K; i++) {
            explode();
            rotate();
        }
        explode();

        int numOfAllBombs = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] != 0) numOfAllBombs++;
            }
        }
        System.out.println(numOfAllBombs);
    }
}