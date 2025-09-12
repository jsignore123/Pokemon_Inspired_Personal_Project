public class DecisionTree {
    // Extends
    public DecisionTree(Pokemon pokemon, Pokemon opponent){
        this.pokemon = pokemon;
        this.opponent = opponent;
    };

    // Enemy pokemon decision tree runs on
    public Pokemon pokemon;

    // Enemy pokemon's opponent (your pokemon)
    public Pokemon opponent;


    // BOOLEAN FUNCTIONS : Decision node
    // isLowHealth ? (health < 25%)
    // isHalfHeath ? (health < 50%)
    // hasHealthPots ?
    // counters(String element) opponent ?

    // ACTION FUNCTIONS  : Leaf node
    // run
    // heal
    // tackle
    // elemental
    public String function;

    // True and False Branches
    // NULL         : leaf node (just runs action func)
    // DecisionTree : decision node

    // "no" tree
    public DecisionTree falseNode;

    // "yes" tree
    public DecisionTree trueNode;

    public void init(){
        // Root node : health < 0.25 ?
        this.function = "isLowHealth";
        DecisionTree rootTrue = new DecisionTree(pokemon, opponent);
        rootTrue.function = "run";

        // NODE 2
        DecisionTree node2 = new DecisionTree(pokemon, opponent);
        node2.function = "isHalfHealth";

        // NODE 3
        DecisionTree node3 = new DecisionTree(pokemon, opponent);
        node3.function = "hasHealthPots";

        // NODE 4
        DecisionTree node4 = new DecisionTree(pokemon, opponent);
        node4.function = "heal";

        // NODE 5
        DecisionTree node5 = new DecisionTree(pokemon, opponent);
        node5.function = "counters";

        // NODE 6
        DecisionTree node6 = new DecisionTree(pokemon, opponent);
        node6.function = "tackle";

        // NODE 7
        DecisionTree node7 = new DecisionTree(pokemon, opponent);
        node7.function = "elemental";

        // Set Node 5 Children
        node5.trueNode = node7;
        node5.falseNode = node6;

        // Set Node 3 Children
        node3.trueNode = node4;
        node3.falseNode = node5;

        // Set Node 2 Children
        node2.trueNode = node3;
        node2.falseNode = node5;

        // Set Root's Children
        this.trueNode = rootTrue;
        this.falseNode = node2;
    }
    /**
     * determines whether this is a leaf node
     * @return true if leaf, false if decision node
     */
    public boolean isLeaf(){
        return this.falseNode == null && this.trueNode == null;
    }

    /**
     * @return boolean from method if decision node
     * true = go down true tree
     * false = go down false tree
     */
    public boolean checkCondition(){
        switch (this.function) {
            case "isLowHealth":
                return this.pokemon.isLowHealth();
            case "isHalfHealth":
                return this.pokemon.isHalfHealth();
            case "hasHealthPots":
                return this.pokemon.hasHealthPots();
            case "counters":
                return this.pokemon.counters(this.opponent.element);
        }
        return false;
    }

    public void runLeafFunction(){
        switch (this.function) {
            case "run":
                System.out.println("So, the enemy chose to run.");
                this.pokemon.move(this.opponent, "run", "enemy");
                break;
            case "heal":
                System.out.println("So, the enemy chose to heal.");
                this.pokemon.move(this.opponent, "heal", "enemy");
                break;
            case "tackle":
                System.out.println("So, the enemy chose to tackle.");
                this.pokemon.move(this.opponent, "tackle", "enemy");
                break;
            case "elemental":
                System.out.println("So, the enemy chose to use its elemental attack.");
                this.pokemon.move(this.opponent, "elemental", "enemy");
                break;
        }
    }

    public void decide(){
        if(this.isLeaf()){ // run action function if leaf
            this.runLeafFunction();
        }
        else{ // pass down pokemon info and evaluate true/false node
            boolean b = this.checkCondition();
            System.out.println(this.function + "() returned " + b + "...");
            if(b){
                this.trueNode.decide();
            }
            else{
                this.falseNode.decide();
            }
        }
    }




}
