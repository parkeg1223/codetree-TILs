import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()) - 1;
        int[][] field = new int[n][n];
        int[] highestIdxs = new int[n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(in.readLine());
        	for (int j = 0; j < n; j++) {
        		field[i][j] = Integer.parseInt(st.nextToken());
        		if (highestIdxs[j] == 0 && field[i][j] == 1) {
        			highestIdxs[j] = i;
        		}
        	}
        }
        
        int highstIdx = n;
        for (int i = k; i < k+m; i++) {
        	if (highestIdxs[i] != 0) {
        		highstIdx = Math.min(highstIdx, highestIdxs[i]);
        	}
        }
        
        Arrays.fill(field[highstIdx-1], k, k+m, 1);
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		sb.append(field[i][j]).append(' ');
        	}
        	sb.append('\n');
        }
        System.out.println(sb.toString());
        
    }
}