import java.util.HashMap;

/**
 *Holds a Student's information (firstName, lastName, address, phone, email, suBox)
 * Has getter methods to access these private variables
 */
public class Student {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private int suBox;

    /**
     * Constructor for Student class
     *
     * @param firstName student's first name
     * @param lastName  student's last name
     * @param address   student's address
     * @param phone     student's phone number
     * @param email     student's email address
     * @param suBox     student's suBox
     */
    public Student(String firstName, String lastName, String address, String phone, String email, int suBox) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.suBox = suBox;

    }

    /**
     * getter methods for firstName
     * @return first name of the student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter methods for last name
     * @return student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter methods for address
     * @return student's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter method for phone
     * @return student's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * getter method for student's email address
     * @return student's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter methods for suBOX
     * @return student's SUBox
     */
    public int getSuBox() {
        return suBox;
    }

    /**
     * Creates string with student's full name and counts the number of vowels in said name
     * (does not count y as a vowel)
     * loops through the name and if it finds a vowel it adds 1 to the vowelCounter
     * @return int vowelCounter (number of vowels in full name)
     */
    public int countsVowelFullName() {
        //creates FullName string of their full name lowercase
        String FullName = getFirstName().toLowerCase() + getLastName().toLowerCase();
        int vowelCounter = 0;
        for (int i = 0; i < FullName.length(); i++) {
            if (FullName.charAt(i) == 'a' || FullName.charAt(i) == 'e' ||
                    FullName.charAt(i) == 'i' || FullName.charAt(i) == 'o' ||
                    FullName.charAt(i) == 'u') {
                vowelCounter++;
            }

        }
        return vowelCounter;
    }

    /**
     * gets the phone number and creates hashmap of each char appearing in phone number
     * the frequency of chars are stored in a hashmap
     * @return int maxDigit (the most amount of similar chars in phone number)
     */
    public int maxDigits(){
    String phoneNumbers = getPhone();
    HashMap<Character, Integer> digitCounter = new HashMap<>();
        for (int i = 0; i < phoneNumbers.length(); i++){
            if (digitCounter.containsKey(phoneNumbers.charAt(i))){
                digitCounter.put(phoneNumbers.charAt(i), digitCounter.get(phoneNumbers.charAt(i)) + 1);
            }else{
                digitCounter.put(phoneNumbers.charAt(i), 1);
            }


            }
        int maxDigit = 0;
        for (HashMap.Entry<Character,Integer> mapElement : digitCounter.entrySet()){
            if (mapElement.getValue() > maxDigit){
                maxDigit = mapElement.getValue();
            }


        }
        return maxDigit;

    }


    /**
     * toString for Student class
    *@return first name, last name, su box number, phone number, email
     */
        public String toString() {
            String result = firstName + " " + lastName + "," + suBox + "," + phone + "," + email;
            return result;
        }


    }

