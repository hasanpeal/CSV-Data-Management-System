import java.util.Scanner;
public class PersonManager {
    private static PersonDataManager dm;
    public static void main(String[] args) throws IllegalArgumentException, PersonAlreadyExistsException, PersonDoesNotExistsException {
        Scanner scan = new Scanner(System.in);
        dm = new PersonDataManager(20);
        String option = null;
        do {
            System.out.println("(I) – Import from File");
            System.out.println("(A) – Add Person");
            System.out.println("(R) – Remove Person");
            System.out.println("(G) – Get Info on Person");
            System.out.println("(P) – Print Table");
            System.out.println("(S) – Save to File");
            System.out.println("(Q) – Quit");
            System.out.print("Please select an option: ");
            option = scan.nextLine();
            
            try {
                if(option.equals("I")) {
                System.out.print("Please enter a location: ");
                String location = scan.nextLine();
                dm.buildFromFile(location);
            }
                if(option.equals("A")) {
                    System.out.print("Please enter the name of the person: ");
                    String name = scan.nextLine();
                    int age;
                    try {
                        System.out.print("Please enter the age: ");
                        age = scan.nextInt();
                        scan.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        scan.nextLine();
                        throw new IllegalArgumentException("Age is not valid!");
                    }
                    System.out.print("Please enter the gender (M or F): ");
                    String gender = scan.nextLine();
                    double height;
                    try {
                        System.out.print("Please enter the height (in inches): ");
                        height = scan.nextDouble();
                        scan.nextLine(); 
                    } catch (java.util.InputMismatchException e) {
                        scan.nextLine(); 
                        throw new IllegalArgumentException("Height is not valid!");
                    }
                    double weight;
                    try {
                        System.out.print("Please enter the weight (in lbs): ");
                        weight = scan.nextDouble();
                        scan.nextLine(); 
                    } catch (java.util.InputMismatchException e) {
                        scan.nextLine(); 
                        throw new IllegalArgumentException("Weight is not valid!");
                    }
                    Person p = new Person(name, age, gender, height, weight);
                    dm.addPerson(p);
                    System.out.println("Person has been added successfully :)");
            }
                if(option.equals("R")) {
                    System.out.print("Please enter the name of the person: ");
                    String removeName = scan.nextLine();
                    dm.removePerson(removeName);
                    System.out.println("Person has been removed successfully :)");
            }
                if(option.equals("G")) {
                    System.out.print("Please enter the name of the person: ");
                    String infoName = scan.nextLine();
                    dm.getPerson(infoName);
            }
                if(option.equals("P")) {
                    dm.printTable();
            }
                if(option.equals("S")) {
                    System.out.print("Please enter location to save: ");
                    String newLocation = scan.nextLine();
                    dm.writeFile(newLocation);
            }
                if(option.equals("Q")) {
                    System.out.println("Sorry to see you go :(");
            }
                if(!option.equals("I") && !option.equals("A") && !option.equals("R") && !option.equals("G") && !option.equals("P") && !option.equals("S") && !option.equals("Q")) {
                    System.out.print("Invalid choice!");
            }
            } catch(IllegalArgumentException | PersonAlreadyExistsException | PersonDoesNotExistsException e) {
                System.out.println(e.getMessage());
            }
  
        } while(!option.equals("Q"));
    }
}
