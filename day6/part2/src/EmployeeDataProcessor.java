import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDataProcessor {

    public static Map<String, Double> processEmployeeData(List<Employee> employees) {
        // Input validation
        if (employees == null) {
            return Collections.emptyMap();
        }

        // Step 1: Filter Engineering employees with salary > 80,000
        List<Employee> filteredEmployees = employees.stream()
                .filter(Objects::nonNull)
                .filter(e -> "Engineering".equals(e.getDepartment()))
                .filter(e -> e.getSalary() > 80000)
                .collect(Collectors.toList());

        // Step 2: Sort by salary descending
        List<Employee> sortedEmployees = filteredEmployees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());

        // Step 3: Group by department (though all are Engineering after filter)
        Map<String, List<Employee>> employeesByDept = sortedEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // Step 4: Calculate average salary per department
        Map<String, Double> avgSalaryByDept = employeesByDept.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToDouble(Employee::getSalary)
                                .average()
                                .orElse(0.0)
                ));

        return avgSalaryByDept;
    }

    // Alternative one-stream solution
    public static Map<String, Double> processEmployeeDataConcise(List<Employee> employees) {
        if (employees == null) {
            return Collections.emptyMap();
        }

        return employees.stream()
                .filter(Objects::nonNull)
                .filter(e -> "Engineering".equals(e.getDepartment()))
                .filter(e -> e.getSalary() > 80000)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
    }
}

// Immutable Employee class
class Employee {
    private final int id;
    private final String name;
    private final String department;
    private final double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.department = Objects.requireNonNull(department);
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return String.format("Employee[id=%d, name=%s, dept=%s, salary=%.2f]",
                id, name, department, salary);
    }
}