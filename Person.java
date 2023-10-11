public class Person {
    private int age;
    private double height;
    private double weight;
    private String name;
    private String gender;

    public Person() {
        this.age = 0;
        this.height = 0.0;
        this.weight = 0.0;
        this.name = null;
        this.gender = null;
    }

    public Person(String name, int age, String gender, double height, double weight) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        return String.format("%s is a %d year old %s who is %.0f feet and %.0f inches tall and weighs %.1f pounds.", name, age, gender, height/10, height%10, weight);
    }

    /*
     * Specification of print method:
     * public String print()
     * print the data field of person slightly different than toString, the purpose of this method is
     * to use it for print the table on PersonalDataManager class.
     * Precondition: None
     * Postcondition: Prints the data field in a specific format.
     */
    public String print() {
        return String.format("     %s         %d            %s              %.1f                %.1f", name, age, gender, height, weight);
    }
}
