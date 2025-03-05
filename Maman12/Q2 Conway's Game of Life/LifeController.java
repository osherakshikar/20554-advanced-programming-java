import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LifeController {

    @FXML
    private Canvas canv;

    private final int CELL_SIZE = 56; // Size of each cell in pixels
    private GraphicsContext gc;      // Graphics context for drawing
    private LifeLogic lifeLogic;     // Instance of the logic class
    
    public void initialize() {
        gc = canv.getGraphicsContext2D();
        lifeLogic = new LifeLogic(); // Create an instance of the logic class
    }

    /**
     * Event handler for the button press.
     * On the first press, initializes the grid randomly.
     * On subsequent presses, updates the grid and redraws it.
     */
    @FXML
    void buttonPressed(ActionEvent event) {
        if (isGridEmpty()) {
            lifeLogic.initializeGridRandomly(); // Initialize grid randomly
        } else {
            lifeLogic.updateGrid(); // Update the grid to the next generation
        }
        drawGrid(); // Draw the updated grid
    }

     //Checks if the grid is empty (contains only zeros).
    private boolean isGridEmpty() {
        int[][] grid = lifeLogic.getGrid();
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 1) {
                    return false; // Found a live cell
                }
            }
        }
        return true;
    }

    /**
     * Draws the current grid on the Canvas.
     * Live cells are filled in black.
     * Dead cells are filled in white.
     * Draws a gray border around each cell for clarity.
     */
    private void drawGrid() {
        gc.clearRect(0, 0, canv.getWidth(), canv.getHeight()); // Clear the Canvas
        int[][] grid = lifeLogic.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    gc.setFill(Color.BLACK); // Live cell: black
                } else {
                    gc.setFill(Color.WHITE); // Dead cell: white
                }
                // Draw the cell
                gc.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                // Draw the border
                gc.setStroke(Color.GRAY);
                gc.strokeRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
