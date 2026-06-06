public class TransportSnapshot {
    public final String name;
    public final double cost;
    public final double distance;
    public final int eta;

    public TransportSnapshot(String name, double cost, double distance, int eta) {
        this.name = name;
        this.cost = cost;
        this.distance = distance;
        this.eta = eta;
    }
}