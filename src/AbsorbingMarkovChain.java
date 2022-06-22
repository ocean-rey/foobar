import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;

public class AbsorbingMarkovChain {
    public static void main(String[] args) {
        int[][] firstTestCase = { { 0, 2, 1, 0, 0 }, { 0, 0, 0, 3, 4 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 } };
        int[][] secondTestCase = { { 0, 1, 0, 0, 0, 1 }, { 4, 0, 0, 3, 2, 0 }, { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
        int[][] thirdTestCase = { { 0, 1, 1, 1 }, { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } };
        System.out.println(
                Arrays.toString(solution(firstTestCase)));
        System.out.println(
                Arrays.toString(solution(secondTestCase)));
        System.out.println(Arrays.toString(solution(thirdTestCase)));
    }

    public static int[] solution(int[][] m) {
        int[] denominators = new int[m.length];
        int transientCount = 0;
        int terminalCount = 0;
        int[] chips = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            int denominator = 0;
            for (int j = 0; j < m[i].length; j++) {
                denominator = denominator + m[i][j];
            }
            denominators[i] = denominator;
            if (denominator != 0) {
                transientCount++;
            } else {
                terminalCount++;
            }
            if (i == 0) {
                chips[i] = denominator;
            } else if (denominator != 0) {
                chips[i] = denominator - 1;
            }
        }
        int[] engels = engelsAlgorithm(chips, m, denominators, true);
        int denominator = 0;
        int[] answer = new int[terminalCount + 1];
        int answersIndex = 0;
        for (int i = 0; i < engels.length; i++) {
            if (denominators[i] == 0) {
                answer[answersIndex] = engels[i];
                answersIndex++;
                denominator = denominator + engels[i];
            }
        }
        answer[answer.length - 1] = denominator;
        return answer;
    }

    static int[] engelsAlgorithm(int[] chips, int[][] matrix, int[] denominators, boolean isFirst) {
        if (!isFirst) {
            boolean criticalLoading = true;
            for (int i = 0; i < chips.length; i++) {
                if (denominators[i] != 0) {
                    if (i == 0) {
                        if (chips[i] != denominators[i]) {
                            criticalLoading = false;
                            break;
                        }
                    } else {
                        if (chips[i] != (denominators[i] - 1)) {
                            criticalLoading = false;
                            break;
                        }
                    }
                }
            }
            if (criticalLoading) {
                return chips;
            }
        }
        isFirst = false;
        boolean typeTwoMoveAvailable = false;
        for (int i = 0; i < chips.length; i++) {
            if (denominators[i] != 0) {
                if (chips[i] >= denominators[i]) {
                    typeTwoMoveAvailable = true;
                    for (int j = 0; j < matrix[i].length; j++) {
                        chips[i] = chips[i] - matrix[i][j];
                        chips[j] = chips[j] + matrix[i][j];
                    }

                }
            }
        }
        if (!typeTwoMoveAvailable) {
            chips[0] = chips[0] + (denominators[0] - chips[0]);
        }
        return (engelsAlgorithm(chips, matrix, denominators, isFirst));
    }
}
