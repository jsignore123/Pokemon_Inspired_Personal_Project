import java.util.Random;

public class Pokemon {
    //water > fire
    //fire > grass
    //grass > water

    //fighting > ice                 (water)
    //electric > water               (fire)
    //fairy > fighting               (water)
    //ice > grass                    (water)
    //psychic > fighting             (grass)
    //poison > grass & fairy         (fire)


    public String element;
    public int level;
    public int currentXP;
    public int effectiveBaseDamage;
    public int baseDamage;
    public int currentHealth;
    public int totalHealth;
    public int healthPots;
    public StatusEffect specialAttack = new StatusEffect();
    public StatusEffect status = new StatusEffect();



    public String randomElement(){
        Random r = new Random();
        int el = r.nextInt(3);
        if(el == 0){
            return "fire";
        }
        if(el == 1){
            return "grass";
        }
        return "water";
    }

    /**
     * @param element of opponent
     * @return whether this poke counters opponent
     */
    public boolean counters(String element){
        if(this.element.equals("fighting")){
            return element.equals("ice");
        }
        if(this.element.equals("fire")){
            return element.equals("grass") || element.equals("ice");
        }
        if(this.element.equals("fairy") || this.element.equals("psychic")){
            return element.equals("fighting");
        }
        if(this.element.equals("ice")){
            return element.equals("grass");
        }
        if(this.element.equals("poison")){
            return element.equals("grass") || element.equals("fairy");
        }
        if(this.element.equals("water")){
            return element.equals("fire");
        }
        if(this.element.equals("grass") || this.element.equals("electric")){
            return element.equals("water");
        }

        return false;
    }

    public boolean isLowHealth(){
        return this.currentHealth < (int) (this.totalHealth * 0.20);
    }

    public boolean isHalfHealth(){
        return this.currentHealth < (int) (this.totalHealth * 0.5);
    }

    public boolean hasHealthPots(){
        return this.healthPots >= 1;
    }

    /**
     * random pokemon appears
     * @param level current level of game
     */
    public Pokemon(int level){
        this.level = level;
        this.element = this.randomElement();
        this.currentXP = 0;

        if(this.element.equals("fighting")){
            this.specialAttack = new Intimidate();
        }
        if(this.element.equals("fire")){
            this.specialAttack = new Burn();
        }
        if(this.element.equals("fairy")){
            this.specialAttack = new Sleep();
        }
        if(this.element.equals("ice")){
            this.specialAttack = new Freeze();
        }
        if(this.element.equals("poison")){
            this.specialAttack = new Poison();
        }
//      if(this.element.equals("water")){
//        this.specialAttack = nothing lol
//      }
        if(this.element.equals("grass")){
            this.specialAttack = new Siphon();
        }
        if(this.element.equals("electric")){
            this.specialAttack = new Paralysis();
        }
        if(this.element.equals("psychic")){
            this.specialAttack = new Confusion();
        }

        //high DPS, low health
        if(this.element.equals("fire") || this.element.equals("poison") || this.element.equals("electric")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(6) + 3) * this.level;
            this.baseDamage = (r1.nextInt(6) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(4) + 10) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }
        //low DPS, high health
        if(this.element.equals("grass") || this.element.equals("psychic")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(2) + 3) * this.level;
            this.baseDamage = (r1.nextInt(2) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(8) + 20) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }
        //mid DPS/health
        if(this.element.equals("water") || this.element.equals("ice") || this.element.equals("fairy") || this.element.equals("fighting")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(4) + 3) * this.level;
            this.baseDamage = (r1.nextInt(4) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(6) + 15) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }

        Random r3 = new Random();
        this.healthPots = r3.nextInt(2);
        //enemy has either 0 or 1 health pots
    }


    /**
     * starter pokemon picked
     * @param element picked by player for their very first pokemon
     */
    public Pokemon(String element){
        this.level = 1;
        this.element = element;
        this.currentXP = 0;

        if(this.element.equals("fire")){
            this.specialAttack = new Burn();
        }
//      if(this.element.equals("water")){
//        this.specialAttack = nothing lol
//      }
        if(this.element.equals("grass")){
            this.specialAttack = new Siphon();
        }


        //high DPS, low health
        if(this.element.equals("fire") || this.element.equals("poison") || this.element.equals("electric")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(6) + 3) * this.level;
            this.baseDamage = (r1.nextInt(6) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(4) + 10) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }
        //low DPS, high health
        if(this.element.equals("grass") || this.element.equals("psychic")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(2) + 3) * this.level;
            this.baseDamage = (r1.nextInt(2) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(8) + 20) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }
        //mid DPS/health
        if(this.element.equals("water") || this.element.equals("ice") || this.element.equals("fairy") || this.element.equals("fighting")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(4) + 3) * this.level;
            this.baseDamage = (r1.nextInt(4) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(6) + 15) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }
    }



    /**
     * levels up pokemon
     * update damage and health
     * @param leftoverXP after killing enemy
     */
    public void levelUp(int leftoverXP){
        this.level++;
        this.currentXP = leftoverXP;


        //high DPS, low health
        if(this.element.equals("fire") || this.element.equals("poison") || this.element.equals("electric")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(6) + 3) * this.level;
            this.baseDamage = (r1.nextInt(6) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(4) + 10) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }
        //low DPS, high health
        if(this.element.equals("grass") || this.element.equals("psychic")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(2) + 3) * this.level;
            this.baseDamage = (r1.nextInt(2) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(8) + 20) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }
        //mid DPS/health
        if(this.element.equals("water") || this.element.equals("ice") || this.element.equals("fairy") || this.element.equals("fighting")){
            Random r1 = new Random();
            this.effectiveBaseDamage = (r1.nextInt(4) + 3) * this.level;
            this.baseDamage = (r1.nextInt(4) + 3) * this.level;

            Random r2 = new Random();
            int health = (r2.nextInt(6) + 15) * this.level;
            this.totalHealth = health;
            this.currentHealth = health;
        }
    }

    /**
     * @param enemy pokemon
     * @return damage dome by this poke to enemy poke by elemental attack
     */
    public int elementalAttack(Pokemon enemy){
        //if against counter...
        if(this.counters(enemy.element)){
            return (int) (this.effectiveBaseDamage * 1.3);
        }

        //if against weaker element...
        if(enemy.counters(this.element)){
            return (int) (this.effectiveBaseDamage * 0.7);
        }

        //if against same element... base damage
        return this.effectiveBaseDamage;
    }

    /**
     * pokemon takes damage
     * @param damage taken
     */
    public void takeDamage(int damage){
        this.totalHealth -= damage;
    }

    /**
     * @return whether this pokemon is dead
     */
    public boolean isDead(){
        return this.currentHealth <= 0;
    }

    /**
     * pokemon uses healing potion
     * gets rid of paralysis
     */
    public void healingPot(){
        this.currentHealth += (int) (this.totalHealth * 0.3);
        if(this.currentHealth > this.totalHealth){
            this.currentHealth = this.totalHealth;
        }
        if(this.status.isParalysis()){
            this.status = new StatusEffect();
            System.out.println("Paralysis removed!");
        }
    }

    /**
     *
     * @param se status effect
     * @return whether this pokemon has that status effect
     */
    public boolean doesNotHaveStatus(StatusEffect se){
        if(se.isIntimidate()){
            return !this.status.isIntimidate();
        }
        if(se.isBurn()){
            return !this.status.isBurn();
        }
        if(se.isConfusion()){
            return !this.status.isConfusion();
        }
        if(se.isFreeze()){
            return !this.status.isFreeze();
        }
        if(se.isPoison()){
            return !this.status.isPoison();
        }
        if(se.isParalysis()){
            return !this.status.isParalysis();
        }
        if(se.isSiphon()){
            return !this.status.isSiphon();
        }
        if(se.isSleep()){
            return !this.status.isSleep();
        }
        return true;
    }

    /**
     * Applies effect of current status to pokemon (DOT, DOT/heal, self attack, lower dmg)
     * also prints to terminal status effect
     * lowers rounds left in status, applies status until status is over
     * --------------------------------------------------------
     * SE's:
     * DOT                                  (burn, poison)
     * DOT/heal                             (siphon)
     * self attack                          (confusion)
     * lower dmg                            (intimidate)
     * --------------------------------------------------------
     * TODO: add in main:
     * miss attack                          (paralysis)
     * miss turn                            (freeze, sleep)
     */
    public void applyStatus(){
        if(this.status.roundsLeft == 0){
            this.effectiveBaseDamage = this.baseDamage;
            this.status = new StatusEffect();
            return;
        }

        if (this.status.isBurn() || this.status.isPoison()){
            int damage = (int) (Burn.DOT * this.totalHealth);
            this.currentHealth -= damage;
            System.out.println("Your pokemon took " + damage + " damage from the " + this.status.getClass() + " effect!");
        }
        if(this.status.isSiphon()){
            int damage = (int) (Siphon.DOT * this.totalHealth);
            this.currentHealth -= damage;
            System.out.println("Your pokemon took " + damage + " damage from the " + this.status.getClass() + " effect!");
            //TODO: add in main function that heals enemy for SIPHON.heal * pokemon.totalHealth
        }
        if(this.status.isConfusion()){
            int damage = (int) (Confusion.SELF_ATTACK * this.baseDamage);
            this.currentHealth -= damage;
            System.out.println("Your pokemon got confused and attacked itself for " + damage + "damage...");
            //TODO: add in main function that checks if confusion is applied and attacks itself
        }
        if(this.status.isIntimidate()){
            this.effectiveBaseDamage = (int) (Intimidate.LOWER_DMG * this.baseDamage);
            System.out.println("Your pokemon feels intimidated and does half as much damage...");
        }

        this.status.roundsLeft--;
    }

    /**
     * updates your XP/level after killing an enemy
     * @param enemy killed
     */
    public void updateXP(Pokemon enemy){
        int enemiesToKill = 5;
        int totalXP = this.level * 100;
        //TODO: more xp higher level enemy, little less xp for lower level enemy
        int XPgained = this.level * (100/enemiesToKill);
        this.currentXP += XPgained;


        //if got enough XP to level up
        if(this.currentXP >= totalXP){
            int leftoverXP = 0;
            if(this.currentXP > totalXP){
                leftoverXP = this.currentXP - totalXP;
            }
            this.levelUp(leftoverXP);
        }
    }

    //TODO: override updateXP in other scenarios that xp might be increased ?

    //grass have spore/seed that life steals
    //fire blast (20% to cause burn status effect, 5% health DOT, 3 turns)
    // -element attack (fire blast, spore life steal, etc...)
    // -base attack immune to element counters (tackle)
    // -run away (not guaranteed)
    // -healing potion (30%)
    /**
     * @param opponent of this pokemon
     * @return the move made against opponent... either
     * "heal" (enemy uses healing pot)
     * "run" (run away)
     * "elemental" (special atack with status effect)
     * "tackle" (basic attack)
     *
     *  -also prints to terminal a message saying what attack is done
     */
    public String enemyPicksMove(Pokemon opponent){
        //"this" is the enemy pokemon
        //"opponent" is your pokemon

        //if below half health and this enemy pokemon has a healing pot... try to heal!
        if(this.currentHealth < (int) (this.totalHealth * 0.5) && this.healthPots >= 1){
            System.out.println("The enemy pokemon uses a healing potion!");
            return "heal";
        }

        //if this enemy has < 1/4 health, try to run!
        if(this.currentHealth < (int) (this.totalHealth * 0.20)){
            System.out.println("The enemy pokemon in its bad health tries to run away...");
            return "run";
        }

        //if this enemy is in good health, and against weaker opponent, use elemental attack
        if(this.counters(opponent.element)){
            System.out.println("The enemy pokemon uses its elemental attack!");
            return "elemental";

        }

        //if not against weaker opponent, use tackle
        System.out.println("The enemy pokemon uses its basic attack, tackle!");
        return "tackle";
    }

    //TODO: write this function for YOUR move too, not just enemy!
    /**
     * @param enemy pokemon
     * @param move enemy made
     * @param who "you" or "enemy"
     * PRINT to terminal:
     *   -damage done & status effect (elemental attack / basic attack)
     *   -amount healed (heal)
     *   -succeeded in running away? (run)
     */
    public void move(Pokemon enemy, String move, String who){
        //"enemy" is enemy
        //"this" is your pokemon

        boolean enemyMove = who.equals("enemy");

        if(move.equals("elemental")){
            int damage = enemy.elementalAttack(this);
            this.takeDamage(damage);

            if(enemyMove) {
                System.out.println("The enemy pokemon did " + damage + " damage to you!");
            }
            else{
                System.out.println("You dealt " + damage + "damage!");
            }

            //status effect
            boolean applied = enemy.specialAttack.appliedCOUNTERS() && this.doesNotHaveStatus(enemy.specialAttack);
            if(applied){
                enemy.specialAttack.randomizeRounds();
                this.status = enemy.specialAttack;
                System.out.println("The enemy pokemon gave you " + enemy.specialAttack.getClass() + "!");
            }
        }
        if(move.equals("tackle")){
            //if enemy is countered... do 30% less damage, else: base damage
            int damage = (this.counters(enemy.element) ? (int) (enemy.effectiveBaseDamage * 0.7) : enemy.effectiveBaseDamage);
            this.takeDamage(damage);
            System.out.println("The enemy pokemon did " + damage + " damage to you!");

            //status effect
            boolean statusApplied = (this.counters(enemy.element) ? enemy.status.appliedCOUNTERED() : enemy.status.appliedBASE());
            boolean applied = statusApplied && this.doesNotHaveStatus(enemy.specialAttack);
            if(applied){
                enemy.specialAttack.randomizeRounds();
                this.status = enemy.specialAttack;
                System.out.println("The enemy pokemon gave you " + enemy.specialAttack.getClass() + "!");
            }
        }
        if(move.equals("run")){
            Random r1 = new Random();
            //  1/3 chance to succeed in running
            boolean succeeded = r1.nextInt(3) == 0;
            if(succeeded){
                System.out.println("And succeeded!");
            }
            System.out.println("but failed! :(");
        }
        if(move.equals("heal")){
            enemy.healingPot();
            System.out.println("The enemy used a healing potion!");
        }

    }
}
