import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * This class is an iterator that takes in a List of T type and is able to iterate through all the potential subsets
 * totalNumSubset is the total number of subsets that the List of n size can have.
 * ex. [a, b, c] would have the subsets  [], [a], [b], [c], [a, b], [a, c], [b, c], [a, b, c].
 * @param <T> of T type, this is the type of the List that is being used to create subsets
 */
public class SubsetIterator<T> implements Iterator<List<T>> {

    private List<T> items;

    private long totalNumSubset;
    private long currSubset;

    /**
     * This is the constructor for the SubsetIterator class.
     * @param items List of T type
     */
    public SubsetIterator(List<T> items) {
        this.items = items;
        this.currSubset = 0;
        //
        this.totalNumSubset = 1L << items.size(); //would be 2 to the power of the size
    }

    /**
     * Checks to see whether there are more possible subsets
     * @return bool
     * True: there are subsets
     * False: there are not
     */


    @Override
    public boolean hasNext() {
        return currSubset < totalNumSubset;
    }

    /**
     * This produces and returns the next subset.
     * This is by representing a subset of n list indices using an n-bit binary number
     * each bit represents the exclusion or inclusion of the corresponding index as a 0 or 1, respectively
     * Uses these facts in order to produce the subset.
     * @return oneSubset (ArrayList) holding the next combination/subset.
     */
    @Override
    public List<T> next() {
        List<T> oneSubset = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            //uses the left shift operator (<<) to shift i 1 place to the left
            //uses bitwise and operator(&) to determine whether that index would be included in the subset
            if ((currSubset & (1L << i)) != 0) {
                oneSubset.add(items.get(i));
            }
        }


        currSubset++;
        return oneSubset;
    }

//    public static void main(String[] args) {
//        List<Integer> items = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            items.add(i);
//        }
//        SubsetIterator<Integer> iterator = new SubsetIterator<>(items);
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//    }
}
