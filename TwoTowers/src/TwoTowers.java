import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 *given n uniquely sized cubic blocks, where each block has a face area (not
 * side length) of 1 to n square units. In other words, each block k has a face area of k square units
 * and a side length of √k units. Your goal is simple: you want to use all n blocks to build two towers
 * such that the heights of the towers are as close as possible.
 *
 * Program utilizes SubsetIterator in order to answer these questions:
 * 1. The optimal tower height (i.e., the height of each tower if they were exactly equal in height).
 * 2. In the best possible tower configuration, the subset of blocks making up the shorter tower
 * (the taller tower would simply be the rest of the blocks).
 * 3. The height of the (shorter) tower represented by the above subset. This height should be
 * close to, but not larger than, the optimal tower height.
 * 4. The difference between the height of the best shorter tower and the optimal tower height.
 * 5. The clock time (i.e., actual real-world time) taken to solve the problem, in milliseconds. Note
 * that this duration may vary from run to run or machine to machine
 */
public class TwoTowers {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //asks user to enter the height of the tower
        System.out.println("Enter number of blocks: ");
        int numBlocks = scan.nextInt();

        //starts calculating runtime
        long startTime = System.currentTimeMillis();

        //creates an ArrayList & loops through numbers 1-> numBlocks to store side lengths of each block
        List srSideLengths = new ArrayList();
        for (int i = 1; i<numBlocks +1; i++ ){
            double sideLength = Math.sqrt(i);
            srSideLengths.add(sideLength);
        }

        //creates the height using the side lengths
        double height = convertTotalHeight(srSideLengths);

        //this would be considered the optimal height
        double tallestTower = height/2;

        //these variables will store the best height and subset that is closest to the optimal height
        double bestShorterTowerHeight = 0;
        List<Double> bestShorterTower = new ArrayList();

        //creates SubsetIterator to iterate through all the combinations of heights and generate subsets
        SubsetIterator<Double> iterator = new SubsetIterator<>(srSideLengths);
        while (iterator.hasNext()) {

            //stores subset information because .next() always generates the next one every time it's called
            List subset = iterator.next();
            double heightOfSubset = convertTotalHeight(subset);
            if (heightOfSubset > bestShorterTowerHeight && heightOfSubset<tallestTower){
                    bestShorterTower = subset;
                    bestShorterTowerHeight = heightOfSubset;
            }
        }



        //converts the subset (that's square rooted) back
        String shorterTowerString = "";
        for (int i = 0; i< bestShorterTower.size(); i++){
            double sideAsADouble = bestShorterTower.get(i)*bestShorterTower.get(i);
            long side = Math.round(sideAsADouble);
            shorterTowerString += side + " ";
        }
        double differenceInHeight = tallestTower - bestShorterTowerHeight;

        //stops calculating runtime and generates the duration
        long duration = System.currentTimeMillis() - startTime;

        //prints answers to all the questions
        System.out.println("Target (optimal) height: " + tallestTower);
        System.out.println("Best Subset " + shorterTowerString);
        System.out.println("Best Height: " + bestShorterTowerHeight);
        System.out.println("Distance from optimal: " + differenceInHeight);
        System.out.println("Solve duration: " + duration + " ms");


    }

    /**
     * converts a list of heights (one subset) and converts it to it's total height
     * @param items list of heights
     * @return double (represent the total height of the given list of heights)
     */

    public static double convertTotalHeight(List items){
        double height = 0;
        for (int i = 0; i< items.size(); i++){
            double sideLength = (double)items.get(i);
            height += sideLength;
        }
        return height;
    }

}
