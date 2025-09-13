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
    public int healthPots; // only for enemy
    public StatusEffect specialAttack; // this poke's elemental/status applied
    public StatusEffect status = new StatusEffect();

    public void printStats(String thisPoke){
        int level = this.level;
        String element = this.element;
        String health = this.currentHealth + "/" + this.totalHealth + " health";
        int baseDmg = this.effectiveBaseDamage;
        int numPots = this.healthPots;
        System.out.println(thisPoke + " POKEMON: Level " + level + " " + element + ", " + health + ", " + baseDmg + " damage" +
                (thisPoke.equals("\nENEMY") ? ", has " + numPots + " health pots" : "")
                + (this.noStatus() ? "" : ", Status effect: " + this.status.getClass() + " for " + this.status.roundsLeft + " rounds"));
    }



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
     * Constructor : random pokemon appears!
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
        if(this.element.equals("water")){
            this.specialAttack = new StatusEffect(); //nothing lol
        }
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
     * Constructor : starter pokemon picked!
     * @param element picked by player for their very first pokemon
     */
    public Pokemon(String element){
        this.level = 1;
        this.element = element;
        this.currentXP = 0;

        if(this.element.equals("fire")){
            this.specialAttack = new Burn();
        }
        if(this.element.equals("water")){
            this.specialAttack = new StatusEffect(); //nothing lol
        }
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
        this.currentHealth -= damage;
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
    public void heal(){
        int amountHealed = (int) ((this.totalHealth) * 0.4);
        this.currentHealth += amountHealed;
        if(this.currentHealth > this.totalHealth){
            this.currentHealth = this.totalHealth;
        }
        if(this.status.isParalysis()){
            this.status = new StatusEffect();
            System.out.println("Paralysis removed!");
        }

        System.out.println("Healed for " + amountHealed + "!");
    }

    /**
     * @param se status effect
     * @return whether this pokemon has that status effect
     */
    public boolean doesNotHaveThisStatus(StatusEffect se){
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
     * TODO: set status to null by default to avoid this hardcoding
     * @return whether this pokemon has a status effect currently
     */
    public boolean noStatus(){
        return this.status == null || (!this.status.isIntimidate() && !this.status.isBurn() && !this.status.isConfusion() &&
                !this.status.isFreeze() && !this.status.isPoison() && !this.status.isParalysis() &&
                !this.status.isSiphon() && !this.status.isSleep());
    }

    /**
     * @param who is getting SE applied
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
    public void applyStatus(String who, Pokemon opponent){
        if (this.status.isBurn() || this.status.isPoison()){
            int damage = (int) (Burn.DOT * this.totalHealth);
            this.currentHealth -= damage;
            System.out.println(who + " pokemon took " + damage + " damage from the " + this.status.getClass() + " effect!");
        }
        else if(this.status.isSiphon()){
            int damage = (int) (Siphon.DOT * this.totalHealth);
            this.currentHealth -= damage;
            System.out.println(who + " pokemon took " + damage + " damage from the " + this.status.getClass() + " effect!");

            String otherPoke = (who.equals("Your") ? "The Enemy" : "Your");
            int heal = (int) (Siphon.HEAL * this.totalHealth);
            opponent.currentHealth += heal;
            if(opponent.currentHealth > opponent.totalHealth){
                opponent.currentHealth = opponent.totalHealth;
                System.out.println(otherPoke + "healed to full health using Siphon!");
            }
            else{
                System.out.println(otherPoke + "healed from Siphon for " + heal + "!");
            }
        }
        else if(this.status.isConfusion()){
            int damage = (int) (Confusion.SELF_ATTACK * this.baseDamage);
            this.currentHealth -= damage;
            System.out.println(who + "pokemon got confused and attacked itself for " + damage + "damage...");
            //TODO: add in main function that checks if confusion is applied and attacks itself
        }
        else if(this.status.isIntimidate()){
            this.effectiveBaseDamage = (int) (Intimidate.LOWER_DMG * this.baseDamage);
            System.out.println(who + "pokemon feels intimidated and does half as much damage...");
        }

        this.status.roundsLeft--;

        if(this.status.roundsLeft <= 0){
            this.effectiveBaseDamage = this.baseDamage;
            this.status = new StatusEffect();
        }
    }

    /**
     * TODO: override updateXP in other scenarios that xp might be increased ?
     * TODO: print xp gained
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



    /**
     * Applies the move the pokemon (enemy or you) chooses using decision tree
     * Prints to terminal
     * Applies damage/effects/healing
     * TODO: grass have spore/seed that life steal, fire blast (20% to cause burn status effect, 5% health DOT, 3 turns)
     * @param opponent the enemy is against (your pokemon)
     * @param move the enemy picks using decision tree
     * @param who is making move (for print statements) : "enemy" if enemy is making move, anything else for player
     * @return whether run was successful
     */
    public boolean move(Pokemon opponent, String move, String who){
        // Strings for print statements
        String thisPoke = "", otherPoke = "";
        thisPoke = (who.equals("enemy") ? "The Enemy Pokemon " : "You ");
        otherPoke = (who.equals("enemy") ? " you " : " the enemy Pokemon ");

        switch (move) {
            case "run":
                Random r1 = new Random();
                //  1/3 chance to succeed in running
                boolean success = r1.nextInt(3) == 1;

                System.out.println(thisPoke + "tried to run... " + (success ? " And Succeeded!" : "but failed! :("));
                return success;
            case "heal":
                System.out.println(thisPoke + "used a healing potion!");
                this.heal();
                return false;
            case "elemental": {
                // apply damage done to opponent and print
                int damage = this.elementalAttack(opponent);
                opponent.takeDamage(damage);
                System.out.println(thisPoke + "did " + damage + " damage to" + otherPoke + "!");

                // apply status effect to opponent
                boolean applied = (!this.element.equals("water")) && // water pokemon have no special attack/status effect to give
                        this.specialAttack.isAppliedWhenCOUNTERS() && opponent.doesNotHaveThisStatus(this.specialAttack);
                if (applied) {
                    // randomize num rounds
                    this.specialAttack.randomizeRounds();
                    opponent.status = this.specialAttack;
                    System.out.println(thisPoke + "gave" + otherPoke + this.specialAttack.getClass() + "!");
                }
                return false;
            }
            case "tackle": {
                // Full 100% damage for tackle
                opponent.takeDamage(this.effectiveBaseDamage);
                System.out.println(thisPoke + "did " + this.effectiveBaseDamage + " damage to" + otherPoke + "!");

                // status effect to opponent
                boolean statusApplied = (!this.element.equals("water")) && // water pokemon have no special attack/status effect to give
                        (opponent.counters(this.element) ? this.status.isAppliedWhenCOUNTERED() : this.status.appliedBASE());
                boolean applied = statusApplied && this.doesNotHaveThisStatus(opponent.specialAttack);
                if (applied) {
                    this.specialAttack.randomizeRounds();
                    opponent.status = this.specialAttack;
                    System.out.println(thisPoke + "gave" + otherPoke + opponent.specialAttack.getClass() + "!");
                }
                break;
            }
        }

        return false;
    }
}
