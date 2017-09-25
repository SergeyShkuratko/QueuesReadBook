import java.util.concurrent.ConcurrentLinkedQueue;

public class Consumer implements Runnable {
    private static final String WORD = "страдание";
    private static volatile boolean fileFinished = false;

    private static volatile int allCount = 0;
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public void fileFinished() {
        fileFinished = true;
    }

    public void addString(String string) {
        queue.add(string);
    }

    @Override
    public void run() {
        while (true) {
            String string = queue.poll();
            if (string != null) {
                int lastIndex = 0;
                int count = 0;

                while (lastIndex != -1) {
                    lastIndex = string.indexOf(WORD, lastIndex);
                    if (lastIndex != -1) {
                        count++;
                        lastIndex += WORD.length();
                    }
                }
                allCount = allCount + count;
                System.out.println(allCount);
            }
        }
    }
}