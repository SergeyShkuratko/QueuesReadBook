import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Consumer> consumerList = new ArrayList<>();
        consumerList.add(new Consumer());
        consumerList.add(new Consumer());
        consumerList.add(new Consumer());
        consumerList.add(new Consumer());
        consumerList.add(new Consumer());
        Producer producer = new Producer("book1UTF8.txt", consumerList);
        for (Consumer consumer : consumerList) {
            new Thread(consumer).start();
        }
        new Thread(producer).start();
    }
}
