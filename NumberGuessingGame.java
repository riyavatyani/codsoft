import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        boolean playAgain = true; 
        int totalRounds = 0; 
        int totalAttempts = 0;
        int maxAttempts = 5; 

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; 
            int attempts = 0; 
            boolean guessedCorrectly = false; 
            
            System.out.println("\n Round " + (totalRounds + 1) + ": Try to guess the number between 1 and 100.");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print(" Enter your guess (Attempt " + (attempts + 1) + "): ");
                int userGuess = scanner.nextInt();                 
                attempts++; 
                totalAttempts++;
            
                if (userGuess < 1 || userGuess > 100) {
                    System.out.println(" Please enter a number between 1 and 100.");
                    continue; 
                }

                if (userGuess == numberToGuess) {
                    System.out.println(" Correct! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true; 
                } else if (userGuess < numberToGuess) {
                    System.out.println(" Too low! Try again.");
                } else {
                    System.out.println(" Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" You've used all " + maxAttempts + " attempts! The correct number was: " + numberToGuess);
            }

            totalRounds++;
            System.out.print("\n Do you want to play again? (yes/no): ");
            scanner.nextLine(); 
            String playChoice = scanner.nextLine().trim().toLowerCase();

           
            if (!playChoice.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\n Game Over! You played " + totalRounds + " rounds and took " + totalAttempts + " total attempts.");
        
        scanner.close(); 
    }
}
