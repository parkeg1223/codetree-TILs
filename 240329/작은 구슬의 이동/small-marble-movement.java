import java.util.*;
import java.io.*;

public class Main {
    static int n;

    private static boolean inRange(int x, int y) {
        return (x > 0 && x <= n && y > 0 && y <= n);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char d = st.nextToken().charAt(0);
        int dir = 0;
        if (d == 'U') dir = 0;
        else if (d == 'R') dir = 1;
        else if (d == 'D') dir = 2;
        else dir = 3;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int x = r, y = c;
        for (int i = 0; i < t; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (inRange(nx, ny)) {
                x = nx; y = ny;
            } else {
                dir = (dir + 2) % 4;
            }
        }

        System.out.println(x + " " + y);
    }
}