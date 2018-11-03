
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {

    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {
       // this.containsEmployee();
        this.removeObjects();
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
}


