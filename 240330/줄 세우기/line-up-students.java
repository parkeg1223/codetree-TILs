import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    int idx, height, weight;

    public Student() {}
    public Student(int idx, int height, int weight) {
        this.idx = idx;
        this.height = height;
        this.weight = weight;
    }

    public void getInfo() {
        System.out.println(this.height + " " + this.weight + " " + this.idx);
    }

    @Override
    public int compareTo(Student s) {
        if (this.height == s.height) {
            if (this.weight == s.weight) {
                return this.idx - s.idx;
            }
            return s.weight - this.weight;
        }
        return s.height - this.height;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        Student[] students = new Student[N];
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            students[i] = new Student(
                i+1,
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );
        }

        Arrays.sort(students);
        for (int i = 0; i < N; i++) {
            sb.append(students[i].height).append(" ")
                .append(students[i].weight).append(" ")
                .append(students[i].idx).append("\n");
        }

        System.out.print(sb.toString());
    }
}