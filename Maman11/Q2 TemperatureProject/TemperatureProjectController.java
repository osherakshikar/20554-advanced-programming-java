import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
//TemperatureProjectController class used for the frontend 
public class TemperatureProjectController {

	@FXML
	private Canvas canv;
	private GraphicsContext gc;

	TemperatureProjectLogic logic = new TemperatureProjectLogic(); // Handles temperature data and logic.
	private final int SPACE_BETWEEN_COLUMNS = 47; // Space between adjacent columns in the graph.
	private final int COLUMN_WIDTH = 40; // Width of each column.
	private final int COLUMN_HEIGHT = 400; // Base height for the columns.


	public void initialize() {
		gc = canv.getGraphicsContext2D();
		int[] temperatures = logic.getCurrentYearTemperatures(); // Retrieve the temperature data
		int monthIndex = 0;
		int maxColumnX = 0;// Position (x-coordinate) of the column with the highest temperature.
		int minColumnX  = 0;// Position (x-coordinate) of the column with the lowest temperature.
		int min = temperatures[0];
		int max = temperatures[0];
		
        // Draw the year label on the canvas.
		gc.setFill(Color.GREY);
		gc.fillText("Year: "+ logic.getCurrentYear(), 250, 50);
		
        // Loop through each month and draw the temperature columns.
		while(monthIndex<12) {
			gc.fillRect(SPACE_BETWEEN_COLUMNS*monthIndex,temperatures[monthIndex], COLUMN_WIDTH, COLUMN_HEIGHT);
			
            // Update the max temperature and its position if needed.
			if(temperatures[monthIndex] < max) {
				max = temperatures[monthIndex];
				maxColumnX = SPACE_BETWEEN_COLUMNS*monthIndex;
			}
			
            // Update the min temperature and its position if needed.
			if(temperatures[monthIndex] > min) {
				min = temperatures[monthIndex];
				minColumnX = SPACE_BETWEEN_COLUMNS*monthIndex;
			}
			monthIndex++;
		} 	
        // Highlight the maximum temperature column in red.
		gc.setFill(Color.RED);
		gc.fillRect(maxColumnX, max, COLUMN_WIDTH, COLUMN_HEIGHT);
        // Highlight the minimum temperature column in blue.
		gc.setFill(Color.BLUE);
		gc.fillRect(minColumnX, min, COLUMN_WIDTH, COLUMN_HEIGHT);
		logic.nextYear();
	}

	@FXML 
	//Copy of initialize code integrated in "Next" button
	void nextYear(ActionEvent event) {
		int[] temperatures = logic.getCurrentYearTemperatures();
		int monthIndex = 0;
		int maxColumnX = 0;
		int minColumnX = 0;
		int min = temperatures[0];
		int max = temperatures[0];
		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
		gc.setFill(Color.GREY);
		gc.fillText("Year: "+ logic.getCurrentYear(), 250, 50);
		while(monthIndex<12) {
			gc.fillRect(SPACE_BETWEEN_COLUMNS*monthIndex,temperatures[monthIndex], COLUMN_WIDTH, COLUMN_HEIGHT);
			if(temperatures[monthIndex] < max) {
				max = temperatures[monthIndex];
				maxColumnX = SPACE_BETWEEN_COLUMNS*monthIndex;
			}
			if(temperatures[monthIndex] > min) {
				min = temperatures[monthIndex];
				minColumnX = SPACE_BETWEEN_COLUMNS*monthIndex;
			}
			monthIndex++;
		} 	
		gc.setFill(Color.RED);
		gc.fillRect(maxColumnX, max, COLUMN_WIDTH, COLUMN_HEIGHT);
		gc.setFill(Color.BLUE);
		gc.fillRect(minColumnX, min, COLUMN_WIDTH, COLUMN_HEIGHT);
		logic.nextYear();
	}
}
