import java.io.*;
import java.util.*;

public class Main {
	public static int N, x, y, time;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static boolean[][] field, hasApple;
	public static ArrayDeque<int[]> queue = new ArrayDeque<>();
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static boolean simulate(char ch, int dist) {
		for (int i = 0; i < dist; i++) {
			time++;
			int nx = x, ny = y;
			if (ch == 'L') {
				nx += dx[3]; ny += dy[3];
			} else if (ch == 'R') {
				nx += dx[1]; ny += dy[1];
			} else if (ch == 'U') {
				nx += dx[0]; ny += dy[0];
			} else if (ch == 'D') {
				nx += dx[2]; ny += dy[2];
			}
			if (!inRange(nx, ny) || field[nx][ny]) {
				return false;
			}
			queue.offer(new int[] {nx, ny});
			field[nx][ny] = true;
			if (!hasApple[nx][ny]) {
				int[] tail = queue.poll();
				field[tail[0]][tail[1]] = false;
			}
			x = nx; y = ny;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		field = new boolean[N][N];
		hasApple = new boolean[N][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			hasApple[x][y] = true;
		}
		
		field[0][0] = true;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			char ch = st.nextToken().charAt(0);
			int dist = Integer.parseInt(st.nextToken());
			if (!simulate(ch, dist)) break;
		}
		
		System.out.println(time);
		
	}
}