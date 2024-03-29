import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();

        int x = 0, y = 0, dir = 0, t = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < s.length(); i++) {
            t++;
            char c = s.charAt(i);
            if (c == 'L') {
                dir = (dir + 3) % 4;
            } else if (c == 'R') {
                dir = (dir + 1) % 4;
            } else {
                x += dx[dir];
                y += dy[dir];
                if (x == 0 && y == 0) {
                    System.out.println(t);
                    return;
                }
            }
        }
        System.out.println(-1);
    }
}