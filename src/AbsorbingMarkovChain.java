import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbsorbingMarkovChain {
    public static void main(String[] args) {

        int[][] firstTestCase = { { 0, 2, 1, 0, 0 }, { 0, 0, 0, 3, 4 }, { 0, 0, 0, 0,
                0 }, { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 } };
        int[][] secondTestCase = { { 0, 1, 0, 0, 0, 1 }, { 4, 0, 0, 3, 2, 0 }, { 0,
                0, 0, 0, 0, 0 },
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
        int terminalCount = 0;
        int[] chips = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            int denominator = 0;
            for (int j = 0; j < m[i].length; j++) {
                denominator = denominator + m[i][j];
            }
            denominators[i] = denominator;
            if (denominator == 0) {
                terminalCount++;
            }
            if (i == 0) {
                chips[i] = denominator;
            } else if (denominator != 0) {
                chips[i] = denominator - 1;
            }
        }

        int[] answer = new int[terminalCount + 1];
        if (denominators[0] == 0) {
            // intial state is terminal, engels algorithm does not apply
            answer[0] = 1;
            answer[answer.length - 1] = 1;
            return answer;
        }

        Map<Integer, List<ChipMove>> memo = new HashMap<Integer, List<ChipMove>>();
        int[] engels = engelsAlgorithm(chips, m, denominators, true, memo);
        int answersIndex = 0;
        int denominator = 0;
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

    static int[] engelsAlgorithm(int[] chips, int[][] matrix, int[] denominators, boolean isFirst,
            Map<Integer, List<ChipMove>> memo) {
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
        boolean typeOneMoveAvailable = false;
        for (int i = 0; i < chips.length; i++) {
            if (denominators[i] != 0) {
                if (chips[i] >= denominators[i]) {
                    typeOneMoveAvailable = true;
                    List<ChipMove> chipMoves;
                    if (memo.containsKey(i)) {
                        chipMoves = memo.get(i);
                    } else {
                        chipMoves = new ArrayList<>();
                        for (int j = 0; j < matrix[i].length; j++) {
                            if (matrix[i][j] != 0) {
                                chipMoves.add(new ChipMove(i, j, matrix[i][j]));
                            }
                        }
                        memo.put(i, chipMoves);
                    }
                    chipMoves.forEach(move -> moveChips(chips, move));
                }
            }

        }
        if (!typeOneMoveAvailable)
            chips[0] = chips[0] + (denominators[0] - chips[0]);
        return (engelsAlgorithm(chips, matrix, denominators, isFirst, memo));
    }

    public static void moveChips(int[] chips, ChipMove move) {
        chips[move.from] = chips[move.from] - move.count;
        chips[move.to] = chips[move.to] + move.count;
        return;
    }

    static class ChipMove {
        int from, to, count;

        public ChipMove(int from, int to, int count) {
            this.count = count;
            this.to = to;
            this.from = from;
        }
    }
}
