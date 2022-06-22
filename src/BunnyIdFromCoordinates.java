import java.util.ArrayList;
import java.util.List;

public class BunnyIdFromCoordinates {

    public static void main(String args[]) {
        System.out.println("(3,2) => " + solution(3, 2));
        System.out.println("(5, 10) => " + solution(5, 10));
        System.out.println("(1, 50) => " + solution(1, 50));

        System.out.println("(4,1) => " + solution(4, 1));
        System.out.println("(1000, 2000) => " + solution(1000, 2000));
        System.out.println("(100000, 100000) => " + solution(100000, 100000));
    }

    public static String solution(long x, long y) {
        long yStart = 1;
        for (long i = 1; i < y; i++) {
            yStart = i + yStart;
        }
        long xStart = yStart;
        for (long i = 1; i < x; i++) {
            xStart = (y + i + xStart);
        }
        return Long.toString(xStart);
    }
}
