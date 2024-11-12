import java.util.Random;

public class Paralysis extends StatusEffect {
    public Paralysis() {
        super("electric", "Paralysis", Integer.MAX_VALUE, "miss attack");
    }

    @Override
    public boolean hasEffectWhenApplied(){
        Random r1 = new Random();
        return r1.nextInt(2) == 0;
    }

}
