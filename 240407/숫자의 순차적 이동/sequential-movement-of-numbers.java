import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] field, numPos;
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}
	
	public static void simulate() {
		for (int i = 1; i <= n*n; i++) {
			int maxVal = 0;
			int[] maxPos = new int[2];
			int x = numPos[i][0], y = numPos[i][1];
			for (int k = 0; k < 8; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (inRange(nx, ny) && maxVal < field[nx][ny]) {
					maxPos[0] = nx;
					maxPos[1] = ny;
					maxVal = field[nx][ny];
				}
			}
			
			field[x][y] = maxVal;
			numPos[maxVal][0] = x;
			numPos[maxVal][1] = y;
			field[maxPos[0]][maxPos[1]] = i;
			numPos[i][0] = maxPos[0];
			numPos[i][1] = maxPos[1];	
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		field = new int[n][n];
		numPos = new int[n*n+1][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
				numPos[field[i][j]][0] = i;
				numPos[field[i][j]][1] = j;
			}
		}
		
		for (int i = 0; i < m; i++) simulate();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(field[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
}