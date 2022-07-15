import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PutElementsThread extends Thread {
    private final static int NUMBER_WORK_THREAD = 4;
    private final int[] initialArray;
    private static Map<Integer, Integer> testMap;

    public PutElementsThread(int[] initialArray, Map<Integer, Integer> testMap) {
        this.initialArray = initialArray;
        this.testMap = testMap;
    }

    public void run() {
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_WORK_THREAD);
        for (int i = 0; i < initialArray.length; i++) {
            Runnable runTask = new PutTask(initialArray, testMap, i);
            executor.execute(runTask);
        }
        executor.shutdown();
        System.out.printf("ПРОИЗВЕДЕНА ЗАПИСЬ В %s\n", getName());
        long time = System.nanoTime() - startTime;
        System.out.println("Поток " + getName() + " работал: " + time + " ns");
    }
}