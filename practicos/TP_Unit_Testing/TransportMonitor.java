import java.util.ArrayList;
import java.util.List;

public class TransportMonitor {
    private volatile TransportStrategy strategy;
    private List<TransportObserver> observers = new ArrayList<>();
    private volatile boolean running = false;

    public TransportMonitor(TransportStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(TransportStrategy strategy) {
        this.strategy = strategy;
    }

    public void subscribe(TransportObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(TransportObserver observer) {
        observers.remove(observer);
    }

    public void stop() {
        running = false;
    }

    public void start(int intervalMs) throws InterruptedException {
        running = true;
        while (running) {
            TransportStrategy s = strategy;
            TransportSnapshot snapshot = new TransportSnapshot(
                strategy.getName(),
                strategy.getCost(),
                strategy.getDistance(),
                strategy.getETA()
            );

            for (TransportObserver observer : observers) {
                observer.onUpdate(snapshot);
            }
            Thread.sleep(intervalMs);
        }
    }
}