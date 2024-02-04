import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalRounds = 0;
        int totalAttempts = 0;

        while (true) {
            System.out.println("Round " + (totalRounds + 1) + ": New mystery number has been chosen between 1 and 100");
            int secretNumber = (int) (Math.random() * 100) + 1;

            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < 10) {
                System.out.print("Enter your guess (attempt " + (attempts + 1) + "/10): ");
                int userGuess = scanner.nextInt();

                if (userGuess == secretNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    totalAttempts += attempts + 1;  // Include the successful attempt
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                attempts++;
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you're out of attempts. The correct number was " + secretNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }

            if (guessedCorrectly) {
                totalRounds++;
            }
        }

        if (totalRounds > 0) {
            System.out.println("Game Over. You played " + totalRounds + " rounds, and your average attempts per round was " + (double) totalAttempts / totalRounds);
        } else {
            System.out.println("Game Over. You played 0 rounds, and your average attempts per round is not applicable.");
        }

        scanner.close();
    }
}
