import java.io.*;
import java.util.*;

public class Main {
	static int n, r, c;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		
		List<int[]> bombs = new ArrayList<>();
		visited = new boolean[n][n];
		
		bombs.add(new int[] {r, c});
		visited[r][c] = true;
		
		int dist = 1;
		for (int i = 1; i <= m; i++) {
			List<int[]> newBombs = new ArrayList<>();
			for (int j = 0; j < bombs.size(); j++) {
				int[] b = bombs.get(j);
				for (int k = 0; k < 4; k++) {
					int nx = b[0] + dist*dx[k];
					int ny = b[1] + dist*dy[k];
					if (!inRange(nx, ny) || visited[nx][ny]) continue;
					newBombs.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
			bombs.addAll(newBombs);
			dist *= 2;
		}
		
		System.out.println(bombs.size());
	}
}