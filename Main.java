import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int enemiesKilled = 0;

        //welcome message, pick starter pokemon
        System.out.println("here's my knock off pokemon game. what starter pokemon do you want - 'fire', 'water', or 'grass'?");
        Scanner s = new Scanner(System.in);
        String startingElement = s.nextLine();

        //TODO: initialize rival trainer as another person

        // Initialize player and their pokemon
        Person YOU = new Person("JESS");
        Pokemon starterPoke = new Pokemon(startingElement);
        YOU.addPokemon(starterPoke);

        // Boolean if game still running
        boolean keepGoing = true;

        // Game loop
        while(keepGoing){
            //TODO: random chance to find passive pokemon, give user option to catch it, random chance it's added to their collection of pokemons
            //TODO: add option to return to poke center to heal pokemon
            //TODO: add enemies they defeat to your pokemon collection?

            // 1 / chance  =  chance enemy will spawn
            int chanceForEnemyToSpawn = 5;
            Random r1 = new Random(System.currentTimeMillis());
            int chance = r1.nextInt(chanceForEnemyToSpawn);

            //if enemy spawns and you have pokemon
            if(chance == 0 && !YOU.hasNoPokemon()){
                // fight ongoing
                boolean fightOngoing = true;

                // initialize enemy spawn
                Random r2 = new Random(System.currentTimeMillis());
                int enemyLevel = starterPoke.level + r2.nextInt(5);
                Pokemon enemy = new Pokemon(enemyLevel);
                String enemyEl = enemy.element;
                System.out.println("A " + enemyEl + " pokemon approaches you with the intention to fight !");

                // prompt user to pick pokemon to fight with
                System.out.println("Which pokemon will you pick to fight them? (enter int index)");
                YOU.printPokemon();
                Scanner s3 = new Scanner(System.in);
                int p = s3.nextInt();
                Pokemon currentPokemon = YOU.pokePicked(p);

                // initialize decision tree
                DecisionTree DT = new DecisionTree(enemy, currentPokemon);
                DT.init();


                // Run fight!
                while(fightOngoing) {

                    // TODO: switch out pokemon?

                    System.out.println();
                    enemy.printStats("ENEMY");
                    currentPokemon.printStats("YOUR");

                    boolean missTurn = currentPokemon.status.isFreeze() || currentPokemon.status.isParalysis();
                    boolean hasHealingPots = YOU.numHealPots > 0;
                    String yourMove = "";

                    if(missTurn){ // missed turn
                        currentPokemon.status.roundsLeft--;

                        if(currentPokemon.status.roundsLeft <= 0){
                            currentPokemon.status = new StatusEffect();
                            System.out.println("Your Pokemon's status effect has been lifted! You can make turns now.");
                        }
                        else {
                            System.out.println("Your pokemon has " + currentPokemon.status.roundsLeft + " rounds left before it can make turns again,");
                        }
                    }
                    else { // did not miss turn

                        System.out.println("Would you like to use 'elemental' attack, 'tackle', try to 'run'" + (hasHealingPots ? ", or 'heal'? (" + YOU.numHealPots + " healing pots left)": "?"));

                        Scanner s1 = new Scanner(System.in);
                        yourMove = s1.nextLine();

                        boolean runSuccess = currentPokemon.move(enemy, yourMove, "you");

                        if(yourMove.equals("run") && runSuccess){
                            System.out.println("YOU SUCCEEDED IN RUNNING AWAY! Fight over. Another enemy may appear though...");
                            fightOngoing = false;
                        }
                        else if (yourMove.equals("heal") && hasHealingPots){
                            YOU.numHealPots--;
                        }


                        currentPokemon.applyStatus();

                    }

                    ///////////////////
                    // DECISION TREE //
                    ///////////////////
                    DT.decide();


                    //if enemy died
                    if(enemy.isDead()){
                        enemiesKilled++;
                        starterPoke.updateXP(enemy);

                        System.out.println("You defeated the enemy pokemon, and it's yours now!!! congrats!!! \nThe fight is over. But another is yet to come...");
                        enemy.currentHealth = enemy.totalHealth;
                        enemy.status = new StatusEffect();
                        YOU.addPokemon(enemy);

                        fightOngoing = false;
                    }

                    //if your pokemon died
                    if(currentPokemon.isDead()){
                        System.out.println("Your Pokemon DIED!!!");
                        YOU.removePokemon(currentPokemon);
                        if(YOU.hasNoPokemon()){
                            System.out.println("\nThat was your last pokemon... L");
                            System.out.println("/////////////////////\n* * * GAME OVER * * *\n/////////////////////");

                            keepGoing = false;
                            fightOngoing = false;
                        }
                        else{
                            // prompt user to pick pokemon to fight with
                            System.out.println("Which pokemon will you pick to fight now?");
                            YOU.printPokemon();
                            Scanner s4 = new Scanner(System.in);
                            int p2 = s4.nextInt();
                            currentPokemon = YOU.pokePicked(p2);
                        }

                    }

//                    System.out.println("\nWant to quit? (y)es or (n)o:");
//                    Scanner s2 = new Scanner(System.in);
//                    String quit = s2.nextLine();
//                    if(quit.equals("y")){
//                        keepGoing = false;
//                    }
                }
            }
            else{
                System.out.println("Waiting for an enemy to appear... the silence lingers...");
                Thread.sleep(1000);
            }




            System.out.println("\nWant to quit? (y)es or (n)o:");
            Scanner s2 = new Scanner(System.in);
            String quit = s2.nextLine();
            if(quit.equals("y")){
                keepGoing = false;
            }
        }

    }
}
