import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Producer implements Runnable {
    private BufferedReader bufferedReader;
    private List<Consumer> consumers;

    public Producer(String fileName, List<Consumer> consumers) {
        this.consumers = consumers;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String row;
        int currentConsumerIndex = 0;
        try {
            while ((row = bufferedReader.readLine()) != null) {
                Consumer consumer = consumers.get(currentConsumerIndex);
                consumer.addString(row);
                currentConsumerIndex++;
                if (currentConsumerIndex == consumers.size()) {
                    currentConsumerIndex = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Consumer consumer : consumers) {
            consumer.fileFinished();
        }
    }
}
