import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int x = 0, y = 0;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            String dir = st.nextToken();
            int dist = Integer.parseInt(st.nextToken());
            if (dir.equals("N")) {
                x += dist*dx[0]; y += dist*dy[0];
            } else if (dir.equals("E")) {
                x += dist*dx[1]; y += dist*dy[1];
            } else if (dir.equals("S")) {
                x += dist*dx[2]; y += dist*dy[2];
            } else {
                x += dist*dx[3]; y += dist*dy[3];
            }
        }

        System.out.println(x + " " + y);
    }
}