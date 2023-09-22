import java.util.Comparator;

/**
 * SortableArrayList extends the SimpleArrayList class in order to create a sort method that take in comparators.
 * the sort() method uses selection sort to sort the data entered in
 * It allows users to create a sortable ArrayList and sort through the data
 * @param <T> generic type allowing users to put in diff types of data
 */
public class SortableArrayList<T> extends SimpleArrayList<T> {
    private static final int DEFAULT_START_CAPACITY = 10;

    /**
     * Constructor for class
     * Calls SimpleArrayList class
     * @param startingCapacity (size of array)
     */
    public SortableArrayList(int startingCapacity) {
        super(startingCapacity);
    }

    /**
     * If starting capacity is not given, constructor for the class
     * Calls SimpleArrayList to create SortableArrayList
     */
    public SortableArrayList() {
        super(DEFAULT_START_CAPACITY);
    }

    /**
     *sort() uses selection sort to allow you to sort through any T data type
     * @param c the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     */
    public void sort(Comparator<? super T> c) {
        for (int lastIndexUnsorted = this.size() - 1; lastIndexUnsorted>0; lastIndexUnsorted--){
            int biggest = 0;
            //determines the largest value in the unsorted part of the list
            for (int i = 1; i<= lastIndexUnsorted; i++){
                // use the comparator to determine which element is bigger
                if (c.compare(this.get(i), this.get(biggest)) > 0) {
                    biggest = i;
                }
            }
            // swap the biggest element with the last unsorted element
            T temporary = this.get(lastIndexUnsorted);
            this.set(lastIndexUnsorted, this.get(biggest));
            this.set(biggest, temporary);
                }
            }
    /**
     * toString method
     * Presents the SortableArrayList in a visual manner.
     * @return visual representation of SortableArrayList.
     */
    public String toString() {
        String end = "";
        for (int i = 0; i < this.size(); i++) {
            end+=this.get(i)+ "\n";
        }
        return end;
    }
        }

