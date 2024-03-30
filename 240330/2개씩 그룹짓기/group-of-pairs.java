import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (maxSum < nums[i] + nums[N-i-1]) {
                maxSum = nums[i] + nums[N-i-1];
            }
        }

        System.out.println(maxSum);
    }
}