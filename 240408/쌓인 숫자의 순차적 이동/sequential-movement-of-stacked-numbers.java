import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayDeque<Integer>[][] field;
	static Number[] numbers;
	static int[][] maxValue;
	
	public static class Number {
		int x, y;
		
		Number() {}
		Number(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}
	
	public static boolean move(int num) {
		int x = numbers[num].x, y = numbers[num].y;
		int maxVal = 0, maxIdx = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (inRange(nx, ny) && maxVal < maxValue[nx][ny]) {
				maxIdx = i;
				maxVal = maxValue[nx][ny];
			}
		}
		
		if (maxVal == 0) return false;
		
		// num 이동
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		while (true) {
			int n = field[x][y].poll();
			queue.offer(n);
			if (n == num) break;
		}
		
		int newX = x+dx[maxIdx], newY = y+dy[maxIdx];
		while (!queue.isEmpty()) {
			int n = queue.pollLast();
			numbers[n].x = newX;
			numbers[n].y = newY;
			maxValue[newX][newY] = Math.max(maxValue[newX][newY], n);
			field[newX][newY].offerFirst(n);
		}
		
		if (field[x][y].size() > 0) {
			maxValue[x][y] = Collections.max(field[x][y]);
		} else maxValue[x][y] = -1;
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		field = new ArrayDeque[n][n];
		maxValue = new int[n][n];
		numbers = new Number[n*n+1];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				field[i][j] = new ArrayDeque<>();
				int num = Integer.parseInt(st.nextToken());
				numbers[num] = new Number(i, j);
				field[i][j].add(num);
				maxValue[i][j] = num;
			}
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < m; i++) {
			if (!move(Integer.parseInt(st.nextToken()))) break;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (field[i][j].size() == 0) sb.append("None");
				while (!field[i][j].isEmpty()) {
					sb.append(field[i][j].poll()).append(' ');
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb.toString());
	}
}