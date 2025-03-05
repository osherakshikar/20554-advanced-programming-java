import java.util.Random;

public class LifeLogic {
    private final int SIZE = 10; // Grid size
    private int[][] grid;       // The grid of cells
    private final Random random = new Random();

    public LifeLogic() {
        grid = new int[SIZE][SIZE];
    }

    
     //Initializes the grid with random values (0 or 1).
    public void initializeGridRandomly() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = random.nextInt(2); // Randomly set cell as alive (1) or dead (0)
            }
        }
    }


     //Updates the grid to the next generation based on the rules of Conway's Game of Life.
    public void updateGrid() {
        int[][] newGrid = new int[SIZE][SIZE]; // Create a new grid for the next generation
        
        // Iterate over every cell in the grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Count the number of live neighbors for the current cell
            	int liveNeighbors = countLiveNeighbors(i, j);

                // Determine the next state of the cell based on its current state and neighbors
                if (grid[i][j] == 1) { // If the cell is alive
                    if (liveNeighbors == 2 || liveNeighbors == 3) {
                        newGrid[i][j] = 1; // Cell survives
                    } else {
                        newGrid[i][j] = 0; // Cell dies (overpopulation or loneliness)
                    }
                } else { // If the cell is dead
                    if (liveNeighbors == 3) {
                        newGrid[i][j] = 1; // Cell becomes alive
                    } else {
                        newGrid[i][j] = 0; // Cell remains dead
                    }
                }
            }
        }

        // Replace the old grid with the new one
        grid = newGrid;
    }

     //Returns the current state of the grid.
    public int[][] getGrid() {
        return grid;
    }

  
     //Counts the number of live neighbors for a given cell in the grid
    private int countLiveNeighbors(int x, int y) {
        int count = 0; // Initialize the count of live neighbors to zero
        
        // Iterate over the 3x3 grid centered around
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Skip the cell itself
                if (dx == 0 && dy == 0) continue;

                // Calculate the coordinates of the neighboring cell
                int nx = x + dx;
                int ny = y + dy;

                // Check if the neighboring cell is within bounds of the grid
                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE) {
                    // Add the value of the neighbor to the count (1 if alive, 0 if dead)
                    count += grid[nx][ny];
                }
            }
        }

        // Return the total count of live neighbors
        return count;
    }
}
