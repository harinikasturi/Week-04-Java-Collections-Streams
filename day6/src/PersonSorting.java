import java.util.Arrays;
import java.util.List;

class Person {
    String name;
    int age;
    double salary;

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Salary: " + salary + ")";
    }
}

public class PersonSorting {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("John", 30, 50000),
                new Person("Alice", 25, 45000),
                new Person("Bob", 35, 60000)
        );

        people.sort((p1, p2) -> Integer.compare(p1.age, p2.age));
        System.out.println("Sorted by age:");
        people.forEach(System.out::println);
    }
}