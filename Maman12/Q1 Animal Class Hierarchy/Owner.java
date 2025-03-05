public class Owner implements Cloneable {
    // Instance variables representing the owner's name and phone number
    private String ownerName;
    private int ownerNumber;

    //Constructor to initialize an Owner object.
    public Owner(String ownerName, int ownerNumber) {
        this.ownerName = ownerName;
        this.ownerNumber = ownerNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public int getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(int number) {
        this.ownerNumber = number;
    }

    /*Overrides the `equals` method to compare two Owner objects.
     *Two owners are considered equal if their names and phone numbers are the same.
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Owner owner = (Owner) obj;
        return ownerNumber == owner.ownerNumber &&
                ownerName.equals(owner.ownerName);
    }

    //Overrides the `toString` method to provide a string representation of the owner.
    public String toString() {
        return "Owner [Name: " + ownerName + ", Phone: " + ownerNumber + "]";
    }

    //Overrides the `clone` method to create a deep copy of an Owner object.
    public Owner clone() {
        try {
            return (Owner) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}


