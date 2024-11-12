import java.util.Random;

public class Confusion extends StatusEffect {
    public static double SELF_ATTACK = 0.08;
    public Confusion() {
        super("psychic", "Confusion", 0, "self inflict");
        this.randomizeRounds();
    }

    @Override
    public boolean hasEffectWhenApplied(){
        Random r1 = new Random();
        return r1.nextInt(2) == 0;
    }
}
