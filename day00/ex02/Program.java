import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int nbr;
        int sum;
        int count = 0;

        Scanner sc = new Scanner(System.in);
        do {
            nbr = sc.nextInt();
            sum = sumOfDigits(nbr);
            count += isPrimeNumber(sum) ? 1 : 0;
        } while (nbr != 42);
        System.out.println("Count of coffee-requests - " + count);
    }

    private static boolean isPrimeNumber(int number) {
        if (number < 2)
                return false;
        else if (number == 2)
            return true;
        for (int i = 2; i * i <= number; ++i) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    private static int sumOfDigits(int nbr) {
        int res = 0;

        while (nbr > 0) {
            res += nbr % 10;
            nbr /= 10;
        }
        return res;
    }
}
