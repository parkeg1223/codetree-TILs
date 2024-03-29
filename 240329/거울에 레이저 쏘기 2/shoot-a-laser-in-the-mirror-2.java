import java.util.*;
import java.io.*;

public class Main {
    static int N;

    private static boolean inRange(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        char[][] field = new char[N][N];
        for (int i = 0; i < N; i++) {
            field[i] = in.readLine().toCharArray();
        }

        int K = Integer.parseInt(in.readLine());
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int x = 0, y = 0, dir = 0;
        int side = (K-1) / N;
        int idx = (K-1) % N;

        if (side == 0) {
            x = 0;
            y = idx;
            dir = 2;
        } else if (side == 1) {
            x = idx;
            y = N-1;
            dir = 3;
        } else if (side == 2) {
            x = N-1;
            y = N-idx-1;
            dir = 0;
        } else {
            x = N-idx-1;
            y = 0;
            dir = 1;
        }

        int nRef = 0;
        while (inRange(x, y)) {
            nRef++;
            if (field[x][y] == '/') {
                /*
                    0 -> 1
                    1 -> 0
                    2 -> 3
                    3 -> 2
                */
                dir = (5 - dir) % 4;
            } else if (field[x][y] == '\\') {
                /*
                    0 -> 3
                    1 -> 2
                    2 -> 1
                    3 -> 0
                */
                dir = 3 - dir;
            }
            x += dx[dir];
            y += dy[dir];
        }

        System.out.println(nRef);
    }
}