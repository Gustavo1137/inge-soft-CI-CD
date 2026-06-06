import java.util.Random;

public class Bicicleta implements TransportStrategy{
    private final Random random = new Random();

    @Override
    public String getName(){
        return "Bicicleta";
    }

    public double getCost(){
        return 0;
    }
    public double getDistance(){
        return 1.0 + (10.0 - 1.0) * random.nextDouble();
    }
    public int getETA(){
        double km = getDistance();
        int vel = random.nextInt(10) + 5;
        return (int) Math.round((60.0 * km) / vel);  
    } 
}