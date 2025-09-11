import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        int enemiesKilled = 0;

        //welcome message, pick starter pokemon
        System.out.println("here's my dumb fuck pokemon game, what starter pokemon do you want? fire, water, or grass?");
        Scanner s = new Scanner(System.in);
        String startingElement = s.nextLine();


        //TODO: initialize rival trainer

        Person YOU = new Person("Jessie");
        Pokemon starterPoke = new Pokemon(startingElement);
        YOU.addPokemon(starterPoke);


        //boolean for, is game still running?
        boolean keepGoing = true;





        //run simulation/game:
        while(keepGoing){

            //TODO: random chance to find passive pokemon, give user option to catch it, random chance it's added to their collection of pokemons
            //TODO: add option to return to poke center to heal pokemon

            //    1 / chance  =  chance enemy will spawn
            int chanceForEnemyToSpawn = 5;
            Random r1 = new Random(System.currentTimeMillis());
            int chance = r1.nextInt(chanceForEnemyToSpawn);


            //if enemy spawns
            if(chance == 0 && !YOU.hasNoPokemon()){
                boolean fightOngoing = true;

                Random r2 = new Random(System.currentTimeMillis());
                int enemyLevel = starterPoke.level + r2.nextInt(5) - 2;
                Pokemon enemy = new Pokemon(enemyLevel);
                String enemyEl = enemy.element;

                System.out.println("A " + enemyEl + " pokemon approaches you with the intention to beat yo ass!");
                System.out.println("Which pokemon will you pick?");
                YOU.printPokemon();
                Scanner s3 = new Scanner(System.in);
                int p = s3.nextInt();

                Pokemon currentPokemon = YOU.pokePicked(p);



                while(fightOngoing) {


                    //actual fight (options for attacks, switch out pokemon, try to catch it (chance increases the lower health is)...)
                    //element attack
                    //base attack immune to element counters
                    //run away (not guaranteed)
                    //healing potion (30%)
                    //switch out pokemon
                    //try to catch it (higher chance lower enemy health)

                    boolean missTurn = currentPokemon.status.isFreeze() || currentPokemon.status.isParalysis();
                    boolean hasHealingPots = YOU.numHealPots > 0;
                    String yourMove = "";

                    if(!missTurn) {
                        if (YOU.hasOnePokemon()) {
                            currentPokemon = YOU.pokemons.getFirst();
                            System.out.println("Would you like to use 'elemental' attack, 'tackle', try to 'run'" + (hasHealingPots ? ", or 'heal'?" : "?"));

                            Scanner s1 = new Scanner(System.in);
                            yourMove = s1.nextLine();
                        } else {
                            System.out.println("Would you like to use 'elemental' attack, 'tackle', try to 'run', 'switch' pokemon" + (hasHealingPots ? ", or 'heal'?" : "?"));

                            Scanner s1 = new Scanner(System.in);
                            yourMove = s1.nextLine();
                        }


                        //TODO: make function for your move


                        currentPokemon.applyStatus();

                    }
                    if(missTurn){
                        currentPokemon.status.roundsLeft--;

                        if(currentPokemon.status.roundsLeft == 0){
                            currentPokemon.status = new StatusEffect();
                        }
                    }



                    //enemy move




                    //if enemy died
                    if(enemy.isDead()){
                        enemiesKilled++;
                        starterPoke.updateXP(enemy);

                        //TODO: enemy killed msg

                        fightOngoing = false;
                    }

                    //if you died
                    if(starterPoke.isDead()){
                        System.out.println("starterPoke DIED!!! GAME OVER!!!");
                        //TODO: if the player has other pokemon... give option to switch. if not... fight over but could still catch more
                    }
                }
            }
            //TODO: message for when no enemy spawns "..."



            System.out.println("Want to quit? (y)es or (n)o:");
            Scanner s2 = new Scanner(System.in);
            String quit = s2.nextLine();
            if(quit.equals("n")){
                keepGoing = false;
            }
        }

    }
}
