import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.io.File;
import java.util.Scanner;
import java.util.HashSet;

/**
 *Builds a trie made up of LexiconNodes that contains a working Lexicon. This implements the Lexicon interface.
 *The lexicon allows you to: add words (addWord()), remove words (removeWord()),
 * check to see if words or prefixes exist (containsWord(), containsPrefix()), add words from a file (addWordsFromFile),
 * provides spelling corrections (suggestCorrections()), and use regex's (matchRegex).
 *
 */
public class LexiconTrie implements Lexicon {
    private LexiconNode root;
    private int numWords;

    public LexiconTrie() {
        root = new LexiconNode(' ');

    }
    @Override
    public boolean addWord(String word) {
        //if it already contains the word, then return false
        if (containsWord(word)) {
            return false;
        }
        LexiconNode curr = root;

        //traversing through all the characters of the word
        for (int i = 0; i < word.length(); i++) {
            LexiconNode child = curr.child(word.charAt(i));
            if (child == null){
                LexiconNode newLetter = new LexiconNode(word.charAt(i));
                curr.addLetter(newLetter);
                curr = newLetter;
            }
            else{
                curr = child;
            }
        }
        numWords++;
        curr.setWord(true);
        return true;
    }
    @Override
    public int addWordsFromFile (String filename){
        File file = new File(filename);

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String word = scan.next();
                addWord(word.toLowerCase());
            }
            scan.close();

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Unable to read file");
        }
        return numWords();
    }


    @Override
    public boolean removeWord (String word){
        LexiconNode curr = root;
        if (containsWord(word)){
            //traverses through the lexicon to reach the last node of the word you want to remove
            for (int i = 0; i < word.length(); i++) {
                LexiconNode child = curr.child(word.charAt(i));
                curr = child;
            }
            //sets the node to false (so it wouldn't count as a word).
            curr.setWord(false);
            //reduces the number of words in the lexicon
            numWords--;
            return true;

        }
        return false;

    }
    @Override
    public int numWords () {
        return numWords;
    }

    @Override
    public boolean containsWord (String word){
        return containsWordHelper(word, root);

    }

    /**
     *Helper method that allows the class to recursively check if the Lexicon contains a word
     * @param word (String) the word you want to check exists or not
     * @param curr (LexiconNode) the node that is recursively being updated. begins at the root.
     * @return (boolean) whether the word exists within the Lexicon
     */
    public boolean containsWordHelper(String word, LexiconNode curr){
        if (word.length() == 0) {
            //ensures that the node we end at is considered a full word (rather than a prefix).
            if (curr.isItAWord() == true) {
                return true;
            } else {
                return false;
            }
        }
        while (curr.hasChild(word.charAt(0))) {
            LexiconNode save = curr.child(word.charAt(0));
            curr = save;
            String newWord = word.substring(1, word.length());
            return containsWordHelper(newWord, curr);
        }
        return false;

    }

    @Override
    public boolean containsPrefix(String prefix) {
        return containsPrefixHelper(prefix, root);
    }

    /**
     *Helper method that allows the class to recursively check if the Lexicon contains a prefix
     * @param prefix (String) the prefix you want to check exists or not
     * @param curr (LexiconNode) the node that is recursively being updated. begins at the root.
     * @return (boolean) whether the prefix exists within the Lexicon
     */
    public boolean containsPrefixHelper(String prefix, LexiconNode curr) {
        //base case. once the string is empty, you know it's gone through all necessary tests
        if (prefix.length() == 0) {
            return true;
        }
        //checks whether the current node has the next character in its children list & continues
        while (curr.hasChild(prefix.charAt(0))) {
            LexiconNode save = curr.child(prefix.charAt(0));
            curr = save;
            String newWord = prefix.substring(1, prefix.length());
            return containsPrefixHelper(newWord, curr);
        }
        return false;
    }

    @Override
    public Iterator<String> iterator() {

        ArrayList<String> wordList = new ArrayList<String>();
        String s = "";
        iteratorHelper(wordList, s, root);
        Iterator<String> iterator = wordList.iterator();
        return iterator;
    }

    /**
     *Helper that allows recursion
     * @param wordList
     * @param s
     * @param curr
     */
    public void iteratorHelper(ArrayList<String> wordList, String s, LexiconNode curr){
        s += curr.value();
        if (curr.isItAWord()){
            wordList.add(s);
        }
        for (LexiconNode child: curr.getChildren()){
            iteratorHelper(wordList, s, child);
        }
    }

    @Override
    public Set<String> suggestCorrections(String target, int maxDistance) {
        Set<String> allSuggestions = new HashSet<String>();
        String prefix = "";
        suggestCorrectionsHelper(target, maxDistance, 0,  prefix, allSuggestions, root);
        return allSuggestions;
    }

    /**
     *Helper for suggestCorrections method.
     * @param target (String) (the word that you're trying to provide suggestions for) -- same as the one put in the suggestCorrections method
     * @param maxDistance (int) max deviation from target word -- same as the one put in the suggestCorrections method
     * @param distance (int) tracking the amount of deviation, begins at 0.
     * @param prefix (String) starts building up possible suggestions. if conditions met, will be added to allSuggestions. begins with empty string.
     * @param allSuggestions (Set<String>) all possible words within the maxDistance from the target. begins with empty set
     * @param curr (LexiconNode) begins with the root. needs to be updated as you recurse
     */

    public void suggestCorrectionsHelper(String target, int maxDistance, int distance, String prefix, Set<String> allSuggestions, LexiconNode curr){
        //if the distance/deviation from the target word is above the preset maxDistance then you return
        //not a word that would be counted/recommended. path is eliminated
        if (distance>maxDistance){
            return;
        }
        //if the length of the suggested word & the target word is the same. since you're adding to the prefix this point will eventually be reached
        if (prefix.length() == target.length()){
            //if it is a word then it gets added
            if (curr.isItAWord()){
                allSuggestions.add(prefix);
            }
            //must return or index errors (target.charAt() would overflow)
            return;
        }
        for (LexiconNode child: curr.getChildren()){
            //checking if the child's value is the same as the target's character.
            if (child.value() == target.charAt(prefix.length())){
                //if so then it keeps going down that path without adding to the distance
                suggestCorrectionsHelper(target,  maxDistance, distance,  prefix+child.value(), allSuggestions, child);
            }
            else{
                //else it adds to the distance because it would deviate, but it would still check all possibilities until it's deviation is more than max distance.
                suggestCorrectionsHelper(target,  maxDistance, distance+1, prefix+child.value(), allSuggestions, child);
            }

        }

    }

    @Override
    public Set<String> matchRegex(String pattern) {
        Set<String> regexList = new HashSet<String>();
        String prefix = "";
        matchRegexHelper(pattern, prefix, regexList, root);
        return regexList;
    }

    /**
     *Helper for the matchRegex method.
     * @param pattern (String) same one inputted in matchRegex. the pattern that would set the rule for possible words
     * @param prefix consisting of all the letters from the root to the current node, and that is
     * assumed to have matched some or all of the leading characters of the original pattern string
     * @param regexList (Set<String>). set where the words matching the pattern are put. begins empty
     * @param curr (LexiconNode) begins at the root. updated as recursion is taking place.
     */

    public void matchRegexHelper(String pattern, String prefix,  Set<String> regexList, LexiconNode curr){
        if (pattern.isEmpty()){
            if (curr.isItAWord()){
                regexList.add(prefix);
            }
            return;
        }
        if (pattern.charAt(0) == '_'){
            pattern = pattern.substring(1);

            //for loops every single child and adds its value to the prefix, deletes the _ from pattern
            for (LexiconNode child: curr.getChildren()) {
                matchRegexHelper(pattern, prefix + child.value(), regexList, child);
            }
            return;
        }
        if (pattern.charAt(0) == '?'){ //zero or one
            pattern = pattern.substring(1);

            //continues on with the same prefix and the same curr so the question mark didn't exist
            //accounts for zero characters added because of question mark (because same curr)
            matchRegexHelper(pattern, prefix, regexList, curr);

            //adds one character to the prefix and keeps going. same as _
            for (LexiconNode child: curr.getChildren()) {
                matchRegexHelper(pattern, prefix + child.value(), regexList, child);
            }
            return;
        }
        if (pattern.charAt(0)=='*') {
            //star continues when there is no more children. does not delete anything from the pattern, so it can add
            // any amount of characters. only stops when it runs out of children
            for (LexiconNode child : curr.getChildren()) {
                matchRegexHelper(pattern, prefix + child.value(), regexList, child);
            }
            //star ends. it stops because we delete the star (and stops the infinite loop).
            //0 characters being added
            pattern = pattern.substring(1);
            matchRegexHelper(pattern, prefix, regexList, curr);

            return;
        }
        //this is for regular letter.
        for (LexiconNode child : curr.getChildren()) {
            //if the value of the child is the value of the letter in the pattern then you go down that path
            if (child.value() == pattern.charAt(0)){
                pattern = pattern.substring(1);
                matchRegexHelper(pattern, prefix + child.value(), regexList, child);
                return;

            }
            }
    }
}