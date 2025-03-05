# Conway's Game of Life

Conway's Game of Life is a cellular automaton devised by mathematician John Horton Conway in 1970. It's a zero-player game that evolves based on its initial state, requiring no further input. This Java implementation is developed as part of the Advanced Programming course (20554) at The Open University of Israel.

## How the Game Works

The game consists of a grid of cells, each of which can be in one of two states: **alive** or **dead**. The evolution of the grid is determined by the following rules:

1. **Underpopulation**: A live cell with fewer than two live neighbors dies.
2. **Survival**: A live cell with two or three live neighbors continues to live.
3. **Overpopulation**: A live cell with more than three live neighbors dies.
4. **Reproduction**: A dead cell with exactly three live neighbors becomes alive.

These simple rules can lead to complex and often unpredictable patterns over successive generations.

## Features

- **Fixed Initial State**: The grid is initialized with a predefined pattern, and users cannot interactively modify the grid during runtime.
- **Preset Patterns**: Load predefined patterns such as gliders, oscillators, and still lifes to observe their behavior.
- **Simulation Controls**: Start and run the next gen buttons.

## Game Flow

1. **Initialization**: Upon launching the application, the grid is displayed with a predefined initial state.
2. **Starting the Simulation**: The simulation begins upon user initiation, evolving the grid according to the rules of the game.
3. **Observing the Simulation**: Users can watch the evolution of patterns as the simulation progresses.

## Graphical Interface

The application features a graphical interface implemented using Java Swing:

- **Grid Display**: A visual representation of the grid where alive cells are colored, and dead cells are blank.
- **Control Panel**: Buttons to start and next gen the simulation.


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
