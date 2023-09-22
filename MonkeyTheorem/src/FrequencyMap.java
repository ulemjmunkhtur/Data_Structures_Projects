import java.util.HashMap;
import java.util.Random;

/**
 * class (FrequencyMap) has two methods (addFreq & generateChar)
 *stores the frequency with which various characters follow a specific length-k-character sequence
 * methods: generateChar(), addFreq()
 */
public class FrequencyMap {

    private HashMap<Character, Integer> freq;
    private int total_number;

    /**
     * adds +1 to char a's value
     * if char a did not previously exist as a key you would put both the key (char a) and value (1)
     * if it did, you would update the value (the frequency) to add 1
     * @param a character that is added to the frequency table
     */
    public void addFreq(char a) {
        if (freq.get(a) != null) {
            int x = freq.get(a);
            freq.put(a, x + 1);

        } else {
            freq.put(a, 1);
        }
        total_number += 1;
    }

    /**
     *constructor of FrequencyMap
     * consisting of a Hashmap linking the next char of the sequence (character) to it's frequency (int)
     */
    public FrequencyMap() {
        freq = new HashMap<>();
    }

    /**
     * generates a new character based on the probabilities stored in FrequencyMap
     * @return randomly generated char meant to follow k length sequence
     */
    public char generateChar() {
        Random rand = new Random();
        //     generates a random number between origin and total_number's
        int rand_index = rand.nextInt(0, total_number+1 );
        int current_num = 0;
        //loops through all the keys and selects the key at the rand index's frequency
        //ex. if total number is 5 and the FrequencyMap holds the values t:2, h:4, j:4, it would select h
        //
        for (char key : freq.keySet()) {
            if (current_num == 0) {
                current_num = freq.get(key);
            }
            if (current_num + freq.get(key) > rand_index) {
                return key;

            }
            current_num += freq.get(key);
        }
        return ' ';

    }
}
