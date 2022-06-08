import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BunnyIdFromCoordinates {

    public static void main(String args[]) {

        System.out.println("(3,2) => " + solution(3, 2));
        System.out.println("(5, 10) => " + solution(5, 10));
        System.out.println("(1, 50) => " + solution(1, 50));
        //System.out.println("(99999, 99999) => " + solution(99999, 99999));
    }

    public static String solution(long x, long y) {
        // the smallest possible triangle where points x / y are on the hypotenuse.
        // slope = 1 because base and height are the same.
        // slope = hieght / base = 1
        // hieght = base * slope
        // a^2 + b^2 = c
        // c = x + y
        // (b*slope)^2 + (b*slope)^2  = x + y
        // math stuff yay
        int max = (int) Math.round((int) (x + y)-2 / Math.sqrt(2));
        System.out.println(max);
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
        // x = 0 {1, 2, 4, 7} for both, only one is reversed. holds for all values of x
        List<List<Long>> listVer = floydsTriangleList(max);
        return Long.toString(listVer.get((int) x - 1).get((int) (y - 1)));
    }

    static List<List<Long>> floydsTriangleList(int MAX) {
        long value = 1;
        // long[][] floydsTriangle = new long[MAX][MAX];
        List<List<Long>> floydsTriangleList = new ArrayList<List<Long>>();
        for (int i = 0; i < MAX; i++) {
            // create empty lists until max
            floydsTriangleList.add(new ArrayList<Long>());
        }
        for (int yAxis = MAX; yAxis > 0; yAxis--) {
            for (int xAxis = 1; xAxis <= MAX - yAxis + 1; xAxis++) {
                floydsTriangleList.get(xAxis - 1).add(value);
                // floydsTriangle[xAxis - 1][yAxis - 1] = value;
                value++;
            }
        }
        return floydsTriangleList;
    }
}
