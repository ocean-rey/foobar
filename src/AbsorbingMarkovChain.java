import java.util.Arrays;

public class AbsorbingMarkovChain {

    public static void main(String[] args) {
        int[][] firstTestCase = { { 0, 2, 1, 0, 0 }, { 0, 0, 0, 3, 4 }, { 0, 0, 0, 0,
                0 }, { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 } };
        int[][] secondTestCase = { { 0, 1, 0, 0, 0, 1 }, { 4, 0, 0, 3, 2, 0 }, { 0,
                0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };

        int[][] thirdTestCase = { { 0, 1, 1, 1 }, { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } };

        int[][] fourthTestCase = { { 0, 1 }, { 0, 0, }, };
        int[][] fifthTestCase = { { 0 } };
        int[][] sixthTestCase = { { 0, 3, 5, 2, 6, }, { 2, 2, 5, 4, 6, },
                { 0, 0, 0, 0, 0, }, { 0, 0, 0, 0, 0, }, { 0, 1, 2, 3, 4, }, };

        int[][] seventhTestCase = { { 0, 5, 3, 6 }, { 40, 0, 40, 60 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

        System.out.println(Arrays.toString(solution(firstTestCase)));
        System.out.println(Arrays.toString(solution(secondTestCase)));

        System.out.println(Arrays.toString(solution(thirdTestCase)));

        System.out.println(Arrays.toString(solution(fourthTestCase)));
        System.out.println(Arrays.toString(solution(fifthTestCase)));

        System.out.println(Arrays.toString(solution(sixthTestCase)));

        System.out.println(Arrays.deepToString(sixthTestCase));
        System.out.println(Arrays.deepToString(seventhTestCase));
        System.out.println(Arrays.toString(solution(seventhTestCase)));

        System.out.println(Arrays.deepToString(seventhTestCase));

    }

    public static int[] solution(int[][] m) {
        int[] denominators = new int[m.length];
        int terminalCount = 0;
        int[] chips = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            int gcd = gcdOfArray(m[i], 0);
            int denominator = 0;
            for (int j = 0; j < m[i].length; j++) {
                if (gcd != 0) {
                    m[i][j] = m[i][j] / gcd;
                }
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
        int[] engels = engelsAlgorithm(chips, m, denominators);
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

    static int[] engelsAlgorithm(int[] chips, int[][] matrix, int[] denominators) {
        boolean criticalLoading = false;
        while (!criticalLoading) {
            boolean typeOneMoveAvailable = false;
            for (int i = 0; i < chips.length; i++) {
                if (denominators[i] != 0) {
                    if (chips[i] >= denominators[i]) {
                        typeOneMoveAvailable = true;
                        for (int j = 0; j < matrix[i].length; j++) {
                            if (matrix[i][j] != 0) {
                                chips[i] = chips[i] - matrix[i][j];
                                chips[j] = chips[j] + matrix[i][j];
                            }
                        }
                    }
                }
            }
            if (!typeOneMoveAvailable)
                chips[0] = chips[0] + (denominators[0] - chips[0]);
            criticalLoading = true;
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
        }
        return chips;
    }

    static int __gcd(int a, int b) {
        return b == 0 ? a : __gcd(b, a % b);
    }

    static int gcdOfArray(int[] arr, int idx) {
        if (idx == arr.length - 1) {
            return arr[idx];
        }
        int a = arr[idx];
        int b = gcdOfArray(arr, idx + 1);
        return __gcd(
                a, b);
    }
}
