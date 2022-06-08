import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BunnyIdFromCoordinates {
    final static int MAX = 14;

    public static void main(String args[]) {
        
        System.out.println("(3,2) => " + solution(3, 2));
        System.out.println("(5, 10) => "+ solution(5, 10));
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
        // other stuff later
        long[][] floydsTriangle = floydsTriangle();
        // we can cheat now
        // each diagonal in our floyds triangle translates to a row in the weird
        // triangle google wants me to make
        int zeros = 0;
        for (int i = 0; i < floydsTriangle[(int) x - 1].length; i++) {
            if (floydsTriangle[(int) x - 1][i] == 0) {
                zeros++;
            }
        }
        return Long.toString(floydsTriangle[(int) x - 1][Math.abs((int) y - (MAX - zeros))]);
    }

    static long[][] floydsTriangle() {
        long value = 1;
        long[][] floydsTriangle = new long[MAX][MAX];
        for (int yAxis = MAX; yAxis > 0; yAxis--) {
            for (int xAxis = 1; xAxis <= MAX - yAxis + 1; xAxis++) {
                floydsTriangle[xAxis - 1][yAxis - 1] = value;
                value++;
            }
        }
        return floydsTriangle;
    }
}
