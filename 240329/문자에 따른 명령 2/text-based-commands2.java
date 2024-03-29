import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int x = 0, y = 0, dir = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'L') {
                dir = (dir + 3) % 4;
            } else if (c == 'R') {
                dir = (dir + 1)% 4;
            } else {
                x += dx[dir]; y += dy[dir];
            }
        }

        System.out.println(x + " " + y);
    }
}