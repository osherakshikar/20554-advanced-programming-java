/**
 * Chimpanzee class represents a specific type of mammal.
 * It extends the Mammal class and defines behaviors specific to chimpanzees.
 */
public class Chimpanzee extends Mammal {
    public Chimpanzee(String name, int age, String color) {
        super(name, age, color);
    }

    //Method to simulate the chimpanzee eating.
    public void eat() {
        System.out.println(getName() + " is eating fruits and seeds.");
    }

    //Method to simulate the chimpanzee climbing.
    public void climb() {
        System.out.println(getName() + " is climbing.");
    }

    /**
     * Overrides the `equals` method to compare two Chimpanzee objects.
     * First uses the parent class's equals method and then checks if the object is an instance of Chimpanzee.
     */
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // Use parent class's equals
        return obj instanceof Chimpanzee;
    }

    /**
     * Overrides the `toString` method to provide a string representation of the chimpanzee.
     * Appends specific chimpanzee type information to the base mammal description.
     */
    public String toString() {
        return super.toString() + " [Type: Chimpanzee]";
    }
}
