public class Blackboard {
    public Pokemon enemy; // enemy pokemon
    public Pokemon you; // pokemon user picked
    public Blackboard(){}
    public void setBlackboard(Pokemon pokemon, Pokemon opponent){
        this.enemy = pokemon; // enemy pokemon
        this.you = opponent; // your pokemon
    }
}
