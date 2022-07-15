import java.util.Map;

public class GetTask implements Runnable {

    private Map<Integer, Integer> testMap;
    private int currentElement;

    public GetTask(Map<Integer, Integer> testMap, int currentElement) {
        this.testMap = testMap;
        this.currentElement = currentElement;
    }

    @Override
    public void run() {
        testMap.get(currentElement);
    }
}