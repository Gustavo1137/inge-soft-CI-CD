import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AlertObserverTest {
    
    private FakeLogger fakeLogger;
    private TransportSnapshot snapshot;

    public void iniciar(){
        fakeLogger = new FakeLogger();
        snapshot = new TransportSnapshot("TestTransport", 100.0, 10.0, 30);
        //Logger.setTestInstance(fakeLogger);
    }

    @Test
    public void testAlertObserver() {
        // Arrange
        AlertService alertService = new AlwaysAlertService();
        FakeLogger fakeLogger = new FakeLogger();
        AlertObserver alertObserver = new AlertObserver(alertService, fakeLogger);
        TransportSnapshot snapshot = new TransportSnapshot("TestTransport", 100.0, 10.0, 30);

        // Act
        alertObserver.onUpdate(snapshot);

        // Assert
        assertEquals(2, fakeLogger.getMensajes().size(), "Se deberían generar alertas");
        assertTrue(fakeLogger.getMensajes().get(0).contains("[WARN]"), "Primer log, Warning de costo");
        assertTrue(fakeLogger.getMensajes().get(1).contains("[ERROR]"), "Segundo log, Error de ETA" );
    }

    @Test
    public void testAlertObserverNoLogg(){
        AlertService alertService = new NeverAlertService();
        FakeLogger fakeLogger = new FakeLogger(); 
        AlertObserver alertObserver = new AlertObserver(alertService, fakeLogger);
        TransportSnapshot snapshot = new TransportSnapshot("TestTransport", 100.0, 10.0, 30);

        alertObserver.onUpdate(snapshot);

        assertTrue(fakeLogger.getMensajes().isEmpty(), "No se deberían generar alertas");
    }
}
