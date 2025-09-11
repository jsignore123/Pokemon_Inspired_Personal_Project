public class DecisionTree extends Pokemon {
    // Extends
    public DecisionTree(){
        super(1); // random level, just needs access to
    };

    // function name
    // Boolean func (if decision node)
    // Action func (if leaf node)
    public String function;

    // "no" tree
    // NULL if leaf node (just runs action func)
    // DecisionTree if decision node
    public DecisionTree falseNode;

    // "yes" tree
    // NULL if leaf node (just runs action func)
    // DecisionTree if decision node
    public DecisionTree trueNode;

    // BOOLEAN FUNCTIONS
    // isLowHealth ? (health < 25%)
    // isHalfHeath ? (health < 50%)
    // hasHealthPots ?
    // counters(String element) opponent ?

    // ACTION FUNCTIONS
    // run
    // heal
    // basic
    // elementalAttack() elemental TODO: update this

    public void runFunction(){

        // If decision node
        if (trueNode != null && falseNode != null){

        }
    }

}
