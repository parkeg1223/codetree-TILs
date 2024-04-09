import java.io.*;
import java.util.*;

public class Main {
	static int N, mNum, time, lastCollisionTime = -1;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] sqrDist;
	static Marble[] marbles;
	
	static class Marble {
		int num, x, y, w, d;
		
		Marble(int num, int x, int y, int w, int d) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.w = w;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Marble [num=" + num + ", x=" + (y-2000.0)/2 + ", y=" + (2000.0-x)/2 + "]";
		}
	}
	
	public static int toDir(char ch) {
		if (ch == 'L') return 3;
		else if (ch == 'R') return 1;
		else if (ch == 'U') return 0;
		else if (ch == 'D') return 2;
		return -1;
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < 4001 && y >= 0 && y < 4001);
	}
	
	public static boolean getDist() {
		boolean isFinished = true;
		for (int i = 1; i <= N; i++) {
			if (marbles[i] == null) continue;
			for (int j = i+1; j <= N; j++) {
				if (marbles[j] == null) continue;
				int dist = getSqrDistOfPoints(marbles[i].x, marbles[i].y,
						marbles[j].x, marbles[j].y);
				if (sqrDist[i][j] > dist) isFinished = false;
				sqrDist[i][j] = dist;
			}
		}
		return isFinished;
	}
	
	public static boolean move() {
		boolean hasCollision = false;
		int[][] temp = new int[4001][4001];
		for (int i = 1; i <= N; i++) {
			if (marbles[i] == null) continue;
			int nx = marbles[i].x + dx[marbles[i].d];
			int ny = marbles[i].y + dy[marbles[i].d];;
			if (inRange(nx, ny)) {
				temp[nx][ny]++;
				marbles[i].x += dx[marbles[i].d];
				marbles[i].y += dy[marbles[i].d];
			} else {
				marbles[i] = null;
				mNum--;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (marbles[i] == null) continue;
			Marble m = marbles[i];
			if (temp[m.x][m.y] > 1) {
				for (int j = i+1; j <= N; j++) {
					if (marbles[j] == null) continue;
					if (marbles[j].x == m.x && marbles[j].y == m.y) {
						hasCollision = true;
						temp[m.x][m.y]--;
						mNum--;
						if (marbles[j].w >= m.w) {
							marbles[i] = null;
							break;
						} else {
							marbles[j] = null;
						}
					}
				}
			}
		}
		
		return hasCollision;
	}
	
	public static int getSqrDistOfPoints(int x1, int y1, int x2, int y2) {
		return (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int tc = 1; tc <= T; tc++) {
			mNum = N = Integer.parseInt(in.readLine());
			marbles = new Marble[N+1];
			sqrDist = new int[N+1][N+1];
			time = 0;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int d = toDir(st.nextToken().charAt(0));
				marbles[i] = new Marble(i, -2*y+2000, 2*x+2000, w, d);
			}
			
			
			for (int i = 1; i <= N; i++) {
				for (int j = i+1; j<= N; j++) {
					sqrDist[i][j] = getSqrDistOfPoints(marbles[i].x, marbles[i].y,
							marbles[j].x, marbles[j].y);
				}
			}

			int time = 0;
			while (mNum > 1) {
				time++;
				if (move()) lastCollisionTime = time;
				else if (getDist()) break;
			} 
			
			sb.append(lastCollisionTime).append('\n');
		}
		System.out.println(sb.toString());
	}
}