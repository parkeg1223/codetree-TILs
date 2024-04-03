import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] d = {1, -1};
    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {1, -1, -1, 1};
    static int[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine().trim());
        field = new int[n][n];
        
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(in.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int[] m = new int[4];
        for (int i = 0; i < 4; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }
        int dir = Integer.parseInt(st.nextToken());

        List<int[]> idxList = new ArrayList<>();
        List<Integer> valList = new ArrayList<>();
        int x = r, y = c;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < m[i]; j++) {
                x += dx[i];
                y += dy[i];
                idxList.add(new int[]{x, y});
                valList.add(field[x][y]);
            }
        }
        
        Collections.rotate(valList, d[dir]);

        for (int i = 0; i < idxList.size(); i++) {
            int[] idx = idxList.get(i);
            field[idx[0]][idx[1]] = valList.get(i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(field[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}