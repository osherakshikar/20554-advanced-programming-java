import java.util.*;
//TemperatureProjectLogic class used as backend class for TemperatureProject.
public class TemperatureProjectLogic {
    private static final int START_YEAR = 2017;
    private final List<int[]> temperatures;
    private int currentYearIndex=0;
    
    // Initialize the temperature data for each year.
    public TemperatureProjectLogic() {
    	 temperatures = new ArrayList<>();
         temperatures.add(new int[]{50, 100, 150, 175, 200, 205, 300, 350, 325, 200, 175, 150}); // 2017
         temperatures.add(new int[]{300, 250, 200, 170, 150, 100, 150, 175, 200, 225, 250, 300}); // 2018
         temperatures.add(new int[]{250, 300, 250, 170, 100, 150, 300, 325, 300, 150, 100, 70}); // 2019
         temperatures.add(new int[]{65, 100, 150, 200, 300, 80, 100, 150, 200, 300, 80, 100});  // 2020
         temperatures.add(new int[]{200, 125, 300, 320, 275, 250, 200, 150, 250, 100, 250, 150}); // 2021
                                                                          
    }
    // Get the temperature data for the current year.
    public int[] getCurrentYearTemperatures() {
        return temperatures.get(currentYearIndex);
    }
    // Get the current year as a string based on the index.
    public String getCurrentYear() {
        return String.valueOf(START_YEAR + currentYearIndex);
    }
    // Advance to the next year in a circular fashion.
    public void nextYear() {
        currentYearIndex = (currentYearIndex + 1) % getTotalYears();
        // If at the end of the list, wrap around to the beginning (circular behavior).
    }
    public int getTotalYears() {
        return temperatures.size();
    }
}
