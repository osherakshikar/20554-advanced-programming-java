/**
 * Owl class represents a specific type of bird that behaves differently from other birds.
 * It extends the Bird class and provides a specific eating behavior for owls.
 */
public class Owl extends Bird {
    public Owl(String name, int age, String color) {
        super(name, age, color);
    }
    /**
     * Method to simulate the owl eating.
     * Prints a message indicating the owl is eating insects and spiders.
     */
    public void eat() {
        System.out.println(getName() + " is eating insects and spiders.");
    }

}
