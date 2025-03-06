public class Person implements Comparable<Person> {
    private final String ID;
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final int YEAR_OF_BIRTH;

    public Person(String id, String firstName, String lastName, int yearOfBirth) {
        this.ID = id;
        this.FIRST_NAME = firstName;
        this.LAST_NAME = lastName;
        this.YEAR_OF_BIRTH = yearOfBirth;
    }

    @Override
    public int compareTo(Person other) {
        return this.ID.compareTo(other.ID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return ID.equals(person.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Person{id='%s', firstName='%s', lastName='%s', yearOfBirth=%d}", ID, FIRST_NAME, LAST_NAME, YEAR_OF_BIRTH);
    }
}