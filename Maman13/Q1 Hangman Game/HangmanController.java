
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class HangmanController {

    @FXML
    private Label attemptsLabel;

    @FXML
    private Canvas canv;

    @FXML
    private GridPane grid;

    @FXML
    private Label guessedLettersLabel;

    private Button[] btns;

    private GraphicsContext gc;
    private HangmanGame game;

    final int SIZE = 5;
    private static final int MAX_ATTEMPTS = 6;
    private static final int BASE_Y_POSITION = 400;
    private static final double LETTER_SPACING = 20.0;
    String wordToGuess;


    public void initialize() {
        gc = canv.getGraphicsContext2D();
        newGame();

    }
    @FXML
    void newGamePressed(ActionEvent event) {
        newGame();
    }

    private void newGame() {
        try {
            // Clear the Canvas before starting a new game
            gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
            drawHangmanBase();
            // Game initialization code
            WordRepository wordRepository = new WordRepository();
            wordRepository.readWordFromFile();
            wordToGuess = wordRepository.getRandomWord();
            game = new HangmanGame(wordToGuess, MAX_ATTEMPTS);
            setupKeyboard();
            updateUI();
        } catch (Exception e) {
            attemptsLabel.setText("Error starting new game!");
        }
    }

    private void updateUI() {
        guessedLettersLabel.setText("Guessed Letters: " + Arrays.toString(game.getGuessedLetters()));
        attemptsLabel.setText("Attempts Left: " + game.getAttemptsLeft());
        drawHangman();
    }


    private void drawHangmanBase() {
        {
            gc.strokeLine(50, 250, 150, 250); // Base

            gc.strokeLine(100, 250, 100, 50); // Pole

            gc.strokeLine(100, 50, 200, 50); // Beam

            gc.strokeLine(200, 50, 200, 100); // Rope
        }

    }

    private void drawHangman() {
        int mistakes = MAX_ATTEMPTS - game.getAttemptsLeft();
        switch (mistakes) {
            case 0:
                for (int i = 0; i < game.getDisplayedWord().length(); i++) {
                    gc.strokeLine(i * canv.getWidth() / game.getDisplayedWord().length(), 400, i * canv.getWidth() / game.getDisplayedWord().length() + 20, 400);
                }
                break;
            case 1:
                gc.strokeOval(175, 100, 50, 50); // Head
                break;
            case 2:
                gc.strokeLine(200, 150, 200, 200); // Body
                break;
            case 3:
                gc.strokeLine(200, 160, 170, 190); // Left Arm
                break;
            case 4:
                gc.strokeLine(200, 160, 230, 190); // Right Arm
                break;
            case 5:
                gc.strokeLine(200, 200, 170, 230); // Left Leg
                break;
            case 6:
                gc.strokeLine(200, 200, 230, 230); // Right Leg
                break;
        }

    }

    private void setupKeyboard() {
        grid.getChildren().clear();
        btns = new Button[(SIZE * SIZE) + 1];
        int j = 0;
        for (char i = 'A'; i <= 'Z'; i++) {
            btns[j] = new Button(i + "");
            btns[j].setPrefSize(grid.getPrefWidth() / (SIZE + 1), grid.getPrefHeight() / (SIZE + 1));
            grid.add(btns[j], j % SIZE, j / SIZE);
            btns[j].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    handelButton(event);
                }
            });
            j++;
        }
    }
    private void handelButton(ActionEvent event) {
        Button btn = (Button) event.getSource();
        gc.setFont(javafx.scene.text.Font.font("Arial", 20));
        guessedLettersLabel.setText("Guessed Letters : " + btn.getText());
        handleGuess(btn.getText().charAt(0), btn);
    }



    private void handleGuess(char letter, Button button) {
        button.setDisable(true); // Disable the button after it has been clicked
        boolean correct = game.guessLetter(letter);
        if (correct) {
            drawCorrectLetters(letter);
        }
        updateUI();

        if (game.isGameOver()) {
            endGame(game.isWordGuessed());
        }
    }
    private void drawCorrectLetters(char letter) {
        for (int i = 0; i < game.getDisplayedWord().length(); i++) {
            if (game.getSelectedWord().charAt(i) == letter) {
                // Calculate position for the letter on the canvas
                double x = i * canv.getWidth() / game.getDisplayedWord().length();
                double y = BASE_Y_POSITION - LETTER_SPACING;

                // Draw the letter above the line
                gc.fillText(String.valueOf(letter), x + 5, y);
            }
        }
    }


    private void endGame(boolean won) {
        String message;
        if (won) {
            message = "You won! The word was: ";
        } else {
            message = "You lost! The word was: ";
        }
        attemptsLabel.setText(message + game.getSelectedWord());
        // Disable all buttons in the btns array
        for (Button btn : btns) {
            if (btn != null) {
                btn.setDisable(true);
            }
        }
    }
}