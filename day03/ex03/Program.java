import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final String THREADS_COUNT = "--threadsCount=";
    private static final String URLS = "files_urls.txt";
    private static int threadsCount;

    public static void main(String[] args) {
        List<Pair<Integer, String>> urls = new ArrayList<>();

        validateInput(args);
        try (BufferedReader reader = new BufferedReader(new FileReader(URLS))) {
            while (reader.ready()) {
                String buf[] = reader.readLine().split("\\s+");
                urls.add(new Pair<>(Integer.valueOf(buf[0]), buf[1].trim()));
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        Downloader downloader = new Downloader(urls);
        for (int i = 0; i < threadsCount; ++i)
            new Thread(downloader).start();
    }

    private static void validateInput(String[] args) {
        if (args.length != 1 || !args[0].startsWith(THREADS_COUNT)) {
            System.out.println("Usage: Program --threadsCount=YOUR_NUMBER");
            System.exit(1);
        }
        try {
            threadsCount = Integer.parseInt(args[0].substring(THREADS_COUNT.length()));
        } catch (NumberFormatException exc) {
            System.out.println("Invalid argument");
            System.err.println(exc.getMessage());
            System.exit(1);
        }
        if (threadsCount < 1) {
            System.out.println("threadsCount must be a positive number");
            System.exit(1);
        }
    }
}
