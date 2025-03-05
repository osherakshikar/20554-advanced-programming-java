
import java.util.Arrays;

public class HangmanGame {
    private final String selectedWord;
    private final char[] displayedWord;
    private final char[] guessedLetters;
    private int guessedCount; // Keeps track of the number of guessed letters
    private int attemptsLeft;

    public HangmanGame(String selectedWord, int maxAttempts) {
        this.selectedWord = selectedWord.toUpperCase();
        this.displayedWord = new char[selectedWord.length()];
        int i;
        for (i = 0; i < displayedWord.length; i++) {
            displayedWord[i] = '_';
        }
        this.guessedLetters = new char[26]; // Enough to store all unique alphabetic letters
        Arrays.fill(guessedLetters, ' '); // Initialize array with null characters
        this.guessedCount = 0; // No letters guessed at the start
        this.attemptsLeft = maxAttempts;
    }

    public boolean guessLetter(char letter) {
            letter = Character.toUpperCase(letter);
        // Check if the letter has already been guessed
        for (int i = 0; i < guessedCount; i++) {
            if (guessedLetters[i] == letter) {
                return false; // Letter already guessed
            }
        }

        // Add the letter to the guessed letters array
        guessedLetters[guessedCount++] = letter;

        boolean found = false;
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == letter) {
                displayedWord[i] = letter;
                found = true;
            }
        }

        if (!found) {
            attemptsLeft--;
        }
        return found;
    }

    public boolean isGameOver() {
        return attemptsLeft == 0 || isWordGuessed();
    }

    public boolean isWordGuessed() {
        return new String(displayedWord).equals(selectedWord);
    }

    public String getDisplayedWord() {
        return new String(displayedWord);
    }

    public char[] getGuessedLetters() {
        // Return a copy of the guessed letters up to guessedCount
        return Arrays.copyOf(guessedLetters, guessedCount);
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public String getSelectedWord() {
        return selectedWord;
    }
}