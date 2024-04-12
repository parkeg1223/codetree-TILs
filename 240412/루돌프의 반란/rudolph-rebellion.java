import java.io.*;
import java.util.*;

public class Main {
	static int N, M, P, C, D, rr, rc;
	static int nSanta;
	static int[][] field;
	static Santa[] santas;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static class Santa {
		int num, r, c, point, isStunned = 0;
		boolean isAlive = true;
		
		Santa(int num, int r, int c, int point) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.point = point;
		}

		@Override
		public String toString() {
			return "Santa [num=" + num + ", point=" + point + "]";
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);
	}
	
	public static int getRudolphNextDir(int rx, int ry, int sx, int sy) {
		if (rx < sx) {
			if (ry < sy) return 3;
			else if (ry == sy) return 4;
			else return 5;
		} else if (rx == sx) {
			if (ry > sy) return 6;
			else if (ry < sy) return 2;
		} else {
			if (ry < sy) return 1;
			else if (ry == sy) return 0;
			else return 7;
		}
		return -1;
 	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nSanta = P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		field = new int[N][N];
		santas = new Santa[P+1];
		
		st = new StringTokenizer(in.readLine());
		rr = Integer.parseInt(st.nextToken())-1;
		rc = Integer.parseInt(st.nextToken())-1;
		field[rr][rc] = -1;
		
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			santas[num] = new Santa(num, x, y, 0);
			field[x][y] = num;
		}
		
		while (M-- > 0 && nSanta > 0) {
			// 1. 루돌프 이동
			// 1-1. 가장 가까운 산타 찾기: nearestSantaIdx번 산타
			int nearestSantaIdx = -1, minDist = Integer.MAX_VALUE;
			for (int i = 1; i <= P; i++) {
				if (santas[i].isAlive) {
					int dist = getDistance(rr, rc, santas[i].r, santas[i].c);
					if (dist < minDist) {
						nearestSantaIdx = i;
						minDist = dist;
					} else if (dist == minDist) {
						if ((santas[i].r > santas[nearestSantaIdx].r) ||
						(santas[i].r == santas[nearestSantaIdx].r 
								&& santas[i].c > santas[nearestSantaIdx].c)) {
							nearestSantaIdx = i;
						}
					}
				}
			}
			
			// 1-2. 루돌프 이동 
			int dir = getRudolphNextDir(rr, rc, santas[nearestSantaIdx].r, santas[nearestSantaIdx].c);
			field[rr][rc] = 0;
			rr += dx[dir];
			rc += dy[dir];
			
			// 1-3. 충돌 여부 체크
			if (field[rr][rc] > 0) {
				int nx = rr, ny = rc, sNum = field[nx][ny], tempC = C;
				santas[sNum].point += C;
				santas[sNum].isStunned = 2;
				field[nx][ny] = -1;
				while (sNum > 0) {
					int temp = 0;
					nx += C * dx[dir];
					ny += C * dy[dir];
					if (!inRange(nx, ny)) {
						santas[sNum].isAlive = false;
						nSanta--;
						break;
					}
					santas[sNum].r = nx;
					santas[sNum].c = ny;
					temp = field[nx][ny];
					field[nx][ny] = sNum;
					sNum = temp;
					C = 1;
				}
				C = tempC;
			}
			field[rr][rc] = -1;		
			
			// 2. 산타 이동
			// 2-1. 기절한 산타 살려내고 continue
			for (int i = 1; i <= P; i++) {
				if (santas[i].isAlive) {
					if (santas[i].isStunned > 0) {
						santas[i].isStunned--;
						continue;
					}
					int prevDist = getDistance(rr, rc, santas[i].r, santas[i].c);
					minDist = Integer.MAX_VALUE;
					dir = -1;
					for (int k = 0; k < 8; k+=2) {
						int nx = santas[i].r + dx[k];
						int ny = santas[i].c + dy[k];
						if (!inRange(nx, ny) || field[nx][ny] > 0) continue;
						int newDist = getDistance(rr, rc, nx, ny);
						if (prevDist < newDist || minDist <= newDist) continue;
						dir = k;
						minDist = newDist;
					}
					
					if (dir == -1) continue;
					int nx = santas[i].r + dx[dir];
					int ny = santas[i].c + dy[dir];
					int sNum = i, tempD = D;
					field[santas[i].r][santas[i].c] = 0;
					if (nx == rr && ny == rc) {
						santas[i].point += D;
						santas[i].isStunned = 1;
						dir = (dir + 4) % 8;
						while (sNum > 0) {
							int temp = 0;
							nx += D * dx[dir];
							ny += D * dy[dir];
							if (!inRange(nx, ny)) {
								santas[sNum].isAlive = false;
								nSanta--;
								break;
							}
							santas[sNum].r = nx;
							santas[sNum].c = ny;
							temp = field[nx][ny];
							field[nx][ny] = sNum;
							sNum = temp;
							D = 1;
						}
						D = tempD;
					} else {
						field[nx][ny] = sNum;
						santas[i].r = nx;
						santas[i].c = ny;
					}
				}
			}
		
			// 3. 생존한 산타 point 증가
			for (int i = 1; i <= P; i++) {
				if (santas[i].isAlive) santas[i].point++;
			}		
		}
		
		for (int i = 1; i <= P; i++) {
			sb.append(santas[i].point).append(' ');
		}
		
		System.out.println(sb.toString());
	}
}