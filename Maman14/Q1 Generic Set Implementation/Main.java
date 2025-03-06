import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // Create 3 sets with random values
        GenericSet<Integer> set1 = new GenericSet<>();
        GenericSet<Integer> set2 = new GenericSet<>();
        GenericSet<Integer> set3 = new GenericSet<>();

        for (int i = 0; i < 10; i++) {
            set1.insert(random.nextInt(101)); // Random numbers between 0 and 100
            set2.insert(random.nextInt(101));
            set3.insert(random.nextInt(101));
        }
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        System.out.println("Set 3: " + set3);

        // Perform union of set1 and set2
        set1.union(set2);
        System.out.println("Union of Set 1 and Set 2: " + set1);

        // Perform intersection of set1 after the union with set3
        set1.intersect(set3);
        System.out.println("Intersection of set1 after the union with Set 3: " + set1);

        // Read two numbers from the user to create a fourth set
        System.out.print("Enter two numbers for Set 4: ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        GenericSet<Integer> set4 = new GenericSet<>();
        set4.insert(num1);
        set4.insert(num2);
        System.out.println("Set 4: " + set4);

        // Check if set4 is a subset of any other set
        System.out.println("Is set 4 a subset of Set 1: " + set1.isSubset(set4));
        System.out.println("is set 4 a subset of Set 2: " + set2.isSubset(set4));
        System.out.println("is set 4 a subset of Set 3: " + set3.isSubset(set4));

        // Read a number from the user and perform operations
        System.out.print("Enter a number to perform operations: ");
        int userNumber = scanner.nextInt();

        System.out.println("Is " + userNumber + " a member of Set 1: " + set1.isMember(userNumber));

        set2.insert(userNumber);
        System.out.println("Set 2 after adding " + userNumber + ": " + set2);

        set3.delete(userNumber);
        System.out.println("Set 3 after deleting " + userNumber + ": " + set3);

        // Test with Person objects
        GenericSet<Person> personSet = new GenericSet<>();
        personSet.insert(new Person("823456789", "John", "Doe", 1990));
        personSet.insert(new Person("987654321", "Jane", "Smith", 1985));
        personSet.insert(new Person("456789123", "Alice", "Johnson", 1992));
        personSet.insert(new Person("654321987", "Bob", "Brown", 1988));
        personSet.insert(new Person("321987654", "Charlie", "Davis", 1995));

        System.out.println("People Set: " + personSet);

        // Find the minimum Person by ID
        Person minPerson = FindMinimum.findMinimum(personSet);
        System.out.println("Person with minimum ID: " + minPerson);
    }
}


