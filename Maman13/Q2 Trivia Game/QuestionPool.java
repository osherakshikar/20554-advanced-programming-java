
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionPool {
    private List<Question> questions;

    public QuestionPool() {
        questions = new ArrayList<>();
        loadQuestionsFromFile();
    }

    private void loadQuestionsFromFile() {

        try {
            Scanner input = new Scanner(new File("trivia.txt"));
            while (input.hasNextLine()) {
                // Read lines for each question
                String question = input.nextLine().trim();
                if (question.isEmpty()) continue; // Skip blank lines
                if (!input.hasNextLine()) break; // Prevent errors if file ends prematurely

                String correctAnswer = input.nextLine().trim();
                if (!input.hasNextLine()) break;
                String wrongAnswer1 = input.nextLine().trim();
                if (!input.hasNextLine()) break;
                String wrongAnswer2 = input.nextLine().trim();
                if (!input.hasNextLine()) break;
                String wrongAnswer3 = input.nextLine().trim();

                // Add question to the list
                questions.add(new Question(question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3));
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }
        //System.out.println("Loaded " + questions.size() + " questions.");

    }

    public List<Question> getQuestions() {
        return questions;
    }
}
