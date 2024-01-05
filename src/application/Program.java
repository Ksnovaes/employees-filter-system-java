package application;

import entities.Employee;
import model.services.EmployeeService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> list = new ArrayList<>();

            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();

            System.out.println("Email of people whose salary is more than: " + salary);
            List<String> emailSalary = list.stream()
                    .filter(e -> e.getSalary() >= salary)
                    .map(e -> e.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            emailSalary.forEach(System.out::println);

            EmployeeService es = new EmployeeService();

            double sum = es.filteredSum(list, e -> {
               return e.getName().charAt(0) == 'M';
            });
            System.out.println("Sum of salary of people whose name starts with 'M': " + sum);


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
