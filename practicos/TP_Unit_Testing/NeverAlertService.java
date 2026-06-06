public class NeverAlertService implements AlertService{
    
    public boolean shouldAlertCost(double cost) {
        return false;
    }

    public boolean shouldAlertETA(int eta) {
        return false;
    }
}