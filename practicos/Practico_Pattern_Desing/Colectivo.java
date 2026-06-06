import java.util.Random;

public class Colectivo implements TransportStrategy {
    private final Random random = new Random();

    public String getName(){
        return "Colectivo";
    }

    public double getCost(){
        return 1700;
    }

    public double getDistance(){
        return 3.0 + 50.0 * random.nextDouble();
    }

    public int getETA(){
        double km = getDistance();
        int vel = random.nextInt(30) + 20;
        return (int) Math.round((60.0 * km) / vel);
    }
}