import java.lang.System.Logger;
import java.util.*;

public class FakeLogger implements ILogger {
    
    private List<String> mensajes = new ArrayList<>();

    public void logInfo(String mensaje) {
        mensajes.add("[INFO] " + mensaje);
    }

    public void logWarning(String mensaje) {
        mensajes.add("[WARN] " + mensaje);
    }

    public void logError(String mensaje) {
        mensajes.add("[ERROR] " + mensaje);
    }

    public void logDebug(String mensaje){
        mensajes.add("[DEBUG] " + mensaje);
    }

    public List<String> getMensajes() {
        return mensajes;
    }

}