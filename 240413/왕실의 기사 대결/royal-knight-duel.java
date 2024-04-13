import java.io.*;
import java.util.*;

public class Main {
	static int L, N, Q;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] field, kField;
	static int[] damages;
	static Knight[] knights;
	static boolean[] moved;
	
	static class Knight {
		int n, r, c, h, w, k;
		boolean isAlive = true;
		
		public Knight(int n, int r, int c, int h, int w, int k) {
			this.n = n;
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Knight [n=" + n + ", k=" + k + "]";
		}
	}
	
	static boolean inRange(int x, int y) {
		return (x >= 0 && x < L && y >= 0 && y < L);
	}
	
	static boolean isMovable(int num, int d) {
		Knight k = knights[num];
		int r = k.r, c = k.c;
		if (d == 0) r -= 1;
		else if (d == 1) c += k.w;
		else if (d == 2) r += k.h;
		else if (d == 3) c -= 1;
		if (!inRange(r, c)) {
			return false;
		}
		if (d == 0 || d == 2) {
			for (int j = c; j < c+k.w; j++) {
				if (kField[r][j] == 0) {
					if (field[r][j] == 2) {
						return false;
					}
				} else if (!isMovable(kField[r][j], d)) {
					return false;
				}
			}
		} else if (d == 1 || d == 3) {
			for (int i = r; i < r+k.h; i++) {
				if (kField[i][c] == 0) {
					if (field[i][c] == 2) {
						return false;
					}
				} else if (!isMovable(kField[i][c], d)) {
					return false;
				}
			}
		}
		moved[num] = true;
		return true;
	}
	
	static void move(int i, int d) {
		int[][] temp = new int[L][L];
		for (int j = 1; j <= N; j++) {
			Knight kn = knights[j];
			if (moved[j]) {
				kn.r += dx[d];
				kn.c += dy[d];
			}
			
			moved[i] = false;
			for (int k = kn.r; k < kn.r+kn.h; k++) {
				for (int l = kn.c; l < kn.c+kn.w; l++) {
					if (moved[j] && field[k][l] == 1) damages[j]++;
					temp[k][l] = j;
				}
			}
			
			for (int k = 0; k < L; k++) {
				kField[k] = Arrays.copyOf(temp[k], L);
			}
		}
		Arrays.fill(moved, false);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		field = new int[L][L];
		kField = new int[L][L];
		knights = new Knight[N+1];
		moved = new boolean[N+1];
		damages = new int[N+1];
		
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < L; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			knights[i] = new Knight(i, r, c, h, w, k);
			
			for (int j = r; j < r+h; j++) {
				for (int l = c; l < c+w; l++) {
					kField[j][l] = i;
				}
			}
		}
		
		for (int idx = 0; idx < Q; idx++) {
			st = new StringTokenizer(in.readLine());
			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if (isMovable(i, d)) {
				move(i, d);
			}
		}
		
		int dSum = 0;
		for (int i = 1; i <= N; i++) {
			dSum += damages[i];
		}
		
		System.out.println(dSum);
	}
}