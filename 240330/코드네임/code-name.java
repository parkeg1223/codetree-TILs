import java.util.*;
import java.io.*;

class Agent {
    char code;
    int score;

    public Agent() {}

    public Agent(char code, int score) {
        this.code = code;
        this.score = score;
    }
};

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Agent[] agents = new Agent[5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(in.readLine());
            agents[i] = new Agent(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
        }

        Agent worstAgent = agents[0];
        for (int i = 1; i < 5; i++) {
            if (worstAgent.score > agents[i].score) worstAgent = agents[i];
        }

        System.out.println(worstAgent.code + " " + worstAgent.score);
    }
}