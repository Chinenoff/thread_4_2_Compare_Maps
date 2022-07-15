import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetElementsThread extends Thread {
    private final static int NUMBER_WORK_THREAD = 4;
    private final int[] initialArray;
    private static Map<Integer, Integer> testMap;

    public GetElementsThread(int[] initialArray, Map<Integer, Integer> testMap) {
        this.initialArray = initialArray;
        this.testMap = testMap;

    }

    public void run() {
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_WORK_THREAD);
        for (int i = 0; i < testMap.size(); i++) {
            Runnable runTask = new GetTask(testMap, i);
            executor.execute(runTask);
        }
        executor.shutdown();
        System.out.printf("ПРОИЗВЕДЕНО ЧТЕНИЕ ИЗ %s\n", getName());
        long time = System.nanoTime() - startTime;
        System.out.println("Поток " + getName() + " работал: " + time + " ns");
    }
}
