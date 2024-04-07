import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Marble[] marbles;
	
	public static class Marble {
		int x, y, dir;
		
		Marble() {}
		Marble(int x, int y, char ch) {
			this.x = x;
			this.y = y;
			if (ch == 'U') dir = 0;
			else if (ch == 'R') dir = 1;
			else if (ch == 'D') dir = 2;
			else if (ch == 'L') dir = 3;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static void simulate() {
		int[][] newField = new int[N][N];
		
		for (int i = 0; i < M; i++) {
			if (marbles[i] == null) continue;
            Marble m = marbles[i];
            int nx = m.x + dx[m.dir];
            int ny = m.y + dy[m.dir];
            if (inRange(nx, ny)) {
                marbles[i].x = nx;
                marbles[i].y = ny;
                newField[nx][ny]++;
            } else {
                marbles[i].dir = (m.dir + 2) % 4;
                newField[m.x][m.y]++;
            }
		}
		
		for (int i = 0; i < M; i++) {
            Marble m = marbles[i];
            if (m == null) continue;
            if (newField[m.x][m.y] > 1) {
                marbles[i] = null;
            }
        }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			marbles = new Marble[M];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken())-1;
				int y = Integer.parseInt(st.nextToken())-1;
				char ch = st.nextToken().charAt(0);
				marbles[i] = new Marble(x, y, ch);
			}
			
			for (int i = 0; i < 2*N; i++) {
				simulate();
			}
			
			int numOfMarbles = 0;
			for (int i = 0; i < M; i++) {
                if (marbles[i] != null) numOfMarbles++;
			}
			
			sb.append(numOfMarbles).append('\n');
		}
		
		System.out.println(sb.toString());
	}
}