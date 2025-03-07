# 🧠 Trivia Game

## 📝 Overview
This JavaFX application presents an interactive trivia challenge where users answer multiple-choice questions loaded from a text file. The game tracks user performance, provides immediate feedback, and offers options to restart or end the game.

## ✨ Features
- **📚 Dynamic Question Loading**: Questions loaded from an external text file
- **🔄 Randomized Answers**: Answer options displayed in randomized order
- **📊 Score Tracking**: Real-time score updates as players answer questions
- **🎮 Multiple Game Controls**: Next, Restart, and End Game options
- **📱 Responsive UI**: Clean layout with properly sized text elements

## 🎯 How the Game Works
1. **📥 Initialization**: Questions are loaded from the "trivia.txt" file when the application starts
2. **❓ Question Display**: One question is shown at a time with four possible answers
3. **🔘 Answer Selection**: User selects an answer using radio buttons
4. **⏭️ Progression**: User advances to the next question with the "Next" button
5. **🏁 Game End**: Game concludes when all questions are answered or the user selects "End Game"

## 📋 Question Format
The questions in the trivia.txt file follow a specific format:
Question text Correct answer Incorrect answer 1 Incorrect answer 2 Incorrect answer 3

## 🖼️ Screenshots

![Trivia Game Question](https://github.com/user-attachments/assets/efb167ae-1da8-4ce8-a644-90eaf27c7346)
![Trivia Game Results](https://github.com/user-attachments/assets/a3052517-aa3a-4942-9136-536c27828db0)
![Trivia Game Feedback](https://github.com/user-attachments/assets/568ce0cd-03e3-4b2f-aaa5-f99aac9950cf)

## ⚙️ Implementation Details
- **JavaFX Framework**: UI built with JavaFX components including VBox, HBox, Labels, RadioButtons, and Buttons
- **MVC Architecture**: Clear separation between model (Question, QuestionPool), view (FXML), and controller
- **File I/O**: Uses File and Scanner classes to read question data
- **Collections**: ArrayList used to manage the question pool
- **Event Handling**: Uses JavaFX event handlers for button clicks and selection changes

## 🚀 How to Run
1. Ensure you have Java 8 or higher with JavaFX support installed
2. Make sure the "trivia.txt" file is in the same directory as the application
3. Compile the Java files: `javac *.java`
4. Run the application: `java TriviaGame`

## 📝 File Requirement
The game requires a "trivia.txt" file with properly formatted questions and answers as described above. If the file is missing or improperly formatted, the game will start with an empty question pool.
