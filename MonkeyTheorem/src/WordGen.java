import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *contains main class in which the user can enter in a text file of the content they want processed
 * they will also enter in k which represents the length at which substrings will be taken
 * level-k analysis determines the probability with which each character
 * follows every possible sequence of characters of length k
 * utilizes the SequenceTable & FrequencyMap classes
 */
public class WordGen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file to read \n");
        String file =  scanner.next();
        System.out.print("Enter desired value of k ");
        int k = scanner.nextInt();
        String cont = readFileAsString(file);
        //creates SequenceTable called monkey
        SequenceTable monkey = new SequenceTable();
        //creates a new string with the information prepended
        String new_cont = cont.substring(cont.length()-k,cont.length()) + cont.substring(0, cont.length()-k);
        //adds values into the monkey SequenceTable
        for (int i = k; i<new_cont.length(); i++){
            String current_substring = new_cont.substring(i-k, i);
            monkey.createSeq(current_substring, new_cont.charAt(i));
        }
        //starts analyzes the content from index 0 rather than prepending
        //mon represents first k length substring
        String mon = cont.substring(0,k);
        String k_char = cont.substring(0,k);
        int initial = 0;
        int end = k;
        //generates 500 characters
        while (mon.length()<= 500) {
            mon += monkey.getChar(k_char);
            initial++;
            end++;
            k_char = mon.substring(initial, end);

        }
        System.out.println(mon);
    }
    /**
            * Read the contents of a file into a string. If the file does not
            * * exist or cannot not be read for any reason, returns null.
            *
            * @param filename The name of the file to read.
            * @return The contents of the file as a string, or null.
            */
    private static String readFileAsString(String filename) {
        try {
            return Files.readString(Paths.get(filename));
        } catch (IOException e) {
            return null;
        }
    }

}
