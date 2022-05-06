import java.util.List;

public class Counter extends Thread {
    private static final Object lock = new Object();
    private List<Integer> array;
    private int begin, end;
    private static volatile int sum;

    public Counter(List<Integer> array, int begin, int end) {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }

    public static int getSum() {
        return sum;
    }

    @Override
    public  void run() {
        int result = 0;
        for (int i = begin; i <= end; ++i) {
            result += array.get(i);
        }
        synchronized(lock) {
            sum += result;
            System.out.printf("%s: from %d to %d sum is %d\n",
                    Thread.currentThread().getName(), begin, end, result);
        }
    }
}
