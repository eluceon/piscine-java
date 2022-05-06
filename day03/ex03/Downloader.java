import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Downloader implements Runnable {
    private List<Pair<Integer, String>> urls;
    private volatile int idx = 0;

    public Downloader(List<Pair<Integer, String>> urls) {
        this.urls = urls;
    }

    @Override
    public void run() {
        try {
            download();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void download() throws IOException {
        int localStackIdx;
        InputStream inputStream = null;

        while (idx < urls.size()) {
            synchronized (this) {
                if (idx >= urls.size())
                    break;
                localStackIdx = idx;
                System.out.printf("%s start download file number %d\n",
                        Thread.currentThread().getName(), urls.get(localStackIdx).getFirst());
                ++idx;
            }
            inputStream = new URL(urls.get(localStackIdx).getSecond()).openStream();
            Path tempFile = Files.createTempFile("temp-",".tmp");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            String[] urlTokens = urls.get(localStackIdx).getSecond().split("/");
            Path downloadPath = Paths.get(urlTokens[urlTokens.length - 1]);
            Files.move(tempFile, downloadPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.printf("%s finish download file number %d\n",
                    Thread.currentThread().getName(), urls.get(localStackIdx).getFirst());
        }
        if (inputStream != null)
            inputStream.close();
    }

}
