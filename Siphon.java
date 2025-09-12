import java.util.Random;

public class Siphon extends StatusEffect {

    public static double DOT = 0.03;

    public static double HEAL = 0.02;
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
