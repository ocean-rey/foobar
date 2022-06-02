import java.util.Scanner;


public class IDGenerator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter minion number");
        int index = Integer.parseInt(scanner.next());
        String id = solution(index);
        System.out.println(id);
        scanner.close();
    }

    public static String solution(int startIndex) {
        String primes = "";
        int previousPrime = 2;
        for (int i = 2; primes.length() < startIndex + 5; i++) {
            boolean prime = true;
            for (int j = previousPrime - 1; (j > 1); j--) {
                if (i % j == 0) {
                    prime = false;
                }
            }
            if (prime) {
                primes = primes.concat(Integer.toString(i));
                previousPrime = i;
            }
        }
        return primes.substring(startIndex, startIndex + 5);
    }

}
