import java.io.*;
import java.util.*;

public class Main {
	static int n, m, r, c;
	static int[][] field;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[] dice = new int[] {1, 6, 4, 3, 2, 5};	// top, bottom, left, right, front, back
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}
	
	public static void simulate(char ch) {
		int top = dice[0], bottom = dice[1], 
			left = dice[2], right = dice[3],
			front = dice[4], back = dice[5];
		if (ch == 'L') {
			if (!inRange(r + dx[3], c + dy[3])) return;
			r += dx[3];
			c += dy[3];
			dice[0] = right;
			dice[1] = left;
			dice[2] = top;
			dice[3] = bottom;
		} else if (ch == 'R') {
			if (!inRange(r + dx[1], c + dy[1])) return;
			r += dx[1];
			c += dy[1];
			dice[0] = left;
			dice[1] = right;
			dice[2] = bottom;
			dice[3] = top;
		} else if (ch == 'U') {
			if (!inRange(r + dx[0], c + dy[0])) return;
			r += dx[0];
			c += dy[0];
			dice[0] = front;
			dice[1] = back;
			dice[4] = bottom;
			dice[5] = top;
		} else if (ch == 'D') {
			if (!inRange(r + dx[2], c + dy[2])) return;
			r += dx[2];
			c += dy[2];
			dice[0] = back;
			dice[1] = front;
			dice[4] = top;
			dice[5] = bottom;
		}
		field[r][c] = dice[1];
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        
        field = new int[n][n];
        field[r][c] = dice[1];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < m; i++) {
        	simulate(st.nextToken().charAt(0));
        }
        
        int sum = 0;
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		sum += field[i][j];
        	}
        }
        
        System.out.println(sum);
    }
}