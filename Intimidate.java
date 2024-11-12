public class Intimidate extends StatusEffect {
    public static double LOWER_DMG = 0.5;
    public Intimidate() {
        super("fighting", "Intimidate", 0, "less damage");
        this.randomizeRounds();
    }
}
