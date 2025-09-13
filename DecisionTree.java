import java.util.Random;

public class DecisionTree {
    // Extends
    public DecisionTree(Blackboard blackboard){
        this.blackboard = blackboard;
    };

    // Blackboard
    public Blackboard blackboard;


    // BOOLEAN FUNCTIONS : Decision node
    // isLowHealth() ? (health < 25%)
    // isHalfHeath() ? (health < 50%)
    // hasHealthPots() ?
    // counters(String element) opponent ?
    // random(x : 0 - 10)  gives percentage chance (0.x chance to be true)

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

    public boolean random(int chance){
        Random r1 = new Random();
        return r1.nextInt(10) < chance;
    }

    public void init(){
        // Root node : health < 0.25 ?
        this.function = "isLowHealth";

        // Root True Branch
        DecisionTree rootTrue = new DecisionTree(blackboard);
        rootTrue.function = "run";

        // NODE 2
        DecisionTree node2 = new DecisionTree(blackboard);
        node2.function = "isHalfHealth";

        // NODE 3
        DecisionTree node3 = new DecisionTree(blackboard);
        node3.function = "hasHealthPots";

        // NODE 4
        DecisionTree node4 = new DecisionTree(blackboard);
        node4.function = "random";

        // NODE 5
        DecisionTree node5 = new DecisionTree(blackboard);
        node5.function = "counters";

        // NODE 6
        DecisionTree node6 = new DecisionTree(blackboard);
        node6.function = "tackle";

        // NODE 7
        DecisionTree node7 = new DecisionTree(blackboard);
        node7.function = "elemental";

        // NODE 8
        DecisionTree node8 = new DecisionTree(blackboard);
        node8.function = "heal";

        // Set Node 5 Children
        node5.trueNode = node7;
        node5.falseNode = node6;

        // Set Node 4 Children
        node4.trueNode = node8;
        node4.falseNode = node5;

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
                return this.blackboard.enemy.isLowHealth();
            case "isHalfHealth":
                return this.blackboard.enemy.isHalfHealth();
            case "hasHealthPots":
                return this.blackboard.enemy.hasHealthPots();
            case "counters":
                return this.blackboard.enemy.counters(this.blackboard.you.element);
            case "random":
                System.out.println("The enemy randmoly decided between healing (70% : true) and attacking (30% : false)...");
                return this.random(7);
        }
        return false;
    }

    public void runLeafFunction(){
        switch (this.function) {
            case "run":
                System.out.println("So, the enemy chose to run.");
                this.blackboard.enemy.move(this.blackboard.you, "run", "enemy");
                break;
            case "heal":
                System.out.println("So, the enemy chose to heal.");
                this.blackboard.enemy.move(this.blackboard.you, "heal", "enemy");
                break;
            case "tackle":
                System.out.println("So, the enemy chose to tackle.");
                this.blackboard.enemy.move(this.blackboard.you, "tackle", "enemy");
                break;
            case "elemental":
                System.out.println("So, the enemy chose to use its elemental attack.");
                this.blackboard.enemy.move(this.blackboard.you, "elemental", "enemy");
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
