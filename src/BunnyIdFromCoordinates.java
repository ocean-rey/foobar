import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BunnyIdFromCoordinates {
    final static int MAX = 100000;

    public static void main(String args[]) {
        solution(1, 2);
    }

    public static String solution(long x, long y) {
        // given triangle
        // | 7
        // | 4 8
        // | 2 5 9
        // | 1 3 6 10
        // more simply becomes floyds triangle
        // | 1
        // | 2 3
        // | 4 5 6
        // | 7 8 9 10
        // after reflecting and rotating
        // the latter is easier to compute so we will use that one and then do some
        // rotation and reflecting afterwards
        String id = "";
        long[][] floydsTriangle = floydsTriangle();
        System.out.println(Arrays.deepToString(floydsTriangle).replace("], ", "]\n"));
        return id;
    }

    static Map<Character, Long> rotateCords(long x, long y) {
        Map<Character, Long> newCords = new HashMap<Character, Long>();
        newCords.put('x', -y);
        newCords.put('y', x);
        return newCords;
    }

    static long[][] floydsTriangle() {
        long value = 1;
        long[][] floydsTriangle = new long[MAX][MAX];
        for (int yAxis = MAX; yAxis > 0; yAxis--) {
            for (int xAxis = 1; xAxis <= MAX - yAxis + 1; xAxis++) {
                floydsTriangle[yAxis - 1][xAxis - 1] = value;
                value++;
            }
        }
        return floydsTriangle;
    }
}
