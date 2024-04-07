import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static int[][] field, marbles;
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}
	
	public static void simulate() {
		int[][] newMarbles = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (marbles[i][j] == 1) {
					int maxIdx = 0, maxVal = Integer.MIN_VALUE;
					for (int k = 0; k < 4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						if (!inRange(x, y)) continue;
						if (maxVal < field[x][y]) {
							maxVal = field[x][y];
							maxIdx = k;
						}
					}
					newMarbles[i+dx[maxIdx]][j+dy[maxIdx]]++;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (newMarbles[i][j] > 1) newMarbles[i][j] = 0;
			}
		}
		
		for (int i = 0; i < n; i++) {
			marbles[i] = Arrays.copyOf(newMarbles[i], n);
		}
	}

	public static int getNumOfMarbles() {
		int numOfMarbles = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (marbles[i][j] > 0) numOfMarbles++;
			}
		}
		return numOfMarbles;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		field = new int[n][n];
		marbles = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			marbles[x][y]++;
		}
		
		for (int i = 0; i < t; i++) {
			simulate();
		}
		
		System.out.println(getNumOfMarbles());
		
	}
}