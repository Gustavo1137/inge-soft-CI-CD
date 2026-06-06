import java.util.Random;

public class Taxi implements TransportStrategy {
    private final Random random = new Random();

    public String getName(){
        return "Taxi";
    }
    public double getCost(){
        double km = getDistance();
        return km * 1000 + 1900;
    }
    public double getDistance(){
        return 1.0 + (10.0 - 1.0) * random.nextDouble();
    }
    public int getETA(){
        double km = getDistance();
        int vel = random.nextInt(20) + 40; 
        return (int) Math.round((60.0 * km) / vel);
    }
}