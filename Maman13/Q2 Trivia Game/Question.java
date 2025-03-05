/**
 * The Question class represents a single trivia question.
 * It stores the question text, correct answer, and incorrect answers.
 */
public class Question {
    private String question;
    private String correctAnswer;
    private String[] incorrectAnswers;
    //Constructor for creating a Question object
    public Question(String question, String correctAnswer, String... incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }
    //Retrieves the question text.
    public String getQuestion() {
        return question;
    }
    //Retrieves the correct answer.
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    //Combines the correct answer and incorrect answers into one array.
    public String[] getAllAnswers() {
        String[] allAnswers = new String[incorrectAnswers.length + 1];
        allAnswers[0] = correctAnswer;
        System.arraycopy(incorrectAnswers, 0, allAnswers, 1, incorrectAnswers.length);
        return allAnswers;
    }
}
