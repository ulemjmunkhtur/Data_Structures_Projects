import java.util.HashMap;

/**
 * creates a SequenceTable with a k length substring linked to FrequencyMap
 * contains all the FrequencyMap objects
 * methods: createSeq(), getChar()
 */
public class SequenceTable {
    HashMap<String, FrequencyMap> seq;





    /**
     *constructor of SequenceTable
     * links string to a frequency map
     */
    public SequenceTable() {
        seq = new HashMap<>();

    }

    /**
     * establishes a sequence table linking a k length substring to a frequency map
     *  the frequency map has the characters and frequency of said character that appeared after a k-length sequence
     * @param current_substring takes k length
     * @param nextChar char that follows k length sequence
     */
    public void createSeq(String current_substring, char nextChar){
            FrequencyMap prob = new FrequencyMap();
            if (seq.containsKey(current_substring)) {
                //update the frequency map for current_substring
                seq.get(current_substring).addFreq(nextChar);
            }
            else {
                //adds nextChar to prob and then establishes the sequence table
                prob.addFreq(nextChar);
                seq.put(current_substring,prob);
                }

        }

    /**
     * generates char using generateChar() from FrequencyTable class
     * should not return e if working properly
     * @param current_substring substring of k length. should be a key in
     * @return a randomly generated character from FrequencyMap
     */
    public char getChar(String current_substring){
        if (seq.containsKey(current_substring)){
            return seq.get(current_substring).generateChar();
        }
        return 'e'; //if working properly, should never get to this point


    }
}
