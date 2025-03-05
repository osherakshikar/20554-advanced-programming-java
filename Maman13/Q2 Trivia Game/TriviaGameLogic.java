
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The TriviaGameLogic class manages the core game logic for a trivia game.
 * It retrieves questions from a pool, tracks the score, and ensures no question repeats.
 */
public class TriviaGameLogic {
    private List<Question> questionPool;
    private int score;
    private Random random;
    private List<Question> askedQuestions;

 //Constructor that initializes the game logic.
    public TriviaGameLogic(QuestionPool pool) {
        this.questionPool = pool.getQuestions();
        this.score = 0;
        this.random = new Random();
        this.askedQuestions = new ArrayList<>();
    }
    //Retrieves the next random question that has not been asked yet.
    public Question getNextQuestion() {
        if (askedQuestions.size() < questionPool.size()) {
            Question nextQuestion;
            do {
                nextQuestion = questionPool.get(random.nextInt(questionPool.size()));
            } while (askedQuestions.contains(nextQuestion));
            askedQuestions.add(nextQuestion);
            return nextQuestion;
        } else {
            return null; // No more questions
        }
    }
    // Checks the player's selected answer and updates the score accordingly
    public void answerQuestion(Question question, String selectedAnswer) {
        if (selectedAnswer.equals(question.getCorrectAnswer())) {
            score += 10;
        } else if(score ==0) ;
          else  score -= 5;

    }
    //Retrieves the player's current score.
    public int getScore() {
        return score;
    }
}
