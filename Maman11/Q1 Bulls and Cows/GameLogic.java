import java.util.ArrayList;
import java.util.Random;
import java.util.List;
/* Guess the number game backend page
 * Manages the game logic, including number generation, guess checking, and history tracking.
 * */
public class GameLogic {

    private String GAME_NUMBER;// The number to guess
    private final int NUMBER_LENGTH = 4;
    private final int NUMBER_OF_DIGITS = 10;
    private final List<String> HISTORY;// Stores previous guesses and results
    private int  attempts = 0;
    // Constructor to start a new game
    public GameLogic() {
        GAME_NUMBER = generateUniqueNumber();
        this.HISTORY = new ArrayList<>();
    }
    // Method to generate four-digit number
    private String generateUniqueNumber() {
        Random rand = new Random();
        ArrayList<Integer> digits = new ArrayList<Integer>();
        String number = "";

        while (number.length() <NUMBER_LENGTH) {
            int localnumber = rand.nextInt(NUMBER_OF_DIGITS);
            if (!digits.contains(localnumber)) {
                digits.add(localnumber);
                number += localnumber;
            }
        }
        return number;
    }
    // Checks the users guess against the game number.
    public String checkGuess(String guess) {
        int correct = 0; // Guessed right number at the right place
        int hits = 0; // Guessed right number wrong place
        for(int i = 0; i<NUMBER_LENGTH; i++) {
            if (guess.charAt(i) == GAME_NUMBER.charAt(i)) {
                correct++;
            }
            else if (GAME_NUMBER.contains(String.valueOf(guess.charAt(i)))) {
                hits++;
            }
        }
        attempts++;
        String result = guess + ":" +correct + "Correct," + hits + "Hits";
        HISTORY.add(result);// Add the result to the history
        return result;
    }
    //Returns a formatted string of all previous guesses and their results.
    public String getHistory() {
        StringBuilder sb = new StringBuilder("Previous guesses:\n");
        for (String entry : HISTORY) {
            sb.append(entry).append("\n");
        }
        return sb.toString();
    }
    // Check if the user won
    public boolean isCorrectGuess(String guess) {
        return GAME_NUMBER.equals(guess);
    }
    // Get method for gameNumber
    public String getGameNumber() {
        return GAME_NUMBER;
    }
    // Get method for number of attempts
    public int getAttempts() {
        return attempts;
    }
    // Check if the guessed number is valid
    public static boolean validGuess(String guess) {
        if(guess.length() !=4 || !isNumeric(guess)) {
            return false;
        }
        ArrayList<Character> digits = new ArrayList<Character>();
        for(char c : guess.toCharArray()) {
            if (digits.contains(c)) {
                return false;
            }
            digits.add(c);
        }
        return true;
    }
    // Check if the number is numeric (used in validGuess)
    private static boolean isNumeric(String guess) {
        for (char c : guess.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    // Method to restart the game
    public void resetGame() {
        this.GAME_NUMBER = generateUniqueNumber();
        this.HISTORY.clear();
        this.attempts = 0;
    }

}
