import javax.swing.JOptionPane;
/* Guess the number game frontend page
 * using "GameLogic" to check for game rolls and logic behind the game
 * */
public class GameApp {
    public static void main(String[] args) {
        boolean playAgain;
        GameLogic game = new GameLogic();
        do {

            game.resetGame();

            while (true) {
                //Display history and prompt user for a guess
                String guess = JOptionPane.showInputDialog(null, game.getHistory() + "\nEnter your 4-digit guess:" + game.getAttempts());

                // If the user cancels the game
                if (guess == null) {
                    JOptionPane.showMessageDialog(null, "Game canceled.");
                    return;
                }
                // Check for valid guess
                if (!GameLogic.validGuess(guess)) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Enter a 4-digit number without duplicate digits.");
                    continue;
                }

                String result = game.checkGuess(guess);
                // End of game guessed the correct number
                if (game.isCorrectGuess(guess)) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the number " + game.getGameNumber() + " in " + game.getAttempts() + " attempts.");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, result);
                }
            }
            // Play again function
            int response = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            playAgain = (response == JOptionPane.YES_OPTION);
        } while (playAgain);
    }
}