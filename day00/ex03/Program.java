import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        int weekCounter = 0;
        int input;
        long grades = 0;

        while (weekCounter < 18) {
            str = sc.next();
            if (str.equals("42"))
                break;
            else if (!str.equals("Week") || sc.nextInt() - weekCounter != 1)
                exitError("IllegalArgument");
            ++weekCounter;
            int minimum = 9;
            for (int i = 0; i < 5; ++i) {
                input = sc.nextInt();
                if (input > 9 || input < 1)
                    exitError("IllegalArgument");
                minimum = input < minimum ? input : minimum;
            }
            grades = grades * 10 + minimum;
        }
        displayStatistics(grades, weekCounter);
    }

    private static  void exitError(String errMsg) {
        System.err.println(errMsg);
        System.exit(-1);
    }

    private static void displayStatistics(long grades, int weekCounter) {
        long multiplier = 1;

        while (--weekCounter > 0)
            multiplier *= 10;
        while (multiplier > 0) {
            System.out.print("Week ");
            System.out.print(++weekCounter);
            System.out.print(' ');
            int grade = (int)(grades / multiplier);
            grades %= multiplier;
            multiplier /= 10;
            while (grade-- > 0)
                System.out.print('=');
            System.out.println('>');
        }
    }


}
