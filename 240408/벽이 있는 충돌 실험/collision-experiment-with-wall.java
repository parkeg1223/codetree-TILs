import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static List<Marble>[][] field;
	
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
		
		@Override
		public String toString() {
			return "Marble [x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}

	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static void move() {
		List<Marble>[][] newField = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newField[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < field[i][j].size(); k++) {
					Marble m = field[i][j].get(k);
					int nx = m.x + dx[m.dir];
					int ny = m.y + dy[m.dir];
					if (inRange(nx, ny)) {
						m.x = nx;
						m.y = ny;
						newField[nx][ny].add(m);
					} else {
						m.dir = (m.dir + 2) % 4;
						newField[m.x][m.y].add(m);
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			field[i] = Arrays.copyOf(newField[i], N);
		}
	}
	
	public static void remove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (field[i][j].size() > 1) field[i][j].clear();
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
			int M = Integer.parseInt(st.nextToken());
			field = new ArrayList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					field[i][j] = new ArrayList<>();
				}
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken())-1;
				int y = Integer.parseInt(st.nextToken())-1;
				char ch = st.nextToken().charAt(0);
				field[x][y].add(new Marble(x, y, ch));
			}
			
			for (int i = 0; i < 2*N; i++) {
				move();
				remove();
			}
			
			int numOfMarbles = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (field[i][j].size() > 0) numOfMarbles++; 
				}
			}
			
			sb.append(numOfMarbles).append('\n');
		}
		
		System.out.println(sb.toString());
	}
}