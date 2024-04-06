import java.io.*;
import java.util.*;

public class Main {
	static int N, x, y, dir;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static char[][] field;
	static boolean[][] visited;
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine().trim());
        field = new char[N][N];
        visited = new boolean[N][N];
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;
        
        for (int i = 0; i < N; i++) {
    		field[i] = in.readLine().toCharArray();
        }

        visited[x][y] = true;
        int currX = x, currY = y, time = 0;
        while (true) {
        	time++;
        	int nx = 0, ny = 0;
        	for (int i = 0; i < 4; i++) {
        		nx = currX + dx[dir];
            	ny = currY + dy[dir];
            	if (!inRange(nx, ny)) {
            		System.out.println(time);
            		return;
            	} else if (field[nx][ny] == '#') dir++;
            	else break;
        	}
        	if (dir == 4) {
        		System.out.println(-1);
        		return;
        	} else {
        		if (visited[nx][ny]) {
        			System.out.println(-1);
        			return;
        		} else visited[nx][ny] = true;
        		if (field[nx + dx[(dir+3)%4]][ny + dy[(dir+3)%4]] == '#') {
        			currX = nx; currY = ny;
            	} else {
            		time++;
            		dir = (dir + 3) % 4;
            		currX = nx + dx[dir];
            		currY = ny + dy[dir];
            		if (visited[currX][currY]) {
            			System.out.println(-1);
            			return;
            		} else visited[currX][currY] = true;
            	}
        	}
        }
    }
}