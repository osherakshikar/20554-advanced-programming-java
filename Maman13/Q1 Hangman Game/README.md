# 🎮 Hangman Game

This project is a Java implementation of the classic Hangman game, developed as part of the Advanced Programming course (20554) at The Open University of Israel.

## 🎯 How the Game Works

Hangman is a word-guessing game where the player attempts to deduce a hidden word by suggesting letters within a limited number of guesses. The rules are as follows:

1. **📝 Word Selection**: A random word is chosen from a predefined list.
2. **👁️ Display**: The word is represented by a series of underscores, each corresponding to a letter in the word.
3. **🔤 Guessing**: The player suggests letters one at a time.
4. **✅ Correct Guess**: If the guessed letter is in the word, all instances of that letter are revealed in their correct positions.
5. **❌ Incorrect Guess**: If the guessed letter is not in the word, the player loses a life.
6. **🏁 Game Over**: The game continues until the player either guesses the entire word or exhausts all allotted lives.

## ✨ Features

- **🎲 Random Word Selection**: The game reads a list of words from an external file (`words.txt`) and selects one at random for each game session.
- **⌨️ User Interaction**: Players input their guesses through the console, and the game provides feedback on each guess.
- **❤️ Life Tracking**: The game tracks the number of incorrect guesses and ends the game when the player runs out of lives.

## 📋 Requirements

- **☕ Java Development Kit (JDK)**: Ensure that JDK 8 or higher is installed on your system.

## 🚀 Setup and Running the Game

1. **📥 Clone the Repository**: Download or clone the repository from GitHub.
2. **📄 Prepare the Word List**:
   - Create a `words.txt` file containing a list of words, with each word on a new line.
   - Place this file in the same directory where the Java program is executed.
3. **🔨 Compile the Code**: Use your preferred Integrated Development Environment (IDE) or command-line tools to compile the Java source files.
4. **▶️ Run the Application**: Execute the main class to start the game.

## 🎮 How to Play

1. **🎬 Start the Game**: Upon running the program, the game will randomly select a word from `words.txt`.
2. **🔤 Guess Letters**: Input your letter guesses when prompted.
3. **📊 Receive Feedback**: The game will inform you if your guess is correct or incorrect and display the current state of the word.
4. **🔄 Continue Guessing**: Keep guessing letters until you either guess the word correctly or run out of lives.

## 📸 Screenshots
![Screenshot 2025-03-05 202511](https://github.com/user-attachments/assets/916a683a-5d87-4609-b991-c4e3d6138276)
![Screenshot 2025-03-05 202542](https://github.com/user-attachments/assets/357738c4-2ee8-4fb8-b68e-32b7cca76de3)
![Screenshot 2025-03-05 202443](https://github.com/user-attachments/assets/065e3075-26d7-4cee-b19c-43a72500f2da)

Enjoy playing Hangman! 🎯
