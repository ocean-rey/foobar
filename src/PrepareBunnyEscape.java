import java.util.ArrayDeque;
import java.util.Arrays;

public class PrepareBunnyEscape {

    public static void main(String args[]) {
        int[][] testCase = { { 0, 1, 1, 0 }, { 0, 0, 0, 1 }, { 1, 1, 0, 0 }, { 1, 1,
                1, 0 } };
        int[][] secondTestCase = { { 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 0 }, { 0,
                0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0 } };
        int[][] thirdTestCase = { { 0, 0, 0, 0, 1, 1 }, { 1, 1, 1, 1, 0, 0 }, { 1, 1,
                1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 1 }, { 0, 0, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0 } };
        int[][] fourthTestCase = { { 0, 1, 0 }, { 1, 0, 0 } };
        int[][] smallTestCase = { { 0, 0 }, { 0, 0 } };
        int firstSolution = solution(testCase);
        System.out.println(firstSolution);
        int secondSolution = solution(secondTestCase);
        System.out.println(secondSolution);
        int thirdSolution = solution(thirdTestCase);
        System.out.println(thirdSolution);
        int fourthSolution = solution(fourthTestCase);
        System.out.println(fourthSolution);
        System.out.println(solution(smallTestCase));
        int[][] bigTest = new int[10][10];
        System.out.println(solution(bigTest));

    }

    static int solution(int[][] map) {
        int minPossible = map.length + map[0].length - 1;
        ArrayDeque<Integer> distances = new ArrayDeque<Integer>();
        ArrayDeque<Coordinate> explored = new ArrayDeque<Coordinate>();
        ArrayDeque<Coordinate> queue = new ArrayDeque<Coordinate>();
        queue.add(new Coordinate(0, 0, 1, false));
        while (!queue.isEmpty()) {
            Coordinate current = queue.remove();
            // if we reached the end; check the distance
            if (current.x == map[0].length - 1 && current.y == map.length - 1) {
                // check if we found the minimum possible distance; it doesn't get better than
                // this
                if (current.distance == minPossible) {
                    return minPossible;
                }
                // add the result to distances and to explored
                distances.add(current.distance);
                // don't add anything to the queue.
                break;
            }
            // add adjacent cells
            addAdjacentToQueue(current, map, queue, explored);
            explored.add(current);
        }
        int min = (map.length) * (map[0].length); // the longest possible distance traverses every coordinate
        while (!distances.isEmpty()) {
            int test = distances.remove();
            if (test < min) {
                min = test;
            }
        }
        return min;
    }

    static void addAdjacentToQueue(Coordinate from, int[][] map, ArrayDeque<Coordinate> queue,
            ArrayDeque<Coordinate> explored) {
        if (from.x + 1 < map[0].length) {
            Coordinate attemptXRight = new Coordinate(from.x + 1, from.y, from.distance + 1, from.wallBroken);
            if (!explored.contains(attemptXRight)) {
                if (map[attemptXRight.y][attemptXRight.x] == 1) {
                    if (!attemptXRight.wallBroken) {
                        attemptXRight.wallBroken = true;
                        queue.add(attemptXRight);
                        explored.add(attemptXRight);
                    }
                } else {
                    queue.add(attemptXRight);
                    explored.add(attemptXRight);
                }
            }
        }
        if (from.x != 0) {
            Coordinate attemptXLeft = new Coordinate(from.x - 1, from.y, from.distance + 1, from.wallBroken);
            if (!explored.contains(attemptXLeft)) {
                if (map[attemptXLeft.y][attemptXLeft.x] == 1) {
                    if (!attemptXLeft.wallBroken) {
                        attemptXLeft.wallBroken = true;
                        queue.add(attemptXLeft);
                        explored.add(attemptXLeft);
                    }
                } else {
                    queue.add(attemptXLeft);
                    explored.add(attemptXLeft);
                }
            }
        }
        if (from.y != 0) {
            Coordinate attemptYUp = new Coordinate(from.x, from.y - 1, from.distance + 1, from.wallBroken);
            if (!explored.contains(attemptYUp)) {
                if (map[attemptYUp.y][attemptYUp.x] == 1) {
                    if (!attemptYUp.wallBroken) {
                        attemptYUp.wallBroken = true;
                        queue.add(attemptYUp);
                        explored.add(attemptYUp);
                    }
                } else {
                    queue.add(attemptYUp);
                    explored.add(attemptYUp);

                }
            }
        }
        if (from.y + 1 < map.length) {
            Coordinate attemptYDown = new Coordinate(from.x, from.y + 1, from.distance + 1, from.wallBroken);
            if (!explored.contains(attemptYDown)) {
                if (map[attemptYDown.y][attemptYDown.x] == 1) {
                    if (!attemptYDown.wallBroken) {
                        attemptYDown.wallBroken = true;
                        queue.add(attemptYDown);
                        explored.add(attemptYDown);

                    }
                } else {
                    queue.add(attemptYDown);
                    explored.add(attemptYDown);
                }

            }
        }
    }

    static class Coordinate extends Object {
        int x, y, distance;
        boolean wallBroken;

        public Coordinate(int x, int y, int distance, boolean wallBroken) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.wallBroken = wallBroken;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Coordinate)) {
                return false;
            }
            Coordinate castObject = (Coordinate) obj;
            return (castObject.x == this.x && castObject.y == this.y && castObject.distance <= this.distance
                    && this.wallBroken == castObject.wallBroken);
        }
    }
}
