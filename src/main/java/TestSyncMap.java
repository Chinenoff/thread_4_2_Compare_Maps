import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class TestSyncMap {
    private static final int ARRAY_SIZE = 10000000;
    private static final int NUMBER_RUNDOM_GENERATION = 1000;
    private static final Random random = new Random();
    private static int[] initialArrayInt;
    private static Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
    private static Map<Integer, Integer>  map = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> synmap = Collections.synchronizedMap(map);

    public static void main(String[] args) {

         int[] initialArrayInt = arrayGenerator();

         //тест записи в concurrentHashMap
        PutElementsThread putElementsConcurrentHashMap = new PutElementsThread(initialArrayInt,
                concurrentHashMap );
        putElementsConcurrentHashMap.setName("write ConcurrentHashMap ");
        putElementsConcurrentHashMap.start();
        try {
            putElementsConcurrentHashMap.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //тест записи в SynchronizedMap
        PutElementsThread putElementsSynchronizedMap = new PutElementsThread(initialArrayInt,
                synmap );
        putElementsSynchronizedMap.setName("write SynchronizedMap ");
        putElementsSynchronizedMap.start();
        try {
            putElementsSynchronizedMap.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //тест чтения в concurrentHashMap
        GetElementsThread getElementsConcurrentHashMap = new GetElementsThread(initialArrayInt,
                concurrentHashMap );
        getElementsConcurrentHashMap.setName("Read ConcurrentHashMap ");
        getElementsConcurrentHashMap.start();
        try {
            getElementsConcurrentHashMap.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //тест чтения в SynchronizedMap
        GetElementsThread getElementsSynchronizedMap = new GetElementsThread(initialArrayInt,
                synmap );
        getElementsSynchronizedMap.setName("Read SynchronizedMap ");
        getElementsSynchronizedMap.start();
        try {
            getElementsSynchronizedMap.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int[] arrayGenerator(){
        int[] initialArrayInt = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            initialArrayInt[i] = random.nextInt(NUMBER_RUNDOM_GENERATION);
        }
        return initialArrayInt;
    }
}
