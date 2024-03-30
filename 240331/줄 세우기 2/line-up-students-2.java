import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    int h, w, idx;

    public Student() {}
    public Student(int h, int w, int idx) {
        this.h = h;
        this.w = w;
        this.idx = idx;
    }

    @Override
    public int compareTo(Student s) {
        if (this.h == s.h) {
            return s.w - this.w;
        }
        return this.h - s.h;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine().trim());

        Student[] students = new Student[N];
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            students[i] = new Student(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                i+1
            );
        }

        Arrays.sort(students);
        for (int i = 0; i < N; i++) {
            sb.append(students[i].h).append(' ')
                .append(students[i].w).append(' ')
                .append(students[i].idx).append('\n');
        }

        System.out.print(sb.toString());
    }
}