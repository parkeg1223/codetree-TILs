import java.io.*;
import java.util.*;

public class Main {
    static int N, M, numOfBombs;
    static int[] bombs;

    public static boolean explode() {
        int numOfSequence = 0, sIdx = 0;
        boolean isExploded = false, isSequenceExploded = false;
        for (int i = 0; i < numOfBombs; i++) {
            if (bombs[i] == bombs[sIdx]) {
                if (++numOfSequence >= M) {
                    isExploded = true;
                    isSequenceExploded = true;
                }
            } else {
                if (isSequenceExploded) {
                    Arrays.fill(bombs, sIdx, i, 0);
                    isSequenceExploded = false;
                }
                sIdx = i;
                numOfSequence = 1;
            }
        }

        if (isSequenceExploded)  Arrays.fill(bombs, sIdx, numOfBombs, 0);
        return isExploded;
    }

    public static void fall() {
        int[] temp = new int[N];
        int tempIdx = 0;

        for (int i = 0; i < N; i++) {
            if (bombs[i] != 0) {
                temp[tempIdx++] = bombs[i];
            }
        }

        for (int i = 0; i < N; i++) {
            bombs[i] = temp[i];
        }

        numOfBombs = tempIdx;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numOfBombs = N;

        bombs = new int[N];
        for (int i = 0; i < N; i++) {
            bombs[i] = Integer.parseInt(in.readLine());
        }

        while (explode()) fall();

        sb.append(numOfBombs).append('\n');
        for (int i = 0; i < numOfBombs; i++) {
            sb.append(bombs[i]).append('\n');
        }
        System.out.print(sb.toString());
    }
}