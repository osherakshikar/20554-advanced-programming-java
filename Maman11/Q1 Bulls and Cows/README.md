# Bulls and Cows Game

## Overview
Bulls and Cows is a classic number-guessing game where the player attempts to guess a randomly selected four-digit number with no repeating digits. The game provides feedback in the form of "bulls" (correct digits in the correct position) and "cows" (correct digits in the wrong position). The objective is to correctly guess the number in as few attempts as possible.

## Features
- **Random Number Generation**: The game generates a unique four-digit number with no repeating digits.
- **Swing Dialog Interface**: The game uses `Swing` dialog boxes for user input and feedback.
- **Real-Time Feedback**: After each guess, the game provides a count of bulls and cows to help the player refine their guesses.
- **Replay Option**: After solving the number, the player can start a new game.

## Game Flow
1. **Game Start**: A four-digit number with unique digits is randomly selected.
2. **User Input**: The player enters a four-digit guess using a Swing dialog box.
3. **Feedback**: The game checks the guess against the target number:
   - A "Currect" means a correct digit in the correct position.
   - A "Hit" means a correct digit in the wrong position.
4. **Repeat Until Correct**: The player continues guessing with feedback after each attempt until they guess the correct number.
5. **Game End**: The game displays the total number of attempts and asks if the player wants to play again.

## Graphical Interface
The game features a simple, user-friendly interface using `Swing` dialog boxes:
- **Input Dialog**: The player enters their guess in a text field.
- **Feedback Dialog**: Displays the number of bulls and cows after each attempt.
- **Victory Dialog**: Shows the final result and allows the player to restart the game.

![Screenshot 2025-03-05 173943](https://github.com/user-attachments/assets/5daafbf5-b108-4d0a-8c67-c916384fcfef)
![Screenshot 2025-03-05 174040](https://github.com/user-attachments/assets/9f1ffb63-f865-4a57-8bdd-3cf026f5fcd8)
![Screenshot 2025-03-05 174121](https://github.com/user-attachments/assets/ac6b3386-3ba2-4c14-abc6-43aee55a0126)
![Screenshot 2025-03-05 174200](https://github.com/user-attachments/assets/83a77c4b-dd27-46f9-b8fa-9c4068a40c00)
![Screenshot 2025-03-05 174212](https://github.com/user-attachments/assets/f867a16d-beeb-43fb-9955-a23111e10b59)


## Requirements
- Java Development Kit
- Swing Library

This game is a fun way to practice logical deduction and pattern recognition while learning Swing dialog programming!

