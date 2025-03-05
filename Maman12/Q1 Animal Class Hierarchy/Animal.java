/**
 * Abstract class representing a generic animal.
 * Serves as the base class for all types of animals, providing common properties and behaviors.
 */
abstract public class Animal implements Cloneable {
    // Instance variables representing the name, age, and color of the animal.
    private String name;
    private int age;
    private String color;

    //Constructor to initialize an Animal object.
    public Animal(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Abstract method to define the animal's eating behavior.
     * Must be implemented by subclasses.
     */
    public abstract void eat();

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    //Overrides the `toString` method to provide a string representation of the animal.
    public String toString() {
        return "Animal [Name: " + getName() + ", Age: " + getAge() + ", Color: " + getColor() + "]";
    }


    /**
     * Overrides the `equals` method to compare two Animal objects.
     * Two animals are considered equal if their name, age, and color are the same.
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal animal = (Animal) obj;
        return age == animal.age && name.equals(animal.name) && color.equals(animal.color);
    }

    /**
     * Creates and returns a copy of the Animal object.
     * Implements shallow cloning for the base class.
     */
    public Animal clone() {
        try {
            return (Animal) super.clone(); // קריאה ל־clone של Object
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}

