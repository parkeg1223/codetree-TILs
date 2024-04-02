import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] field;

    public static List<int[]> getNotOverlappedIdx(int n) {
        List<int[]> idxs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                for (int k = j; k < n; k++) {
                    for (int l = k+1; l <= n; l++) {
                        idxs.add(new int[] {i, j, k, l});
                    }
                }
            }
        }

        return idxs;
    }

    public static List<int[]> getOverlappedIdx(int n) {
        List<int[]> idxs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = k+1; l <= n; l++) {
                        idxs.add(new int[] {i, j, k, l});
                    }
                }
            }
        }

        return idxs;
    }

    public static int getSumOfSquares(int[] sq1, int[] sq2) {
        int sum = 0;
        for (int i = sq1[0]; i < sq1[1]; i++) {
            for (int j = sq2[0]; j < sq2[1]; j++) {
                sum += field[i][j];
            }
        }

        for (int i = sq1[2]; i < sq1[3]; i++) {
            for (int j = sq2[2]; j < sq2[3]; j++) {
                sum += field[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        field = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSum = Integer.MIN_VALUE;
        List<int[]> rowIdx = getNotOverlappedIdx(n);
        List<int[]> colIdx = getOverlappedIdx(m);

        for (int i = 0; i < rowIdx.size(); i++) {
            for (int j = 0; j < colIdx.size(); j++) {
                maxSum = Math.max(maxSum, getSumOfSquares(rowIdx.get(i), colIdx.get(j)));
            }
        }

        rowIdx = getOverlappedIdx(n);
        colIdx = getNotOverlappedIdx(m);

        for (int i = 0; i < rowIdx.size(); i++) {
            for (int j = 0; j < colIdx.size(); j++) {
                maxSum = Math.max(maxSum, getSumOfSquares(rowIdx.get(i), colIdx.get(j)));
            }
        }

        System.out.println(maxSum);

    }
}