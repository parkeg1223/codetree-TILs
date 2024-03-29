import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        
        int x = 0, y = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        StringTokenizer st;
        int t = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            char d = st.nextToken().charAt(0);
            int dist = Integer.parseInt(st.nextToken());

            int dir = 0;
            if (d == 'N') dir = 0;
            else if (d == 'E') dir = 1;
            else if (d == 'S') dir = 2;
            else dir = 3;
            
            for (int j = 0; j < dist; j++) {
                t++;
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