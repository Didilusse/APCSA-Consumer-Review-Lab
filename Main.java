public class Main {
    public static void main(String[] args) 
    {
        // Activity 1: Call the sentimentVal method in Review with a word 
        //     like "terrible" and print out the result
        
        System.out.println(Review.totalSentiment("simpleReview.txt"));
        System.out.println(Review.starRating("simpleReview.txt"));
        System.out.println(Review.fakeReview("simpleReview.txt"));
    
    }
}