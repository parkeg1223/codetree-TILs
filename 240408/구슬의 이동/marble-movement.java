import java.io.*;
import java.util.*;

public class Main {
	static int n, m, t, k;
	static Marble[] marbles;
	static PriorityQueue<Marble>[][] field, newField;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static class Marble implements Comparable<Marble> {
		int num, x, y, dir, v;
		
		Marble() {}
		Marble(int num, int x, int y, int dir, int v) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.v = v;
		}
		
		@Override
		public int compareTo(Marble o) {
			if (this.v == o.v) {
				return this.num - o.num;
			}
			return this.v - o.v;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}
	
	public static void move() {
		for (int i = 1; i <= m; i++) {
			Marble m = marbles[i];
			if (m == null) continue;
			int x = m.x;
			int y = m.y;
			int nStep = m.v % ((n-1) * 2);
			while (nStep-- > 0) {
				if (!inRange(x + dx[m.dir], y + dy[m.dir])) m.dir = (m.dir + 2) % 4;
				x += dx[m.dir];
				y += dy[m.dir];
			}
			m.x = x; m.y = y;
			newField[m.x][m.y].offer(new Marble(m.num, m.x, m.y, m.dir, m.v));
		}
	}
	
	public static void remove() {
		for (int i = 1; i <= m; i++) {
			Marble m = marbles[i];
			if (m != null) {
				int nSize = newField[m.x][m.y].size();
				for (int j = 0; j < nSize-k; j++) {
					Marble ma = newField[m.x][m.y].poll();
					marbles[ma.num] = null;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			field[i] = Arrays.copyOf(newField[i], n);
			for (int j = 0; j < n; j++) {
                newField[i][j].clear();
            }
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		field = new PriorityQueue[n][n];
		newField = new PriorityQueue[n][n];
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		marbles = new Marble[m+1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				field[i][j] = new PriorityQueue<>();
				newField[i][j] = new PriorityQueue<>();
			}
		}
		
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			char ch = st.nextToken().charAt(0);	
			int v = Integer.parseInt(st.nextToken());
			marbles[i] = new Marble(i, r, c, toDir(ch), v);
		}
		
		for (int i = 0; i < t; i++) {
			move();
			remove();
		}
		
		int numOfMarbles = 0;
		for (int i = 1; i <= m; i++) {
			if (marbles[i] != null) numOfMarbles++;
		}
		
		System.out.println(numOfMarbles);
	}
	
	public static int toDir(char ch) {
		int dir = 0;
		if (ch == 'L') dir = 3;
		else if (ch == 'R') dir = 1;
		else if (ch == 'U') dir = 0;
		else if (ch == 'D') dir = 2;
		return dir;
	}
}