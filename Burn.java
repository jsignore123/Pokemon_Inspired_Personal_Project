import java.util.Random;

public class Burn extends StatusEffect {
    public static double DOT = 0.05;

    public Burn() {
        super("fire", "Burn", 0, "DOT");
        this.randomizeRounds();
    }

    @Override
    public boolean isAppliedWhenCOUNTERS() {
        Random r = new Random();
        return r.nextInt(4) != 0;
    }
}
