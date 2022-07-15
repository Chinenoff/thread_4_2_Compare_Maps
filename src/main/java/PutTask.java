import java.util.Map;

public class PutTask implements Runnable {

    private final int[] initialArray;
    private Map<Integer, Integer> testMap;
    private int currentElement;

    public PutTask(int[] initialArray, Map<Integer, Integer> testMap, int currentElement) {
        this.initialArray = initialArray;
        this.testMap = testMap;
        this.currentElement = currentElement;
    }

    @Override
    public void run() {
        testMap.put(currentElement, initialArray[currentElement]);
    }
}