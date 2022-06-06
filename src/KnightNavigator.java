import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class KnightNavigator {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter source: ");
        int src = scanner.nextInt();
        System.out.println("Enter destination: ");
        int dest = scanner.nextInt();
        int minMoves = getMinMoves(src, dest);
        System.out.println(minMoves);
        scanner.close();

        // System.out.println(Arrays.toString(getLegalMoves(19)));
    }

    public static int getMinMoves(int src, int dest) {
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        ArrayDeque<Integer> parents = new ArrayDeque<Integer>();
        ArrayDeque<Integer> explored = new ArrayDeque<Integer>();
        if (src == dest) {
            return 0;
        }
        int min = 1;
        int currentParent = 1;
        q.add(src);
        parents.push(-1); // indicates root
        while (!q.isEmpty()) {
            int verify = q.remove();
            if (verify != dest) {
                explored.push(verify);
                int[] nextMoves = getLegalMoves(verify);
                for (int i = 0; i < nextMoves.length; i++) {
                    if (nextMoves[i] != -1 && !explored.contains(nextMoves[i])) {
                        q.add(nextMoves[i]);
                        parents.push(verify);
                    }
                }
            } else {
                for (int i = 0; i <= q.size(); i++) {
                    currentParent = parents.pop();
                }
                int backTrack = -1;
                int backTrackParent = -1;
                while (currentParent != src) {
                    while (backTrack != currentParent) {
                        backTrack = explored.pop();
                        backTrackParent = parents.pop();
                    }
                    currentParent = backTrackParent;
                    min++;
                }
                return min;
            }
        }
        return min;
    }

    static int[] getLegalMoves(int src) {
        int[] legalMoves = new int[8]; // there cannot be more than 8 legal moves at a time
        Arrays.fill(legalMoves, -1);
        int currentIndex = 0;
        for (int i = -2; i <= 2; i++) {
            if (i == 0) {
                continue;
            }
            try {
                int landedRow = rowMove(src, i);
                for (int j = -1; j <= 1; j++) {
                    int destination;
                    if (j == 0) {
                        continue;
                    }
                    try {
                        destination = colMove(landedRow, j * ((Math.abs(i) % 2) + 1));
                    } catch (Exception e) {
                        continue;
                    }
                    legalMoves[currentIndex] = destination;
                    currentIndex++;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return legalMoves;
    }

    static int rowMove(int src, int rowsToMove) throws Exception {
        int landsAt = src + rowsToMove * 8;
        if (landsAt > 63 || landsAt < 0) {
            throw new Exception("Invalid row move from " + src + " to " + rowsToMove + " rows");
        }
        return landsAt;
    }

    static int colMove(int src, int colsToMove) throws Exception {
        int landsAt = src + colsToMove;
        int currentRow = src / 8;
        int landedRow = landsAt / 8;
        if (currentRow != landedRow || landsAt > 63 || landsAt < 0) {
            throw new Exception("Invalid col move from " + src + " to " + colsToMove + " cols");
        }
        return landsAt;
    }
}
