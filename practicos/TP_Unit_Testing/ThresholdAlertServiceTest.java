import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ThresholdAlertServiceTest{

    @Test
    public void testCost_DebajoDelUmbral_RetornaFalse(){
        ThresholdAlertService test = new ThresholdAlertService(2000.0, 30);

        boolean resultado = test.shouldAlertCost(1500.0);
        if (!resultado) {System.out.println("falso"); };
        assertFalse(resultado);
    }

    @Test
    public void testCost_ExactoEnElUmbral_RetornaFalse(){
        ThresholdAlertService test = new ThresholdAlertService(2000.0, 30);

        boolean resultado = test.shouldAlertCost(2000.0);
        if (!resultado) {System.out.println("falso"); };
        assertFalse(resultado);
    }

    @Test
    public void testCost_PorEncimaDelUmbral_RetornaTrue(){
        ThresholdAlertService test = new ThresholdAlertService(2000.0, 30);

        boolean resultado = test.shouldAlertCost(2001.5);
        if (resultado) {System.out.println("verdadero"); };
        assertTrue(resultado);
    }

    @Test
    public void testETA_PorDebajoDelUmbral_RetornaFalse(){
        ThresholdAlertService test = new ThresholdAlertService(2000.0, 30);

        boolean resultado = test.shouldAlertETA(20);
        if (!resultado) {System.out.println("falso"); };
        assertFalse(resultado);
    }

    @Test
    public void testETA_PorEncimaDelUmbral_RetornalTrue(){
        ThresholdAlertService test = new ThresholdAlertService(2000.0, 30);

        boolean resultado = test.shouldAlertETA(45);
        if (resultado) {System.out.println("verdadero"); };
        assertTrue(resultado);
    }
}