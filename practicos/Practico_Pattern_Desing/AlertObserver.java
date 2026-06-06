public class AlertObserver implements TransportObserver {

    private double maxCost;
    private int maxETA;

    public AlertObserver(double maxCost, int maxETA) {
        this.maxCost = maxCost;
        this.maxETA = maxETA;
    }

    public void onUpdate(TransportSnapshot snapshot) {

        Logger logger = Logger.getInstance();

        if (snapshot.cost > maxCost) {
            logger.logWarning("Costo demasiado alto: " + snapshot.cost);
        }

        if (snapshot.eta > maxETA) {
            logger.logError("ETA demasiado alto: " + snapshot.eta);
        }
    }
}