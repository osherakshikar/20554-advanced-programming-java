/**
 * Bird class represents a specific type of animal that is capable of flying.
 * It extends the Animal class and defines behaviors specific to birds.
 */
abstract public class Bird extends Animal {
    public Bird(String name, int age, String color) {
        super(name, age, color);
    }
    /**
     * Method to simulate the bird flying.
     * Prints a message indicating the bird is flying.
     */
    public void fly() {
        System.out.println(getName() + " is flying.");
    }
    /**
     * Overrides the `toString` method to provide a string representation of the bird.
     * Appends specific bird type information to the base animal description.
     */
    public String toString() {
        return super.toString() + " [Type: Bird]";
    }
    /**
     * Overrides the `equals` method to compare two Bird objects.
     * Uses the parent class's equals method and ensures they are of the exact same subclass.
     */
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // Use parent class's equals
        return getClass() == obj.getClass(); // Ensure exact same subclass
    }

}
