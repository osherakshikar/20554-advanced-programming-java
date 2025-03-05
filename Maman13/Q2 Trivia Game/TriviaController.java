
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TriviaController {

    private TriviaGameLogic game;
    private Question currentQuestion;
    private QuestionPool pool;



    @FXML
    private ToggleGroup buttonGroup;

    @FXML
    private Label questionLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private RadioButton firstAnswer;

    @FXML
    private RadioButton secondAnswer;

    @FXML
    private RadioButton thirdAnswer;

    @FXML
    private RadioButton fourthAnswer;

    @FXML
    void endPressed(ActionEvent event) {
        showEndGameAlert();
        Stage stage = (Stage) questionLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void firstAnswerSelected(ActionEvent event) {
        handleAnswerSelection(firstAnswer.getText());
    }

    @FXML
    void secondAnswerSelected(ActionEvent event) {
        handleAnswerSelection(secondAnswer.getText());
    }

    @FXML
    void thirdAnswerSelected(ActionEvent event) {
        handleAnswerSelection(thirdAnswer.getText());
    }

    @FXML
    void fourthAnswerSelected(ActionEvent event) {
        handleAnswerSelection(fourthAnswer.getText());
    }

    @FXML
    void nextPressed(ActionEvent event) {
        loadNextQuestion();
    }
    @FXML
    void restartPressed(ActionEvent event) {
        initialize();
    }


    public void initialize() {
        pool = new QuestionPool();
        game = new TriviaGameLogic(pool);
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        currentQuestion = game.getNextQuestion();
        buttonGroup.selectToggle(null);
        enableAnswerButtons();
        if (currentQuestion != null) {
            String[] allAnswers = currentQuestion.getAllAnswers();
            List<String> answersList = Arrays.asList(allAnswers);
            Collections.shuffle(answersList);
            firstAnswer.setText(answersList.get(0));
            secondAnswer.setText(answersList.get(1));
            thirdAnswer.setText(answersList.get(2));
            fourthAnswer.setText(answersList.get(3));


            questionLabel.setText(currentQuestion.getQuestion());
        } else {
            System.out.println("No more questions available or pool is empty.");
            showEndGameAlert();
        }
    }

    private void handleAnswerSelection(String selectedAnswer) {

        game.answerQuestion(currentQuestion, selectedAnswer);
        showAnswerFeedback(selectedAnswer);
        disableAnswerButtons();
    }

    private void disableAnswerButtons() {
        firstAnswer.setDisable(true);
        secondAnswer.setDisable(true);
        thirdAnswer.setDisable(true);
        fourthAnswer.setDisable(true);
    }

    private void enableAnswerButtons() {
        firstAnswer.setDisable(false);
        secondAnswer.setDisable(false);
        thirdAnswer.setDisable(false);
        fourthAnswer.setDisable(false);
    }

    private void showAnswerFeedback(String selectedAnswer) {
        String feedback = selectedAnswer.equals(currentQuestion.getCorrectAnswer()) ? "Correct!" : "Wrong!";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Answer Feedback");
        alert.setHeaderText(null);
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            alert.setContentText(feedback);
        }
        else {
            alert.setContentText(feedback + " The correct answer is: " + currentQuestion.getCorrectAnswer());
        }
        alert.showAndWait();
    }

    private void showEndGameAlert() {
        updateScoreLabel();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Your final score is: " + game.getScore());
        alert.showAndWait();

    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + game.getScore());
    }
}
