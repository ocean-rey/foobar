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
    }

    public static int getMinMoves(int src, int dest) {
        int min = 0;
        int[] targets = getLegalMoves(src);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (targets[j] == dest) {
                    min = i;
                }
            }
        }
        return min;
    }

    static int[] getLegalMoves(int src) {
        int[] legalMoves = new int[8]; // there cannot be more than 8 legal moves at a time
        int currentIndex = 0;
        for (int i = -2; i <= 2; i++) {
            if (i == 0) {
                continue;
            }
            System.out.println(i);
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
                        // System.out.println(e);
                        continue;
                    }
                    legalMoves[currentIndex] = destination;
                    currentIndex++;
                }
            } catch (Exception e) {
                // System.out.println(e);
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
        if (currentRow != landedRow) {
            throw new Exception("Invalid col move from " + src + " to " + colsToMove + " cols");
        }
        return landsAt;
    }
}
