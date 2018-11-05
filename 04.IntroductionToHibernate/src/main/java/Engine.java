
import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {

    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {
        //this.removeObjects(); Problem 2
        //this.containsEmployee(); Problem 3
        //this.employeeHasSalaryOver(); Problem 4
        //this.employeesFromDepartment(); Problem 5
        //this.addAddress(); Problem 6
        //this.employeeCount(); Problem 7
        //this.getEmployeeWithProject(); Problem 8
        //this.findLatestProjects(); Problem 9
        //this.increaseSalary(); Problem 10
        //this.removeTowns(); Problem 11
        //this.findEmployees(); Problem 12
        //this.getMaximumSalaries(); Problem 13
    }

    //Problem 6.Adding a New Address and Updating Employee
    private void addAddress() {
        this.entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        Employee employee =
                this.entityManager
                .createQuery("FROM Employee WHERE last_Name = :name", Employee.class)
                .setParameter("name", name)
                .getSingleResult();

        Address address = new Address();
        address.setText("Vitoshka 15");

        this.entityManager.detach(employee.getAddress());
        employee.setAddress(address);
        this.entityManager.merge(employee);

        this.entityManager.getTransaction().commit();
    }

    //Problem 2.Remove Objects
    private void removeObjects() {

        List<Town> towns = this.entityManager
                .createQuery("FROM Town", Town.class)
                .getResultList();
        for (Town town : towns) {
            if (town.getName().length() > 5)
                entityManager.detach(town);
        }
        this.entityManager.getTransaction().begin();

        for (Town town : towns) {
            if (entityManager.contains(town)) {
                System.out.print(town.getName() + " -> ");

                town.setName(town.getName().toLowerCase());

                System.out.println(town.getName());
            }
        }
        entityManager.getTransaction().commit();
    }

    //Problem 3.Contains Employee
    private void containsEmployee(){
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        this.entityManager.getTransaction().begin();

        try {
            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE concat(first_name, ' ', last_name) = :name", Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();
            System.out.println("Yes");
        }catch (NoResultException e){
            System.out.println("No");
        }
        this.entityManager.getTransaction().commit();
    }

    //Problem 4.Employees with Salary Over 50 000
    private void employeeHasSalaryOver() {
        this.entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.salary > 50000", Employee.class)
                .getResultList()
                .forEach(employee -> System.out.println(employee.getFirstName()));
    }

    //Problem 5.Employees from Department
    private void employeesFromDepartment() {
        this.entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.department.name = 'Research and Development' ORDER BY e.salary, e.id", Employee.class)
                .getResultList()
                .forEach(employee -> System.out.printf("%s %s from %s - $%.2f%n",
                        employee.getFirstName(), employee.getLastName(),
                        employee.getDepartment().getName(), employee.getSalary()));
    }

    //Problem 7.Addresses with Employee Count
    private void employeeCount() {
        StringBuilder sb = new StringBuilder();

        this.entityManager
                .createQuery("SELECT a FROM Address AS a ORDER BY a.employees.size DESC, a.town.id", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(address -> sb.append(String.format("%s, %s - %d employees%n",
                address.getText(), address.getTown().getName(), address.getEmployees().size())));

        System.out.println(sb.toString().trim());
    }

    //Problem 8.Get Employee with Project
    private void getEmployeeWithProject() {

        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());

        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        StringBuilder sb = new StringBuilder();

        sb.append(employee.getFirstName()).append(" ").append(employee.getLastName())
                .append(" - ").append(employee.getJobTitle()).append(System.lineSeparator());

        employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> sb.append("\t").append(project.getName()).append(System.lineSeparator()));

        System.out.println(sb.toString().trim());
    }

    //Problem 9.Find Latest 10 Projects
    private void findLatestProjects() {

        StringBuilder sb = new StringBuilder();

        this.entityManager
                .createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> sb
                        .append("Project name: ").append(project.getName()).append(System.lineSeparator())
                        .append("\tProject Description: ").append(project.getDescription()).append(System.lineSeparator())
                        .append("\tProject Start Date: ").append(project.getStartDate()).append(System.lineSeparator())
                        .append("\tProject End Date: ").append(project.getEndDate()).append(System.lineSeparator()));

        System.out.println(sb.toString().trim());
    }

    //Problem 10.Increase Salaries
    private void increaseSalary() {
        this.entityManager.getTransaction().begin();

        this.entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')" +
                "ORDER BY e.id", Employee.class)
                .getResultList()
                .forEach(employee -> {
                    employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    System.out.printf("%s %s($%.2f)%n", employee.getFirstName(),
                            employee.getLastName(), employee.getSalary());
                });
        this.entityManager.getTransaction().commit();
    }

    //Problem 11.RemoveTowns
    private void removeTowns() {
        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        Town town = this.entityManager
                .createQuery("SELECT t FROM Town AS t WHERE t.name = :townName", Town.class)
                .setParameter("townName", townName)
                .getSingleResult();

        List<Address> addresses = entityManager
                .createQuery("SELECT a FROM Address AS a WHERE a.town.name = :townName", Address.class)
                .setParameter("townName", townName)
                .getResultList();

        String output = String.format("%d address%s in %s deleted%n",
                addresses.size(), (addresses.size() != 1) ? "es" : "", town.getName());

        entityManager.getTransaction().begin();

        addresses.forEach(address -> {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            address.setTown(null);
            entityManager.remove(address);
        });

        entityManager.remove(town);

        entityManager.getTransaction().commit();

        System.out.println(output);
    }

    //Problem 12.Find Employees by First Name
    private void findEmployees() {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();

        this.entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + "%")
                .getResultList()
                .forEach(employee -> System.out.printf("%s %s - %s - ($%.2f)%n", employee.getFirstName(),
                        employee.getLastName(), employee.getJobTitle(), employee.getSalary()));
    }

    //Problem 13.Employees Maximum Salaries
    private void getMaximumSalaries() {
        StringBuilder sb = new StringBuilder();

        entityManager
                .createQuery("SELECT e FROM  Employee AS e " +
                        "WHERE e.salary NOT BETWEEN 30000 AND 70000 " +
                        "GROUP BY e.department " +
                        "ORDER BY e.salary DESC", Employee.class)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(e -> e.getDepartment().getId()))
                .forEach(employee -> sb.append(String.format("%s - %.2f%n",
                        employee.getDepartment().getName(), employee.getSalary())));
    }
}


