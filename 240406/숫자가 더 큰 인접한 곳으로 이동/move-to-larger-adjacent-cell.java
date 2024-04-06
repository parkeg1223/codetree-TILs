import java.io.*;
import java.util.*;

public class Main {
	static int n, r, c, currX, currY;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] field;
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        field = new int[n][n];
        currX = r = Integer.parseInt(st.nextToken()) - 1;
        currY = c = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(in.readLine());
        	for (int j = 0; j < n; j++) {
        		field[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        
        boolean isProceedable = true;
        while (isProceedable) {
            isProceedable = false;
        	sb.append(field[currX][currY]).append(' ');
        	for (int i = 0; i < 4; i++) {
        		int nx = currX + dx[i];
        		int ny = currY + dy[i];
        		if (inRange(nx, ny) && field[nx][ny] > field[currX][currY]) {
        			currX = nx; currY = ny;
        			isProceedable = true;
        			break;
        		}
        	}
        }
        
        System.out.println(sb.toString());
    }

}