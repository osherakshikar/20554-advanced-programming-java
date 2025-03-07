# 🎮 Conway's Game of Life

Conway's Game of Life is a cellular automaton devised by the mathematician John Horton Conway in 1970. It's a zero-player game that evolves based on its initial state, requiring no further input. This Java implementation is developed as part of the Advanced Programming course (20554) at The Open University of Israel.

## 🧩 How the Game Works

The game consists of a grid of cells, each of which can be in one of two states: **alive** or **dead**. The evolution of the grid is determined by the following rules:

1. **⚰️ Underpopulation**: A live cell with fewer than two live neighbors dies.
2. **✨ Survival**: A live cell with two or three live neighbors continues to live.
3. **💥 Overpopulation**: A live cell with more than three live neighbors dies.
4. **🔄 Reproduction**: A dead cell with exactly three live neighbors becomes alive.

These simple rules can lead to complex and often unpredictable patterns over successive generations.

## ⚙️ Features

- **🎲 Random Initial State**: The grid is initialized with random live and dead cells when you first press the button.
- **🔄 Generation Evolution**: Watch as patterns evolve according to Conway's rules with each button press.
- **👁️ Visual Representation**: Clear visual distinction between alive (black) and dead (white) cells.

## 🔄 Game Flow

1. **🚀 Initialization**: Upon pressing the "Next Gen" button for the first time, the grid is randomly populated.
2. **⏭️ Next Generation**: Each subsequent button press advances the simulation by one generation.
3. **📊 Pattern Observation**: Users can observe how patterns evolve, form stable structures, or completely die out.

## 🖥️ Graphical Interface

The application features a clean graphical interface implemented using JavaFX:

- **📏 Grid Display**: 10x10 grid where alive cells are black and dead cells are white.
- **🔲 Cell Borders**: Gray borders around each cell for better visibility.
- **⏯️ Control Button**: "Next Gen" button to advance the simulation one step at a time.

## 📸 Screenshots

<p align="left">
  <img src="https://github.com/user-attachments/assets/ba8be84a-377d-41ae-8857-62c41cd875c0" alt="Conway's Game of Life" width="400" height="400">

  <img src="https://github.com/user-attachments/assets/2ad84006-e0a5-402c-bdee-ba049c191885" alt="Conway's Game of Life" width="400" height="400">

  <img src="https://github.com/user-attachments/assets/3494d8cc-0e76-4ab5-a9a0-e692268b539f" alt="Conway's Game of Life" width="400" height="400">

  <img src="https://github.com/user-attachments/assets/bf10d6bc-cfda-46d4-aed8-f8f4dcb6dd42" alt="Conway's Game of Life" width="400" height="400">

  <img src="https://github.com/user-attachments/assets/6b9054b2-092f-4929-be44-940ad19aeddf" alt="Conway's Game of Life" width="400" height="400">

  <img src="https://github.com/user-attachments/assets/9560f5cf-0fe4-4253-8005-eb67be5cf38b" alt="Conway's Game of Life" width="400" height="400">

  <img src="https://github.com/user-attachments/assets/a788aaf5-b7f8-4e51-ac02-6f4b7c57dcfa" alt="Conway's Game of Life" width="400" height="400">

  <img src="https://github.com/user-attachments/assets/1c12fedc-047f-4237-bc6c-d5d159ceb387" alt="Conway's Game of Life" width="400" height="400">
</p>

## 🧠 Interesting Patterns

Conway's Game of Life can produce fascinating patterns:
- **🔄 Oscillators**: Patterns that repeat after a fixed number of generations
- **🚀 Spaceships**: Patterns that travel across the grid
- **🔄 Still Lifes**: Stable patterns that don't change
- **🌱 Methuselahs**: Simple patterns that evolve through many generations before stabilizing
