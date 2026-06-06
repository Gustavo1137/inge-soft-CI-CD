public interface ILogger {
    void logInfo(String mensaje);
    void logWarning(String mensaje);
    void logError(String mensaje);
    void logDebug(String mensaje);
}
