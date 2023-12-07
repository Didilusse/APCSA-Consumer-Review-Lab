import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 * (method removePunctuation() was added from teacher code)
 **/
public class Review {

    private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
    private static ArrayList<String> posAdjectives = new ArrayList<String>();
    private static ArrayList<String> negAdjectives = new ArrayList<String>();

    private static final String SPACE = " ";

    static {
        try {
            Scanner input = new Scanner(new File("cleanSentiment.csv"));
            while (input.hasNextLine()) {
                String[] temp = input.nextLine().split(",");
                sentiment.put(temp[0], Double.parseDouble(temp[1]));
                // System.out.println("added "+ temp[0]+", "+temp[1]);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading or parsing cleanSentiment.csv");
        }

        // read in the positive adjectives in postiveAdjectives.txt
        try {
            Scanner input = new Scanner(new File("positiveAdjectives.txt"));
            while (input.hasNextLine()) {
                String temp = input.nextLine().trim();
                // System.out.println(temp);
                posAdjectives.add(temp);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading or parsing "
                    + "postitiveAdjectives.txt\n" + e);
        }

        // read in the negative adjectives in negativeAdjectives.txt
        try {
            Scanner input = new Scanner(new File("negativeAdjectives.txt"));
            while (input.hasNextLine()) {
                negAdjectives.add(input.nextLine().trim());
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading or parsing "
                    + "negativeAdjectives.txt");
        }
    }

    /**
     * returns a string containing all of the text in fileName (including
     * punctuation),
     * with words separated by a single space
     */
    public static String textToString(String fileName) {
        String temp = "";
        try {
            Scanner input = new Scanner(new File(fileName));

            // add 'words' in the file to the string, separated by
            // a single space
            while (input.hasNext()) {
                temp = temp + input.next() + " ";
            }
            input.close();

        } catch (Exception e) {
            System.out.println("Unable to locate " + fileName);
        }
        // make sure to remove any additional space that may have
        // been added at the end of the string.
        return temp.trim();
    }

    /**
     * @returns the sentiment value of word as a number between -1
     *          (very negative) to 1 (very positive sentiment)
     */
    public static double sentimentVal(String word) {
        try {
            return sentiment.get(word.toLowerCase());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Returns the ending punctuation of a string, or the empty string
     * if there is none
     */
    public static String getPunctuation(String word) {
        String punc = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            if (!Character.isLetterOrDigit(word.charAt(i))) {
                punc = punc + word.charAt(i);
            } else {
                return punc;
            }
        }
        return punc;
    }

    /**
     * Returns the word after removing any beginning or ending punctuation
     */
    public static String removePunctuation(String word) {
        while (word.length() > 0 && !Character.isAlphabetic(word.charAt(0))) {
            word = word.substring(1);
        }
        while (word.length() > 0 &&
                !Character.isAlphabetic(word.charAt(word.length() - 1))) {
            word = word.substring(0, word.length() - 1);
        }

        return word;
    }

    /**
     * Randomly picks a positive adjective from the positiveAdjectives.txt
     * file and returns it.
     */
    public static String randomPositiveAdj() {
        int index = (int) (Math.random() * posAdjectives.size());
        return posAdjectives.get(index);
    }

    /**
     * Randomly picks a negative adjective from the negativeAdjectives.txt
     * file and returns it.
     */
    public static String randomNegativeAdj() {
        int index = (int) (Math.random() * negAdjectives.size());
        return negAdjectives.get(index);

    }

    /**
     * Randomly picks a positive or negative adjective and returns it.
     */
    public static String randomAdjective() {
        boolean positive = Math.random() < .5;
        if (positive) {
            return randomPositiveAdj();
        } else {
            return randomNegativeAdj();
        }
    }

    /**
     * Activity 2: totalSentiment()
     * Write the code to total up the sentimentVals of each word in a review.
     */
    public static double totalSentiment(String filename) {
        // Read in the file contents into a string using the

        String review = textToString(filename);

        // Set up a sentimentTotal variable to store the cumulative sentiment value
        double sentimentTotal = 0;

        // Initialize indices to traverse the review string
        int startIndex = 0;
        int spaceIndex = review.indexOf(" ");

        // Loop through the words in the review
        while (spaceIndex != -1) {
            // Extract the current word from the review string
            String word = review.substring(startIndex, spaceIndex);

            // Remove punctuation from the word
            word = removePunctuation(word);

            // Add the sentiment value of the word to the sentimentTotal
            sentimentTotal += sentimentVal(word);

            // Move the start index to the next character after the space
            startIndex = spaceIndex + 1;

            // Find the next space in the review string
            spaceIndex = review.indexOf(" ", startIndex);
        }

        // Return the cumulative sentiment value of the entire review
        return sentimentTotal;
    }

    /**
     * Activity 2 starRating method
     * Write the starRating method here which returns the number of
     * stars for thereview based on its totalSentiment.
     */
    public static int starRating(String filename) {
        // Call the totalSentiment method with the fileName to
        // get the cumulative sentiment value
        double sentimentTotal = totalSentiment(filename);

        // Initialize stars variable to store the rating
        int stars = 0;

        // Determine the number of stars between 0 and 4
        // based on the totalSentiment

        if (sentimentTotal < -10) {
            stars = 0;
        } else if (sentimentTotal < 0) {
            stars = 1;
        } else if (sentimentTotal < 10) {
            stars = 2;
        } else if (sentimentTotal < 20) {
            stars = 3;
        } else {
            stars = 4;
        }

        // Return the number of stars
        return stars;
    }

    // This method generates a fake review with a random adjective.
    public static String fakeReview(String fileName) {
        // Convert the contents of the file to a string
        String review = textToString(fileName);

        // Initialize an empty string to store the fake review
        String fake = "";

        // Loop through each character in the review string
        for (int i = 0; i < review.length() - 1; i++) {
            // Check if the current character is an asterisk (*)
            if (review.substring(i, i + 1).equals("*")) {
                i++; // Move to the next character after the asterisk

                // Initialize an empty string to store the
                // adjective to be replaced
                String replace = "";
                boolean isWord = true;

                // Loop to capture the entire adjective marked by asterisks
                while (isWord) {
                    replace += review.substring(i, i + 1);
                    i++;

                    // Check if the next character is a space,
                    // indicating the end of the adjective
                    if (review.substring(i, i + 1).equals(" ")) {
                        isWord = false;
                    }
                }

                // Remove punctuation from the captured adjective
                replace = replace.replaceAll("\\p{Punct}", "");

                // Check the sentiment value of the adjective and
                // replace it accordingly
                if (sentimentVal(replace) > 0) {
                    replace = randomPositiveAdj() + " ";
                } else if (sentimentVal(replace) < 0) {
                    replace = randomNegativeAdj() + " ";
                } else {
                    replace = randomAdjective() + " ";
                }

                // Append the replaced adjective to the fake review
                fake += replace;
            } else {
                // If the current character is not an asterisk,
                // append it to the fake review
                fake += review.substring(i, i + 1);
            }
        }

        // Return the generated fake review
        return fake;
    }

}
