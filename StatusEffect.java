import java.util.Random;

public class StatusEffect {
    public String element;
    public String name;
    public int roundsLeft;

    /**
     * DOT                                  (burn, poison)
     * DOT/heal                             (siphon)
     * self attack                          (confusion)
     * lower dmg                            (intimidate)
     * miss attack                          (paralysis)
     * miss turn                            (freeze, sleep)
     */
    public String effect;


    // -intimidate = lowers base damage
    // -confusion = 1/2 chance of hurting itself, doesn't attack you after
    // -sleep/freeze = miss turn but can take attacks
    // -paralysis = 1/2 chance of attack failing (only goes away from healing pot)
    // -burn/poison = DOT 5%
    // -siphon = DOT 3% and life steal 2%

    //maybe add?
    //Fly - flying, Fly   flies in the air, attacks from the air, opponent's attack misses
    //Dig - rock, Dig - digs underground, attacks from there, opponent's attack misses


    public StatusEffect(){}

    public StatusEffect(String element, String name, int turnsLeft, String effect){
        this.element = element;
        this.name = name;
        this.roundsLeft = turnsLeft;
        this.effect = effect;
    }

    /**
     * between 2-4 rounds
     * set # rounds status effect active for
     */
    public void randomizeRounds(){
        Random r = new Random();
        this.roundsLeft = r.nextInt(3) + 2;
    }

    /**
     * @return whether status effect has effect this turn (base 100% chance)
     * chance AFFECTS THIS TURN when applied
     *     burn            1
     *     freeze          1
     *     intimidate      1
     *     poison          1
     *     siphon          1
     *     sleep           1
     *     paralysis       1/2
     *     confusion       1/2
     */
    public boolean hasEffectWhenApplied(){
        return true;
    }



    //chance APPLIED:
    //               COUNTERS(elemental attack)     COUNTERED(base)     BASE(base, against same)
    //confusion      1/2                            1/5                 1/4
    //intimidate     1/2                            1/5                 1/4
    //paralysis      1/2                            1/5                 1/4
    // -------------------------------------------------------------------------------------------
    //poison         3/4                            1/5                 1/4
    //burn           3/4                            1/5                 1/4
    //siphon         3/4                            1/5                 1/4
    // -------------------------------------------------------------------------------------------
    //freeze/sleep   1/2                            1/5                 1/4

    /**
     * chance SE applied when pokemon counters enemy (1/2)
     * @return whether SE applied
     */
    public boolean isAppliedWhenCOUNTERS(){
        Random r = new Random();
        return r.nextInt(2) == 0;
    }

    /**
     * chance SE applied when pokemon is countered (1/5)
     * @return whether SE applied
     */
    public boolean isAppliedWhenCOUNTERED(){
        Random r = new Random();
        return r.nextInt(5) == 0;
    }

    /**
     * base chance SE is applied (1/4)
     * @return whether SE applied
     */
    public boolean appliedBASE(){
        Random r = new Random();
        return r.nextInt(4) == 0;
    }


    public boolean isPoison(){
        return this instanceof Poison;
    }

    public boolean isBurn(){
        return this instanceof Burn;
    }

    public boolean isConfusion(){
        return this instanceof Confusion;
    }

    public boolean isFreeze(){
        return this instanceof Freeze;
    }

    public boolean isIntimidate(){
        return this instanceof Intimidate;
    }

    public boolean isParalysis(){
        return this instanceof Paralysis;
    }

    public boolean isSiphon(){
        return this instanceof Siphon;
    }

    public boolean isSleep(){
        return this instanceof Sleep;
    }


}
