import java.io.*;
import java.util.*;

public class Main {
	public static int n, x, y;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static int[][] field;
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}
	
	public static int simulate(int r, int c, int dir) {
		int time = 1;
		x = r; y = c;
		while (true) {
			time++;
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (!inRange(nx, ny)) return time;
			if (field[nx][ny] == 1) dir ^= 1;
			else if (field[nx][ny] == 2) dir ^= 3;
			x = nx; y = ny;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine().trim());
		field = new int[n][n];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxTime = 0;
		int x = 0, y = 0, dir = 0;
		for (int i = 0; i < 4*n; i++) {
			if (i < n) {
				x = 0;
				y = i%n;
				dir = 2;
			} else if (i < 2*n) {
				x = i%n;
				y = n-1;
				dir = 3;
			} else if (i < 3*n) {
				x = n-1;
				y = n-1-(i%n);
				dir = 0;
			} else {
				x = n-1-(i%n);
				y = 0;
				dir = 1;
			}
			if (field[x][y] == 1) dir ^= 1;
			else if (field[x][y] == 2) dir ^= 3;
			maxTime = Math.max(maxTime, simulate(x, y, dir));
		}
		
		System.out.println(maxTime);
	}
}