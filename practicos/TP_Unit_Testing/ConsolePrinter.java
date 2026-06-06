public class ConsolePrinter implements TransportObserver {
    @Override
    public void onUpdate(TransportSnapshot snapshot) {
        Logger log = Logger.getInstance();
        log.logInfo("Transporte: " + snapshot.name);
        log.logDebug("Costo:      " + String.format("%.2f", snapshot.cost));
        log.logDebug("Distancia:  " + String.format("%.2f", snapshot.distance) + " km");
        log.logDebug("ETA:        " + snapshot.eta + " min");
    }
}