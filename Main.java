import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int enemiesKilled = 0;

        // Welcome message. Pick Your Pokemon!
        System.out.println("here's my pokemon game. what starter pokemon do you want - 'fire', 'water', or 'grass'?");
        Scanner s = new Scanner(System.in);
        String startingElement = s.nextLine();

        //TODO: initialize rival trainer as another person

        // Initialize Blackboard and Decision Tree (Blackboard values updated later)
        Blackboard blackboard = new Blackboard();
        DecisionTree DT = new DecisionTree(blackboard);
        DT.init();

        // Initialize player and their Pokemon
        Person YOU = new Person("JESS");
        Pokemon starterPoke = new Pokemon(startingElement);
        YOU.addPokemon(starterPoke);

        // Boolean if game still running
        boolean keepGoing = true;

        // ********* //
        // Game loop //
        // ********* //
        while(keepGoing){
            //TODO: random chance to find passive pokemon, give user option to catch it, random chance it's added to their collection of pokemons
            //TODO: add option to return to poke center to heal pokemon
            //TODO: add enemies they defeat to your pokemon collection?

            // 1 / chance  =  chance enemy will spawn
            int chanceForEnemyToSpawn = 3;
            Random r1 = new Random(System.currentTimeMillis());
            int chance = r1.nextInt(chanceForEnemyToSpawn);

            //if enemy spawns and you have pokemon
            if(chance == 0 && !YOU.hasNoPokemon()){
                // fight ongoing
                boolean fightOngoing = true;

                // prompt user to pick pokemon to fight with
                System.out.println("An enemy appeared! Which pokemon will fight with? (enter int index)");
                YOU.printPokemon();
                Scanner s3 = new Scanner(System.in);
                int p = s3.nextInt();
                Pokemon currentPokemon = YOU.pokePicked(p);

                // ENEMY SPAWNED: initialize
                Random r2 = new Random(System.currentTimeMillis());
                int enemyLevel = currentPokemon.level; //TODO: randomize level/set stats better? + r2.nextInt(2);
                Pokemon enemy = new Pokemon(enemyLevel);
                String enemyEl = enemy.element;
                System.out.println("\nIt turned out to be a " + enemyEl + " pokemon, ready to fight!");

                // initialize blackboard values (decision tree already has pointer to blackboard as field)
                blackboard.setBlackboard(enemy, currentPokemon);

                // Run fight!
                while(fightOngoing) {
                    // TODO: switch out pokemon?

                    ///////////////////////
                    // Print pokemon info//
                    enemy.printStats("\nENEMY");
                    currentPokemon.printStats("YOUR");
                    System.out.println();

                    ///// missed turn /////
                    boolean missTurn = !currentPokemon.noStatus() && (currentPokemon.status.isFreeze() || currentPokemon.status.isParalysis());
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
                    else { // did not miss turn - let player choose turn
                        // get user's turn
                        System.out.println("Would you like to use 'elemental' attack, 'tackle', try to 'run'" +
                                (hasHealingPots ? ", or 'heal'? (" + YOU.numHealPots + " healing pots left)": "?"));
                        Scanner s1 = new Scanner(System.in);
                        yourMove = s1.nextLine();
                        boolean runSuccess = currentPokemon.move(enemy, yourMove, "you"); // runs move func, grab bool incase "run".

                        System.out.println();

                        if(yourMove.equals("run") && runSuccess){ // run
                            System.out.println("YOU SUCCEEDED IN RUNNING AWAY! Fight over. Another enemy may appear though...");
                            fightOngoing = false;
                            break;
                        }
                        else if (yourMove.equals("heal") && hasHealingPots){ // heal
                            YOU.numHealPots--;
                        }
                        else{ // tackle or elemental move
                            if(enemy.isDead()){ //if enemy died
                                enemiesKilled++;
                                currentPokemon.updateXP(enemy);

                                System.out.println("You defeated the enemy pokemon, and it's yours now!!! congrats!!! \nThe fight is over. But another is yet to come...");
                                enemy.currentHealth = enemy.totalHealth;
                                enemy.status = new StatusEffect();
                                YOU.addPokemon(enemy);
                                blackboard.enemy = null;

                                fightOngoing = false;
                                break;
                            }
                        }

                        //////////////////
                        // APPLY STATUS //
                        //////////////////
                        currentPokemon.applyStatus("Your", enemy);

                    }


                    //////////////////
                    // APPLY STATUS //
                    //////////////////
                    enemy.applyStatus("The Enemy", currentPokemon);


                    ///////////////////
                    // DECISION TREE //
                    ///////////////////
                    System.out.println("\nThe enemy is making a move...\n--------------");
                    DT.decide();


                    //if your pokemon died
                    if(currentPokemon.isDead()){
                        System.out.println("Your Pokemon DIED!!!");
                        YOU.removePokemon(currentPokemon);
                        if(YOU.hasNoPokemon()){
                            System.out.println("\n* That was your last pokemon... L\n");
                            System.out.println("/////////////////////\n* * * GAME OVER * * *\n/////////////////////");

                            fightOngoing = false;
                            keepGoing = false;
                            break;
                        }
                        else{
                            // prompt user to pick pokemon to fight with
                            System.out.println("Which pokemon will you pick to fight now?");
                            YOU.printPokemon();
                            Scanner s4 = new Scanner(System.in);
                            int p2 = s4.nextInt();
                            currentPokemon = YOU.pokePicked(p2);
                            blackboard.you = currentPokemon; // update blackboard
                        }

                    }
                }
            }
            else{
                System.out.println("Waiting for an enemy to appear... but the air is still, no enemies in sight.");
                Thread.sleep(1000);
            }


            if(!keepGoing){
                break;
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
