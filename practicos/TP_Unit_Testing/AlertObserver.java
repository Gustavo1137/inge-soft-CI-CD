public class AlertObserver implements TransportObserver {

    private AlertService alertService;
    private ILogger logger;

    public AlertObserver(AlertService alertService, ILogger logger) {
        this.alertService = alertService;
        this.logger = logger;
    }

    public void onUpdate(TransportSnapshot snapshot) {

        if (alertService.shouldAlertCost(snapshot.cost)) {
            logger.logWarning("Costo demasiado alto: " + snapshot.cost);
        }

        if (alertService.shouldAlertETA(snapshot.eta)) {
            logger.logError("ETA demasiado alto: " + snapshot.eta);
        }
    }
}