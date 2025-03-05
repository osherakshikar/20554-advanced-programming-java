/**
 * Abstract class representing mammals, which are a subclass of Animal.
 * Provides additional behaviors specific to mammals.
 */
abstract public class Mammal extends Animal {
/**
 * Constructor to initialize a Mammal object.
 * Calls the parent class constructor to set common animal attributes.
 */
    public Mammal(String name, int age, String color) {
        super(name, age, color);
    }
    /**
     * Method to simulate the mammal running.
     * Prints a message indicating the mammal is running.
     */
    public void run() {
        System.out.println(getName() + " is running.");
    }
    /**
     * Overrides the `toString` method to provide a string representation of the mammal.
     * Appends specific mammal type information to the base animal description.
     */
    public String toString() {
        return super.toString() + " [Type: Mammal]";
    }
    /**
     * Overrides the `equals` method to compare two Mammal objects.
     * Uses the parent class's equals method and ensures they are of the exact same subclass.
     */
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // Use parent class's equals
        return getClass() == obj.getClass(); // Ensure exact same subclass
    }
}
