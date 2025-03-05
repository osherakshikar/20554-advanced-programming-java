import java.util.ArrayList;

public class AnimalHierarchy {
    /**
     * Main class demonstrating animal hierarchy, polymorphism, and object cloning.
     * Creates and manages a collection of animals, demonstrates polymorphic behavior,
     * and tests the cloning functionality of an animal with an owner.
     */
    public static void main(String[] args) {
        // List to store different animals
        ArrayList<Animal> animals = new ArrayList<>();

        // Create owners for animals
        Owner owner1 = new Owner("Alice", 123456789);

        // Create animals
        Chimpanzee chimpanzee = new Chimpanzee("Pan", 5, "Brown");
        Parrot parrot = new Parrot("Nigel", 2, "Green", owner1);
        Owl owl = new Owl("Fluffy", 3, "White");
        Mammal dog = new Mammal("Buddy", 4, "Black") {// Anonymous subclass for dog

            public void eat() {
                System.out.println(getName() + " is eating dog food.");
            }
        };

        // Add animals to the list
        animals.add(chimpanzee);
        animals.add(parrot);
        animals.add(owl);
        animals.add(dog);

        // Demonstrate polymorphism
        for (Animal animal : animals) {

            System.out.println(animal);
            animal.eat();
            animal.sleep();

            if (animal instanceof Mammal) {
                ((Mammal) animal).run();
            }
            if (animal instanceof Bird) {
                ((Bird) animal).fly();
            }

            if (animal instanceof Chimpanzee) {
                ((Chimpanzee) animal).climb();
            }

            if (animal instanceof Parrot) {
                ((Parrot) animal).displayOwnerInfo();
            }

            System.out.println("-----------------------");
        }
        // Demonstrating cloning functionality
        Owner originalOwner = new Owner("Marsel", 1234);
        Parrot originalParrot = new Parrot("Zazu", 2, "Green", originalOwner);

        Parrot clonedParrot = originalParrot.clone();

        // Display original and cloned parrot details
        System.out.println("Original Parrot: " + originalParrot);
        System.out.println("Cloned Parrot: " + clonedParrot);

        // Modify the cloned parrot's owner
        clonedParrot.getOwner().setOwnerName("Bob");
        clonedParrot.getOwner().setOwnerNumber(987654321);

        // Show that the original parrot's owner remains unchanged
        System.out.println("\nAfter modifying the cloned parrot's owner:");
        System.out.println("Original Parrot: " + originalParrot);
        System.out.println("Cloned Parrot: " + clonedParrot);
    }
}




