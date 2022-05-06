import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    private static final String ARRAY_SIZE = "--arraySize=";
    private static final String THREADS_COUNT = "--threadsCount=";
    private static final int MAX_MODULE_VALUE = 1000;

    private static int arraySize;
    private static int threadsCount;
    private static List<Integer> array = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        validateInput(args);
        List<Thread> threads = new ArrayList<>();
        int chunkSize;
        int lastChunk;

        if (arraySize % threadsCount == 0) {
            chunkSize = arraySize / threadsCount;
            lastChunk = chunkSize;
        } else {
            chunkSize = arraySize / threadsCount + 1;
            if (chunkSize * threadsCount > arraySize)
                chunkSize = arraySize / threadsCount;
            lastChunk = arraySize - chunkSize * (threadsCount - 1);
        }

        generateArray();
        int sum = 0;
        for (Integer i : array)
            sum += i;
        System.out.println("sum: " + sum);

        for (int i = 0; i < threadsCount - 1; ++i) {
            threads.add(new Counter(array, i * chunkSize,
                    i * chunkSize + chunkSize - 1));
        }
        threads.add(new Counter(array, (threadsCount - 1) * chunkSize, (threadsCount - 1) * chunkSize + lastChunk - 1));

        for (Thread t : threads)
            t.start();
        for (Thread t : threads)
            t.join();
        System.out.println("Sum by threads: " + Counter.getSum());
    }

    private static void validateInput(String[] args) {
        if (args.length != 2 || !args[0].startsWith(ARRAY_SIZE)
            || !args[1].startsWith(THREADS_COUNT)) {
                System.out.println("Usage: Program --arraySize=YOUR_NUMBER --threadsCount=YOUR_NUMBER");
                System.exit(1);
        }
        try {
            arraySize = Integer.parseInt(args[0].substring(ARRAY_SIZE.length()));
            threadsCount = Integer.parseInt(args[1].substring(THREADS_COUNT.length()));
        } catch (NumberFormatException exc) {
            System.out.println("Invalid argument");
            System.err.println(exc.getMessage());
            System.exit(1);
        }
        if (threadsCount > arraySize) {
            System.out.println("threadsCount can't be greater than arraySize");
            System.exit(1);
        }
        if (threadsCount < 1 || arraySize < 1) {
            System.out.println("Number must be positive");
            System.exit(1);
        }
    }

    private static void generateArray() {
        Random random = new Random();
        for (int i = 0; i < arraySize; ++i)
            array.add(i, random.nextInt(MAX_MODULE_VALUE * 2 + 1) - MAX_MODULE_VALUE);
    }
}
