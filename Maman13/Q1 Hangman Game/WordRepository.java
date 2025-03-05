
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordRepository {

    private String[] words = new String[100];

    public void readWordFromFile() {
        try {
            List<String> wordList = new ArrayList<>();
            Scanner sc = new Scanner(new File("words.txt"));
            while (sc.hasNext()) {
                wordList.add(sc.nextLine());
            }
            words = wordList.toArray(new String[0]); // Convert list to an array
            sc.close();
        } catch (IOException e) {
            System.out.println("Error reading word file!");
        }
    }

    public String getRandomWord() {
        // Ensure the array is not empty or null
        if (words == null || words.length == 0) {
            throw new IllegalStateException("Word list is empty, make sure to call readWordFromFile() first.");
        }
        // Pick a random index
        Random random = new Random();
        int index = random.nextInt(words.length);

        // Return the word at the random index
        return words[index];
    }
}
