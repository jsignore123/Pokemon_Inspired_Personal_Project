import java.util.Random;

public class Poison extends StatusEffect {

    public static double DOT = 0.05;

    public Poison() {
        super("poison", "Poison", 0, "DOT");
        this.randomizeRounds();
    }

    @Override
    public boolean appliedCOUNTERS() {
        Random r = new Random();
        return r.nextInt(4) != 0;
    }
}
