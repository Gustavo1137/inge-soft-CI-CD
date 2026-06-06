import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

public class AlertObserverMockitoTest {

    private AlertService mockAlertService;
    private ILogger mockLogger;
    private AlertObserver observer;
    private TransportSnapshot test;

    
    @BeforeEach
    public void setUp() {
        
        mockAlertService = mock(AlertService.class);
        mockLogger = mock(ILogger.class);

        //Logger.setTestInstance(mockLogger);
        
        observer = new AlertObserver(mockAlertService, mockLogger);

        // Creamos un snapshot genérico para usar en todos los tests
        test = new TransportSnapshot("Test", 3000.0, 10.0, 45);
    }

    @Test
    public void testOnUpdate_CuandoCostoEsAlto_LlamaLogWarning() {
        
        when(mockAlertService.shouldAlertCost(anyDouble())).thenReturn(true);
        when(mockAlertService.shouldAlertETA(anyInt())).thenReturn(false);

        observer.onUpdate(test);

        verify(mockLogger, times(1)).logWarning(anyString());
        
        
        verify(mockLogger, never()).logError(anyString());
    }

    @Test
    public void testCuandoEtaEsAlto() {
        
        when(mockAlertService.shouldAlertCost(anyDouble())).thenReturn(false);
        when(mockAlertService.shouldAlertETA(anyInt())).thenReturn(true);

        
        observer.onUpdate(test);
        
        verify(mockLogger, times(1)).logError(anyString());
        verify(mockLogger, never()).logWarning(anyString());
    }

    @Test
    public void testCuandoTodoEsNormal() {
        when(mockAlertService.shouldAlertCost(anyDouble())).thenReturn(false);
        when(mockAlertService.shouldAlertETA(anyInt())).thenReturn(false);

        observer.onUpdate(test);

        verifyNoInteractions(mockLogger);
    }
}