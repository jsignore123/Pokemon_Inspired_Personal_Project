import java.util.Random;

public class Siphon extends StatusEffect {

    public static double DOT = 0.1;

    public static double HEAL = 0.075;
    public Siphon() {
        super("grass", "Siphon", 0, "DOT heal");
        this.randomizeRounds();
    }

    @Override
    public boolean isAppliedWhenCOUNTERS(){
        Random r = new Random();
        return r.nextInt(4) != 0;
    }
}
