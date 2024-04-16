import java.io.*;
import java.util.*;

public class Main {
	static int R, C, K, nAirc, answer;
	static int[] highest;
	static int[][] field;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Aircraft[] aircrafts;
	
	static class Aircraft {
		int x, y, dir, bot;
		
		Aircraft(int x, int y, int dir, int bot) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.bot = bot;
		}
	}
	
	static boolean inRange(int x, int y) {
		return (x >= 0 && x < R && y >= 0 && y < C);
	}
	
	static boolean isEmptySpace(int x, int y) {
		if (y <= 0 || y >= C-1) return false;
		if (x == -1) {
			return (field[x+1][y] == 0);
		} else if (x == 0) {
			return (field[x][y] == 0 && field[x+1][y] == 0
				&& field[x][y-1] == 0 && field[x][y+1] == 0);
		} else {
			return (x < R-1 && field[x-1][y] == 0 && field[x][y] == 0 && field[x+1][y] == 0
					&& field[x][y-1] == 0 && field[x][y+1] == 0);
		}
	}
	
	static int getMin(int x1, int x2, int x3) {
		int min = x1;
		min = Math.min(min, x2);
		min = Math.min(min, x3);
		return min;
	}
	
	static boolean fall(int col, int dir) {
		int sr = -2;
		if (col == 0) {
			sr = Math.min(highest[col], highest[col+1]);
		} else if (col == C-1) sr = Math.min(highest[col-1], highest[col]);
		else sr = getMin(highest[col-1], highest[col], highest[col+1])-2; 
		int x = sr, y = col, exit = dir;
		
		// 아래, 왼쪽, 오른쪽
		while (true) {
			// 1. 아래
			if (isEmptySpace(x+1, y)) {
				x++;
			} else if (isEmptySpace(x, y-1) && isEmptySpace(x+1, y-1)) {
				x++; y--;
				exit = (exit + 3) % 4;
			} else if (isEmptySpace(x, y+1) && isEmptySpace(x+1, y+1)) {
				x++; y++;
				exit = (exit + 1) % 4;
			} else break;
		}
		
		if (getMin(Math.min(highest[y-1], x), Math.min(highest[y], x-1), 
				Math.min(highest[y+1], x)) < 0) return false;
		
		highest[y-1] = Math.min(highest[y-1], x);
		highest[y] = Math.min(highest[y], x-1);
		highest[y+1] = Math.min(highest[y+1], x);
		
		draw(x, y, ++nAirc);
		aircrafts[nAirc] = new Aircraft(x, y, exit, x+1);
		
		return true;
	}
	
	static void draw(int x, int y, int num) {
		field[x][y] = num;
		field[x-1][y] = num;
		field[x][y+1] = num;
		field[x+1][y] = num;
		field[x][y-1] = num;
	}
	
	static int score() {
		for (int i = 1; i <= nAirc; i++) {
			Aircraft ac = aircrafts[i];
			boolean[] visited = new boolean[nAirc+1];
			Queue<Integer> queue = new ArrayDeque<>();
			queue.offer(i);
			visited[i] = true;
			int maxBot = ac.bot;
			
			while (!queue.isEmpty()) {
				int n = queue.poll();
				Aircraft a = aircrafts[n];
				maxBot = Math.max(maxBot, a.bot);
				for (int j = 0; j < 4; j++) {
					int nx = a.x + dx[a.dir] + dx[j];
					int ny = a.y + dy[a.dir] + dy[j];
					if (inRange(nx, ny) && field[nx][ny] != 0) {
						int nn = field[nx][ny];
						if (visited[nn]) continue;
						visited[nn] = true;
						queue.offer(nn);
					}
						
				}
			}
			
			ac.bot = maxBot;
		}
		return aircrafts[nAirc].bot+1;
		
	}
	
	static void reset() {
		nAirc = 0;
		Arrays.fill(highest, R);
		Arrays.fill(aircrafts, null);
		
		for (int i = 0; i < R; i++) {
			Arrays.fill(field[i], 0);
		}
	}
	
	static void simulate(int col, int dir) {
		if (!fall(col, dir)) reset();
		else {
			answer += score();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		highest = new int[C];
		Arrays.fill(highest, R);
		field = new int[R][C];
		aircrafts = new Aircraft[1001];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int col = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			simulate(col, dir);
		}
		
		System.out.println(answer);
	}
}