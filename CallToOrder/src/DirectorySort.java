import java.io.File;
import java.util.Comparator;
import java.util.Scanner;

/**
 *Overall purpose of the project is to read data from StudentDirectory file
 * Puts data into a SortableArrayList with data (first name, last name, SU box, address, etc)
 * Sorts and identifies different things (ex. Largest SUBOx) using comparators
 * Comparators: suBoxComparator, lastNameComparator, vowelComparator
 * author: ulemj
 * class: CS2201
 */
public class DirectorySort {

    public static void main(String[] args) {
        //asks user to enter file name (would be directory.txt)
        System.out.println("Enter a file name ");
        Scanner scan = new Scanner(System.in);
        String someFilename = scan.nextLine();

        //calls readTheFile to check if valid file and create SortableArrayList
        SortableArrayList studentInfo = readTheFile(someFilename);

        //sorts by suBox
        studentInfo.sort(new suBoxComparator());

        //prints student info of smallest suBox
        System.out.println("a " + studentInfo.get(0));

        //prints student info of largest suBox
        System.out.println("b " + studentInfo.get(studentInfo.size() - 1));

        //sorts by last name
        studentInfo.sort(new lastNameComparator());

        //prints student info of alphabetically first student (by last name)
        System.out.println("c " + studentInfo.get(0));

        //prints student info of alphabetically last student (by last name)
        System.out.println("d " + studentInfo.get(studentInfo.size() - 1));

        //sorts by number of vowels present in full name
        studentInfo.sort(new vowelComparator());

        //prints student with most vowels in their full name
        System.out.println("e " + studentInfo.get(studentInfo.size() - 1));

        //prints students with the least vowels in their full name
        System.out.println("f " + studentInfo.get(0));

        //sorts by max number of the same phone number digit
        studentInfo.sort(new phoneComparator());

        //prints student info of biggest number of similar phone number digits
        System.out.println("extra credit " + studentInfo.get(studentInfo.size() - 1));

        }




    /**
     * Reads file and calls Student class to create student (with all attributes)
     * checks to see whether file is valid
     * Students are then added to a SortableArrayList (studentInfo)
     * @param filename file you want read
     * @return created SortableArrayList with Student Objects
     */
        private static SortableArrayList<Student> readTheFile(String filename) {
        SortableArrayList<Student> studentInfo = new SortableArrayList<>();
        Scanner scan = new Scanner(System.in);
        try {

            scan = new Scanner(new File(filename));

        } catch (Exception e) {
            // failed to read file - good idea to print an error and exit/return
            System.out.println("Error");
            return null;
        }

        while (scan.hasNext()) { // while there’s more of the file to read
            String line = scan.nextLine();
            String[] parts = line.split("\\|");
            String[] name = parts[0].split(" ");
            int suBox = Integer.parseInt(parts[4].trim());

            Student create = new Student(name[0], name[1], parts[1], parts[2], parts[3], suBox);
            studentInfo.add(create);

        }

        scan.close(); // done reading the file, close the Scanner
            return studentInfo;


    }


    /**
     *Class that implements comparator class. Has function that compares suBox's
     */
    private static class suBoxComparator implements Comparator<Student> {
        /**
         * Compares suBox's (from Student class)
         * @param a the first object to be compared. (Student)
         * @param b the second object to be compared. (Student)
         * @return int
         * -1 if a<b
         * 0 if a=b
         * 1 if a>b
         */
        @Override
        public int compare(Student a, Student b) {
            if (a.getSuBox() < b.getSuBox()) {
                return -1; // note: magnitude of the result is irrelevant
            } else if (a == b) {
                return 0;
            } else {
                return 1;
            }
        }
    }

        /**
         *class that implements comparator class. has a method which compares last names
         */
        private static class lastNameComparator implements Comparator<Student> {
            /**
             * Compares Student (object) names (terms of alphabetical order)
             * @param a the first object to be compared.
             *  @param b the second object to be compared.
             *@return int
             * -1 (if a's last name is alphabetically before o2)
             *  0 (alphabetically the same)
             * 1 (b's name is before a's)
             */
            @Override
            public int compare(Student a, Student b) {
                //turns student a's last name lower case
                String aLastName = a.getLastName().toLowerCase();
                //turns student b's last name lower case
                String bLastName = b.getLastName().toLowerCase();
                //returns the comparison of last names (-1, 0, 1)
                return aLastName.compareTo(bLastName);
            }


        }


        /**
         *class that implements comparator class.
         * has a method which compares number of vowels in full name
         */
        private static class vowelComparator implements Comparator<Student> {
            /**
             * Compares the number of vowels in student's full names
             * calls countsVowelFullName() method from student class
             * @param a the first object to be compared.
             * @param b the second object to be compared.
             * @return int
             * -1 (student a's vowel counter is less than b's)
             * 0 (student a's vowel counter is same as b's)
             * 1 (student b's vowel counter is less than a's)
             */
            @Override
            public int compare(Student a, Student b) {
                int aVowelCounter = a.countsVowelFullName();
                int bVowelCounter = b.countsVowelFullName();
                return Integer.compare(aVowelCounter, bVowelCounter);

            }

            }

    /**
     * class that implements comparator class
     * has class that compares max digits from phone numbers
     */
        private static class phoneComparator implements Comparator<Student> {
        /**
         *figures out which student has the most similar amount of digits in their phone number
         * calls maxDigits() method from student class
         * @param a the first object to be compared.
         * @param b the second object to be compared.
         * @return int
         * -1 (student a's max digit is less than b's)
         * 0 (student a's max digit is same as b's)
         *   1 (student b's max digit is less than a's)
         */

            @Override
            public int compare(Student a, Student b) {
                int aMaxDigit = a.maxDigits();
                int bMaxDigit = b.maxDigits();
                return Integer.compare(aMaxDigit, bMaxDigit);
            }
        }

    }










