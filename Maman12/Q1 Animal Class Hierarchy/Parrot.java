/**
 * Parrot class represents a specific type of bird with an owner.
 * It extends the Bird class and adds owner-specific attributes and behaviors.
 */
public class Parrot extends Bird {
    private Owner owner;

    /**
     * Constructor to initialize a Parrot object.
     * Calls the parent class constructor to set common bird attributes
     * and initializes the owner of the parrot.
     */
    public Parrot(String name, int age, String color, Owner owner) {
        super(name, age, color);
        this.owner = owner;
    }

    /**
     * Method to simulate the parrot eating.
     * Prints a message indicating the parrot is eating seeds and fruits.
     */
    public void eat() {
        System.out.println(getName() + " is eating seeds and fruits.");
    }

    /**
     * Method to display the information about the parrot's owner.
     * Prints a message with the owner's details.
     */
    public void displayOwnerInfo() {
        System.out.println(getName() + "'s owner is " + getOwner() + ".");
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Overrides the `equals` method to compare two Parrot objects.
     * First uses the parent class's `equals` method, then checks if the owners are equal.
     */
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // Use parent class's equals
        Parrot parrot = (Parrot) obj;
        return owner != null && owner.equals(parrot.owner);
    }

    /**
     * Overrides the `toString` method to provide a string representation of the parrot.
     * Appends the owner's information to the bird's description.
     */
    public String toString() {
        return super.toString() + "," + (owner != null ? owner.toString() : "No owner");
    }
    /**
     * Clones the current parrot object.
     * Creates a deep copy of the owner as well to avoid shared references.
     */
    public Parrot clone() {
        Parrot cloned = (Parrot) super.clone();
        if (this.owner != null) {
            cloned.owner = this.owner.clone();
        }
        return cloned;
    }
}
