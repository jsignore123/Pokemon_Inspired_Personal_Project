import java.util.LinkedList;

public class Person {
    public String name;
    public LinkedList<Pokemon> pokemons;
    public int numHealPots;

    /**
     * new person in game
     * @param name of person
     */
    public Person(String name){
        this.name = name;
        this.pokemons = new LinkedList<>();
        this.numHealPots = 3;
    }

    /**
     * @param p pokemon to add (enemy killed or pokemon found)
     */
    public void addPokemon(Pokemon p){
        this.pokemons.add(p);
    }

    public void removePokemon(Pokemon p){
        this.pokemons.remove(p);
    }

    /**
     * @param index in list of pokemon picked
     * @return pokemon user chose
     */
    public Pokemon pokePicked(int index){
        return this.pokemons.get(index);
    }

    /**
     * @param index of pokemon taking damage
     * @param damage taken by that pokemon
     */
    public void takeDamage(int index, int damage){
        this.pokemons.get(index).takeDamage(damage);
    }

    /**
     * @param index of pokemon that killed an enemy
     * @param enemyKilled by that pokemon
     */
    public void updateXPofPokemon(int index, Pokemon enemyKilled){
        this.pokemons.get(index).updateXP(enemyKilled);
    }

    /**
     * removes pokemon at index
     * @param index in list
     */
    public void pokemonKilled(int index){
        this.pokemons.remove(index);
    }

    /**
     * @return whether this person has no pokemon
     */
    public boolean hasNoPokemon(){
        return this.pokemons.size() == 0;
    }

    /**
     * @return whether this person has one pokemon
     */
    public boolean hasOnePokemon(){
        return this.pokemons.size() == 1;
    }

    /**
     * prints this person's pokemon collection
     */
    public void printPokemon(){
        System.out.println(this.name + "'s pokemon:");
        for(int i=0; i<this.pokemons.size(); i++){
            Pokemon thisPoke = this.pokemons.get(i);
            int level = thisPoke.level;
            String element = thisPoke.element;
            String health = thisPoke.currentHealth + "/" + thisPoke.totalHealth + " health";
            int baseDmg = thisPoke.effectiveBaseDamage;
            System.out.println("[" + (i) + "] : Level " + level + " " + element + ", " + health + ", " + baseDmg + " damage");
        }
    }




}
