import java.util.Iterator;
import java.util.ArrayList;

/**
 * Represents a single node in the lexicon trie.
 * Main methods: addLetter(), removeLetter(), setWord(), isItAWord(), getChildren(), child(), hasChild(), value()
 * Implements the Iterable interface and provides iteration capabilities over its children
 */
public class LexiconNode implements Iterable<LexiconNode>{

    private char data;

    private ArrayList<LexiconNode> childrenList = new ArrayList<>();

    private boolean isWord;

    /**
     *Constructor for LexiconNode class
     * sets the default isWord variable to false.
     * @param letter (char) the data that the LexiconNode will contain
     */
    public LexiconNode(char letter) {
        this.data = letter;
        this.isWord = false;

    }

    /**
     *Adds a letter to the node's arraylist of children in alphabetical order.
     * @param newLetter (LexiconNode) the new letter that is being added
     */
    public void addLetter(LexiconNode newLetter) {
        //extracts the data from the LexiconNode (should be char)
        char value = newLetter.data;
        //if there are no previous items in the childrenList, just add the letter
        if (childrenList.isEmpty()) {
            childrenList.add(newLetter);
        }
        else {
            //checks whether it would be the smallest value
            if (value < childrenList.get(0).data) {
                childrenList.add(0, newLetter);
            }
            //checks whether it would be the biggest value
            else if (value > childrenList.get(childrenList.size() - 1).data) {
                childrenList.add(childrenList.size() - 1, newLetter);
            } else {
                //loops through the preexisting childrenList to find the correct position
                for (int i = 1; i < childrenList.size(); i++) {
                    if (value < childrenList.get(i - 1).data && value > childrenList.get(i).data) {
                        childrenList.add(i + 1, newLetter);
                    }

                }
            }
        }
    }

    /**
     *(I wrote this method and never ended up using it but wanted to keep it instead of deleting it)
     * Searches the childrenList of the node to remove the correct node
     * @param letter (char) the letter you want to remove
     */
    public void removeLetter(char letter) {
        for (int i = 0; i < childrenList.size(); i++) {
            if (childrenList.get(i).data == letter) {
                childrenList.remove(i);
            }
        }
    }

    /**
     *Setter method for isWord instance variable
     * @param bool (boolean) True: if the node represents a word, False: if it isn't a word
     */
    public void setWord(boolean bool) {
        isWord = bool;

    }

    /**
     *Getter method for isWord instance variable
     * @return True: if at that node it is a word. False: if it is not a word
     */
    public boolean isItAWord() {
        return this.isWord;
    }

    /**
     *Getter method for childrenList
     * @return (ArrayList) of the node's children (which are of type LexiconNode)
     */
    public ArrayList<LexiconNode> getChildren() {
        return this.childrenList;

    }

    /**
     *Accesses the LexiconNode from the childrenList based on inputted char value
     * @param children (char) the value of the child you want to access
     * @return (LexiconNode) the node of the value you're looking for
     */
    public LexiconNode child(char children) {
        for (int i = 0; i < childrenList.size(); i++) {
            if (childrenList.get(i).data == children) {
                return childrenList.get(i);
            }
        }
        return null;
    }

    /**
     * Checks to see whether the node has a child of the inputted value
     * @param child (char) the value that you want to check
     * @return (boolean) True: if the node has a child with that value. False if they do not
     */
    public boolean hasChild(char child) {
        for (int i = 0; i < childrenList.size(); i++) {
            if (childrenList.get(i).data == child) {
                return true;
            }
        }
        return false;
    }

    /**
     *Getter method for the data the node contains
     * @return (char) the letter that the node contains
     */
    public char value () {
        return this.data;

    }

    @Override
    public Iterator<LexiconNode> iterator() {
        return childrenList.iterator();
    }

    public String toString(){
        String toString = "";
        toString += this.data;
        return toString;
    }
}