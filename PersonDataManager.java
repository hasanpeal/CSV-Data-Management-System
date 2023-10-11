import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class PersonDataManager {
    private Person[] people;
    private int size;

    public PersonDataManager(int initialCapacity) {
        people = new Person[initialCapacity];
        size = 0;
    }
    
    public void buildFromFile(String location) throws IllegalArgumentException, PersonAlreadyExistsException {
        try {
            // This create a File instance
            File file = new File(location);
    
            // This create a Scanner for the file
            Scanner scan = new Scanner(file);
    
            // This skips this first line (title of each column)
            if (scan.hasNextLine()) {
                scan.nextLine();
            }
    
            // This reads data and then add data to array
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
    
                // This remove double quotes also splis line into particular data field
                String[] data = s.replaceAll("\"", "").split(",");
    
                // This checks if the file format is correct then insert the data to array
                if (data.length == 5) {
                    String name = data[0].trim();
                    int age = Integer.parseInt(data[2].trim());
                    String gender = data[1].trim();
                    double height = Double.parseDouble(data[3].trim());
                    double weight = Double.parseDouble(data[4].trim());
                    Person newPerson = new Person(name, age, gender, height, weight);
                    addPerson(newPerson);
                } else {
                    throw new IllegalArgumentException("CSV file format error! Line content: " + s);
                }
            }
    
            System.out.println("loading.....");
            System.out.println("Person data has been loaded successfully!");
            scan.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    /*
     * Specification of ensureCapacity method:
     * public void ensureCapacity(int minimumCapacity)
     * Changes the capacity of the array.
     * Precondition: Takes a parameter which is minimum capacity.
     * Postcondition: Changes the capacity of the array to minimum capacity.
     */

    public void ensureCapacity(int minimumCapacity) {
        Person biggerArray[];
        if(people.length < minimumCapacity) {
            biggerArray = new Person[minimumCapacity];
            System.arraycopy(people, 0, biggerArray, 0, size);
            people = biggerArray;
        }
    }

    public void addPerson(Person newPerson) throws PersonAlreadyExistsException {
        int j;
        for(j = 0; j < size; j++) {
            if((people[j].getName()).equals(newPerson.getName())) throw new PersonAlreadyExistsException("Person with this name already exists!");
        }
        if(size == people.length) ensureCapacity(size*2+1);
        int i;
        for(i = 0; i < size; i++) {
            if(newPerson.getName().compareToIgnoreCase(people[i].getName()) <= 0){
                break;
            }
        }
        System.arraycopy(people, i, people, i+1, size - i);
        people[i] = newPerson;
        size++;
    }

    public void getPerson(String name) throws PersonDoesNotExistsException {
        int i;
        int count = 0;
        for(i = 0; i < size; i++) {
            if(people[i].getName().equalsIgnoreCase(name)) {
                System.out.println(people[i]);
                break;
            } 
            count++;
        }
        if(count == size) throw new PersonDoesNotExistsException("This person doesn't exist!");
    }

    public void removePerson(String name) throws PersonDoesNotExistsException {
        int i, j;
        int count = 0;;
        for(i = 0; i < size; i++) {
            if(people[i].getName().equalsIgnoreCase(name)) {
                for (j = i; j < size - 1; j++) {
                    people[j] = people[j + 1];
                }
                size--; // Decrease the size of the array
                break;
            }
            count++;
        }
        if(size == count) throw new PersonDoesNotExistsException("No match found with that name");
    }

    public void printTable() {
        System.out.println("|    Name    |    Age    |    Gender    |    Height(in)    |    Weight(lbs)    |");
        System.out.println("================================================================================");
        int i;
        for(i = 0; i < size; i++) {
            System.out.println(people[i].print());
        }
    }

    public void writeFile(String newLocation) {
        try {
            FileWriter f = new FileWriter(newLocation);
            f.write("|    Name    |    Age    |    Gender    |    Height(in)    |    Weight(lbs)    |\n");
            for(int i = 0; i < size; i++) {
                f.write(String.format("     %s         %d            %s              %.1f                %.1f\n", people[i].getName(), people[i].getAge(), people[i].getGender(), people[i].getHeight(), people[i].getWeight()));
            }
            f.write("\n");
            System.out.println("File succefully has been saved");
            f.close();
        } catch (IOException e) {
            System.out.println("File name already exists");
        }
    }
}
