import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] field;
    static int[] numOfBombs;

    public static void explode() {
        // 폭발
        if (M == 1) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(field[i], 0);
            }
            Arrays.fill(numOfBombs, 0);
        } else {
            for (int i = 0; i < N; i++) {
                if (numOfBombs[i] > 0) {
                    boolean isExploded = false;
                    do {
                        isExploded = false;
                        int numOfSequence = 0, sIdx = 0;
                        boolean isSequenceExploded = false;
                        for (int j = N-numOfBombs[i]; j < N; j++) {
                            if (field[j][i] == field[sIdx][i]) {
                                if (++numOfSequence >= M) {
                                    isExploded = true;
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
                            isSequenceExploded = false;
                        }

                        if (isExploded) fall(i);
                    } while (isExploded);
                }
            }
        }
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

    public static void fall(int col) {
        // 폭발로 떨어지기
        int[] temp = new int[N];

        int tempIdx = N-1;
        for (int j = 0; j < N; j++) {
            if (field[N-j-1][col] != 0) {
                temp[tempIdx--] = field[N-j-1][col];
            }
        }
        numOfBombs[col] = N-1-tempIdx;
        System.out.println("in fall func: " + Arrays.toString(numOfBombs));

        for (int i = 0; i < N; i++) {
            field[i][col] = temp[i];
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