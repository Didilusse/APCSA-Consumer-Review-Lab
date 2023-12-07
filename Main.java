public class Main {
    public static void main(String[] args) {
        // Activity 1: Call the sentimentVal method in Review with a word
        // like "terrible" and print out the result
        System.out.println(Review.totalSentiment("simpleReview.txt"));
        System.out.println(Review.starRating("simpleReview.txt"));
        System.out.println(Review.fakeReview("simpleReview.txt"));
        System.out.println();

        // Activity 5 shenanigans
        System.out.println("Welcome to the Simple Java Program!");

        // Call a method with parameters
        int result = addNumbers(5, 7);
        System.out.println("The sum of 5 and 7 is: " + result);

        // Call two distinct methods in the String class
        String inputString = "Hello, World!";
        int stringLength = getStringLength(inputString);
        System.out.println("Length of the input string: " + stringLength);

        String reversedString = reverseString(inputString);
        System.out.println("Reversed string: " + reversedString);

        // checks if number is 10
        int number = 10;
        if (isEven(number)) {
            System.out.println(number + " is an even number.");
        } else {
            System.out.println(number + " is an odd number.");
        }

        // Use iteration to print number 1 to 5
        System.out.println("Printing numbers from 1 to 5 using a loop:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }
    }

    // Method to add two numbers
    private static int addNumbers(int a, int b) {
        return a + b;
    }

    // Method to get the length of a string
    private static int getStringLength(String str) {
        return str.length();
    }

    // Method to reverse a string
    private static String reverseString(String str) {
        int length = str.length();
        String reversed = "";

        for (int i = length - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }

        return reversed;
    }

    // Method to check if a number is even
    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

}